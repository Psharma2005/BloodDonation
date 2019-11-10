package com.alokjgoyal.DonorServer.service;

import com.alokjgoyal.DonorServer.model.BloodDonation;
import com.alokjgoyal.DonorServer.model.BloodDonation;
import com.alokjgoyal.DonorServer.model.User;
import com.alokjgoyal.DonorServer.repository.DonationRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DonationService {

    @Autowired
    DonationRepository donationRepository;

    public BloodDonation addDonationAppointment(JSONObject donation_data)
    {
        BloodDonation bloodDonation = new BloodDonation();
        bloodDonation.setId(UUID.randomUUID());

        bloodDonation.setBlood_group(donation_data.getString("blood_group"));
        bloodDonation.setBlood_qty(donation_data.getInt("blood_qty"));
        bloodDonation.setDonation_date(donation_data.getString("donation_date"));
        bloodDonation.setGender(donation_data.getString("gender"));
        bloodDonation.setAge(donation_data.getString("age"));
        bloodDonation.setDonation_address(donation_data.getString("donation_address"));
        bloodDonation.setLongitude(donation_data.getString("longitude"));
        bloodDonation.setLatitude(donation_data.getString("latitude"));
        bloodDonation.setIs_complete(false);
        bloodDonation.setRequest_id(null);

        if(donation_data.getString("request_id").trim().equals(""))
        {
            System.out.println("Value is null");
            bloodDonation.setRequest_id(null);
        }
        else
        {
            bloodDonation.setRequest_id(UUID.fromString(donation_data.getString("request_id")));
        }

        donationRepository.save(bloodDonation);
        return bloodDonation;
    }

}