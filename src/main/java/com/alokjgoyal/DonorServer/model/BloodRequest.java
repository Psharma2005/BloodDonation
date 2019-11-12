package com.alokjgoyal.DonorServer.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(value = "donor_request")
public class BloodRequest {

    @Id
    private UUID id;
    private String blood_group;
    private int blood_quantity;
    private String location;
    private String latitude;
    private String longitudes;
    private String requested_date;
    private int fulfilled_quantity;
    private String status;
    public UUID user_id;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFulfilled_quantity() {
        return fulfilled_quantity;
    }

    public void setFulfilled_quantity(int fulfilled_quantity) {
        this.fulfilled_quantity = fulfilled_quantity;
    }

    @Override
    public String toString() {
        return "BloodRequest{" +
                "id=" + id +
                ", blood_group='" + blood_group + '\'' +
                ", blood_quantity=" + blood_quantity +
                ", location='" + location + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitudes='" + longitudes + '\'' +
                ", requested_date='" + requested_date + '\'' +
                ", user_id=" + user_id +
                '}';
    }

    public UUID getUser_id() {
        return user_id;
    }

    public void setUser_id(UUID user_id) {
        this.user_id = user_id;
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

    public int getBlood_quantity() {
        return blood_quantity;
    }

    public void setBlood_quantity(int blood_quantity) {
        this.blood_quantity = blood_quantity;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitudes() {
        return longitudes;
    }

    public void setLongitudes(String longitudes) {
        this.longitudes = longitudes;
    }

    public String getRequested_date() {
        return requested_date;
    }

    public void setRequested_date(String requested_date) {
        this.requested_date = requested_date;
    }

    public enum Status {
        OPEN, CLOSED, COMPLETED, CANCELLED
    }
}
