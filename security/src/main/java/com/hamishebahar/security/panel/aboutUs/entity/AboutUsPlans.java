package com.hamishebahar.security.panel.aboutUs.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.hamishebahar.security.commonts.Dto.AboutUsPlansDto;
import com.hamishebahar.security.commonts.Enums.AboutUsPlansName;
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
public class AboutUsPlans {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String planTitle;//AboutUsPlansName data is valid
    private String text;

    @ElementCollection(targetClass = String.class, fetch = FetchType.EAGER)
    private List<String> activities;//سرویس رایگان - بازی های فضای باز

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
        private String planTitle; //AboutUsPlansName data is valid
        private String text;
        private List<String> activities;//سرویس رایگان - بازی های فضای باز

        public Builder Id(Long id) {
            this.id = id;
            return this;
        }

        public Builder PlanTitle(String planTitle) {
            this.planTitle = planTitle;
            return this;
        }

        public Builder Text(String text) {
            this.text = text;
            return this;
        }

        public Builder Activities(List<String> activities) {
            this.activities = activities;
            return this;
        }

        public AboutUsPlans build(){
            return new AboutUsPlans(this);
        }
    }

    private AboutUsPlans(Builder builder) {
        this.id = builder.id;
        this.planTitle = builder.planTitle;
        this.text = builder.text;
        this.activities = builder.activities;
    }

    public AboutUsPlansDto convertToDto(){
        return new AboutUsPlansDto.Builder()
                .Id(id)
                .Text(text)
                .PlanTitle(planTitle != null ? AboutUsPlansName.valueOf(planTitle) : null)
                .Activities(activities)
                .build();
    }
}
