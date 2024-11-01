// import {Human} from "../../../abstraction/human";
// import {Book} from "./book";

import {Gender} from "../../../../enums/gender";

export interface Author {
  id: number;
  uniqueValue: string;
  firstName: string;
  middleName: string;
  lastName: string;
  penName: string;
  birthYear: number;
  birthYearAbbreviation: string;
  deathYear: number;
  biography: string;
  imageURL: string;
  gender: Gender;
  //books: Book[];

  isSelected: boolean;
}
