package nl.stenden.eindopdracht.service;

import nl.stenden.eindopdracht.model.User;
import nl.stenden.eindopdracht.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Parameter;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //save a user
    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    //find all users
    @Override
    public List<User> findAll() { return userRepository.findAll(); }

    //find user by email
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    //find user by id
    @Override
    public User findById(Long id) {
        return userRepository.findOne(id);
    }

    //update an existing user
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
        userRepository.save(currentUser);
    }

    //remove a user
    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    //u do not kno de wae
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}
