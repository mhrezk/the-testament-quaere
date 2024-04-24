import {Race} from "../models/history/race";
import {Gender} from "../../enums/gender";
import {Religion} from "../models/dogma/religion";
import {Family} from "../models/society/family";
import {Year} from "../models/calendar/year";
import {Nation} from "../models/places/nation";
import {Title} from "../models/society/title";

export interface Human {
  name: string;
  //race?: Race;
  gender: Gender;
  //religion?: Religion;
  //family?: Family;
  //yearBirthAndDeath?: Year[];
  //nation?: Nation;
  //title?: Title;
  raceName?: string;
  religion?: string;
  family?: string;
  birthday?: string;
  birthMonth?: string;
  birthYear?: string;
  deathDay?: string;
  deathMonth?: string;
  deathYear?: string;
  nation?: string;
  title?: string;
  biography?: string;
  imageURL?: string;
}
