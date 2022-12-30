package uz.mrdos.calendarbot.model;

import lombok.Data;

@Data
public class EditPasswordDto {

    private  String currentPassword;

    private  String newPassword;

}
