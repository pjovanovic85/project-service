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
  RECEPTION = 'RECEPTION',
  SERVICE = 'SERVICE',
  OPERATIVE = 'OPERATIVE',
  ON_HOLD = 'ON HOLD',
  ISSUED = 'ISSUED'
}
