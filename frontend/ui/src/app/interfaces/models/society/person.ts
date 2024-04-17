import {Job} from "./job";
import {Human} from "../../abstraction/human";

export interface Person extends Human {
  id: number;
  //job?: Job;
  job?: string;

  isSelected: boolean;
}
