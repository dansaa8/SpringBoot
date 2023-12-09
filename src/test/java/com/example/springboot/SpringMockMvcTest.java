//package com.example.springboot;
//
//import com.example.springboot.category.Category;
//import com.example.springboot.config.SecurityConfig;
//import com.example.springboot.location.*;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static com.example.springboot.matchers.ResponseBodyMatchers.responseBody;
//import static com.example.springboot.mocks.TestObjectFactory.createMockCategory;
//import static com.example.springboot.mocks.TestObjectFactory.createMockLocation1;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(controllers = LocationController.class)
//@Import(SecurityConfig.class)
//@ComponentScan(basePackages = "com.example.springboot.constraint")
//public class SpringMockMvcTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    ObjectMapper mapper;
//
//    @MockBean
//    private LocationService service;
//
//    @MockBean
//    private LocationRepository repository;
//
//    @Test
//    @WithMockUser(username = "admin", roles = "ADMIN")
//    void shouldReturnLocationView() throws Exception {
//        Category category = createMockCategory();
//        LocationView expected = new ConcreteLocationView(createMockLocation1(category));
//
//        when(repository.existsById(1)).thenReturn(true);
//
//        when(service.getOnePublic(1))
//                .thenReturn(new ConcreteLocationView(createMockLocation1(category)));
//
//        mockMvc.perform(get("/api/locations/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(responseBody().containsObjectAsJson(expected, ConcreteLocationView.class));    }
//}
