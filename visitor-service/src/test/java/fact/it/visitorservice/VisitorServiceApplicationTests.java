package fact.it.visitorservice;

import fact.it.visitorservice.dto.VisitorResponse;
import fact.it.visitorservice.model.Visitor;
import fact.it.visitorservice.repository.VisitorRepository;
import fact.it.visitorservice.service.VisitorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class VisitorServiceApplicationTests {

    @InjectMocks
    private VisitorService visitorService;

    @Mock
    private VisitorRepository visitorRepository;

    @Test
    public void testGetAllVisitors() {
        Visitor visitor = new Visitor();
        visitor.setId("1");
        visitor.setName("Lorenzo");
        visitor.setPhone("0476415847");
        visitor.setEmail("r0885689@student.thomasmore.be");
        visitor.setCodeId("Lo0476");

        when(visitorRepository.findAll()).thenReturn(Arrays.asList(visitor));

        List<VisitorResponse> visitors = visitorService.getAllVisitors();

        assertEquals(1, visitors.size());
        assertEquals("Lorenzo", visitors.get(0).getName());
        assertEquals("0476415847", visitors.get(0).getPhone());
        assertEquals("r0885689@student.thomasmore.be", visitors.get(0).getEmail());
        assertEquals("Lo0476", visitors.get(0).getCodeId());

        verify(visitorRepository, times(1)).findAll();
    }

    @Test
    public void testGetAllVisitorsByCodeId() {
        Visitor visitor = new Visitor();
        visitor.setId("1");
        visitor.setName("Lorenzo");
        visitor.setPhone("0476415847");
        visitor.setEmail("r0885689@student.thomasmore.be");
        visitor.setCodeId("Lo0476");

        when(visitorRepository.findByCodeIdIn(Arrays.asList("Lo0476"))).thenReturn(Arrays.asList(visitor));

        List<VisitorResponse> visitors = visitorService.getAllVisitorsByCodeId(Arrays.asList("Lo0476"));

        assertEquals(1, visitors.size());
        assertEquals("1", visitors.get(0).getId());
        assertEquals("Lorenzo", visitors.get(0).getName());
        assertEquals("0476415847", visitors.get(0).getPhone());
        assertEquals("r0885689@student.thomasmore.be", visitors.get(0).getEmail());
        assertEquals("Lo0476", visitors.get(0).getCodeId());

        verify(visitorRepository, times(1)).findByCodeIdIn(Arrays.asList(visitor.getCodeId()));
    }
}
