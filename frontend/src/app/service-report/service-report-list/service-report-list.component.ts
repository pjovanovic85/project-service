import { Component, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { ServiceReport } from '../service-report.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { ServiceReportService } from '../service-report.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, map } from 'rxjs';
import { Page, PageEvent, PageInfo, PageQuery, TableColumns, TableConfig } from 'src/app/models/core.model';

@Component({
  selector: 'app-service-report-list',
  templateUrl: './service-report-list.component.html',
  styleUrls: ['./service-report-list.component.scss']
})
export class ServiceReportListComponent implements OnInit{
  displayedColumns: string[] = ['actions', 'name', 'lastName', 'phoneNo', 'email', 'manufacturer', 'model', 'serialNumber', 'status'];
  tableColumns: {header:string, field: string}[] = [
    { header: 'Name', field: 'name' },
    { header: 'Surname', field: 'lastName' },
    { header: 'Phone Number', field: 'phoneNo' },
    { header: 'Email', field: 'email' },
    { header: 'Manufacturer', field: 'manufacturer' },
    { header: 'Model', field: 'model' },
    { header: 'Serial Number', field: 'serialNumber' },
    { header: 'Status', field: 'status' }
  ];
  dataSource: Observable<MatTableDataSource<ServiceReport>>;
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  query: PageQuery = {};
  pageInfo: PageInfo;
  tableConfig: TableConfig;

  constructor(
    private serviceReport: ServiceReportService,
    private router: Router,
    private route: ActivatedRoute
    ){}

  ngOnInit(): void {
    // this.dataSource.sort = this.sort;
    this.dataSource = this.serviceReport.getAllServiceReport().pipe(
    map((serviceReportList: any) => {
      console.log("LISTS ", serviceReportList);
      const {content, pageInfo} = serviceReportList;
      this.pageInfo = pageInfo;
      return  new MatTableDataSource<ServiceReport>(serviceReportList.content);
    }));
  }

  applyFilter(column: string, filterValue: any) {
    console.log("COLUMND ", column, filterValue);
    // this.dataSource.filter = filterValue.value.trim().toLowerCase();;
  }

  navigateToDetails(id: number) {
    this.router.navigate([`../details/${id}`], {relativeTo: this.route});
  }

  onFilterChange(value: string, field: string){
    if(['name', 'lastName', 'phoneNo', 'email'].includes(field)) {}
    if(['manufacturer', 'model', 'serialNumber'].includes(field)) {}
    if(field === 'status') {}
  }

  ngOnChanges(changes: SimpleChanges | any) {
    if (changes.pageInfo && changes.pageInfo.currentValue) {
      this.pageInfo = changes.pageInfo.currentValue;
    }
    // for dinamic column visibility
    if (changes && changes.tableConfig && changes.tableConfig.currentValue) {
      this.tableConfig = changes.tableConfig.currentValue;
      this.filterDisplayedColumns(changes.tableConfig.currentValue.columns as TableColumns[]);
    }
  }

  filterDisplayedColumns(columns: TableColumns[]) {
    this.displayedColumns = columns.map((tableColumn) => tableColumn.field);
  }

  onPageChange(event: PageEvent | any) {
    console.log("EVENT ", event)
    const page: Page = { pageIndex: event.pageIndex, pageSize: event.pageSize };
    if (this.query) {
      this.query = { ...this.query, page };
    }
    // call endpoint
  }

}
