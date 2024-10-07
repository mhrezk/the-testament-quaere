import {Gender} from "../../../enums/gender";
import {ClosedAnswer} from "../../../enums/closed-answer";
import {Lineage} from "../../../enums/lineage";

export interface PersonDetails {
  id: number;
  firstName: string;
  secondName: string;
  gender: Gender;
  raceName?: string;
  religion?: string;
  rank?: string;
  birthDay?: string;
  //birthDayName?: string;
  birthMonth?: string;
  //birthMonthName?: string;
  birthYear?: string;
  //birthYearAbbreviation?: string;
  deathDay?: string;
  deathDayName?: string;
  deathMonth?: string;
  //deathMonthName?: string;
  deathYear?: string;
  //deathYearAbbreviation?: string;
  yearAbbreviation: string;
  //calendarSystem: string;
  nation?: string;
  epithet?: string;
  // title?: string;
  biography?: string;
  imageURL?: string;
  job?: string;
  lineage?: Lineage;
  // isBastard?: ClosedAnswer;

  isSelected: boolean;
}
