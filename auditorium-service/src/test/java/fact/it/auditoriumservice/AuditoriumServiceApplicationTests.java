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
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
        auditoriumRequest.setAuditoriumType(3);
        auditoriumRequest.setCodeId("13");

        Auditorium auditorium = new Auditorium();
        auditorium.setAuditoriumNumber(1);
        auditorium.setAuditoriumType(3);
        auditorium.setCodeId("13");

        when(auditoriumRepository.save(any(Auditorium.class))).thenReturn(auditorium);
        when(auditoriumRepository.findAll()).thenReturn(Arrays.asList(auditorium));

        // Act
        auditoriumService.createAuditorium(auditoriumRequest);

        AuditoriumRequest auditoriumRequestUpdated = new AuditoriumRequest();
        auditoriumRequestUpdated.setAuditoriumNumber(1);
        auditoriumRequestUpdated.setAuditoriumType(4);
        auditoriumRequestUpdated.setCodeId("13");

        Auditorium updatedAuditorium = new Auditorium();
        updatedAuditorium.setAuditoriumNumber(1);
        updatedAuditorium.setAuditoriumType(4);
        updatedAuditorium.setCodeId("13");

        when(auditoriumRepository.findAll()).thenReturn(Arrays.asList(updatedAuditorium));

        auditoriumService.updateAuditorium(auditoriumRequestUpdated);

        List<AuditoriumResponse> auditoriumResponses = auditoriumService.getAllAuditoriums();

        // Assert
        assertEquals(1, auditoriumResponses.size());
        assertEquals(1, auditoriumResponses.get(0).getAuditoriumNumber());
        assertEquals(4, auditoriumResponses.get(0).getAuditoriumType());
        assertEquals("13", auditoriumResponses.get(0).getCodeId());

        verify(auditoriumRepository, times(1)).save(any(Auditorium.class));
        verify(auditoriumRepository, times(1)).findAll();
    }
}