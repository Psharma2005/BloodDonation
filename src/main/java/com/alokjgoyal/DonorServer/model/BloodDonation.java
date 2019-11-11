package com.alokjgoyal.DonorServer.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "blood_donations")

public class BloodDonation {

    public enum Status
    {
        OPEN, CLOSED, COMPLETED, CANCELLED};

    @Id
    private UUID id;

    private String blood_group;
    private int blood_qty;
    private UUID user_id;
    private String donation_date;  // appointment date
    private String gender;
    private String age;
    private String donation_address;
    private String longitude;
    private String latitude;
//    private boolean is_complete;
    private String status;
    private UUID request_id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBlood_group() {
        return blood_group;
    }

    public void setBlood_group(String blood_group) {
        this.blood_group = blood_group;
    }

    public int getBlood_qty() {
        return blood_qty;
    }

    public void setBlood_qty(int blood_qty) {
        this.blood_qty = blood_qty;
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
    }

    public String getDonation_date() {
        return donation_date;
    }

    public void setDonation_date(String donation_date) {
        this.donation_date = donation_date;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDonation_address() {
        return donation_address;
    }

    public void setDonation_address(String donation_address) {
        this.donation_address = donation_address;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

//    public boolean isIs_complete() {
//        return is_complete;
//    }
//
//    public void setIs_complete(boolean is_complete) {
//        this.is_complete = is_complete;
//    }

    public UUID getRequest_id() {
        return request_id;
    }

    public void setRequest_id(UUID request_id) {
        this.request_id = request_id;
    }

    @Override
    public String toString() {
        return "BloodDonations{" +
                "id=" + id +
                ", blood_group='" + blood_group + '\'' +
                ", blood_qty=" + blood_qty +
                ", user_id=" + user_id +
                ", donation_date='" + donation_date + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", donation_address='" + donation_address + '\'' +
                ", longitude='" + longitude + '\'' +
                ", latitude='" + latitude + '\'' +
//                ", is_complete=" + is_complete +
                ", request_id=" + request_id +
                '}';
    }
}
