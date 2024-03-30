import {Capital} from "./capital";
import {Nation} from "./nation";

export interface Province {
  id: number;
  name: string;
  capital: Capital;
  nation: Nation;
  description: string;
}
