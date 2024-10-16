import {Gender} from "../../../enums/gender";

export interface Family {
  id?: number;
  motherID?: number;
  fatherID?: number;
  spouseIDs?: number[];
  firstName: string;
  secondName: string;
  gender: Gender;
  nation?: string;
  race?: string;
  age?: number;
  imageURL?: string;
}
