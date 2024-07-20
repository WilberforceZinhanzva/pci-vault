package zw.co.jugaad.pcivault.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import zw.co.jugaad.pcivault.exceptions.GenericException;
import zw.co.jugaad.pcivault.models.dtos.ConsumableUser;
import zw.co.jugaad.pcivault.models.entities.User;
import zw.co.jugaad.pcivault.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(ConsumableUser consumableUser){
        if(userRepository.existsByUsername(consumableUser.getUsername()))
            throw new GenericException("Username already taken");
        User user = new User();
        user.setUsername(consumableUser.getUsername());
        user.setPassword(consumableUser.getPassword());
        return userRepository.save(user);
    }
}
