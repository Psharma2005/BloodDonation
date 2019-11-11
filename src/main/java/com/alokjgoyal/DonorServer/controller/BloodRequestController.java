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

@RestController
public class BloodRequestController {

    @Autowired
    RequestService requestService;

    @PostMapping(value = "/requests/addRequest", produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(value = "/requests/user/{user_id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getDataByUserId(@PathVariable("user_id") String user_id) {
        JSONObject response = new JSONObject();

        List<BloodRequest> request = requestService.getAllUserRequests(UUID.fromString(user_id));

        if (!request.isEmpty()) {
            response.put("status", true);
            response.put("requests", requestService.getAllUserRequests(UUID.fromString(user_id)));
            response.put("message", "Request info Fetched");
        } else {
            response.put("status", false);
            response.put("message", "Request not found");
        }
        return response.toString();
    }


    @PostMapping(value = "/requests/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String updateRequest(@PathVariable("id") UUID id, @RequestBody BloodRequest request) {
        request.setId(id);

        JSONObject response = new JSONObject();
        BloodRequest req = requestService.updateRequest(request);

        response.put("status", true);
        response.put("request_id", req.getId());
        response.put("message", "Request info Updated");


        return response.toString();
    }
}