package uz.mrdos.calendarbot.model;

import lombok.Data;

@Data
public class RegisterDto {
    Integer positionId;
    String firstName;
    String lastName;
    String middleName;
    String username;
    String password;
    Integer roleId;
}
