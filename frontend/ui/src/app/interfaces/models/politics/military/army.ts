import {ArmyLeader} from "./army-leader";
import {Battalion} from "./battalion";

export interface Army {
  id: number;
  leader: ArmyLeader;
  battalions: Battalion[];
}
