package zw.co.jugaad.pcivault.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import zw.co.jugaad.pcivault.models.entities.EncryptedData;

import java.util.Optional;

public interface EncryptedDataRepository extends JpaRepository<EncryptedData,Long> {
    Optional<EncryptedData> findByToken(String token);
}
