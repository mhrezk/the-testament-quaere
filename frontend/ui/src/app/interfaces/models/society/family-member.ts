export interface FamilyMember {
  name: string;
  born: number;
  died?: number;
  location: string;
  parents?: FamilyMember[];
}
