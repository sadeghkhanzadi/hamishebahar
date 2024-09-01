package com.hamishebahar.security.panel.activity.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.security.commonts.Dto.ActivityDto;
import com.hamishebahar.security.panel.Icons.entity.Icons;
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
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String header;
    private String title;
    @Lob
    private String text;

    @OneToOne
    private Icons icons;

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
        private String header;
        private String title;
        private String text;
        private Icons icons;
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

        public Builder Icons(Icons icons) {
            this.icons = icons;
            return this;
        }

        public Activity build(){
            return new Activity(this);
        }
    }

    private Activity(Builder builder) {
        this.id = builder.id;
        this.header = builder.header;
        this.title = builder.title;
        this.text = builder.text;
        this.icons = builder.icons;
    }

    public ActivityDto convertToDto(){
        return new ActivityDto.Builder()
                .Id(getId())
                .Text(getText())
                .Title(getTitle())
                .Icon(getIcons() != null ? getIcons().convertToDto() : null)
                .Header(getHeader())
                .CreatedAt(getCreatedAt()!= null ? getCreatedAt().toString() : null)
                .UpdatedAt(getUpdatedAt() != null ? getUpdatedAt().toString() : null)
                .build();
    }
}
