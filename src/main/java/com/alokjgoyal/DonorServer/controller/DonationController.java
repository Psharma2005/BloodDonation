package com.alokjgoyal.DonorServer.controller;

import com.alokjgoyal.DonorServer.model.BloodDonation;
import com.alokjgoyal.DonorServer.service.DonationService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DonationController {

    @Autowired
    DonationService donationService;

    @PostMapping(value = "/donation/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String createBloodDonationAppointment(@RequestBody String donation_appointment)
    {
        JSONObject appointment = new JSONObject(donation_appointment);
        JSONObject response = new JSONObject();

        BloodDonation bloodDonation = donationService.addDonationAppointment(appointment);

        response.put("status", true);
        response.put("donations", new JSONObject(bloodDonation));
        response.put("message", "Appointment created successfully");

        return response.toString();
    }

}
