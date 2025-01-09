import {Classification} from "../../../../enums/classification";

export interface Animal {
  id: number;
  name: string;
  description: string;
  imageURL: string;
  classification: Classification;
  subClassification: string;

  isSelected: boolean;
}
