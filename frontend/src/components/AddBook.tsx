import {FormEvent, useState} from "react";
import {NewBook} from "../model/Book";
import axios from "axios";
//import {Button, TextField} from "@mui/material";
import "./AddBook.css"

type AddBookProps = {
    addBook: (newBook: NewBook) => void
}

export default function AddBook(props: AddBookProps) {

    const initialState: string = ""
    const [title, setTitle] = useState<string>(initialState)

    const  onSaveBook= (event: FormEvent<HTMLFormElement>) => {
        event.preventDefault()
        const newBook: NewBook = {isbn: " ", author: " ",title: title, cover: 'SOFTCOVER'}
        props.addBook(newBook)
        axios.post("api/books", newBook)
            .then(() => setTitle(initialState))
            .catch(console.error)
    }

    return (
        <div className="addBook">
            <form  onSubmit={onSaveBook}>
                <input  type="text" name="title" value={title} onChange={(event) => {setTitle(event.target.value)}}/>
                {/*<input type="text" name="ISBN" onChange={onSaveBook} value={book.ISBN} />*/}
                <button > Save </button>
            </form>
            {title}
        </div>
    )
}

