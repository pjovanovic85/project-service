import { NgModule } from '@angular/core';
import { ServiceReportDetailsComponent } from './service-report-details/service-report-details.component';
import { ServiceReportRoutingModule } from './service-report-routing.module';
import { MaterialModule } from '../material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { WarrantyPeriodExpiredPipe } from './service-report-details/warranty-period-expired.pipe';
import { ServiceReportListComponent } from './service-report-list/service-report-list.component';
import { CommonModule } from '@angular/common';
import { TableColumnFieldPipe } from './service-report-list/table-column-field.pipe';

const components = [
  ServiceReportListComponent,
  ServiceReportDetailsComponent,
  WarrantyPeriodExpiredPipe,
  TableColumnFieldPipe
];

const modules = [
  ServiceReportRoutingModule,
  MaterialModule,
  ReactiveFormsModule,
  FormsModule,
  CommonModule
];

@NgModule({
  declarations: [...components],
  imports: [...modules],
  providers: []
})
export class ServiceReportModule { }
