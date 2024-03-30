import {Leader} from "../../../abstraction/leader";
import {Rank} from "../rank";
import {Battalion} from "./battalion";

export interface MilitaryLeader extends Leader {
  id: number;
  rank: Rank;
  battalion: Battalion;
}
