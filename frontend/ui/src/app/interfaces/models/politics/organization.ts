export interface Organization {
  id: number;
  name: string;
  founderFirstName: string;
  founderSecondName: string;
  foundationDay: number;
  foundationMonth: number;
  foundationYear: number;
  foundationYearAbbreviation: string;
  disbandmentDay: number;
  disbandmentMonth: number;
  disbandmentYear: number;
  disbandmentYearAbbreviation: string;
  symbolURL: string;
  description: string;
  organizationSize: number;
  leaderFirstName: string;
  leaderSecondName: string;

  isSelected: boolean;
}
