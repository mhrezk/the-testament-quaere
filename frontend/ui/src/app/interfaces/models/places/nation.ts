// import {Capital} from "./capital";
// import {NationLeader} from "../politics/nation-leader";
// import {Language} from "../history/language";
// import {Province} from "./province";
// import {NationType} from "./nation-type";

import {NationType} from "../../../enums/nation-type";
import {GovernanceType} from "../../../enums/governance-type";

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
  type: NationType;
  governanceType: GovernanceType;
  description: string;
  urlFlag: string;

  isSelected: boolean;
}
