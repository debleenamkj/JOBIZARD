package com.stackroute.resourcesservice.repository;

import com.stackroute.resourcesservice.domain.Sequence;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class SequenceRepositoryTest {
    @Autowired
    private SequenceRepository sequenceRepository;

    private Sequence sequence;

    @BeforeEach
    public void setUp(){
        sequence=new Sequence();
        sequence.setId("testId");
        sequence.setSequenceNumber(3);
    }
    @AfterEach
    public void tearDown(){
        sequenceRepository.deleteById(sequence.getId());
        sequence = null;
    }

    @Test
    public void givenSequenceToSaveReturnSequence(){
        sequenceRepository.insert(sequence);
        Sequence sequence1 = sequenceRepository.findById(sequence.getId()).get();
        assertNotNull(sequence1);
        assertEquals(sequence, sequence1);
    }
    @Test
    public void givenSequenceToDeleteReturnDeleteSequence(){
        sequenceRepository.insert(sequence);
        Sequence sequence1 = sequenceRepository.findById(sequence.getId()).get();
        assertNotNull(sequence1);
        sequenceRepository.deleteById(sequence1.getId());

        assertEquals(Optional.empty(), sequenceRepository.findById(sequence1.getId()));
    }
}
