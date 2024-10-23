//import {Language} from "./language";

export interface Letter {
  id: number;
  name: string;
  ipa: string;
  description: string;
  //language: Language;
  language: string;
  scriptURL: string;

  isSelected: boolean;
}
