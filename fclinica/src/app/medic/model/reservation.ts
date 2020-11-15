import {Medic} from './medic';

export class Reservation {
  id: number;
  name: string;
  date: string;
  startConsultation: string;
  endConsultation: string;
  medic: Medic;
}
