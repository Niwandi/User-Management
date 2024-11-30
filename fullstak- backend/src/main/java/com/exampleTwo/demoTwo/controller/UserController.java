package com.exampleTwo.demoTwo.controller;

import com.exampleTwo.demoTwo.exception.UserNotFoundException;
import com.exampleTwo.demoTwo.model.User;
import com.exampleTwo.demoTwo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {
    @Autowired
    private UserRepository userrepository;

   @PostMapping("/user")
   User newUser(@RequestBody User newUser){
       return userrepository.save(newUser);
   }

   @GetMapping("/getUser")
    List<User>getAllUsers(){
       return userrepository.findAll();
   }
   @GetMapping("/user/{id}")
    User getUserById(@PathVariable Long id){
       return userrepository.findById(id)
               .orElseThrow(()->new UserNotFoundException(id));
   }

   @PutMapping("/user/{id}")
    User updateUser(@RequestBody User newUser, @PathVariable Long id){
       return userrepository.findById(id)
               .map(user -> {
                   user.setName(newUser.getName());
                   user.setUsername(newUser.getUsername());
                   user.setEmail(newUser.getEmail());
                   return userrepository.save(user);
               }).orElseThrow(()->new UserNotFoundException(id));
   }

   @DeleteMapping("/user/{id}")
    String deleteUser(@PathVariable Long id){
       if(!userrepository.existsById(id)){
           throw new UserNotFoundException(id);
       }
       userrepository.deleteById(id);
       return "User with this id "+id+" was deleted";
   }

}
