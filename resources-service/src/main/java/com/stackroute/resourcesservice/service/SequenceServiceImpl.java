package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.domain.Sequence;
import com.stackroute.resourcesservice.repository.SequenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SequenceServiceImpl implements SequenceService {

    private SequenceRepository sequenceRepository;

    @Autowired
    public SequenceServiceImpl(SequenceRepository sequenceRepository) {
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public int getSequenceNumber(String sequenceName) {

        int number;
        Sequence sequence;
        Optional<Sequence> sequenceOptional = sequenceRepository.findById(sequenceName);
        if (sequenceOptional.isEmpty())
            number = 0;
        else  {
            number = sequenceOptional.get().getSequenceNumber();
        }
        sequence = new Sequence();
        sequence.setId(sequenceName);
        sequence.setSequenceNumber(number+1);
        sequenceRepository.save(sequence);

        return number+1;
    }
}
