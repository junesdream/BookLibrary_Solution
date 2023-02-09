package de.neuefische.booklibrary.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.neuefische.booklibrary.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();
    @Test
    @DirtiesContext
    void getAllBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json("[]"));
    }

    @Test
    @DirtiesContext
    void getBookByIsbn() throws Exception {
        ResultActions result = mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                {
                                "title": "testTitle",
                                "author" : "testAuthor",
                                "cover" : "SOFTCOVER"
                                }
                                """));
        Book bookToGet = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), Book.class);

        mockMvc.perform(get("/api/books/"+bookToGet.isbn()))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "title": "testTitle",
                        "author" : "testAuthor",
                        "cover" : "SOFTCOVER"
                        }
                        """));
    }

    @Test
    @DirtiesContext
    void addBook() throws Exception {
        mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "title": "testTitle",
                                "author" : "testAuthor",
                                "cover" : "SOFTCOVER"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "title": "testTitle",
                        "author" : "testAuthor",
                        "cover" : "SOFTCOVER"
                        }
                        """)).andExpect(jsonPath("$.isbn").isNotEmpty());
    }

    @Test
    @DirtiesContext
    void updateBook() throws Exception {

        ResultActions result = mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                                {
                                "title": "testTitle",
                                "author" : "testAuthor",
                                "cover" : "SOFTCOVER"
                                }
                                """));
        Book bookToUpdate = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), Book.class);

        mockMvc.perform(put("/api/books/"+bookToUpdate.isbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "title": "updateTitle",
                                "author" : "updateTitle",
                                "cover" : "SOFTCOVER"
                                }
                                """))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                        "title": "updateTitle",
                        "author" : "updateTitle",
                        "cover" : "SOFTCOVER"
                        }
                        """));
    }

    @Test
    @DirtiesContext
    void deleteBook() throws Exception {
        ResultActions result =  mockMvc.perform(post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "title": "testTitle",
                                "author" : "testAuthor",
                                "cover" : "SOFTCOVER"
                                }
                                """));
        Book bookToDelete = objectMapper.readValue(result.andReturn().getResponse().getContentAsString(), Book.class);

        mockMvc.perform(delete("/api/books/"+bookToDelete.isbn()))
                .andExpect(status().isOk());
    }
}