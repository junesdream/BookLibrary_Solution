import "./BookCard.css"
import {Book} from "../model/Book";
import React from "react";

type Props = {
    book: Book,
    updateBook: (book: Book) => void,
    deleteBook: (isbn: string) => void
}

export default function BookCard(props: Props) {

    const bookCover: {
        SOFTCOVER : "EBOOK", EBOOK: "HARDCOVER", HARDCOVER: "AUDIOBOOK", AUDIOBOOK: "SOFTCOVER" } = {
        SOFTCOVER : "EBOOK",
        EBOOK: "HARDCOVER",
        HARDCOVER: "AUDIOBOOK",
        AUDIOBOOK: "SOFTCOVER"
    }

    function onAdvanceClick() {
        const updatedBook: Book = {...props.book, cover: bookCover[props.book.cover]}
        props.updateBook(updatedBook)
    }

    return (
        <div className="book-card">
            <h5>{props.book.isbn}</h5>
            <p>{props.book.title}</p>
            <p>{props.book.author}</p>
            <p>{props.book.cover}</p>
            {/*{props.book.cover === "SOFTCOVER" && <button onClick={onAdvanceClick}>Advance</button>}*/}

        </div>
    )
}