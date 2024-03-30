import {Rank} from "../rank";
import {Leader} from "../../../abstraction/leader";

export interface ArmyLeader extends Leader {
  id: number;
  rank: Rank;
}
