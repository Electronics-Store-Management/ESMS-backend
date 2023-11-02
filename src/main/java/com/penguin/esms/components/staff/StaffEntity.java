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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
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

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PermissionEntity> permissions = new ArrayList<>();

    public StaffEntity() {}

    public StaffEntity(String name, String phone, String password, String email, String citizenId, Role role) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        this.email = email;
        this.citizenId = citizenId;
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return permissions.stream().map(permissionEntity -> new SimpleGrantedAuthority(permissionEntity.toString())).collect(Collectors.toList());
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
