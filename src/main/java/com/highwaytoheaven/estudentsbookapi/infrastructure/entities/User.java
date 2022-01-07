package com.highwaytoheaven.estudentsbookapi.infrastructure.entities;

import static com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.UserStatus.DISABLE;
import static com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.UserStatus.NEW;

import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.Role;
import com.highwaytoheaven.estudentsbookapi.infrastructure.entities.enums.UserStatus;
import com.sun.istack.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@AllArgsConstructor
@NoArgsConstructor
@Builder
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

  private String familyName;
  private String sex;
  private String studentsAlbumNumber;
  private String residence;
  private String province;
  private String personalIdentityNumber;
  private String dateOfBirth;
  private String placeOfBirth;

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