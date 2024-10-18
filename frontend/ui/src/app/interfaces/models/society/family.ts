import {Gender} from "../../../enums/gender";

export interface Family {
  stringID?: string;
  motherID?: string;
  fatherID?: string;
  spouseIDs?: string[];
  firstName?: string;
  secondName?: string;
  gender?: Gender;
  imageURL?: string;
  communityName: string;
}
