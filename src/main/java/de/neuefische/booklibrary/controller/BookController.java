package de.neuefische.booklibrary.controller;
import de.neuefische.booklibrary.service.BookService;
import de.neuefische.booklibrary.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }


    @GetMapping("/books")
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("books/{id}")
    public Book getBookById(@RequestParam String id){
        return bookService.getBookByIsbn(id);
    }

    @PostMapping("/books")
    public

}
