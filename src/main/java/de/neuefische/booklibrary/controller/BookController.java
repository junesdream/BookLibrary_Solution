package de.neuefische.booklibrary.controller;
import de.neuefische.booklibrary.service.BookService;
import de.neuefische.booklibrary.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks(){
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable String id){
        return bookService.getBookByIsbn(id);
    }

    @PostMapping
    public Book addBook(@RequestBody Book bookToAdd){
        return bookService.addBook(bookToAdd);
    }

    @PutMapping("/{isbn}")
    public Book updateBook(@PathVariable String isbn, @RequestBody Book bookToUpdate){
        return bookService.updateBookBy(isbn, bookToUpdate);
    }

    @DeleteMapping("/{isbn}")
    public void deleteBook(@PathVariable String isbn){
        bookService.deleteBookByIsbn(isbn);
    }

}
