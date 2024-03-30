import {NationLeader} from "../nation-leader";
import {Army} from "./army";
import {Year} from "../../calendar/year";

export interface Battle {
  id: number;
  name: string;
  nationalLeaders: NationLeader[];
  armies: Army[];
  battleYear: Year;
  battleDescription: string;
}
