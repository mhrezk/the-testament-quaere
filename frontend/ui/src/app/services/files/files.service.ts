import { Injectable } from '@angular/core';
import {HttpClient, HttpEvent} from "@angular/common/http";
import {environment} from "../../../environments/environment";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class FilesService {
  private baseURL: string = `${environment.API_URL}/files`;

  constructor(private http: HttpClient) { }

  uploadFiles(formData: FormData): Observable<HttpEvent<string[]>> {
    return this.http.post<string[]>(`${this.baseURL}/upload`, formData, {
      reportProgress: true,
      observe: 'events'
    });
  }

  downloadFiles(fileName: string): Observable<HttpEvent<Blob>> {
    return this.http.get(`${this.baseURL}/download/${fileName}`, {
      reportProgress: true,
      observe: 'events',
      responseType: 'blob'
    });
  }
}
