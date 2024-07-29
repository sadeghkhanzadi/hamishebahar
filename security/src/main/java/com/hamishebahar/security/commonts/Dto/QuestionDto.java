package com.hamishebahar.security.commonts.Dto;

import com.hamishebahar.security.panel.questions.entity.Questions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class QuestionDto {

    private Long id;
    private String title;
    private String text;
    
    private String createdAt;
    private String updatedAt;

    public static class Builder{
        private Long id;
        private String title;
        private String text;
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

        public Builder CreatedAt(String createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder UpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public QuestionDto build(){
            return new QuestionDto(this);
        }
    }

    public QuestionDto(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.text = builder.text;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
    }

    public Questions convertToEntity(){
        return new Questions.Builder()
                .Id(getId())
                .Title(getTitle())
                .Text(getText())
                .build();
    }

    public QuestionDto updaterFields(QuestionDto dto) {
        return new QuestionDto.Builder()
                .Id(getId() != null ? getId() : dto.getId())
                .Title(getTitle() != null ? getTitle() : dto.getTitle())
                .Text(getText() != null ? getText() : dto.getText())
                .build();
    }
}
