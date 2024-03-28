export interface CustomResponse {
  timestamp: Date;
  statusCode: number;
  status: string;
  data?: {
    dataRetrieved?: any[],
    datumRetrieved?: any,
    dataSaved?: any,
    dataDeleted?: boolean,
    dataUpdated?: any
  };
  errorData?: {
    error?: string
  };
  issue?: string;
  message?: string;
}

