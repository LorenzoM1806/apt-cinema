package fact.it.auditoriumservice;

import fact.it.auditoriumservice.dto.AuditoriumRequest;
import fact.it.auditoriumservice.dto.AuditoriumResponse;
import fact.it.auditoriumservice.model.Auditorium;
import fact.it.auditoriumservice.repository.AuditoriumRepository;
import fact.it.auditoriumservice.service.AuditoriumService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuditoriumServiceApplicationTests {

    @InjectMocks
    private AuditoriumService auditoriumService;

    @Mock
    private AuditoriumRepository auditoriumRepository;

    @Test
    public void testCreateAuditorium() {
        // Arrange
        AuditoriumRequest auditoriumRequest = new AuditoriumRequest();
        auditoriumRequest.setAuditoriumNumber(1);
        auditoriumRequest.setAuditoriumType(3);
        auditoriumRequest.setCodeId("13");

        // Act
        auditoriumService.createAuditorium(auditoriumRequest);

        // Assert
        verify(auditoriumRepository, times(1)).save(any(Auditorium.class));
    }

    @Test
    public void testGetAllAuditoriums() {
        // Arrange
        Auditorium auditorium = new Auditorium();
        auditorium.setAuditoriumNumber(1);
        auditorium.setAuditoriumType(3);
        auditorium.setCodeId("13");

        when(auditoriumRepository.findAll()).thenReturn(Arrays.asList(auditorium));

        // Act
        List<AuditoriumResponse> auditoriums = auditoriumService.getAllAuditoriums();

        // Assert
        assertEquals(1, auditoriums.size());
        assertEquals(1, auditoriums.get(0).getAuditoriumNumber());
        assertEquals(3, auditoriums.get(0).getAuditoriumType());
        assertEquals("13", auditoriums.get(0).getCodeId());

        verify(auditoriumRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllAuditoriumsByCodeId() {
        // Arrange
        Auditorium auditorium = new Auditorium();
        auditorium.setId(123456789L);
        auditorium.setAuditoriumNumber(1);
        auditorium.setAuditoriumType(3);
        // First Number, then Type
        auditorium.setCodeId("13");

        when(auditoriumRepository.findByCodeIdIn(Arrays.asList("13"))).thenReturn(Arrays.asList(auditorium));

        // Act
        List<AuditoriumResponse> auditoriums = auditoriumService.getAllAuditoriumsByCodeId(Arrays.asList("13"));

        // Assert
        assertEquals(1, auditoriums.size());
        assertEquals(123456789L, auditoriums.get(0).getId());
        assertEquals(1, auditoriums.get(0).getAuditoriumNumber());
        assertEquals(3, auditoriums.get(0).getAuditoriumType());
        assertEquals("13", auditoriums.get(0).getCodeId());

        verify(auditoriumRepository, times(1)).findByCodeIdIn(Arrays.asList(auditorium.getCodeId()));
    }

    @Test
    public void testUpdateAuditorium() {
        // Arrange
        AuditoriumRequest auditoriumRequest = new AuditoriumRequest();
        auditoriumRequest.setAuditoriumNumber(1);
        auditoriumRequest.setAuditoriumType(4);
        auditoriumRequest.setCodeId("13");

        Auditorium existingAuditorium = new Auditorium();
        existingAuditorium.setId(50070L);
        existingAuditorium.setAuditoriumNumber(1);
        existingAuditorium.setAuditoriumType(2);
        existingAuditorium.setCodeId("13");

        Auditorium updatedAuditorium = new Auditorium();
        updatedAuditorium.setId(50070L);
        updatedAuditorium.setAuditoriumNumber(1);
        updatedAuditorium.setAuditoriumType(4);
        updatedAuditorium.setCodeId("13");

        // Act
        when(auditoriumRepository.findByCodeIdIn(Arrays.asList(existingAuditorium.getCodeId())))
                .thenReturn(Arrays.asList(existingAuditorium));

        when(auditoriumRepository.save(any(Auditorium.class))).thenReturn(updatedAuditorium);

        System.out.println("Type of Existing Before: " + existingAuditorium.getAuditoriumType().toString());
        Auditorium result = auditoriumService.updateAuditorium(auditoriumRequest);
        System.out.println("Type of Existing After: " + existingAuditorium.getAuditoriumType().toString());

        // Assert (Should be the same if the update went through)
        assertNotNull(result);
        assertEquals(existingAuditorium.getId(), result.getId());
        assertEquals(existingAuditorium.getAuditoriumType(), result.getAuditoriumType());
        assertEquals(existingAuditorium.getCodeId(), result.getCodeId());
        assertEquals(existingAuditorium.getAuditoriumNumber(), result.getAuditoriumNumber());

        verify(auditoriumRepository, times(1)).findByCodeIdIn(Collections.singletonList("13"));
        verify(auditoriumRepository, times(1)).save(any(Auditorium.class));
    }

//    @Test
//    public void testUpdateAuditorium() {
//        // Arrange
//        AuditoriumRequest auditoriumRequest = new AuditoriumRequest();
//        auditoriumRequest.setAuditoriumNumber(1);
//        auditoriumRequest.setAuditoriumType(4);
//        auditoriumRequest.setCodeId("13");
//
//        Auditorium auditoriumBeforeUpdate = new Auditorium();
//        auditoriumBeforeUpdate.setId(50070L);
//        auditoriumBeforeUpdate.setAuditoriumNumber(1);
//        auditoriumBeforeUpdate.setAuditoriumType(4);
//        auditoriumBeforeUpdate.setCodeId("13");
//
//        Auditorium updatedAuditorium = new Auditorium();
//        updatedAuditorium.setAuditoriumNumber(1);
//        updatedAuditorium.setAuditoriumType(4);
//        updatedAuditorium.setCodeId("13");
//
//        // Act
//        when(auditoriumRepository.save(any(Auditorium.class))).thenReturn(updatedAuditorium);
//
//        auditoriumBeforeUpdate = auditoriumService.updateAuditorium(auditoriumRequest);
//
//        // Assert
//        assertEquals(auditoriumBeforeUpdate.getId(), updatedAuditorium.getId());
//        assertEquals(auditoriumBeforeUpdate.getAuditoriumType(), updatedAuditorium.getAuditoriumType());
//        assertEquals(auditoriumBeforeUpdate.getCodeId(), updatedAuditorium.getCodeId());
//        assertEquals(auditoriumBeforeUpdate.getAuditoriumNumber(), updatedAuditorium.getAuditoriumNumber());
//
//        verify(auditoriumRepository, times(1)).save(any(Auditorium.class));
//        verify(auditoriumRepository, times(1)).findAll();
//    }
//        @Test
//    public void testUpdateAuditorium() {
//        // Arrange
//        AuditoriumRequest auditoriumRequest = new AuditoriumRequest();
//        auditoriumRequest.setAuditoriumNumber(1);
//        auditoriumRequest.setAuditoriumType(3);
//        auditoriumRequest.setCodeId("13");
//
//        // Act
//        //Create the new version and get it
//        auditoriumService.createAuditorium(auditoriumRequest);
//        AuditoriumResponse auditorium_before_update = auditoriumService.getAllAuditoriums().get(0);
//
//        //Make the update
//        auditoriumRequest.setAuditoriumType(4);
//        auditoriumService.updateAuditorium(auditoriumRequest);
//
//        //Get the new version
//        AuditoriumResponse auditorium_after_update = auditoriumService.getAllAuditoriums().get(0);
//
//        //Assert
//        assertEquals(auditorium_before_update.getId(), auditorium_after_update.getId());
//        assertEquals(auditorium_before_update.getAuditoriumNumber(), auditorium_after_update.getAuditoriumNumber());
//        assertNotEquals(auditorium_before_update.getAuditoriumType(), auditorium_after_update.getAuditoriumType());
//        assertEquals(auditorium_before_update.getCodeId(), auditorium_after_update.getCodeId());
//    }
//    @Test
//    public void testUpdateAuditorium() {
//        //Arrange
//        Auditorium existingAuditorium = new Auditorium();
//        existingAuditorium.setId(123456789L);
//        existingAuditorium.setAuditoriumNumber(1);
//        existingAuditorium.setAuditoriumType(3);
//        existingAuditorium.setCodeId("13");
//
//        Auditorium updatedAuditorium = new Auditorium();
//        updatedAuditorium.setId(123456789L);
//        updatedAuditorium.setAuditoriumNumber(1);
//        updatedAuditorium.setAuditoriumType(4);
//        updatedAuditorium.setCodeId("13");
//
//        AuditoriumRequest auditoriumRequest = new AuditoriumRequest();
//        auditoriumRequest.setAuditoriumNumber(1);
//        auditoriumRequest.setAuditoriumType(4);
//        auditoriumRequest.setCodeId("13");
//
//        when(auditoriumRepository.findById(123456789L)).thenReturn(Optional.of(existingAuditorium));
//        when(auditoriumRepository.save(any(Auditorium.class))).thenReturn(updatedAuditorium);
//
//        // Act
//        AuditoriumResponse result = auditoriumService.updateAuditorium(auditoriumRequest);
//
//        // Assert
//        assertEquals(123456789L, result.getId());
//        assertEquals("13", result.getCodeId());
//        assertEquals(1, result.getAuditoriumNumber());
//        assertEquals(4, result.getAuditoriumType());
//
//        verify(auditoriumRepository, times(1)).findById(123456789L);
//        verify(auditoriumRepository, times(1)).save(any(Auditorium.class));
//    }
}