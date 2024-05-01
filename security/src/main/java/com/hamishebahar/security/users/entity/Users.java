package com.hamishebahar.security.users.entity;

import com.commonts.Dto.UsersDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.security.enums.Authority;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.stream.Collectors;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Users implements Serializable, UserDetails, OAuth2User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String phoneNumber;
    private String nationalCode;
    private String password;
    private String name;
    private String picture;
    private Boolean enabled = true;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Roles> roles;

    @Column(
            name = "created_time",
            updatable = false
    )
    @CreationTimestamp
    private LocalDateTime createdAt;
    @Column(
            name = "updated_time",
            updatable = true
    )
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public Users() {
    }

    public Users(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return new HashMap<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        if (roles != null && !roles.isEmpty()) {
            for (Roles roles : roles)
                authorities.addAll(roles.getAuthorities());
        } else {
            authorities.add(Authority.OP_ACCESS_USER);
        }
        return authorities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Roles> getRoles() {
        return roles;
    }

    public void setRoles(List<Roles> roles) {
        this.roles = roles;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public static class Builder{
        private Long id;
        private String email;
        private String phoneNumber;
        private String nationalCode;
        private String password;
        private String name;
        private String picture;
        private Boolean enabled = true;
        private List<Roles> roles;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        public Builder Id(Long id) {
            this.id = id;
            return this;
        }

        public Builder Email(String email) {
            this.email = email;
            return this;
        }

        public Builder PhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder NationalCode(String nationalCode) {
            this.nationalCode = nationalCode;
            return this;
        }

        public Builder Password(String password) {
            this.password = password;
            return this;
        }

        public Builder Name(String name) {
            this.name = name;
            return this;
        }

        public Builder Picture(String picture) {
            this.picture = picture;
            return this;
        }

        public Builder Enabled(Boolean enabled) {
            this.enabled = enabled;
            return this;
        }

        public Builder Roles(List<Roles> roles) {
            this.roles = roles;
            return this;
        }

        public Builder CreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder UpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Users build(){
            return new Users(this);
        }
    }

    private Users(Builder builder) {
        this.id = builder.id;
        this.email = builder.email;
        this.phoneNumber = builder.phoneNumber;
        this.nationalCode = builder.nationalCode;
        this.password = builder.password;
        this.name = builder.name;
        this.picture = builder.picture;
        this.enabled = builder.enabled;
        this.roles = builder.roles;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public UsersDto convertToDto() {
        return  new UsersDto.Builder()
                .Id(getId())
                .Email(getEmail())
                .PhoneNumber(getPhoneNumber())
                .NationalCode(getNationalCode())
                .Password(getPassword())
                .Name(getName())
                .Picture(getPicture())
                .Enabled(getEnabled())
                .Roles(roles != null && !roles.isEmpty() ? getRoles().stream()
                        .map(Roles::convertToDto)
                        .collect(Collectors.toList()) : null)
                .CreatedAt(getCreatedAt() != null ? getCreatedAt().
                        format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)) : null)
                .UpdatedAt(getUpdatedAt() != null ? getUpdatedAt()
                        .format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL)) : null)
                .build();
    }
}
