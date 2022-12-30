package uz.mrdos.calendarbot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.mrdos.calendarbot.entity.UsersEntity;
import uz.mrdos.calendarbot.model.LoginDto;
import uz.mrdos.calendarbot.payload.ApiResponse;
import uz.mrdos.calendarbot.repository.RoleRepository;
import uz.mrdos.calendarbot.repository.UsersRepository;
import uz.mrdos.calendarbot.security.JwtProvider;

@Service
public class AuthService implements UserDetailsService {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    public ApiResponse login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUserName(),
                    loginDto.getPassword()));
            UsersEntity usersEntity = (UsersEntity) authentication.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getUserName(), usersEntity.getRoles());
            return new ApiResponse("Token", true, token);
        } catch (BadCredentialsException badCredentialsException) {
            return new ApiResponse("Parol yoki login xato", false);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        Optional<UsersEntity> optionalUser = usersRepository.findByUserName(username);
//        if (optionalUser.isPresent())
//            return optionalUser.get();
//        throw new UsernameNotFoundException(username + " topilmadi");
        return usersRepository.findByUserName(username).orElseThrow(() -> new UsernameNotFoundException(username + " topilmdi."));

    }

}