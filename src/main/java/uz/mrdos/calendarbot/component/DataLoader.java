package uz.mrdos.calendarbot.component;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.mrdos.calendarbot.entity.RoleEntity;
import uz.mrdos.calendarbot.entity.UsersEntity;
import uz.mrdos.calendarbot.enums.RoleName;
import uz.mrdos.calendarbot.repository.RoleRepository;
import uz.mrdos.calendarbot.repository.UsersRepository;

@Component
public class DataLoader {


    @Value("${spring.datasource.initialization-mode}")
    private String initialMode;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UsersRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;



    public void run(String... args) throws Exception {
        if (initialMode.equals("always")){

            RoleEntity superAdminRole  = roleRepository.save(new RoleEntity(RoleName.ROLE_SUPER_ADMIN));
            RoleEntity adminRole  = roleRepository.save(new RoleEntity(RoleName.ROLE_ADMIN));
            RoleEntity userRole  = roleRepository.save(new RoleEntity(RoleName.ROLE_USER));
            RoleEntity moderRole  = roleRepository.save(new RoleEntity(RoleName.ROLE_MODER));


            UsersEntity admin =   userRepository.save(new UsersEntity("Admin","admin","admin", "admin",passwordEncoder.encode("123"), adminRole));
            UsersEntity owner =   userRepository.save(new UsersEntity("Owner","owner","owner", "owner",passwordEncoder.encode("owner123"), superAdminRole));
        }
    }

}
