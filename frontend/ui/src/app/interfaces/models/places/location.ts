import {Nation} from "./nation";

export interface Location {
  id: number;
  name: string;
  nation: Nation;
  description: string;
}
