export interface NationDetails {
  id: number;
  nation: string;
  leaderFirstName: string;
  leaderSecondName: string;
  flagURL: string;
  history: string;
  languages: string[];
  capital: string;
  type: string;
  governanceType: string;
  nationStatus: string;
  provincialNumber: number;
  rulingPartyName: string;
  rulingFamily: string;
  foundingYear: number;
  endingYear: number;
  precedingNation: string;
  succeedingNation: string;
}
