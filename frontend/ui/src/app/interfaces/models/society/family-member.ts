export interface FamilyMember {
  name: string;
  born: number;
  died?: number;
  location?: string;
  gender: string;
  parents?: FamilyMember[];
  spouses?: FamilyMember[];
  children?: FamilyMember[];
}
