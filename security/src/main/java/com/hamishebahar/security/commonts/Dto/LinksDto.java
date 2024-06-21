package com.hamishebahar.security.commonts.Dto;

import com.hamishebahar.security.panel.Links.entity.Links;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LinksDto {
    private Long id;
    private String name;
    private String uri;

    private IconsDto icon;

    private String createdAt;
    private String updatedAt;

    public static class Builder{
        private Long id;
        private String name;
        private String uri;
        private IconsDto icon;

        private String createdAt;
        private String updatedAt;
        public Builder Id(Long id) {
            this.id = id;
            return this;
        }

        public Builder Name(String name) {
            this.name = name;
            return this;
        }

        public Builder Uri(String uri) {
            this.uri = uri;
            return this;
        }

        public Builder Icon(IconsDto icon) {
            this.icon = icon;
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

        public LinksDto build(){
            return new LinksDto(this);
        }
    }

    private LinksDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.uri = builder.uri;
        this.icon = builder.icon;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Links convertToEntity(){
        return new Links.Builder()
                .Id(getId())
                .Name(getName())
                .Uri(getUri())
                .Icon(getIcon().convertToEntity())
                .build();
    }

    public LinksDto updaterFields(LinksDto dto) {
        return new LinksDto.Builder()
                .Id(getId() != null ? getId() : dto.getId())
                .Name(getName() != null ? getName() : dto.getName())
                .Uri(getUri() != null ? getUri() : dto.getUri())
                .Icon(getIcon() != null ? getIcon() : dto.getIcon())
                .build();
    }
}
