import React, {useEffect, useState} from 'react';
import './App.css';
import Header from "./components/Header";
import BookGallery from "./components/BookGallery";
import axios from "axios";
import {Book, NewBook} from "./model/Book";
import AddBook from "./components/AddBook";

function App() {

    const [books, setBooks] = useState<Book[]>([]);

    useEffect(() => {
        loadAllBooks()
    }, []);

    function loadAllBooks() {
        axios.get("/api/books")
            .then((getAllBooksResponse) => {setBooks(getAllBooksResponse.data)})
     .catch((error) => {console.error(error)})
        /*toast.error('🦄 Wow so easy!', {
            position: "top-left",
            autoClose: 5000,
            hideProgressBar: false,
            closeOnClick: true,
            pauseOnHover: true,
            draggable: true,
            progress: undefined,
            theme: "colored",
        });*/
    }

    function addBook(newBook: NewBook) {
        axios.post("/api/books", newBook)
            .then((addBookResponse) => {
                setBooks([...books, addBookResponse.data])
            })
            .catch((error) => {console.error(error)})
            /*.catch((error) => {
                toast.error("Unknown Error, try again later! " + error.response.statusText, {autoClose: 10000})
            })*/
    }

    function updateBook(book: Book) {
        axios.put(`/api/books/${book.isbn}`, book)
            .then((putBookResponse) => {
                setBooks(books.map(currentBook => {
                    if (currentBook.id === book.id) {
                        return putBookResponse.data
                    }
                    else {
                        return currentBook
                    }
                }))
            })
            .catch(console.error)
    }

    function deleteBook(isbn: string) {
        axios.delete(`/api/books/${isbn}`)
            .then(() => {
                setBooks(books.filter((book) => book.id !== isbn))
            })
            .catch(console.error)
    }

  return (
    <div className="App">
      <Header/>
      <BookGallery books={books} updateBook={updateBook} deleteBook={deleteBook}/>
        <AddBook addBook={addBook} />
    </div>
  );
}

export default App;
