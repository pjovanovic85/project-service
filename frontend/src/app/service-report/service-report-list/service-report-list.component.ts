import { Component, OnDestroy, OnInit, SimpleChanges, ViewChild } from '@angular/core';
import { STATUS, ServiceReport } from '../service-report.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { ServiceReportService } from '../service-report.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Observable, Subject, debounce, map, mergeMap, switchMap, takeUntil, timer } from 'rxjs';
import { Page, PageEvent, PageInfo, PageQuery, RespObjPaged, SearchEvent, TableColumns, TableConfig } from 'src/app/models/core.model';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-service-report-list',
  templateUrl: './service-report-list.component.html',
  styleUrls: ['./service-report-list.component.scss']
})
export class ServiceReportListComponent implements OnInit, OnDestroy{
  displayedColumns: string[] = ['actions', 'name', 'lastName', 'phoneNo', 'email', 'manufacturer', 'model', 'serialNumber', 'status'];
  tableConfig: TableConfig = {
    columns: [
      { header: 'Name', field: 'name' },
      { header: 'Surname', field: 'lastName' },
      { header: 'Phone Number', field: 'phoneNo' },
      { header: 'Email', field: 'email' },
      { header: 'Manufacturer', field: 'manufacturer' },
      { header: 'Model', field: 'model' },
      { header: 'Serial Number', field: 'serialNumber' },
      { header: 'Status', field: 'status' }
    ]
  };
  statusList: string[] = [STATUS.ISSUED, STATUS.ON_HOLD, STATUS.OPERATIVE, STATUS.RECEPTION, STATUS.SERVICE];
  dataSource: MatTableDataSource<ServiceReport>;
  @ViewChild(MatSort, { static: true }) sort: MatSort;
  query: PageQuery = {};
  pageInfo: PageInfo;
  filterForm: FormGroup = new FormGroup({});
  allColumns: string[];
  filterValues: any = {};

  private destroy$ = new Subject<void>();
  private refreshData$ = new Subject<void>(); // Use this to trigger stream changes
  dataStream$: Observable<any>; // The main data stream

  constructor(
    private serviceReport: ServiceReportService,
    private router: Router,
    private route: ActivatedRoute
    ){}

  ngOnInit(): void {
    if(this.tableConfig.columns) this.allColumns = this.tableConfig.columns
      .filter((tableColumn) => tableColumn.field !== 'actions')
      .map((tableColumn) => tableColumn.field);
    if (this.allColumns) {
      this.allColumns.forEach((element) => {
        this.filterValues[element] = '';
      });
    }
    if (this.allColumns) {
      this.buildForm();
    }
    for (const element in this.filterValues) {
      this.filterForm.controls[element].valueChanges
      .pipe(debounce(() => timer(500)),
      )
      .subscribe((value) => {
        this.filterValues[element] = value.trim().toLowerCase();
        this.onQueryChange();
      });
    }

    this.dataStream$ = this.refreshData$.pipe(
      switchMap(() => this.serviceReport.getAllServiceReport(this.query)),
      takeUntil(this.destroy$)
    );
    this.dataStream$.subscribe((data: RespObjPaged<ServiceReport>) => {
      const {content, ...pageInfo} = data;
      this.dataSource = new MatTableDataSource<ServiceReport>(content);
      this.pageInfo = pageInfo;
    });
    this.updateParametersAndRefreshData();
  }

  buildForm() {
    this.allColumns.forEach((element) => {
      this.filterForm.addControl(element, new FormControl(''));
    });
  }

  navigateToDetails(id: number) {
    this.router.navigate([`../details/${id}`], {relativeTo: this.route});
  }

  // onFilterChange(value: string, field: string){
  //   if(['name', 'lastName', 'phoneNo', 'email'].includes(field)) {}
  //   if(['manufacturer', 'model', 'serialNumber'].includes(field)) {}
  //   if(field === 'status') {}
  // }

  // onFilterChange(value: any, field: string) {
  //   const search: { [key: string]: string } = { ...this.query.search };
  //   search[field] = value.toString().trim().toLowerCase();
  //   const page: Page = { pageIndex: 0, pageSize: this.query.page.pageSize };
  //   if (this.query) {
  //     this.query = { ...this.query, search, page };
  //   }
  //   this.dataSource = this.loadData();
  // }

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
    const page: Page = { pageIndex: event.pageIndex, pageSize: event.pageSize };
    if (this.query) {
      this.query = { ...this.query, page };
    }
    this.updateParametersAndRefreshData();
  }

  onQueryChange() {
    let newQuery: { [key: string]: string } = {...this.filterValues};
    Object.keys(newQuery).forEach((element: string) => {
      newQuery[this.prepareAtributesForBackend(element)] = this.filterValues[element];
      if(element !== 'stauts') delete newQuery[element];
    });
    this.query = {...this.query, search: newQuery};
    this.updateParametersAndRefreshData();
  }

  onSortChange(event: SearchEvent) {
    const sortBy = this.prepareAtributesForBackend(event.active);
    const order = event.direction;
    if (this.query) {
      this.query = { ...this.query, sortBy, order };
    }
    // this.dataSource.sort = this.sort;
    this.updateParametersAndRefreshData();
  }

  private prepareAtributesForBackend(field: string): string {
    if(['name', 'lastName', 'phoneNo', 'email'].includes(field)) { return 'client.' + field; }
    if(['manufacturer', 'model', 'serialNumber'].includes(field)) { return 'device.' + field; }
    if(field === 'status') { return field; }
    return '';
  }

  ngOnDestroy(): void {
    // Complete the stream when the component is destroyed
    this.destroy$.next();
    this.destroy$.complete();
  }

  updateParametersAndRefreshData(): void {
    this.refreshData$.next();// Trigger a data refresh by emitting a value to refreshData$
  }

}
