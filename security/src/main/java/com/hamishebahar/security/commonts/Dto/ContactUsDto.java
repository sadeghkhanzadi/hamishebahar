package com.hamishebahar.security.commonts.Dto;

import com.hamishebahar.security.panel.contactUs.entity.ContactUs;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactUsDto {
    private Long id;

    private String text;

    private String phoneNumberCompany;
    private String mobileNumber;

    private String emailAddress;
    private String address;
    private String latAddress;
    private String longAddress;
    private String locationAddress;

    private Boolean is_active;
    private Boolean is_deleted;

    private List<MediasDto> medias;
    
    public static class Builder{
        private Long id;

        private String text;

        private String phoneNumberCompany;
        private String mobileNumber;

        private String emailAddress;
        private String address;
        private String latAddress;
        private String longAddress;
        private String locationAddress;

        private Boolean is_active;
        private Boolean is_deleted;

        private List<MediasDto> medias;

        public Builder Id(Long id) {
            this.id = id;
            return this;
        }

        public Builder Text(String text) {
            this.text = text;
            return this;
        }

        public Builder PhoneNumberCompany(String phoneNumberCompany) {
            this.phoneNumberCompany = phoneNumberCompany;
            return this;
        }

        public Builder MobileNumber(String mobileNumber) {
            this.mobileNumber = mobileNumber;
            return this;
        }

        public Builder EmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder Address(String address) {
            this.address = address;
            return this;
        }

        public Builder LatAddress(String latAddress) {
            this.latAddress = latAddress;
            return this;
        }

        public Builder LongAddress(String longAddress) {
            this.longAddress = longAddress;
            return this;
        }

        public Builder LocationAddress(String locationAddress) {
            this.locationAddress = locationAddress;
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

        public ContactUsDto build(){
            return new ContactUsDto(this);
        }
    }

    private ContactUsDto(Builder builder) {
        this.id = builder.id;
        this.text = builder.text;
        this.phoneNumberCompany = builder.phoneNumberCompany;
        this.mobileNumber = builder.mobileNumber;
        this.emailAddress = builder.emailAddress;
        this.address = builder.address;
        this.latAddress = builder.latAddress;
        this.longAddress = builder.longAddress;
        this.locationAddress = builder.locationAddress;
        this.is_active = builder.is_active;
        this.is_deleted = builder.is_deleted;
        this.medias = builder.medias;
    }

    public ContactUs convertToEntity(){
        return new ContactUs.Builder()
                .Id(id)
                .Text(text)
                .PhoneNumberCompany(phoneNumberCompany)
                .MobileNumber(mobileNumber)
                .EmailAddress(emailAddress)
                .Address(address)
                .LatAddress(latAddress)
                .LongAddress(longAddress)
                .LocationAddress(locationAddress)
                .Is_active(is_active)
                .Is_deleted(is_deleted)
                .Medias(medias.stream().map(MediasDto::convertToEntity).collect(Collectors.toList()))
                .build();
    }

    public ContactUsDto updaterFields(ContactUsDto dto) {
        return new ContactUsDto.Builder()
                .Id(getId() != null ? getId() : dto.getId())
                .Text(getText() != null ? getText() : dto.getText())
                .PhoneNumberCompany(getPhoneNumberCompany() != null ? getPhoneNumberCompany() : dto.getPhoneNumberCompany())
                .MobileNumber(getMobileNumber() != null ? getMobileNumber() :  dto.getMobileNumber())
                .EmailAddress(getEmailAddress() != null ? getEmailAddress() : dto.getEmailAddress())
                .Address(address)
                .LatAddress(latAddress)
                .LongAddress(longAddress)
                .LocationAddress(locationAddress)
                .Is_active(getIs_active() != null &&
                        (getIs_active() == dto.getIs_active()) ? getIs_active() : dto.getIs_active())
                .Is_deleted(getIs_deleted() != null &&
                        (getIs_deleted() == dto.getIs_deleted()) ? getIs_deleted() : dto.getIs_deleted())
                .Medias(getMedias()!= null ? getMedias() : dto.getMedias())
                .build();
    }
}
