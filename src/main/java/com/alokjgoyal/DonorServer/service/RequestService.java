package com.alokjgoyal.DonorServer.service;


import com.alokjgoyal.DonorServer.model.BloodRequest;
import com.alokjgoyal.DonorServer.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RequestService {

    @Autowired
    RequestRepository requestRepository;


    public BloodRequest addDonorRequest(@RequestBody BloodRequest request) {

        UUID id = UUID.randomUUID();
        request.setId(id);
        Format formatter = new SimpleDateFormat("EEEE, dd-MM-yyyy, hh:mm:ss.SSS a");
        String today = formatter.format(new Date());

        request.setRequested_date(today);
        requestRepository.save(request);
        return request;
    }

    public List<BloodRequest> getAllRequests() {
        return requestRepository.findAll();
    }

    public Optional<BloodRequest> getDonorRequestById(UUID id) {
        return requestRepository.findById(id);
    }

    public BloodRequest updateRequest(BloodRequest request) {
        requestRepository.save(request);
        return request;
    }

    public List<BloodRequest> getAllUserRequests(UUID user_id)
    {
        return requestRepository.findAllByUser_id(user_id);
    }
}
