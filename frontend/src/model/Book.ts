export type Book = {
    id: string,
    isbn: string,
    title: string,
    author: string,
    cover: "SOFTCOVER" | "EBOOK" | "HARDCOVER" | "AUDIOBOOK"
}



export type NewBook = {

    isbn: string,
    title: string,
    author: string,

    cover: "SOFTCOVER"
}
