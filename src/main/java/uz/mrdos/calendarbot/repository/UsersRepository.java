package uz.mrdos.calendarbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mrdos.calendarbot.entity.UsersEntity;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<UsersEntity, Integer> {
    boolean existsByUserName(String username);
    boolean existsByUserNameAndIdNot(String username, Integer id);
    Optional<UsersEntity> findByUserName(String username);
    Optional<UsersEntity> findById(Integer id);

    boolean existsById(Integer id);
}
