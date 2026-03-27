package com.example.biblio.controllers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Test
    void shouldCreateBookJson() throws Exception {
        String json = """
        {
            "title": "Don Quichotte",
            "isbn": "909",
            "stockDisponible": 5
        }
        """;
        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());
    }
    @Test
    void shouldReturnErrorSameIsbn() throws Exception {
        String json1 = """
    {
        "title": "Don Quichotte",
        "isbn": "909",
        "stockDisponible": 5
    }
    """;
        String json2 = """
    {
        "title": "Le Rouge et le Noir ",
        "isbn": "909",
        "stockDisponible": 3
    }
    """;

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json1))
                .andExpect(status().isCreated());

        mockMvc.perform(post("/api/v1/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json2))
                .andExpect(status().isBadRequest());
    }
}

