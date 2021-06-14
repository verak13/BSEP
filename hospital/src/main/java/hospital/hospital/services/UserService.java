package hospital.hospital.services;

import hospital.hospital.model.User;
import hospital.hospital.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class UserService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

       if (user != null) {
           return user;
        } else {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", email));
        }
    }

    public LocalDate saveLastLogin(String email) {
        System.out.println("ovde trazim po emailu : " + email);
        User user = userRepository.findByEmail(email);
        LocalDate lastLogin = LocalDate.now();

        if (user == null) {
            user = new User(email, LocalDate.now());
        } else {
            lastLogin = user.getLastLogin();
            user.setLastLogin(LocalDate.now());
        }
        userRepository.save(user);

        return lastLogin;
    }
}
