import { Person } from "./person";
import {Lineage} from "../../../enums/lineage";

export interface Family {
  id: number;
  person: Person;
  fatherName: string;
  motherName: string;
  siblings: string[];
  spouses: string[];
  children: string[];
  familyName: string;
  lineage: Lineage;
}
