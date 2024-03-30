import {Leader} from "../../../abstraction/leader";
import {Rank} from "../rank";

export interface SquadLeader extends Leader {
  id: number;
  rank: Rank;
}
