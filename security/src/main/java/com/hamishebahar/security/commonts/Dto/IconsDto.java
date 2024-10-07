package com.hamishebahar.security.commonts.Dto;

import com.hamishebahar.security.panel.Icons.entity.Icons;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IconsDto {
    private Long id;
    private String name;

    private String color;
    private String backgroundColor;
    private String icon;
    
    private String createdAt;
    private String updatedAt;

    public static class Builder{
        private Long id;
        private String name;

        private String color;
        private String backgroundColor;
        private String icon;

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

        public Builder Color(String color) {
            this.color = color;
            return this;
        }

        public Builder BackgroundColor(String backgroundColor) {
            this.backgroundColor = backgroundColor;
            return this;
        }

        public Builder Icon(String icon) {
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

        public IconsDto build(){
            return new IconsDto(this);
        }
    }

    private IconsDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.color = builder.color;
        this.backgroundColor = builder.backgroundColor;
        this.icon = builder.icon;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Icons convertToEntity(){
        return new Icons.Builder()
                .Id(id)
                .Name(name)
                .Color(color)
                .Icon(icon)
                .BackgroundColor(backgroundColor)
                .build();
    }

    public IconsDto updaterFields(IconsDto dto) {
        return new IconsDto.Builder()
                .Id(getId() != null ? getId() : dto.getId())
                .Name(getName() != null ? getName() : dto.getName())
                .Color(getColor() != null ? getColor() : dto.getColor())
                .BackgroundColor(getBackgroundColor() != null ?  getBackgroundColor() : dto.getBackgroundColor())
                .Icon(getIcon() != null ? getIcon() : dto.getIcon())
                .build();
    }

}
