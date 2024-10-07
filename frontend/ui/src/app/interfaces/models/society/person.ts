import {Gender} from "../../../enums/gender";

export interface Person {
  id: number;
  firstName: string;
  secondName: string;
  gender: Gender;
  raceName?: string;
  // religion?: string;
  // birthDay?: string;
  // birthDayName?: string;
  // birthMonth?: string;
  // birthMonthName?: string;
  // birthYear?: string;
  // birthYearAbbreviation?: string;
  // deathDay?: string;
  // deathDayName?: string;
  // deathMonth?: string;
  // deathMonthName?: string;
  // deathYear?: string;
  // deathYearAbbreviation?: string;
  // nation?: string;
  // title?: string;
  // biography?: string;
  // imageURL?: string;
  // job?: string;
  // isBastard?: boolean;

  isSelected?: boolean;
}
