import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ServiceReportService {

  constructor(private http: HttpClient) { }

  createServiceReport(data: any): Observable<any> {
  return this.http.post<any>(environment.apiURL + '/api/service-report' + '/save', data);
  }
}
