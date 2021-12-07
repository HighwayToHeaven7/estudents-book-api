package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import javax.persistence.*;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.Role;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.UserStatus;
import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.UserStatus.DISABLE;
import static com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.UserStatus.NEW;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "user_table")
public class User extends BasicEntity implements UserDetails {

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String surname;

  @Column(nullable = false, unique = true)
  private String email;

  private String password;

  @Enumerated(EnumType.STRING)
  private UserStatus accountStatus = NEW;

  @NotNull
  @Enumerated(EnumType.STRING)
  private Role role;

  @OneToOne(fetch = FetchType.LAZY)
  private ContactDetails contactDetails;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> listAuthorities = new ArrayList<GrantedAuthority>();
    listAuthorities.add(new SimpleGrantedAuthority("ROLE_" + role.name()));
    return listAuthorities;
  }

  @Override
  public String getUsername() {
    return email;
  }

  @Override
  public boolean isAccountNonExpired() {
    return !accountStatus.equals(DISABLE) && !accountStatus.equals(NEW);
  }

  @Override
  public boolean isAccountNonLocked() {
    return !accountStatus.equals(DISABLE) && !accountStatus.equals(NEW);
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return !accountStatus.equals(DISABLE) && !accountStatus.equals(NEW);
  }

  @Override
  public boolean isEnabled() {
    return !accountStatus.equals(DISABLE) && !accountStatus.equals(NEW);
  }
}