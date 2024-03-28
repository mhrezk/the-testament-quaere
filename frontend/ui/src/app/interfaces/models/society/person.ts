import {Race} from "../history/race";
import {Gender} from "../../../enums/gender";
import {Religion} from "../dogma/religion";
import {Family} from "./family";
import {Year} from "../calendar/year";
import {Nation} from "../places/nation";
import {Title} from "./title";
import {Job} from "./job";

export interface Person {
  name: string;
  race: Race;
  gender: Gender;
  religion: Religion;
  family: Family;
  yearBirthAndDeath: Year[];
  nation: Nation;
  title: Title;
  job: Job;
  biography: string;
  imageURL: string;
}
