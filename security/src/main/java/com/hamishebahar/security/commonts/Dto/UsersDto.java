package com.hamishebahar.security.commonts.Dto;

import com.hamishebahar.security.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsersDto {
    private Long id;
    private String email;
    private String phoneNumber;
    private String nationalCode;
    private String password;
    private String name;
    private String picture;
    private Boolean enabled = true;

    private List<RolesDto> roles;

    private String createdAt;
    private String updatedAt;

    public static class Builder{
        private Long id;
        private String email;
        private String phoneNumber;
        private String nationalCode;
        private String password;
        private String name;
        private String picture;
        private Boolean enabled = true;
        private List<RolesDto> roles;

        private String createdAt;
        private String updatedAt;

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

        public Builder Roles(List<RolesDto> roles) {
            this.roles = roles;
            return this;
        }

        public Builder CreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder UpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public UsersDto build(){
            return new UsersDto(this);
        }
    }

    private UsersDto(Builder builder) {
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

    public Users convertToEntity() {
        return new Users.Builder()
                .Id(getId())
                .Email(getEmail())
                .PhoneNumber(getPhoneNumber())
                .NationalCode(getNationalCode())
                .Password(new BCryptPasswordEncoder().encode(getPassword()))
                .Name(getName())
                .Picture(getPicture())
                .Enabled(getEnabled())
                .Roles(roles != null && !roles.isEmpty() ? getRoles().stream()
                        .map(RolesDto::convertToEntity)
                        .collect(Collectors.toList()) : null)
                .build();
    }

    public UsersDto updaterFields(UsersDto dto) {
        return new UsersDto.Builder()
                .Id(getId() != null ? getId() : dto.getId())
                .Email(getEmail() != null ? getEmail() : dto.getEmail())
                .PhoneNumber(getPhoneNumber() != null ? getPhoneNumber() : dto.getPhoneNumber())
                .NationalCode(getNationalCode() != null ? getNationalCode() : dto.getNationalCode())
                .Password(getPassword() != null ? getPassword() : dto.getPassword())
                .Name(getName() != null ? getName() : dto.getName())
                .Picture(getPicture() != null ? getPicture() : dto.getPicture())
                .Enabled(getEnabled() != null &&
                        (getEnabled() == dto.getEnabled()) ? getEnabled() : dto.getEnabled())
                .Roles(roles != null && !roles.isEmpty() ? getRoles() : dto.getRoles())
                .build();
    }
}
