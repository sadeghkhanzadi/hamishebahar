package com.commonts.Dto;

import com.hamishebahar.panel.media.entity.Medias;
import com.hamishebahar.panel.news_events.entity.Events;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewsEventsDto {
    private Long id;
    private String title;
    private String text;
    private String startDate;
    private String endDate;
    private Boolean is_active = true;
    private Boolean is_deleted = false;
    private List<MediasDto> medias;
    private String createdAt;
    private String updatedAt;

    public static class Builder{
        private Long id;
        private String title;
        private String text;
        private String startDate;
        private String endDate;
        private Boolean is_active = true;
        private Boolean is_deleted = false;
        private List<MediasDto> medias;
        private String createdAt;
        private String updatedAt;

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

        public Builder Medias(List<MediasDto> medias) {
            this.medias = medias;
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

        public NewsEventsDto build(){
            return new NewsEventsDto(this);
        }
    }
    private NewsEventsDto(Builder builder){
        this.id = builder.id;
        this.title = builder.title;
        this.text = builder.text;
        this.startDate = builder.startDate;
        this.endDate = builder.endDate;
        this.is_active = builder.is_active;
        this.is_deleted = builder.is_deleted;
        this.medias = builder.medias;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Events convertToEntity() {
        return new Events.Builder()
                .Id(getId() != null ? getId() : null)
                .Title(getTitle() != null ? getTitle() : null)
                .Text(getText() != null ? getText() : null)
                .StartDate(getStartDate() != null ? getStartDate() : null)
                .EndDate(getEndDate() != null ? getEndDate() : null)
                .Is_active(getIs_active() != null ? getIs_active() : true)
                .Is_deleted(getIs_deleted() != null ? getIs_deleted() : false)
                .build();
    }

    public NewsEventsDto updaterFields(NewsEventsDto dto){
        return new Builder()
                .Id(getId() != null ? getId() : dto.getId())
                .Title(getTitle() != null ? getTitle() : dto.getTitle())
                .Text(getText() != null ? getText() : dto.getText())
                .StartDate(getStartDate() != null ? getStartDate() : dto.getStartDate())
                .EndDate(getEndDate() != null ? getEndDate() : dto.getEndDate())
                .Is_active(getIs_active() != null &&
                        (getIs_active() == dto.getIs_active()) ? getIs_active() : dto.getIs_active())
                .Is_deleted(getIs_deleted() != null &&
                        (getIs_deleted() == dto.getIs_deleted()) ? getIs_deleted() : dto.getIs_deleted())
                .build();
    }
}
