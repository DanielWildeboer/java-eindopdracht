package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.User;
import nl.stenden.eindopdracht.repository.AuthTokenRepository;
import nl.stenden.eindopdracht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    AuthTokenRepository authTokenRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public List<User> findAll() { return userRepository.findAll(); }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long Id) {
        return userRepository.findOne(Id);
    }

    @Override
    public void updateUser(Long Id, User user) {
        User currentUser = findById(Id);
        if(user.getEmail() != null){
            currentUser.setEmail(user.getEmail());
        }
        if(user.getLastName() != null){
            currentUser.setLastName(user.getLastName());
        }
        if(user.getFirstName() != null){
            currentUser.setFirstName(user.getFirstName());
        }
        if(user.getPassword() != null){
            currentUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }
        if(user.getAuthToken() != null)
        {
            currentUser.setAuthToken(user.getAuthToken());
        }
        userRepository.save(currentUser);
    }

    @Override
    public void delete(Long Id) {
        userRepository.delete(Id);
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }

    @Override
    public User findByAuthToken(String authToken){
        return userRepository.findByAuthToken(authTokenRepository.findByToken(authToken));
    }
}
