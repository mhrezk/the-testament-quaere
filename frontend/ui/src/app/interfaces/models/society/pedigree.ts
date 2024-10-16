export interface Pedigree {
  name: string;
  spouses?: Pedigree[];
  children?: Pedigree[];
}
