package uz.mrdos.calendarbot.service;

import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.mrdos.calendarbot.entity.UsersEntity;
import uz.mrdos.calendarbot.model.RegisterDto;
import uz.mrdos.calendarbot.payload.ApiResponse;
import uz.mrdos.calendarbot.repository.RoleRepository;
import uz.mrdos.calendarbot.repository.UsersRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    RoleRepository roleRepository;


    @Autowired
    AuthenticationManager authenticationManager;

    public ApiResponse getAllUsers(){
        List<UsersEntity> allUsers = usersRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
        return new ApiResponse("Barcha foydalanuvchilar ro'yxati.", true, allUsers);
    }

    public ApiResponse getUserById(Integer id){
        Optional<UsersEntity> optionalUsers = usersRepository.findById(id);
        if (optionalUsers.isPresent())
            return new ApiResponse("Bunday user topilmadi.", false);

        return new ApiResponse("Topildi", true, optionalUsers.get());
    }

    public ApiResponse save(RegisterDto registerDto){
        if (usersRepository.existsByUserName(registerDto.getUsername()))
            return new ApiResponse("Bunday username mavjud", false);

        UsersEntity newUser = new UsersEntity();
        newUser.setFirstName(registerDto.getFirstName());
        newUser.setLastName(registerDto.getLastName());
        newUser.setMiddleName(registerDto.getMiddleName());
        newUser.setUserName(registerDto.getUsername());
        newUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        newUser.setRole(roleRepository.getById(registerDto.getRoleId()));
        newUser.setAccountNonExpired(true);
        newUser.setAccountNonLocked(true);
        newUser.setCredentialsNonExpired(true);
        newUser.setEnabled(true);

        UsersEntity savedUser = usersRepository.save(newUser);

        return new ApiResponse("Muvaffaqiyatli qo'shildi.", true, savedUser);

    }

    public ApiResponse edit(RegisterDto registerDto, Integer id){

        if (usersRepository.existsByUserNameAndIdNot(registerDto.getUsername(), id))
            return new ApiResponse("Bunday username mavjud", false);

        Optional<UsersEntity> optionalCurrentUser = usersRepository.findById(id);
        if (optionalCurrentUser.isPresent())
            return new ApiResponse("Bunday foydalanuvchi topilmadi.", false);

        UsersEntity currentUser = optionalCurrentUser.get();

        currentUser.setFirstName(registerDto.getFirstName());
        currentUser.setLastName(registerDto.getLastName());
        currentUser.setMiddleName(registerDto.getMiddleName());
        currentUser.setUserName(registerDto.getUsername());
        if (!registerDto.getPassword().equals(null))
            currentUser.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        if (!registerDto.getRoleId().equals(null))
            currentUser.setRole(roleRepository.getById(registerDto.getRoleId()));

        UsersEntity savedUser = usersRepository.save(currentUser);

        return new ApiResponse("Muvaffaqiyatli qo'shildi.", true, savedUser);

    }

    public ApiResponse delete(Integer id){
        if (!usersRepository.existsById(id))
            return new ApiResponse("Bunday foydalanuvchi topilmadi.", false);

        usersRepository.deleteById(id);

        return new ApiResponse("Muvaffaqiyatli o'chirildi.", true);
    }







}
