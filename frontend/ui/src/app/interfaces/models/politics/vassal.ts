import {NationLeader} from "./nation-leader";

export interface Vassal extends NationLeader {
  id: number;
  suzerain: NationLeader;
}
