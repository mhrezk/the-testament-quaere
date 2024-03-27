import {Race} from "../../history/race/race";
import {Gender} from "../../../../enums/gender/gender";
import {Religion} from "../../dogma/religion/religion";
import {Family} from "../family/family";
import {Year} from "../../calendar/year/year";
import {Nation} from "../../places/nation/nation";
import {Title} from "../title/title";
import {Job} from "../job/job";

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
