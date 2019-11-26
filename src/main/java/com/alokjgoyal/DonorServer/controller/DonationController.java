package com.alokjgoyal.DonorServer.controller;

import com.alokjgoyal.DonorServer.model.BloodDonation;
import com.alokjgoyal.DonorServer.model.BloodRequest;
import com.alokjgoyal.DonorServer.model.User;
import com.alokjgoyal.DonorServer.service.DonationService;
import com.alokjgoyal.DonorServer.service.RequestService;
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

    @Autowired
    RequestService requestService;

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
            if(!bloodDonation.get().getRequest_id().equals(""))
            {
                BloodRequest bloodRequest = requestService.getDonorRequestById(UUID.fromString(bloodDonation.get().getRequest_id().toString())).get();

                int final_qty = bloodRequest.getFulfilled_quantity() + bloodDonation.get().getBlood_qty();
                bloodRequest.setFulfilled_quantity(final_qty);
                if(final_qty >= bloodRequest.getBlood_quantity())
                {
                    bloodRequest.setStatus(bloodDonation.get().getStatus());
                }
                requestService.updateRequest(bloodRequest);
            }
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

    @GetMapping(value = "/donation/view/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String getDonationById(@PathVariable UUID id) {

        JSONObject response = new JSONObject();

        Optional<BloodDonation> request = donationService.getBloodDonationRequestById(id);

        if (request.isPresent()) {
            response.put("status", true);
            response.put("request_info", new JSONObject(request.get()));
            response.put("message", "Donation info fetched");
        } else {
            response.put("status", false);
            response.put("request_info", new JSONObject());
            response.put("message", "Request not found");
        }
        return response.toString();
    }

}