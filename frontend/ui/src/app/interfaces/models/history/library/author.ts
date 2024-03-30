import {Human} from "../../../abstraction/human";
import {Book} from "./book";

export interface Author extends Human {
  id: number;
  books: Book[];
}
