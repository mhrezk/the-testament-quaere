export interface Timeline {
  id: number;
  name: string;
  beginningYear: number;
  endingYear?: number;
  beginningYearAbbreviation?: string;
  endingYearAbbreviation?: string;
  events?: number[];
  // events: Event[];

  isSelected?: boolean;
}
