import {Deity} from "./mythology/deity";

export interface Religion {
  id: number;
  name: string;
  deities: Deity[];
  description: string;
}
