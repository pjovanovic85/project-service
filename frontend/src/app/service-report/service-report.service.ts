import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { ServiceReport } from './service-report.model';
import { PageQuery, RespObjPaged } from '../models/core.model';

@Injectable({
  providedIn: 'root'
})
export class ServiceReportService {

  constructor(private http: HttpClient) { }

  getAllServiceReport(query?: PageQuery): Observable<RespObjPaged<ServiceReport>> {
    let params = new HttpParams();
    if(query && query.page && query.page.pageIndex) params = params.set('pageNumber', query.page.pageIndex.toString());
    if(query && query.page && query.page.pageSize) params = params.set('pageSize', query.page.pageSize.toString());
    if(query && query.sortBy) params = params.set('sortBy', query.sortBy);
    if(query && query.order) params = params.set('order', query.order);
    if(query && query.search) {
      Object.keys(query.search).forEach((el) => {
        const queryElement = (query && query.search) ? query.search[el] : null;
        return queryElement ? (params = params.set(el, queryElement)) : null;
      });
    }
  return this.http.get<RespObjPaged<ServiceReport>>(environment.apiURL + '/api/service-report' + '/all',{params});
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
