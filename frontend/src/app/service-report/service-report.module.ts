import { NgModule } from '@angular/core';
import { ServiceReportComponent } from './service-report/service-report.component';
import { ServiceReportRoutingModule } from './service-report-routing.module';
import { MaterialModule } from '../material.module';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

const components = [
  ServiceReportComponent
];

const modules = [
  ServiceReportRoutingModule,
  MaterialModule,
  ReactiveFormsModule,
  FormsModule,
];

@NgModule({
  declarations: [...components],
  imports: [...modules],
  providers: []
})
export class ServiceReportModule { }
