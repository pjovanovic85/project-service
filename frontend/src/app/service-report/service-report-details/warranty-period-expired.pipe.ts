import { Pipe, PipeTransform } from '@angular/core';
import * as moment from 'moment';

@Pipe({
  name: 'warrantyPeriodExpired'
})
export class WarrantyPeriodExpiredPipe implements PipeTransform {

  transform(purchaseDate: string, warrantyPeriod: string): boolean {
    if(!moment(purchaseDate).isValid()) return false;
    const newPurchaseDate = moment(purchaseDate, "MM/DD/YYYY").add(warrantyPeriod, 'M');
    if(purchaseDate && warrantyPeriod){
      if(newPurchaseDate.valueOf() < ((new Date()).setHours(0,0,0,0))) return true;
    }
    return false;
  }

}
