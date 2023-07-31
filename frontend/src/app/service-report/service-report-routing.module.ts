import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ServiceReportDetailsComponent } from './service-report-details/service-report-details.component';
import { ServiceReportListComponent } from './service-report-list/service-report-list.component';

const routes: Routes = [
  { path: 'list', component: ServiceReportListComponent },
  { path: 'details', component: ServiceReportDetailsComponent },
  { path: 'details/:id', component: ServiceReportDetailsComponent },
  { path: '**', redirectTo: 'list' }
];

@NgModule({
  declarations: [],
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ServiceReportRoutingModule { }
