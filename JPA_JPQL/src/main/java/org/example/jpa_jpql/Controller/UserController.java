package org.example.jpa_jpql.Controller;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import org.example.jpa_jpql.Api.ApiResponse;
import org.example.jpa_jpql.Model.User;
import org.example.jpa_jpql.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<?> addNewUser(@Valid@RequestBody User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        userService.addUser(user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user added successfully"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id,@Valid@RequestBody User user, Errors errors){
        if (errors.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id, user);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user updated successfully"));

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("user deleted successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> logIn(@PathParam("username") String username, @PathParam("password") String password){
        userService.checkUserNameAndPassword(username,password);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("login successfully welcome"));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable String email){
        User user = userService.findUserByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }


    @GetMapping("/role/{role}")
    public ResponseEntity<?> getByRole(@PathVariable String role){
        List<User> user = userService.findUserByRole(role);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/age/{age}")
    public ResponseEntity<?> getUsersByAge(@PathVariable Integer age){
        return ResponseEntity.status(HttpStatus.OK).body(userService.findByAge(age));
    }






}
