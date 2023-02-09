package de.neuefische.booklibrary.service;

import de.neuefische.booklibrary.model.Book;
import de.neuefische.booklibrary.repo.BookRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
@Service
public class BookService {

    private final BookRepo bookRepo;
    private final IdService idService;

    public BookService(BookRepo bookRepo, IdService idService) {
        this.bookRepo = bookRepo;
        this.idService = idService;
    }

    public List<Book> getAllBooks(){
        return bookRepo.getAllBooks();
    }

    public Book getBookByIsbn(String isbn){
        return bookRepo.getBookByIsbn(isbn)
                .orElseThrow(NoSuchElementException::new);
    }

    public Book addBook(Book bookToAdd){
        return bookRepo.addBook(new Book(
                idService.generateId(),
                bookToAdd.title(),
                bookToAdd.author(),
                bookToAdd.cover()
        ));
    }

    public Book updateBookBy(String isbn, Book bookToUpdate){
        return bookRepo.updateBookByIsbn(isbn, bookToUpdate);
    }

    public void deleteBookByIsbn(String isbn){
        bookRepo.deleteBookByIsbn(isbn);
    }

}
