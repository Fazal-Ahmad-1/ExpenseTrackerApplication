package com.FazalProject.ExpenseTracker.Controller;

import com.FazalProject.ExpenseTracker.Entity.LoginDTO;
import com.FazalProject.ExpenseTracker.Entity.User;
import com.FazalProject.ExpenseTracker.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.logging.Logger;

@RestController
@RequestMapping("user")
@CrossOrigin(origins = "https://expense-tracker-frontend-black.vercel.app")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("login")
    public ResponseEntity<?>login(@RequestBody LoginDTO user){
        return userService.login(user);
    }
    @PostMapping("create")
    public ResponseEntity<?> createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    @DeleteMapping("delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable String username){
        return userService.deleteUser(username);
    }
    @GetMapping("{username}/entries")
    public ResponseEntity<?> getUserEntries(@PathVariable String username){
        return userService.getUserEntries(username);
    }
    @GetMapping("{username}/{month}/{year}/stats")
    public ResponseEntity<?> getUserStats(@PathVariable String username, @PathVariable int month, @PathVariable int year){
        return userService.getUserStats(username,month,year);
    }
}
