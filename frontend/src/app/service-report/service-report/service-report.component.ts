import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ServiceReportService } from '../service-report.service';

@Component({
  selector: 'app-service-report',
  templateUrl: './service-report.component.html',
  styleUrls: ['./service-report.component.scss']
})
export class ServiceReportComponent implements OnInit {

  form: FormGroup;

  constructor(private fb: FormBuilder, private serviceReport: ServiceReportService){}

  ngOnInit(){
    this.buildForm();
  }

  buildForm(){
    this.form = this.fb.group({
      accessories: '',
      additionRemark: '',
      failureDescription: '',
      device: this.fb.group({
        deviceGroup: ['', Validators.required],
        manufacturer: ['', Validators.required],
        model: ['', Validators.required],
        serialNumber: ['', Validators.required],
        additionalParam: '',
        purchaseDate: new Date(),
        warrantyPeriod: 0
      }),
      client: this.fb.group({
        name: ['', Validators.required],
        lastName: ['', Validators.required],
        phoneNo: ['', Validators.required],
        email: '',
        adress: ''
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
