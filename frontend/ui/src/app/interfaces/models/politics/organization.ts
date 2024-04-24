// import {Pundit} from "./pundit";
// import {Nation} from "../places/nation";
// import {Year} from "../calendar/year";

export interface Organization {
  id: number;
  name: string;
  // founder: Pundit;
  founder: string;
  // nation: Nation;
  nation: string;
  // yearFoundationAndDisbandment: Year[];
  foundationDay: number;
  foundationMonth: number;
  foundationYear: number;
  disbandmentDay: number;
  disbandmentMonth: number;
  disbandmentYear: number;
  urlSymbol: string;

  isSelected: boolean;
}
