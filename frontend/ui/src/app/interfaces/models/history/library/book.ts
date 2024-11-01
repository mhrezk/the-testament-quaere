// import {Author} from "./author";
// import {Chapter} from "./chapter";

export interface Book {
  id: number;
  name: string;
  authorID: number;
  //author: Author;
  authorFirstName: string;
  authorLastName: string;
  releaseDate: number;
  releaseYearAbbreviation: string;
  //chapters: Chapter[];
  description: string;
  coverURL: string;

  isSelected: boolean;
}
