package com.hamishebahar.security.commonts.Dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultsServiceDto implements Serializable {
    private String referenceNumber;
    private Long messageId;
    private Long durationTime;
    private boolean hasError;
    private int errorCode;
    private String message;
    private Object result;

    @JsonIgnore
    private HttpStatus status;

    @JsonIgnore
    private transient Throwable throwable;

    public ResultsServiceDto(Object ob){
        result = ob;
    }

    public static class Builder{
        private String referenceNumber;
        private Long messageId;
        private Long durationTime;
        private boolean hasError;
        private int errorCode;
        private String message;
        private Object result;

        @JsonIgnore
        private HttpStatus status;

        @JsonIgnore
        private transient Throwable throwable;

        public Builder ReferenceNumber(String referenceNumber) {
            this.referenceNumber = referenceNumber;
            return this;
        }

        public Builder MessageId(Long messageId) {
            this.messageId = messageId;
            return this;
        }

        public Builder DurationTime(Long durationTime) {
            this.durationTime = durationTime;
            return this;
        }

        public Builder HasError(boolean hasError) {
            this.hasError = hasError;
            return this;
        }

        public Builder ErrorCode(int errorCode) {
            this.errorCode = errorCode;
            return this;
        }

        public Builder Message(String message) {
            this.message = message;
            return this;
        }

        public Builder Result(Object result) {
            this.result = result;
            return this;
        }

        public Builder Status(HttpStatus status) {
            this.status = status;
            return this;
        }

        public Builder Throwable(Throwable throwable) {
            this.throwable = throwable;
            return this;
        }

        public ResultsServiceDto build(){
            return new ResultsServiceDto(this);
        }
    }

    public ResultsServiceDto(Builder builder) {
        this.referenceNumber = builder.referenceNumber;
        this.messageId = builder.messageId;
        this.durationTime = builder.durationTime;
        this.hasError = builder.hasError;
        this.errorCode = builder.errorCode;
        this.message = builder.message;
        this.result = builder.result;
        this.status = builder.status;
        this.throwable = builder.throwable;
    }
}