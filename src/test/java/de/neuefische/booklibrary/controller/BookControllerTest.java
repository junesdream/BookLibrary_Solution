package de.neuefische.booklibrary.controller;
import de.neuefische.booklibrary.model.Book;
import de.neuefische.booklibrary.model.Cover;
import de.neuefische.booklibrary.repo.BookRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    BookRepo bookRepo;

    @BeforeEach
    void addBookToRepo() {
        bookRepo.addBook(new Book("123", "testTitle", "testAuthor", Cover.SOFTCOVER));
    }

    @Test
    @DirtiesContext
    void getAllBooks() throws Exception {
        mockMvc.perform(get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        [
                        {
                        "isbn" : "123",
                        "title": "testTitle",
                        "author" : "testAuthor",
                        "cover" : "SOFTCOVER"
                        }
                        ]
                        """));
    }

    @Test
    @DirtiesContext
    void getBookByIsbn() throws Exception {

        mockMvc.perform(get("/api/books/123"))
                .andExpect(status().isOk())
                .andExpect(content().json("""
                        {
                         "isbn" : "123",
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

        mockMvc.perform(put("/api/books/123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                "id" : "123",
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
                mockMvc.perform(delete("/api/books/123")
                                .contentType(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk());
    }
}