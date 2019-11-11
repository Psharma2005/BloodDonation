package com.alokjgoyal.DonorServer.controller;


import com.alokjgoyal.DonorServer.model.BloodRequest;
import com.alokjgoyal.DonorServer.service.RequestService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
//  requestService.addDonorRequest(request);

@RestController
public class BloodRequestController {

    @Autowired
    RequestService requestService;

    @PostMapping(value = "/requests/addRequest", produces = "text/plain")
    public @ResponseBody
    String addRequest(@RequestBody BloodRequest request) {

        JSONObject response = new JSONObject();
        BloodRequest bloodRequest = requestService.addDonorRequest(request);

        response.put("status", true);
        response.put("request_id", bloodRequest.getId().toString());

        return response.toString();
    }

    @GetMapping("/requests/all")
    public List<BloodRequest> getAllRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping(value = "/requests/view/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody
    String getUserById(@PathVariable UUID id) {
        JSONObject response = new JSONObject();

        Optional<BloodRequest> request = requestService.getDonorRequestById(id);

        if (request.isPresent()) {
            response.put("status", true);
            response.put("request_info", new JSONObject(request.get()));
            response.put("message", "Request info fetched");
        } else {
            response.put("status", false);
            response.put("request_info", new JSONObject());
            response.put("message", "Request not found");
        }
        return response.toString();
    }


    @PutMapping("/requests/edit/{id}")
    public String editRequest(@PathVariable("id") UUID id, @RequestBody BloodRequest request) {

        request.setId(id);
        requestService.updateRequest(request);
        return "Request Updated";
    }

}

