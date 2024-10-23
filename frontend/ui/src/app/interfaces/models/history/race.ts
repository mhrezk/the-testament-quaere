export interface Race {
  id: number;
  name: string;
  description: string;
  imageURL: string;
  minimumLifespan: number;
  maximumLifespan: number;
  //subRaces: string[];

  isSelected: boolean;
}
