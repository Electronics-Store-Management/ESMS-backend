package com.penguin.esms.components.staff;
import com.penguin.esms.components.permission.PermissionEntity;
import com.penguin.esms.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@Entity
@Table
public class StaffEntity extends BaseEntity implements UserDetails {
    private String name;
    private String phone;
    private String password;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String citizenId;
    private Role role;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<PermissionEntity> permissions;

    public StaffEntity() {}

    public StaffEntity(String name, String phone, String password, String email, String citizenId, Role role, List<PermissionEntity> permissions) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.citizenId = citizenId;
        this.role = role;
        this.permissions = permissions;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "StaffEntity{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", citizenId='" + citizenId + '\'' +
                '}';
    }
}
