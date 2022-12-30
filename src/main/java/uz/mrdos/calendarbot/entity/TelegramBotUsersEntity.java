package uz.mrdos.calendarbot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TelegramBotUsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Long chatId;

    private String nameUser;

    @JsonIgnore
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @JsonIgnore
    @UpdateTimestamp
    private Timestamp updateAt;


}
