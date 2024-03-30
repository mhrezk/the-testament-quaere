import {Book} from "./book";

export interface Excerpt {
  id: number;
  body: string;
  book: Book;
}
