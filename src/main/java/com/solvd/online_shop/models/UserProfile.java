package com.solvd.online_shop.models;


import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class UserProfile {
    private int profileId;
    private int userId;
    private String phone;
    private String address;
    private String dateOfBirth;

    public UserProfile() {
    }

    public UserProfile(int profileId, int userId, String phone, String address, String dateOfBirth) {
        this.profileId = profileId;
        this.userId = userId;
        this.phone = phone;
        this.address = address;
        this.dateOfBirth = dateOfBirth;
    }

    public int getProfileId() {
        return profileId;
    }

    public void setProfileId(int profileId) {
        this.profileId = profileId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("profileId", profileId)
                .append("userId", userId)
                .append("phone", phone)
                .append("address", address)
                .append("dateOfBirth", dateOfBirth)
                .toString();
    }
}

