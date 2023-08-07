import { Observable, Subject } from 'rxjs';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ServiceReportService } from '../service-report.service';
import { ActivatedRoute } from '@angular/router';
import { ServiceReport } from '../service-report.model';
import * as moment from 'moment';

@Component({
  selector: 'app-service-report-details',
  templateUrl: './service-report-details.component.html',
  styleUrls: ['./service-report-details.component.scss']
})
export class ServiceReportDetailsComponent implements OnInit {

  form: FormGroup;
  id: number = 0;
  isEdit = true;

  constructor(
    private fb: FormBuilder,
    private serviceReport: ServiceReportService,
    private route: ActivatedRoute,
    ){}

  ngOnInit(){
    let idParameter = this.route.snapshot.paramMap.get('id');
    this.id = parseInt(idParameter ? idParameter : '0');
    if(this.id) {
      this.isEdit = false;
      this.serviceReport.getByIdServiceReport(this.id).subscribe(
      (serviceReport: ServiceReport) => {
        console.log("serviceReport", serviceReport);
        this.buildForm(serviceReport);
      }
    );}
    else this.buildForm();
  }

  buildForm(data?: ServiceReport){
    this.form = this.fb.group({
      accessories: [{value: data?.accessories ? data.accessories : '', disabled: !this.isEdit}],
      additionRemark: [{value: data?.additionRemark ? data.additionRemark : '', disabled: !this.isEdit}],
      failureDescription: [{value: data?.failureDescription ? data.failureDescription : '', disabled: !this.isEdit}],
      device: this.fb.group({
        deviceGroup: [{value: data?.device?.deviceGroup ? data.device.deviceGroup : '', disabled: !this.isEdit}, Validators.required],
        manufacturer: [{value: data?.device?.manufacturer ? data.device.manufacturer : '', disabled: !this.isEdit}, Validators.required],
        model: [{value: data?.device?.model ? data.device.model : '', disabled: !this.isEdit}, Validators.required],
        serialNumber: [{value: data?.device?.serialNumber ? data.device.serialNumber : '', disabled: !this.isEdit}, Validators.required],
        additionalParam: [{value: data?.device?.additionalParam ? data.device.additionalParam : '', disabled: !this.isEdit}],
        purchaseDate: [{value: data?.device?.purchaseDate ? data.device.purchaseDate : new Date(), disabled: !this.isEdit}],
        warrantyPeriod: [{value: data?.device?.warrantyPeriod ? data.device.warrantyPeriod : 0, disabled: !this.isEdit}]
      }),
      client: this.fb.group({
        name: [{value: data?.client?.name ? data.client.name : '', disabled: !this.isEdit}, Validators.required],
        lastName: [{value: data?.client?.lastName ? data.client.lastName : '', disabled: !this.isEdit}, Validators.required],
        phoneNo: [{value: data?.client?.phoneNo ? data.client.phoneNo : '', disabled: !this.isEdit}, Validators.required],
        email: [{value: data?.client?.email ? data.client.email : '', disabled: !this.isEdit}],
        address: [{value: data?.client?.address ? data.client.address : '', disabled: !this.isEdit}]
      })
    });
  }

  save(){
    this.serviceReport.createServiceReport(this.form.getRawValue())
    .subscribe((result => console.log('REZULTAT ', result)));
  }

  warrantyPeriodExpired(purchaseDate: string, warrantyPeriod: string): boolean{
    if(!moment(purchaseDate, "MM/DD/YYYY").isValid()) return false;
    const newPurchaseDate = moment(purchaseDate, "MM/DD/YYYY").add(warrantyPeriod, 'M');
    if(purchaseDate && warrantyPeriod){
      if(newPurchaseDate.valueOf() < ((new Date()).setHours(0,0,0,0))) return true;
    }
    return false;
  }

  /* {
    "accessories": "kabl, postolje, daljinski upravljac",
    "additionRemark": "ekran je izgreban",
    "failureDescription": "ne radi hdmi ulaz",
    "device": {
        "deviceGroup": "TV",
        "manufacturer": "LG",
        "model": "12345ABCDE",
        "serialNumber": "987654321",
        "additionalParam": "xxx",
        "purchaseDate": "2020-05-25",
        "warrantyPeriod": 24
    },
    "client": {
        "name": "dragan",
        "lastName": "jovanovic",
        "email": "dragan */

}
