package de.neuefische.booklibrary.repo;

import de.neuefische.booklibrary.model.Book;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Repository
public class BookRepo {

    private final List<Book> bookList;

    public BookRepo(List<Book> bookList) {
        this.bookList = bookList;
    }

    public List<Book> getAllBooks(){
        return bookList;
    }

    public Optional<Book> getBookByIsbn(String isbn){
        return bookList
                .stream()
                .filter(book -> book.isbn().equals(isbn))
                .findFirst();
    }

    public Book addBook(Book bookToAdd){
        bookList.add(bookToAdd);
        return bookToAdd;
    }

    public Book updateBookByIsbn(String isbn, Book bookToUpdate){
        Book bookToDelete = getBookByIsbn(isbn).orElseThrow(NoSuchElementException::new);
        bookList.remove(bookToDelete);
        bookList.add(bookToUpdate);
        return bookToUpdate;
    }

    public void deleteBookByIsbn(String isbn){
        Book bookToDelete = getBookByIsbn(isbn).orElseThrow(NoSuchElementException::new);
        bookList.remove(bookToDelete);
    }

}
