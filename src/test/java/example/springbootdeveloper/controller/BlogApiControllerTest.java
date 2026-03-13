package example.springbootdeveloper.controller;

import example.springbootdeveloper.repository.BlogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
class BlogApiControllerTest {
    @Autowired protected MockMvc mockMvc;
    @Autowired ObjectMapper objectMapper;
    @Autowired WebApplicationContext context;
    @Autowired BlogRepository blogRepository;
    @BeforeEach public void mockMvcSetUp(){
    }
}