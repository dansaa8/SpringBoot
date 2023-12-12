package com.example.springboot;

import com.example.springboot.category.Category;
import com.example.springboot.config.SecurityConfig;
import com.example.springboot.location.controller.LocationController;
import com.example.springboot.location.dto.LocationDTO;
import com.example.springboot.location.dto.LocationDTOWithAddress;
import com.example.springboot.location.repository.LocationRepository;
import com.example.springboot.location.service.LocationQueryService;
import com.example.springboot.location.service.LocationUpdateService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static com.example.springboot.matchers.ResponseBodyMatchers.responseBody;
import static com.example.springboot.mocks.TestObjectFactory.createMockCategory;
import static com.example.springboot.mocks.TestObjectFactory.createMockLocation1;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = LocationController.class)
@Import(SecurityConfig.class)
public class SpringMockMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private LocationQueryService qService;

    @MockBean
    private LocationUpdateService uService;

    @MockBean
    private LocationRepository repository;

    @Test
    @WithMockUser(username = "admin", roles = "ADMIN")
    void shouldReturnLocationDTOWithAddress() throws Exception {
        Category category = createMockCategory();
        var expected = new LocationDTOWithAddress(createMockLocation1(category), null);

        when(repository.existsById(1)).thenReturn(true);

        when(qService.getOnePublic(1))
                .thenReturn(new LocationDTOWithAddress(createMockLocation1(category), null));

        mockMvc.perform(get("/api/locations/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(responseBody().containsObjectAsJson(expected, LocationDTOWithAddress.class));
    }

    @Test
    @WithAnonymousUser
    void shouldReturn401ForAnonymous() throws Exception {
        mockMvc.perform(get("/api/users/bertil/locations"))
                .andExpect(status().is(401));
    }

    @Test
    @WithMockUser(username = "bertil", roles = "USER")
    void shouldReturn200ForBertilsLocations() throws Exception {
        mockMvc.perform(get("/api/users/bertil/locations"))
                .andExpect(status().is(200));
    }

}
