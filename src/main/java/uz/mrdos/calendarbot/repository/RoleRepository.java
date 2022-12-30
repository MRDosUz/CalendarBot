package uz.mrdos.calendarbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mrdos.calendarbot.entity.RoleEntity;
import uz.mrdos.calendarbot.enums.RoleName;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {

    String findByRoleName(RoleName roleName);
    RoleEntity getById(Integer id);

}
