package com.hamishebahar.security.commonts.Dto;

import com.hamishebahar.security.panel.periods.entity.Periods;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PeriodsDto {
    private Long id;
    private String title;
    private String text;
    private String startPeriods;
    private String endPeriods;
    private Boolean is_active = true;
    private Boolean is_deleted = false;
    //todo

    public static class Builder{
        private Long id;
        private String title;
        private String text;
        private String startPeriods;
        private String endPeriods;

        private Boolean is_active;
        private Boolean is_deleted;

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

        public Builder StartPeriods(String startPeriods) {
            this.startPeriods = startPeriods;
            return this;
        }

        public Builder EndPeriods(String endPeriods) {
            this.endPeriods = endPeriods;
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

        public PeriodsDto build(){
            return new PeriodsDto(this);
        }
    }

    private PeriodsDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.text = builder.text;
        this.startPeriods = builder.startPeriods;
        this.endPeriods = builder.endPeriods;
        this.is_active = builder.is_active;
        this.is_deleted = builder.is_deleted;
    }

    public Periods convertToEntity() {
        return new Periods.Builder()
                .Id(id)
                .Text(text)
                .Title(title)
                .EndPeriods(endPeriods)
                .StartPeriods(startPeriods)
                .Is_active(is_active != null ?  is_active : true)
                .Is_deleted(is_deleted != null ? is_deleted : false)
                .build();
    }
}
