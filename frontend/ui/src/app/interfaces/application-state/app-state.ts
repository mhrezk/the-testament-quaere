import { DataState } from "../../enums/data-state/data-state";

export interface AppState<T> {
  dataState: DataState;
  data?: T;
  error?: string;
}

