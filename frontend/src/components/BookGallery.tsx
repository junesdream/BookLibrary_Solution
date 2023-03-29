import "./BookGallery.css"
import {Book} from "../model/Book";
import BookCard from "./BookCard";

type Props = {
    books: Book[],
    updateBook: (book: Book) => void,
    deleteBook: (isbn: string) => void

}

export default function (props: Props){
    // const books: Book[] = props.books.filter(book => book.title === "")
    return (
        <div className="book-gallery">
            <div className='book-gallery_column'>
                <h2>Book</h2>
                {
                    props.books.map((book) => <BookCard key={book.isbn}
                                                      book={book}
                                                  updateBook={props.updateBook}
                                                  deleteBook={props.deleteBook}
                                                     />)
                }
            </div>
        </div>
    )

}
