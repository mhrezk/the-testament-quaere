import {Race} from "../models/history/race";
import {Gender} from "../../enums/gender";
import {Religion} from "../models/dogma/religion";
import {Family} from "../models/society/family";
import {Year} from "../models/calendar/year";
import {Nation} from "../models/places/nation";
import {Title} from "../models/society/title";

export interface Human {
  name: string;
  race: Race;
  gender: Gender;
  religion: Religion;
  family: Family;
  yearBirthAndDeath: Year[];
  nation: Nation;
  title: Title;
  biography: string;
  imageURL: string;
}
