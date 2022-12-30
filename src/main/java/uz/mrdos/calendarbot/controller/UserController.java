package uz.mrdos.calendarbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.mrdos.calendarbot.model.RegisterDto;
import uz.mrdos.calendarbot.payload.ApiResponse;
import uz.mrdos.calendarbot.service.UserService;

@RestController
@RequestMapping("/api/admin/user")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/list")
    public HttpEntity<?> getAllUsers(){
        ApiResponse apiResponse = userService.getAllUsers();
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }
    @GetMapping("/list/{id}")
    public HttpEntity<?> getUserById(@PathVariable Integer id){
        ApiResponse apiResponse = userService.getUserById(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 400).body(apiResponse);
    }

    @PostMapping("/add")
    public HttpEntity<?> addUser(@RequestBody RegisterDto registerDto){
        ApiResponse apiResponse = userService.save(registerDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 409).body(apiResponse);
    }

    @PutMapping("/edit/{id}")
    public HttpEntity<?> editUser(@RequestBody RegisterDto registerDto, @PathVariable Integer id){
        ApiResponse apiResponse = userService.edit(registerDto, id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 201 : 409).body(apiResponse);
    }

    @DeleteMapping("/delete/{id}")
    public HttpEntity<?> deleteUserById(@PathVariable Integer id){
        ApiResponse apiResponse = userService.delete(id);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 404).body(apiResponse);
    }
}
