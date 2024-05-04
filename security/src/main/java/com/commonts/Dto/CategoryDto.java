package com.commonts.Dto;

import com.hamishebahar.panel.category.entity.CourseCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String name;
    private String comment;
    private Boolean is_active = true;
    private Boolean is_deleted = false;

    public static class Builder{
        private Long id;
        private String name;
        private String comment;

        private Boolean is_active;
        private Boolean is_deleted;

        public Builder Id(Long id) {
            this.id = id;
            return this;
        }

        public Builder Name(String name) {
            this.name = name;
            return this;
        }

        public Builder Comment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder Is_active(Boolean is_active) {
            this.is_active = is_active;
            return this;
        }

        public Builder Is_deleted(Boolean is_deleted) {
            this.is_deleted = is_deleted;
            return this;
        }

        public CategoryDto build(){
            return new CategoryDto(this);
        }
    }

    private CategoryDto(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.comment = builder.comment;
        this.is_active = builder.is_active;
        this.is_deleted = builder.is_deleted;
    }

    public CourseCategory convertToEntity(){
        return new CourseCategory.Builder()
                .Id(getId())
                .Name(getName() != null ? getName() : null)
                .Comment(getComment() != null ? getComment() : null)
                .Is_active(getIs_active() != null ? getIs_active() : true)
                .Is_deleted(getIs_deleted() != null ? getIs_deleted() : false)
                .build();
    }

    public CategoryDto updaterFields(CategoryDto dto) {
        return new CategoryDto.Builder()
                .Id(getId() != null ? getId() : dto.getId())
                .Name(getName() != null ? getName() : dto.getName())
                .Comment(getComment() != null ? getComment() : dto.getComment())
                .Is_active(getIs_active() != null &&
                (getIs_active() == dto.getIs_active()) ? getIs_active() : dto.getIs_active())
                .Is_deleted(getIs_deleted() != null &&
                        (getIs_deleted() == dto.getIs_deleted()) ? getIs_deleted() : dto.getIs_deleted())
                .build();
    }
}
