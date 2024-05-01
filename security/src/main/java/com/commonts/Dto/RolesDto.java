package com.commonts.Dto;

import com.hamishebahar.security.enums.Authority;
import com.hamishebahar.security.users.entity.Roles;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolesDto {
    private Long id;
    private String name;
    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    private List<Authority> authorities;
    
    public static class Builder{
        private Long id;
        private String name;
        private List<Authority> authorities;

        public Builder Id(Long id) {
            this.id = id;
            return this;
        }

        public Builder Name(String name) {
            this.name = name;
            return this;
        }

        public Builder Authorities(List<Authority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public RolesDto build(){
            return new RolesDto(this);
        }
    }

    private RolesDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.authorities = builder.authorities;
    }

    public Roles convertToEntity() {
        return new Roles.Builder()
                .Id(getId())
                .Name(getName())
                .Authorities(getAuthorities() != null && !getAuthorities().isEmpty() ? getAuthorities() : null)
                .build();
    }
}
