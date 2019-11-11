package com.alokjgoyal.DonorServer.controller;

import com.alokjgoyal.DonorServer.model.BloodDonation;
import com.alokjgoyal.DonorServer.model.User;
import com.alokjgoyal.DonorServer.service.DonationService;
import com.mongodb.util.JSON;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @PostMapping(value = "/donation/update_status/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String updateBloodDonationAppointment(@PathVariable String id, @RequestBody String donation_appointment)
    {
        JSONObject response = new JSONObject();
        JSONObject donation_info = new JSONObject(donation_appointment);
        Optional<BloodDonation> bloodDonation = donationService.getBloodDonationRequestById(UUID.fromString(id));

        if(bloodDonation.isPresent())
        {
//            bloodDonation.get().setIs_complete(donation_info.getBoolean("is_complete"));
            bloodDonation.get().setStatus(donation_info.getString("status"));
            donationService.updateDonation(bloodDonation.get());

            response.put("status", true);
            response.put("message", "Record updated");
        }
        else
        {
            response.put("status", false);
            response.put("message", "Invalid request");
        }

        return response.toString();
    }

    @GetMapping(value = "/donation/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getAllDonations()
    {
        JSONObject response = new JSONObject();

        List<BloodDonation> donations = donationService.getAllBloodDonations();
        response.put("status", true);
        response.put("blood_donations", donations);

        return response.toString();
    }

    @GetMapping(value = "/donation/user/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getAllDonationsByUser(@PathVariable String user_id)
    {
        JSONObject response = new JSONObject();

        List<BloodDonation> donations = donationService.getAllBloodDonationsByUserId(UUID.fromString(user_id));
        response.put("status", true);
        response.put("blood_donations", donations);

        return response.toString();
    }

}
