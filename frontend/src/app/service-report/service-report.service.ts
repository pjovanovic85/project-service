import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ServiceReport } from './service-report.model';

@Injectable({
  providedIn: 'root'
})
export class ServiceReportService {

  constructor(private http: HttpClient) { }

  getAllServiceReport(): Observable<ServiceReport[]> {
  return this.http.get<ServiceReport[]>(environment.apiURL + '/api/service-report' + '/all');
  }

  getByIdServiceReport(id: number): Observable<ServiceReport> {
    let params = new HttpParams();
    params = params.append('id', id);
  return this.http.get<ServiceReport>(environment.apiURL + '/api/service-report' + '/id', {params});
  }

  createServiceReport(data: ServiceReport): Observable<ServiceReport> {
    return this.http.post<ServiceReport>(environment.apiURL + '/api/service-report' + '/save', data);
  }

  updateStatus(id: number, statusCode: number): Observable<any> {
    let params = new HttpParams();
    params = params.append('id', id);
    params = params.append('statusCode', statusCode);
  return this.http.get<any>(environment.apiURL + '/api/service-report' + '/update-status', {params});
  }
}
