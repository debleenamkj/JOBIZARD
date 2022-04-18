package com.stackroute.resourcesservice.service;

import com.stackroute.resourcesservice.domain.Sequence;
import com.stackroute.resourcesservice.repository.SequenceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SequenceServiceTest {

    @Mock
    private SequenceRepository sequenceRepository;
    @InjectMocks
    private SequenceServiceImpl sequenceService;

    private Sequence sequence;

    @BeforeEach
    public void setUp(){
        sequence=new Sequence();
        sequence.setId("testId");
        sequence.setSequenceNumber(3);
    }
    @AfterEach
    public void tearDown(){
        sequence = null;
    }

    @Test
    public void givenSequenceIdReturnSequenceNumber(){
        when(sequenceRepository.findById(sequence.getId())).thenReturn(Optional.ofNullable(sequence));

        assertEquals(sequence.getSequenceNumber()+1, sequenceService.getSequenceNumber(sequence.getId()));
        verify(sequenceRepository, times(1)).findById(any());
        verify(sequenceRepository, times(1)).save(any());
    }
    @Test
    public void givenSequenceIdToStartNewSequenceReturnSequenceNumberOne(){
        when(sequenceRepository.findById(sequence.getId())).thenReturn(Optional.ofNullable(null));

        assertEquals(1, sequenceService.getSequenceNumber(sequence.getId()));
        verify(sequenceRepository, times(1)).findById(any());
        verify(sequenceRepository, times(1)).save(any());
    }
}
