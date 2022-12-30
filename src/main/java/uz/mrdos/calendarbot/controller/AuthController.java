package uz.mrdos.calendarbot.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.mrdos.calendarbot.model.LoginDto;
import uz.mrdos.calendarbot.payload.ApiResponse;
import uz.mrdos.calendarbot.service.AuthService;

@RequestMapping("/api/auth")
@Api(value = "", tags = "Registratsiya va autorizatsiya bo'limi yo'llari")
public class AuthController {
    @Autowired
    AuthService authService;

    @ApiOperation(value = "autorizatsiyadan o'tish yo'li", response = ApiResponse.class)
    @PostMapping("/login")
    public HttpEntity<?> login(@RequestBody LoginDto loginDto){
        ApiResponse apiResponse = authService.login(loginDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? 200 : 401).body(apiResponse);
    }
}
