package com.alokjgoyal.DonorServer.controller;


import com.alokjgoyal.DonorServer.model.BloodRequest;
import com.alokjgoyal.DonorServer.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@RestController
public class BloodRequestController {

    @Autowired
    RequestService requestService;

    @GetMapping(value = "/requests/test")
    public String test() {
        return "Request Service";
    }

    @PostMapping(value = "/requests/addRequest", produces = "text/plain")
    public @ResponseBody
    String addRequest(@RequestBody BloodRequest request) {
        return requestService.addDonorRequest(request);
    }

    @GetMapping("/requests/all")
    public List<BloodRequest> getAllRequests() {
        return requestService.getAllRequests();
    }

    @GetMapping("/requests/view/{id}")
    public Optional<BloodRequest> findRequestById(@PathVariable UUID id) {
        return requestService.getDonorRequestById(id);
    }

    @PutMapping("/requests/edit/{id}")
    public String editRequest(@PathVariable("id") UUID id, @RequestBody BloodRequest request) {

        request.setId(id);
        requestService.updateRequest(request);
        return "Request Updated";
    }

}

