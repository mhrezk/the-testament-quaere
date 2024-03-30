import {Book} from "./book";

export interface Chapter {
  id: number;
  book: Book;
  text: string;
}
