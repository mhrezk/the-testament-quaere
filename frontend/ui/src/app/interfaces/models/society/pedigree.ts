//import {Gender} from "../../../enums/gender";

export interface Pedigree {
  id: number;
  name: string;
  spouses?: string[];
  pids?: number[];
  motherName?: string;
  mid?: number;
  fatherName?: string;
  fid?: number;
  gender: string;
  img: string;
}
