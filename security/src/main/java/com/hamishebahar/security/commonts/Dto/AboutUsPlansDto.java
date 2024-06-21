package com.hamishebahar.security.commonts.Dto;

import com.hamishebahar.security.commonts.Enums.AboutUsPlansName;
import com.hamishebahar.security.panel.aboutUs.entity.AboutUsPlans;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AboutUsPlansDto {
    private Long id;
    private AboutUsPlansName planTitle;
    private String text;
    private List<String> activities;//سرویس رایگان - بازی های فضای باز


    private String createdAt;
    private String updatedAt;


    public static class Builder{
        private Long id;
        private AboutUsPlansName planTitle;
        private String text;
        private List<String> activities;//سرویس رایگان - بازی های فضای باز

        public Builder Id(Long id) {
            this.id = id;
            return this;
        }

        public Builder PlanTitle(AboutUsPlansName planTitle) {
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

        public AboutUsPlansDto build(){
            return new AboutUsPlansDto(this);
        }
    }

    private AboutUsPlansDto(Builder builder) {
        this.id = builder.id;
        this.planTitle = builder.planTitle;
        this.text = builder.text;
        this.activities = builder.activities;
    }

    public AboutUsPlans convertToEntity(){
        return new AboutUsPlans.Builder()
                .Id(id)
                .Text(text)
                .PlanTitle(planTitle.name())
                .Activities(activities)
                .build();
    }
}
