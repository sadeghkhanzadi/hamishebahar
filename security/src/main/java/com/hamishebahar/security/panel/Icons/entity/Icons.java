package com.hamishebahar.security.panel.Icons.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.security.commonts.Dto.IconsDto;
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
public class Icons {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String color;
    private String backgroundColor;
    private String icon;

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

        private String color;
        private String backgroundColor;
        private String icon;

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

        public Icons build(){
            return new Icons(this);
        }
    }

    private Icons(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.color = builder.color;
        this.backgroundColor = builder.backgroundColor;
        this.icon = builder.icon;
    }

    public IconsDto convertToDto(){
        return new IconsDto.Builder()
                .Id(id)
                .Name(name)
                .Color(color)
                .BackgroundColor(backgroundColor)
                .CreatedAt(createdAt != null ? createdAt.toString() : null)
                .UpdatedAt(updatedAt != null ? updatedAt.toString() : null)
                .build();
    }

}
