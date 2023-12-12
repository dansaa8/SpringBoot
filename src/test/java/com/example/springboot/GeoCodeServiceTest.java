//package com.example.springboot;
//
//import com.example.springboot.category.CategoryRepository;
//import com.example.springboot.config.ConfigProperties;
//import com.example.springboot.location.repository.LocationRepository;
//import com.example.springboot.location.service.LocationQueryService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.test.web.client.MockRestServiceServer;
//
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
//import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.http.MediaType;
//import static org.assertj.core.api.Assertions.assertThat;
//
//@RestClientTest(LocationQueryService.class)
//public class GeoCodeServiceTest {
//
//    @Autowired
//    private LocationQueryService service;
//
//    @Autowired
//    private MockRestServiceServer server;
//
//    @MockBean
//    ConfigProperties configProperties;
//
//    @MockBean
//    LocationRepository locationRepository;
//
//    @MockBean
//    CategoryRepository categoryRepository;
//
//    @Test
//    void reverseGeoCode() {
//        server.expect(requestTo("/reverse?lat=55.0&lon=16.5"))
//                .andRespond(withSuccess("Hello", MediaType.APPLICATION_JSON));
//
//        String result = service.reverseGeoCode(55.0f,16.5f);
//
//        server.verify();
//        assertThat(result).isEqualTo("Hello");
//    }
//}
