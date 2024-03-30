import {SquadLeader} from "./squad-leader";
import {Unit} from "./unit";

export interface Squad {
  id: number;
  leader: SquadLeader;
  unit: Unit;
  unitNumber: number;
  description: string;
  urlSymbol: string;
}
