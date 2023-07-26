import { NgModule } from '@angular/core';
import { ServiceReportComponent } from './service-report/service-report.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'service-report', component: ServiceReportComponent },
  { path: '', component: ServiceReportComponent },
  { path: '*', redirectTo: '' }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceReportRoutingModule { }
