import {Gender} from "../../../enums/gender";
import {ClosedAnswer} from "../../../enums/closed-answer";
import {Lineage} from "../../../enums/lineage";
import {MaritalStatus} from "../../../enums/marital-status";

export interface PersonDetails {
  id: number;
  firstName: string;
  secondName: string;
  gender: Gender;
  raceName?: string;
  religion?: string;
  rank?: string;
  age?: number;
  birthDay?: string;
  //birthDayName?: string;
  birthMonth?: string;
  //birthMonthName?: string;
  birthYear?: string;
  birthYearAbbreviation?: string;
  deathDay?: string;
  deathDayName?: string;
  deathMonth?: string;
  //deathMonthName?: string;
  deathYear?: string;
  deathYearAbbreviation?: string;
  //calendarSystem: string;
  nation?: string;
  epithet?: string;
  // title?: string;
  biography?: string;
  imageURL?: string;
  job?: string;
  lineage?: Lineage;
  maritalStatus?: MaritalStatus;
  // isBastard?: ClosedAnswer;

  isSelected: boolean;
}
