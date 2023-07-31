import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ServiceReportService } from '../service-report.service';
import { ActivatedRoute } from '@angular/router';
import { ServiceReport } from '../service-report.model';

@Component({
  selector: 'app-service-report-details',
  templateUrl: './service-report-details.component.html',
  styleUrls: ['./service-report-details.component.scss']
})
export class ServiceReportDetailsComponent implements OnInit {

  form: FormGroup;
  id: number = 0;

  constructor(
    private fb: FormBuilder,
    private serviceReport: ServiceReportService,
    private route: ActivatedRoute,
    ){}

  ngOnInit(){
    let idParameter = this.route.snapshot.paramMap.get('id');
    this.id = parseInt(idParameter ? idParameter : '0');
    if(this.id) this.serviceReport.getByIdServiceReport(this.id).subscribe(
      (serviceReport: ServiceReport) => {
        console.log("serviceReport", serviceReport);
        this.buildForm(serviceReport);
      }
    );
    else this.buildForm();
  }

  buildForm(data?: ServiceReport){
    this.form = this.fb.group({
      accessories: data?.accessories ? data.accessories : '',
      additionRemark: data?.additionRemark ? data.additionRemark : '',
      failureDescription: data?.failureDescription ? data.failureDescription : '',
      device: this.fb.group({
        deviceGroup: [data?.device?.deviceGroup ? data.device.deviceGroup : '', Validators.required],
        manufacturer: [data?.device?.manufacturer ? data.device.manufacturer : '', Validators.required],
        model: [data?.device?.model ? data.device.model : '', Validators.required],
        serialNumber: [data?.device?.serialNumber ? data.device.serialNumber : '', Validators.required],
        additionalParam: data?.device?.additionalParam ? data.device.additionalParam : '',
        purchaseDate: data?.device?.purchaseDate ? data.device.purchaseDate : new Date(),
        warrantyPeriod: data?.device?.additionalParam ? data.device.additionalParam : 0
      }),
      client: this.fb.group({
        name: [data?.client?.name ? data.client.name : '', Validators.required],
        lastName: [data?.client?.lastName ? data.client.lastName : '', Validators.required],
        phoneNo: [data?.client?.phoneNo ? data.client.phoneNo : '', Validators.required],
        email: data?.client?.email ? data.client.email : '',
        address: data?.client?.address ? data.client.address : ''
      })
    });
  }

  save(){
    this.serviceReport.createServiceReport(this.form.getRawValue())
    .subscribe((result => console.log('REZULTAT ', result)));
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
