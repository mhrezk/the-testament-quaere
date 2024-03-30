import {MilitaryLeader} from "./military-leader";
import {Squad} from "./squad";

export interface Battalion {
  id: number;
  name: string;
  leader: MilitaryLeader;
  squads: Squad[];
}
