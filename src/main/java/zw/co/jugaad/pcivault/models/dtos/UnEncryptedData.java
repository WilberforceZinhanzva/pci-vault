package zw.co.jugaad.pcivault.models.dtos;

import lombok.Data;

@Data
public class UnEncryptedData {
    private String cardExpiryMonth;
    private String cardExpiryYear;
    private String securityCode;
    private String cardNumber;

}
