import { Human } from "../../abstraction/human";
import {Nation} from "../places/nation";
import {Organization} from "./organization";

export interface Pundit extends Human {
  id: number;
  nation: Nation;
  organization: Organization[];
  urlCoatOfArms: string;
}
