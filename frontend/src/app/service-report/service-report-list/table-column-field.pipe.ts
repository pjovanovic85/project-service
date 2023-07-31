import { Pipe, PipeTransform } from '@angular/core';
import { ServiceReport } from '../service-report.model';

@Pipe({
  name: 'tableColumnField'
})
export class TableColumnFieldPipe implements PipeTransform {

  transform(row: ServiceReport | any, field: string): string | undefined {
    if(['name', 'lastName', 'phoneNo', 'email'].includes(field) && row.client) return row.client[field];
    if(['manufacturer', 'model', 'serialNumber'].includes(field) && row.device) return row.device[field];
    if(field === 'status') return row.status?.description;
    return '';
  }

}
