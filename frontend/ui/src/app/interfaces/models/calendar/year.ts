import {Day} from "./day";
import {Month} from "./month";
import {Epoch} from "./epoch";

export interface Year {
  id: number;
  name: string;
  day: Day;
  month: Month;
  epoch: Epoch;
}
