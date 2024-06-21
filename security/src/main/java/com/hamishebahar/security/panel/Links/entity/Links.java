package com.hamishebahar.security.panel.Links.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.security.commonts.Dto.LinksDto;
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
public class Links {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String uri;

    @OneToOne
    private Icons icon;

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
        private String uri;
        private Icons icon;

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

        public Builder Icon(Icons icon) {
            this.icon = icon;
            return this;
        }

        public Links build(){
            return new Links(this);
        }
    }

    private Links(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.uri = builder.uri;
        this.icon = builder.icon;
    }

    public LinksDto convertToDto(){
        return new LinksDto.Builder()
                .Id(getId())
                .Name(getName())
                .Uri(getUri())
                .Icon(getIcon().convertToDto())
                .CreatedAt(getCreatedAt().toString())
                .UpdatedAt(getUpdatedAt().toString())
                .build();
    }
}
