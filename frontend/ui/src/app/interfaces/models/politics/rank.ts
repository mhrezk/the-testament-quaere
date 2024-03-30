import {RankType} from "../../../enums/rank-type";
import {Nation} from "../places/nation";

export interface Rank {
  id: number;
  name: string;
  rankAbove: string;
  rankBelow: string;
  rankType: RankType;
  nation: Nation;
  description: string;
  imageURL: string;
}
