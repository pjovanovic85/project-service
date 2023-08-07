export interface ServiceReport {
  accessories?: string;
  additionRemark?: string;
  client?: Client;
  deviceCheckOut?: string;
  failureDescription?: string;
  id?: number;
  labor?: string;
  receiptDate?: string;
  serviceDescription?: string;
  sparePartList?: string[];
  status?: Status;
  technician?: string;
  device?: Device;
}

export interface Client {
  address?: string;
  email?: string;
  id?: number;
  lastName?: string;
  name?: string;
  phoneNo?: string;
}

export interface Device {
  deviceGroup ?: string;
  manufacturer?: string;
  model?: string;
  serialNumber?: number;
  additionalParam?: string;
  purchaseDate?: string;
  warrantyPeriod?: number;
}

export interface Status {
  description?: string;
  id?: number;
  modifyDate?: string;
  statusCode?: number;
}

export enum STATUS {
  RECEPTION = 'RECEPTION',// ON_FRONT(1, "na prijemu"),
  SERVICE = 'SERVICE',// IN_SERVICE(2, "u servisu"),
  OPERATIVE = 'OPERATIVE',// ON_WORK(3, "u radu"),
  ON_HOLD = 'ON HOLD',//
  ISSUED = 'ISSUED'// RELEASED(4, "izdat");
}


/* {
  "id": 1,
  "accessories": "cable length 2m, stand for TV",
  "additionRemark": "there is no addition remark",
  "failureDescription": "screen not showing enything",
  "serviceDescription": null,
  "labor": null,
  "technician": null,
  "status": {
      "id": 1,
      "statusCode": 1,
      "description": "na prijemu",
      "modifyDate": "2023-08-02"
  },
  "receiptDate": "2023-08-02",
  "deviceCheckOut": null,
  "client": {
      "id": 1,
      "name": "Petar",
      "lastName": "Marjanovic",
      "phoneNo": "0658293447",
      "email": "petar.marjanovic@gmail.com",
      "address": "Kneza Mihaila 47"
  },
  "sparePartList": null
} */
