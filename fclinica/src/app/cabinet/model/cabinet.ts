import {Address} from './address';
import {Medic} from '../../medic/model/medic';
import {Observable} from 'rxjs';

export class Cabinet {
  id: number;
  name: string;
  description: string;
  phone: number;
  photo: Observable<any>;
  address: Address;
  medic: Medic[];
  idPhoto: string;
}
