import {Human} from "./human";
import {Battle} from "../models/politics/military/battle";

export interface Leader extends Human {
  battles: Battle[];
}
