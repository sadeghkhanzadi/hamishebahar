package com.hamishebahar.panel.category.entity;

import com.commonts.Dto.CategoryDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class CourseCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String categoryName;
    private String comment;
    private Boolean is_active;
    private Boolean is_deleted;

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

        public CourseCategory build(){
            return new CourseCategory(this);
        }
    }

    private CourseCategory(Builder builder) {
        this.id = builder.id;
        this.categoryName = builder.name;
        this.comment = builder.comment;
        this.is_active = builder.is_active;
        this.is_deleted = builder.is_deleted;
    }

    public CategoryDto convertToDto(){
        return new CategoryDto.Builder()
                .Id(getId())
                .Name(getCategoryName() != null ? getCategoryName() : null)
                .Comment(getComment() != null ? getComment() : null)
                .Is_active(getIs_active() != null ? getIs_active() : true)
                .Is_deleted(getIs_deleted() != null ? getIs_deleted() : false)
                .build();
    }
}
