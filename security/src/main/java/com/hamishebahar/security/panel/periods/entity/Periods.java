package com.hamishebahar.security.panel.periods.entity;

import com.hamishebahar.security.commonts.Dto.PeriodsDto;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.security.panel.development.entity.Development;
import com.hamishebahar.security.panel.repostcard.entity.ReportCard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Periods {// دوره تحصیلی - دبستان - پیش دبستان
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String text;
    private String startPeriods;
    private String endPeriods;

    //میزان رشد دانش آموز
    @OneToOne
    private Development development;

    //کارنامه
    @OneToMany
    private List<ReportCard> reportCard;
    private Boolean is_active = true;
    private Boolean is_deleted = false;

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

    public static class Builder {
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

        public Periods build() {
            return new Periods(this);
        }
    }

    private Periods(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.text = builder.text;
        this.startPeriods = builder.startPeriods;
        this.endPeriods = builder.endPeriods;
        this.is_active = builder.is_active;
        this.is_deleted = builder.is_deleted;
    }

    public PeriodsDto convertToDto() {
        return new PeriodsDto.Builder()
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
