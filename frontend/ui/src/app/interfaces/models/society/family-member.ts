export interface FamilyMember {
  id: string;
  firstName: string;
  lastName: string;
  gender: 'male' | 'female';
  location: string;
  birthYear: number;
  deathYear?: number;
  spouses: string[];
  children: string[];
}
