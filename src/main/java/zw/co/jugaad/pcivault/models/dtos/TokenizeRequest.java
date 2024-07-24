package zw.co.jugaad.pcivault.models.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenizeRequest implements Serializable {
    private String cardExpiryMonth;
    private String cardExpiryYear;
    private String securityCode;
}
