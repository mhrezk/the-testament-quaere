//import {Gender} from "../../../enums/gender";

export interface Pedigree {
  id: number;
  pids?: number[];
  mid?: number;
  fid?: number;
  name: string;
  gender: string;
  img: string;
}
