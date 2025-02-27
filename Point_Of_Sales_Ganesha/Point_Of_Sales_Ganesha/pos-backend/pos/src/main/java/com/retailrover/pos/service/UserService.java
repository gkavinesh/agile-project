package com.retailrover.pos.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import com.retailrover.pos.entity.User;
import com.retailrover.pos.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User post(User user){

        return userRepository.save(user);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();

    }

    public void deleteUser(Long id){
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundException("User with ID " + id + " not found");
        }

        userRepository.deleteById(id);
    }

    public User getUserByID(Long id){
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id,User user){
        Optional<User> optionalUser = userRepository.findById(id);
        if(optionalUser.isPresent()){
            User existingUser = optionalUser.get();

            existingUser.setUserName(user.getUserName());
            existingUser.setUserPin(user.getUserPin());

            return userRepository.save(existingUser);

        }
        return null;
    }

}
