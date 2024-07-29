package com.hamishebahar.security.commonts.Dto;

import com.hamishebahar.security.panel.activity.entity.Activity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto {
    private Long id;
    private String header;
    private String title;
    private String text;
    private IconsDto icon;
    private String createdAt;
    private String updatedAt;

    public static class Builder{
        private Long id;
        private String header;
        private String title;
        private String text;
        private IconsDto icon;
        private String createdAt;
        private String updatedAt;

        public Builder Id(Long id) {
            this.id = id;
            return this;
        }

        public Builder Header(String header) {
            this.header = header;
            return this;
        }

        public Builder Title(String title) {
            this.title = title;
            return this;
        }

        public Builder Text(String text) {
            this.text = text;
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

        public ActivityDto build(){
            return new ActivityDto(this);
        }
    }

    private ActivityDto(Builder builder) {
        this.id = builder.id;
        this.header = builder.header;
        this.title = builder.title;
        this.text = builder.text;
        this.icon = builder.icon;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Activity convertToEntity(){
        return new Activity.Builder()
                .Id(getId())
                .Text(getText())
                .Title(getTitle())
                .Icons(getIcon() != null ? getIcon().convertToEntity() : null)
                .Header(getHeader())
                .build();
    }

    public ActivityDto updaterFields(ActivityDto dto) {
        return new ActivityDto.Builder()
                .Id(getId() != null ? getId() : dto.getId())
                .Text(getText() != null ? getText() : dto.getText())
                .Title(getTitle() != null ? getTitle() : dto.getTitle())
                .Icon(getIcon() != null ? getIcon() : dto.getIcon())
                .Header(getHeader() != null ? getHeader() : dto.getHeader())
                .build();
    }
}
