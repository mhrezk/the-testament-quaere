export interface Religion {
  id: number;
  name: string;
  nations: string[];
  description: string;
  symbolURL: string;

  isSelected: boolean;
}
