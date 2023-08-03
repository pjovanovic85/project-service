import { NgModule } from '@angular/core';
import { PreloadAllModules, RouterModule, Routes } from '@angular/router';
import { ShellService } from './shell/shell.service';

const routes: Routes = [
  ShellService.childRoutes([
    { path: 'service-report', loadChildren: () => import('./service-report/service-report.module').then((m) => m.ServiceReportModule) },
    { path: '**', redirectTo: 'service-report' }
  ])
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { onSameUrlNavigation: 'reload', preloadingStrategy: PreloadAllModules, useHash: true })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
