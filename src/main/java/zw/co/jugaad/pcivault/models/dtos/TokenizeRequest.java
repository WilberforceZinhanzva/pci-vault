package zw.co.jugaad.pcivault.models.dtos;

import lombok.Data;

import java.io.Serializable;

@Data
public class TokenizeRequest implements Serializable {
    private static final long serialVersionUID = 1L;
    private String cardExpiry;
    private String cardholder;
    private String cardNumber;
}
