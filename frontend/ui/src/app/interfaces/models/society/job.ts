import {JobType} from "../../../enums/job-type";

export interface Job {
  id: number;
  name: string;
  description: string;
  jobType: JobType;
}
