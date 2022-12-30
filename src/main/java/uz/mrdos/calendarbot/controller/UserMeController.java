package uz.mrdos.calendarbot.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mrdos.calendarbot.entity.UsersEntity;
import uz.mrdos.calendarbot.security.CurrentUser;

@RestController
@RequestMapping("/api/userInfo")
public class UserMeController {
    @GetMapping("/me")
    public HttpEntity<?> getUserByJwt(@CurrentUser UsersEntity usersEntity) {
        return ResponseEntity.ok(usersEntity);
    }

}
