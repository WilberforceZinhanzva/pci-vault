package zw.co.jugaad.pcivault.services;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import zw.co.jugaad.pcivault.exceptions.GenericException;
import zw.co.jugaad.pcivault.models.dtos.TokenizeRequest;
import zw.co.jugaad.pcivault.models.dtos.TokenizeResponse;
import zw.co.jugaad.pcivault.models.dtos.UnEncryptedData;
import zw.co.jugaad.pcivault.models.entities.EncryptedData;
import zw.co.jugaad.pcivault.repositories.EncryptedDataRepository;
import zw.co.jugaad.pcivault.util.AESUtils;

import javax.crypto.BadPaddingException;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VaultService {

    private final EncryptedDataRepository encryptedDataRepository;
    private final Gson gson;

    @Value("${security.key}")
    private String key;

    @Value("${security.iv}")
    private String iv;

    public TokenizeResponse tokenize(TokenizeRequest request, String passphrase) throws Exception {

        if(passphrase.length()!= 32)
            throw new GenericException("Passphrase length should be 32");

        //SecretKey secretKey = AesEncryption.getKeyFromPassword(passphrase,"1234567");
        String cardExpiryMonth = AESUtils.encrypt(request.getCardExpiryMonth(),passphrase,iv);
        String cardExpiryYear = AESUtils.encrypt(request.getCardExpiryYear(),passphrase,iv);
        String securityCode = AESUtils.encrypt(request.getSecurityCode(),passphrase,iv);


        EncryptedData encryptedData = new EncryptedData();
        encryptedData.setCardExpiryMonth(cardExpiryMonth);
        encryptedData.setCardExpiryYear(cardExpiryYear);
        encryptedData.setSecurityCode(securityCode);

        EncryptedData savedEncryptedData = encryptedDataRepository.save(encryptedData);

        TokenizeResponse tokenizeResponse = new TokenizeResponse();
        tokenizeResponse.setToken(savedEncryptedData.getToken());

        return tokenizeResponse;
    }


    public UnEncryptedData deTokenize(String token, String passphrase) throws Exception {
        if(passphrase.length()!= 32)
            throw new GenericException("Passphrase length should be 32");

        Optional<EncryptedData> encryptedData = encryptedDataRepository.findByToken(token);
        if(encryptedData.isEmpty())
            throw new GenericException("Data not found");

        UnEncryptedData unEncryptedData = new UnEncryptedData();
        try{
            String cardExpiryMonth = AESUtils.decrypt(encryptedData.get().getCardExpiryMonth(),passphrase,iv);
            String cardExpiryYear = AESUtils.decrypt(encryptedData.get().getCardExpiryYear(),passphrase,iv);
            String securityCode = AESUtils.decrypt(encryptedData.get().getSecurityCode(),passphrase,iv);

            unEncryptedData.setSecurityCode(securityCode);
            unEncryptedData.setCardExpiryYear(cardExpiryYear);
            unEncryptedData.setCardExpiryMonth(cardExpiryMonth);

        }catch (BadPaddingException e){
            throw new GenericException("Wrong passphrase");
        }




        return unEncryptedData;

    }

}
