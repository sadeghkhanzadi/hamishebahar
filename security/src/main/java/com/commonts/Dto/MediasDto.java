package com.commonts.Dto;

import com.commonts.Enums.MediaStates;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hamishebahar.panel.media.entity.Medias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MediasDto {
    private Long id;
    private String name;
    private String pathFile;

    @Enumerated(EnumType.STRING)
    private MediaStates states;//slider or banner or profile or certificate

    private Boolean is_active = true;
    private Boolean is_deleted = false;
    @Transient
    @JsonIgnore
    private MultipartFile file;
    private String createdAt;
    private String updatedAt;

    public static class Builder{
        private Long id;
        private String name;
        private String pathFile;

        @Enumerated(EnumType.STRING)
        private MediaStates states;//slider or banner or profile or certificate

        private Boolean is_active;
        private Boolean is_deleted;
        private MultipartFile file;
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

        public Builder File(MultipartFile file) {
            this.file = file;
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

        public MediasDto build(){
            return new MediasDto(this);
        }
    }

    private MediasDto(Builder builder){
        this.id = builder.id;
        this.name = builder.name;
        this.pathFile = builder.pathFile;
        this.states = builder.states;
        this.is_active = builder.is_active;
        this.is_deleted = builder.is_deleted;
        this.file = builder.file;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Medias convertToEntity() {
        return new Medias.Builder()
                .Id(getId())
                .Name(getName())
                .PathFile(getPathFile() != null ? getPathFile() : null)
                .States(getStates() != null ? getStates() : null)
                .Is_active(getIs_active() != null ? getIs_active() : true)
                .Is_deleted(getIs_deleted() != null ? getIs_deleted() : false)
                .build();
    }

    public MediasDto updaterFields(MediasDto dto) {
        return new MediasDto.Builder()
                .Id(getId() != null ? getId() : dto.getId())
                .Name(getName() != null ? getName() : dto.getName())
                .PathFile(getPathFile() != null ? getPathFile() : dto.getPathFile())
                .States(getStates() != null ? getStates() : dto.getStates())
                .Is_active(getIs_active() != null &&
                        (getIs_active() == dto.getIs_active()) ? getIs_active() : dto.getIs_active())
                .Is_deleted(getIs_deleted() != null &&
                        (getIs_deleted() == dto.getIs_deleted()) ? getIs_deleted() : dto.getIs_deleted())
                .build();
    }
}
