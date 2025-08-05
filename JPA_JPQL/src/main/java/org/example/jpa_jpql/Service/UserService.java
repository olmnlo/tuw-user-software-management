package org.example.jpa_jpql.Service;

import lombok.RequiredArgsConstructor;
import org.example.jpa_jpql.Api.ApiException;
import org.example.jpa_jpql.Model.User;
import org.example.jpa_jpql.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public void addUser(User user){
        User oldUser = userRepository.findUserByUsername(user.getUsername());
        if (oldUser != null){
            throw new ApiException("user is duplicated");
        }
        userRepository.save(user);
    }

    public void updateUser(Integer id, User user){
        User oldUser = userRepository.findUserById(id);
        if (oldUser == null){
            throw new ApiException("user not found");
        }
        oldUser.setAge(user.getAge());
        oldUser.setRole(user.getRole());
        oldUser.setName(user.getName());
        oldUser.setEmail(user.getEmail());
        oldUser.setPassword(user.getPassword());
        oldUser.setUsername(user.getUsername());

        userRepository.save(oldUser);
    }


    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id);
        if (user == null){
            throw new ApiException("user not found");
        }
        userRepository.delete(user);
    }

    public void checkUserNameAndPassword(String username, String password){
        User user = userRepository.findUserByUsernameAndPassword(username,password);
        if (user == null){
            throw new ApiException("username or password is wrong");
        }
    }

    public User findUserByEmail(String email){
        User user = userRepository.findUserByEmail(email);
        if (user == null){
            throw new ApiException("user not found by email");
        }
        return user;
    }

    public List<User> findUserByRole(String role){
        List<User> users = userRepository.getUsersByRole(role);
        if (users.isEmpty()){
            throw new ApiException("no users");
        }

        return users;
    }

    public List<User> findByAge(Integer age){
        return userRepository.findUserByAgeGreaterThanEqual(age);
    }

}
