package com.hamishebahar.security.users.entity;


import com.commonts.Dto.RolesDto;
import com.hamishebahar.security.enums.Authority;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Roles {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @ElementCollection(targetClass = Authority.class, fetch = FetchType.EAGER)
    private List<Authority> authorities;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

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

        public Roles build(){
            return new Roles(this);
        }
    }

    private Roles(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.authorities = builder.authorities;
    }

    public RolesDto convertToDto() {
        return new RolesDto.Builder()
                .Id(getId())
                .Name(getName())
                .Authorities(getAuthorities() != null && !getAuthorities().isEmpty() ? getAuthorities() : null)
                .build();
    }
}
