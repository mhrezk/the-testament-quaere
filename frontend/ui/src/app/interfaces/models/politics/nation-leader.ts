import {Year} from "../calendar/year";
import {Vassal} from "./vassal";

export interface NationLeader {
  id: number;
  yearBeginningAndEnd: Year[];
  vassals: Vassal[];
}
