import {Author} from "./author";
import {Chapter} from "./chapter";

export interface Book {
  id: number;
  name: string;
  author: Author;
  chapters: Chapter[];
}
