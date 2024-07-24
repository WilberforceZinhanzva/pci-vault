package zw.co.jugaad.pcivault.models.dtos;

import lombok.Data;

@Data
public class UnEncryptedData {
    private String cardExpiry;
    private String cardholder;
    private String cardNumber;

}
