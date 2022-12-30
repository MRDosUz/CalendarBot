package uz.mrdos.calendarbot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.mrdos.calendarbot.entity.UsersEntity;
import uz.mrdos.calendarbot.model.EditPasswordDto;
import uz.mrdos.calendarbot.payload.ApiResponse;
import uz.mrdos.calendarbot.security.CurrentUser;
import uz.mrdos.calendarbot.service.ChangePasswordService;

@RestController
@RequestMapping("/api/changePassword")
public class ChangePasswordController {
    @Autowired
    ChangePasswordService changePasswordService;

    @PostMapping("/edit")
    public HttpEntity<?> editPassword(@CurrentUser UsersEntity currentUser, @RequestBody EditPasswordDto editPasswordDto){
        ApiResponse apiResponse = changePasswordService.editPassword(currentUser, editPasswordDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }
}
