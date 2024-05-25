package com.hamishebahar.security.panel.news_events.entity;


import com.hamishebahar.security.commonts.Dto.NewsEventsDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.security.panel.media.entity.Medias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private String startDate;
    private String endDate;
    private Boolean is_active;
    private Boolean is_deleted;
    @ManyToMany
    private List<Medias> medias;


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
        private String title;
        private String text;
        private String startDate;
        private String endDate;
        private Boolean is_active;
        private Boolean is_deleted;

        private List<Medias> medias;

        public Builder Id(Long id) {
            this.id = id;
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

        public Builder StartDate(String startDate) {
            this.startDate = startDate;
            return this;
        }

        public Builder EndDate(String endDate) {
            this.endDate = endDate;
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

        public Builder Medias(List<Medias> medias) {
            this.medias = medias;
            return this;
        }

        public Events build(){
            return new Events(this);
        }
    }

    private Events(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.text = builder.text;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.is_active = builder.is_active;
        this.is_deleted = builder.is_deleted;
        this.medias = builder.medias;
    }

    public NewsEventsDto convertToDto() {
        return new NewsEventsDto.Builder()
                .Id(getId() != null ? getId() : null)
                .Title(getTitle() != null ? getTitle() : null)
                .Text(getText() != null ? getText() : null)
                .StartDate(getStartDate() != null ? getStartDate() : null)
                .EndDate(getEndDate() != null ? getEndDate() : null)
                .Is_active(getIs_active() != null ? getIs_active() : true)
                .Is_deleted(getIs_deleted() != null ? getIs_deleted() : false)
                .Medias(medias != null ? getMedias().stream()
                        .map(Medias::convertToDto)
                        .collect(Collectors.toList()) : null)
                .CreatedAt(getCreatedAt() != null ? getCreatedAt().toString() : null)
                .UpdatedAt(getUpdatedAt() != null ? getUpdatedAt().toString(): null)
                .build();
    }
}
