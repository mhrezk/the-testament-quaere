import {Event} from "./event";

export interface Timeline {
  id: number;
  name: string;
  // events: Event[];
  events: number[];
}
