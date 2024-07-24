package zw.co.jugaad.pcivault.models.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name="encrypted_data")
@Data
public class EncryptedData {
    @Id
    @GeneratedValue
    private Long id;
    private Long userId;
    private String token = UUID.randomUUID().toString();

   private String cardExpiryMonth;
   private String cardExpiryYear;
   private String securityCode;
   private String cardNumber;
}
