package uz.mrdos.calendarbot.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UsersEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer positionId;

    @Column(nullable = false)
    private String firstName;//ism

    @Column(nullable = false)
    private String lastName;//familiyasi

    @Column(nullable = false)
    private String middleName;

    @Column(nullable = false)
    private String userName;

    @JsonIgnore
    @Column(nullable = false)
    private String password;

    @JsonIgnore
    @Column(nullable = false, updatable = false)
    @CreationTimestamp
    private Timestamp createdAt;

    @JsonIgnore
    @UpdateTimestamp
    private Timestamp updateAt;

    @ManyToOne
    private RoleEntity role;

    public UsersEntity(Integer positionId, String firstName, String lastName, String middleName, String userName, String password, RoleEntity role) {
        this.positionId = positionId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    @JsonIgnore
    private boolean  accountNonExpired = true; //bu userning amal qilish muddati tugamagan

    @JsonIgnore
    private boolean accountNonLocked = true; //bu userning bloklanmagan

    @JsonIgnore
    private boolean credentialsNonExpired = true; //bu userning ishonchlilik muddati tugamagan

    private boolean enabled = true; //bu user yoqilganmi (aktivmi) default = false (chunki verifikatsiyadan o'tganidan kn yoqib qo'yamiz)

    //BU USERDETALIS NING METHODLARI
    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(this.role);
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

    @JsonIgnore
    public RoleEntity getRoles() {
        return this.role;
    }



}
