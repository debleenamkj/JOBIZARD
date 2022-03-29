package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.domain.Sequence;
import com.stackroute.resourcesservice.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequenceService {

    private SequenceRepository sequenceRepository;

    @Autowired
    public SequenceService(SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    public int getSequenceNumber(String sequenceName){

        int number=0;
        Sequence sequence;
        if (sequenceRepository.findById(sequenceName).isPresent()) {
            sequence = sequenceRepository.findById(sequenceName).get();
            number = sequence.getSequenceNumber();
        }

        sequence = new Sequence(sequenceName, number +1);
        sequenceRepository.save(sequence);

        return number + 1;
    }
}
