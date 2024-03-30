import {Pundit} from "./pundit";
import {Nation} from "../places/nation";
import {Year} from "../calendar/year";

export interface Organization {
  id: number;
  name: string;
  founder: Pundit;
  nation: Nation;
  yearFoundationAndDisbandment: Year[];
  urlSymbol: string;
}
