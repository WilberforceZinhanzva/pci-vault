package zw.co.jugaad.pcivault.security.config;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import zw.co.jugaad.pcivault.exceptions.GenericException;
import zw.co.jugaad.pcivault.models.entities.User;
import zw.co.jugaad.pcivault.repositories.UserRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> credential = userRepository.findByUsername(username);
        if(credential.isEmpty())
            throw new GenericException("Credential not found");

        return credential.get();
    }
}
