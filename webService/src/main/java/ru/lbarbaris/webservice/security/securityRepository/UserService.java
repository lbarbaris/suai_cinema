package ru.lbarbaris.webservice.security.securityRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.lbarbaris.webservice.security.securityEntity.User;

import ru.lbarbaris.webservice.security.securityEntity.myUserDetails;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    private PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        Optional<User> user = userRepository.findByName(username);
        return user.map(myUserDetails::new).orElseThrow(() -> new UsernameNotFoundException(username + "not found"));
    }

    public void addUser(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
