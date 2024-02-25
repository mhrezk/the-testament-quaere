export interface CustomResponse<T> {
  timestamp: Date;
  statusCode: number;
  status: string;
  data: {
    dataRetrieved?: T[],
    dataSaved?: T,
    dataDeleted?: boolean,
    dataUpdated?: T
  };
  // reason?: {};
  message: string;
}
