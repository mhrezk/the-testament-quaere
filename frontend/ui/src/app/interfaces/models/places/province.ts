//import {Capital} from "./capital";
//import {Nation} from "./nation";

export interface Province {
  id: number;
  name: string;
  //capital: Capital;
  capital: string;
  //nation: Nation;
  nation: string;
  history: string;
  flagURL: string;

  isSelected: boolean;
}
