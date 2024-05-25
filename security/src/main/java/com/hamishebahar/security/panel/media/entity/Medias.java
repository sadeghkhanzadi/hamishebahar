package com.hamishebahar.security.panel.media.entity;

import com.hamishebahar.security.commonts.Dto.MediasDto;
import com.hamishebahar.security.commonts.Enums.MediaStates;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Medias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String pathFile;

    @Enumerated(EnumType.STRING)
    private MediaStates states;//slider or banner or profile or certificate

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
        private String pathFile;

        @Enumerated(EnumType.STRING)
        private MediaStates states;//slider or banner or profile or certificate

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

        public Builder PathFile(String pathFile) {
            this.pathFile = pathFile;
            return this;
        }

        public Builder States(MediaStates states) {
            this.states = states;
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

        public Medias build(){
            return new Medias(this);
        }
    }

    private Medias(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.pathFile = builder.pathFile;
        this.states = builder.states;
        this.is_active = builder.is_active;
        this.is_deleted = builder.is_deleted;
    }

    public MediasDto convertToDto() {
        return new MediasDto.Builder()
                .Id(getId())
                .Name(getName())
                .PathFile(getPathFile() != null ? getPathFile() : null)
                .States(getStates() != null ? getStates() : null)
                .Is_active(getIs_active() != null ? getIs_active() : true)
                .Is_deleted(getIs_deleted() != null ? getIs_deleted() : false)
                .CreatedAt(getCreatedAt() != null ? getCreatedAt().toString() : null)
                .UpdatedAt(getUpdatedAt() != null ? getUpdatedAt().toString() : null)
                .build();
    }
}
