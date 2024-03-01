export interface CustomResponse {
  timestamp: Date;
  statusCode: number;
  status: string;
  data: {
    dataRetrieved?: any[],
    dataSaved?: any,
    dataDeleted?: boolean,
    dataUpdated?: any
  };
  // reason?: {};
  message: string;
}
