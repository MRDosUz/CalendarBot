package uz.mrdos.calendarbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.mrdos.calendarbot.entity.UsersEntity;
import uz.mrdos.calendarbot.model.EditPasswordDto;
import uz.mrdos.calendarbot.payload.ApiResponse;
import uz.mrdos.calendarbot.repository.RoleRepository;
import uz.mrdos.calendarbot.repository.UsersRepository;

@Service
public class ChangePasswordService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    AuthenticationManager authenticationManager;

    public ApiResponse editPassword(UsersEntity currentUser, EditPasswordDto editPasswordDto){
        if (editPasswordDto.getNewPassword() == null)
            return new ApiResponse("Takroriy parol xato, yoki bo'sh.", false);
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    currentUser.getUsername(),
                    editPasswordDto.getCurrentPassword()));

            currentUser.setPassword(passwordEncoder.encode(editPasswordDto.getNewPassword()));
            UsersEntity savedUser = usersRepository.save(currentUser);
            return  new ApiResponse("Muvaffaqiyatli o'zgartirildi.", true, savedUser);
        }catch (BadCredentialsException badCredentialsException){
            return new ApiResponse("Amaldagi yoki yangi parol xato", false);
        }
    }

}
