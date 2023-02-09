package de.neuefische.booklibrary.model;

public record Book(
        String isbn,
        String title,
        String author,
        Cover cover
) {
}
