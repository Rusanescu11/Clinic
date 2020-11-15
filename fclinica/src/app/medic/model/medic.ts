import {Cabinet} from '../../cabinet/model/cabinet';
import {Observable} from 'rxjs';

export class Medic {
  id: number;
  name: string;
  phone: number;
  photo: Observable<any>;
  cabinet: Cabinet ;
  specializationModel: string;
  idPhoto: string;
}
