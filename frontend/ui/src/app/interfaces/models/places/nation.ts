import {Capital} from "./capital";
import {NationLeader} from "../politics/nation-leader";
import {Language} from "../history/language";
import {Province} from "./province";
import {NationType} from "./nation-type";

export interface Nation {
  id: number;
  name: string;
  capital: Capital;
  nationLeader: NationLeader;
  language: Language;
  province: Province[];
  type: NationType;
  description: string;
  urlFlag: string;
}
