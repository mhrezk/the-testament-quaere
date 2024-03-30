import {Language} from "./language";

export interface Letter {
  id: number;
  name: string;
  ipa: string;
  language: Language;
  scriptURL: string;
}
