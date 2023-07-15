package kz.bit.kormefinall.services;

import kz.bit.kormefinall.dto.UserDTO;
import kz.bit.kormefinall.mapper.UserMapper;
import kz.bit.kormefinall.models.Product;
import kz.bit.kormefinall.models.User;
import kz.bit.kormefinall.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not found");
        }
        if (user.isActive()) {// if user is banned throw exception
            throw new UsernameNotFoundException("User is banned");
        }
        return user;
    }

    public User saveUser(User user) {   // save user method using for update user
        return userRepository.save(user);
    }

    public User addUser(User user) {
        User checkUser = userRepository.findByEmail(user.getEmail());
        if (checkUser == null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        return null;
    }

    public User updatePassword(String newPassword, String oldPassword) {

        User currentUser = getCurrentSessionUser();
        if(passwordEncoder.matches(oldPassword, currentUser.getPassword())){
            currentUser.setPassword(passwordEncoder.encode(newPassword));
            return userRepository.save(currentUser);
        }
        return null;
    }

    public User getCurrentSessionUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return (User) authentication.getPrincipal();
        }
        return null;
    }

    public List<User> allUsers(){
        return userRepository.findAll();
    }


//    public UserDTO getUserByUsernameDTO(String username) { // get user by username dto mapper method
//        return userMapper.toUserDTO(userRepository.findByUsername(username));
//    }

    public List<UserDTO> userListDTO() { // get all users list dto mapper method for admin panel
        return userMapper.toUserDTOList(userRepository.findAll().stream().toList());
    }
    public UserDTO getUser(Long id){
        return userMapper.toUserDTO(userRepository.findById(id).orElse(new User()));
    }



    public void Ban(Long id, boolean banned) { //  ban or unban user by id method for admin panel
        User user = userRepository.findById(id).orElse(null);
        if (user != null) {
            user.setActive(banned);
            userRepository.save(user);
        }
    }

}