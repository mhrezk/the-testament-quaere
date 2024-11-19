// import {Capital} from "./capital";
// import {NationLeader} from "../politics/nation-leader";
// import {Language} from "../history/language";
// import {Province} from "./province";
// import {NationType} from "./nation-type";

import {NationType} from "../../../enums/nation-type";
import {GovernanceType} from "../../../enums/governance-type";
import {NationStatus} from "../../../enums/nation-status";

export interface Nation {
  id: number;
  name: string;
  capital: string;
  type: NationType;
  nationStatus: NationStatus;
  governanceType: GovernanceType;
  isSelected: boolean;
}
