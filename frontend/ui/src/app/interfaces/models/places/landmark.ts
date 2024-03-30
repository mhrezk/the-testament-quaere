import {Nation} from "./nation";

export interface Landmark {
  id: number;
  name: string;
  nation: Nation;
  description: string;
}
