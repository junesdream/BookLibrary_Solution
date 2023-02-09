package de.neuefische.booklibrary.service;

import de.neuefische.booklibrary.model.Book;
import de.neuefische.booklibrary.model.Cover;
import de.neuefische.booklibrary.repo.BookRepo;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BookServiceTest {

    BookRepo bookRepo = mock(BookRepo.class);
    IdService idService = mock(IdService.class);
    BookService bookService = new BookService(bookRepo, idService);

    @Test
    void getAllBooks() {
        // GIVEN
        List<Book> expected = Collections.emptyList();
        // WHEN
        when(bookRepo.getAllBooks()).thenReturn(Collections.emptyList());
        List<Book> actual = bookService.getAllBooks();
        // THEN
        verify(bookRepo).getAllBooks();
        assertEquals(expected, actual);
    }

    @Test
    void getBookByIsbn() {
        // GIVEN
        String isbn = "1234";
        Book expected = new Book(
                "1234",
                "Harry Potter und der Spaghetticode",
                "Rowling",
                Cover.HARDCOVER
                );
        // WHEN
        when(bookRepo.getBookByIsbn(isbn)).thenReturn(Optional.of(expected));
        Book actual = bookService.getBookByIsbn(isbn);
        // THEN
        verify(bookRepo).getBookByIsbn(isbn);
        assertEquals(expected, actual);
    }

    @Test
    void getBookByIsbn_whenBookNotExist_thenReturnException(){
        // GIVEN
        String isbn = "1234";
        // WHEN
        when(bookRepo.getBookByIsbn(isbn)).thenThrow(new NoSuchElementException());
        // THEN
        assertThrows(NoSuchElementException.class, () -> bookService.getBookByIsbn(isbn));
    }

    @Test
    void addBook() {
        // GIVEN
        Book bookToAdd = new Book(
                "1234",
                "Herr der Ringe",
                "Tolkien",
                Cover.AUDIOBOOK
        );
        // WHEN
        when(bookRepo.addBook(bookToAdd)).thenReturn(bookToAdd);
        when(idService.generateId()).thenReturn("1234");
        Book actual = bookService.addBook(bookToAdd);
        // THEN
        verify(bookRepo).addBook(bookToAdd);
        assertEquals(bookToAdd, actual);
    }

    @Test
    void updateBookBy() {
        // GIVEN
        String isbn = "1234";
        Book bookToUpdate = new Book(
                "1234",
                "Herr der Ringe",
                "Tolkien",
                Cover.AUDIOBOOK
        );
        Book expected = new Book(
                "1234",
                "Herr der Ringe 2",
                "Tolkien",
                Cover.AUDIOBOOK
        );
        // WHEN
        when(bookRepo.updateBookByIsbn(isbn, bookToUpdate)).thenReturn(expected);
        Book actual = bookService.updateBookBy(isbn, bookToUpdate);
        // THEN
        verify(bookRepo).updateBookByIsbn(isbn, bookToUpdate);
        assertEquals(expected, actual);
    }

    @Test
    void deleteBookByIsbn() {
        // GIVEN
        String isbn = "1234";
        // WHEN
        bookService.deleteBookByIsbn(isbn);
        // THEN
        verify(bookRepo).deleteBookByIsbn(isbn);
    }
}