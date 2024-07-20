package zw.co.jugaad.pcivault.services;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import zw.co.jugaad.pcivault.models.dtos.TokenizeRequest;
import zw.co.jugaad.pcivault.models.dtos.TokenizeResponse;
import zw.co.jugaad.pcivault.models.entities.EncryptedData;
import zw.co.jugaad.pcivault.repositories.EncryptedDataRepository;
import zw.co.jugaad.pcivault.util.AesEncryption;

import javax.crypto.SecretKey;

@Slf4j
@Service
@RequiredArgsConstructor
public class VaultService {

    private final EncryptedDataRepository encryptedDataRepository;
    private final Gson gson;

    public TokenizeResponse tokenize(TokenizeRequest request, String passphrase) throws Exception {

        SecretKey secretKey = AesEncryption.getKeyFromPassword(passphrase,"1234567");
        String encryptedCardExpiry = AesEncryption.encryptPasswordBased(request.getCardExpiry(),secretKey);
        String encryptedCardholder = AesEncryption.encryptPasswordBased(request.getCardholder(),secretKey);
        String encryptedCardNumber = AesEncryption.encryptPasswordBased(request.getCardNumber(),secretKey);


        EncryptedData encryptedData = new EncryptedData();
        encryptedData.setEncryptedCardholder(encryptedCardholder);
        encryptedData.setEncryptedCardNumber(encryptedCardNumber);
        encryptedData.setEncryptedCardExpiry(encryptedCardExpiry);

        EncryptedData savedEncryptedData = encryptedDataRepository.save(encryptedData);

        TokenizeResponse tokenizeResponse = new TokenizeResponse();
        tokenizeResponse.setToken(savedEncryptedData.getToken());

        return tokenizeResponse;
    }

}
