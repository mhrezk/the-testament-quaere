// import {Capital} from "./capital";
// import {NationLeader} from "../politics/nation-leader";
// import {Language} from "../history/language";
// import {Province} from "./province";
// import {NationType} from "./nation-type";

export interface Nation {
  id: number;
  name: string;
  // capital: Capital;
  // leader: NationLeader;
  // language: Language;
  // provinces: Province[];
  //type: NationType;
  capital: string;
  leader: string;
  language: string;
  provinces: string[];
  type: string;
  governanceType: string;
  description: string;
  urlFlag: string;

  isSelected: boolean;
}
