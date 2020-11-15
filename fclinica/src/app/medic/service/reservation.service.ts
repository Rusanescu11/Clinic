import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Reservation} from '../model/reservation';
import {Medic} from '../model/medic';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private reservationUrl: string;

  constructor(private http: HttpClient) {
    this.reservationUrl = 'http://localhost:8080/reservation';
  }

  // tslint:disable-next-line:typedef
  public save(reservation: Reservation, idMed: number) {
    return this.http.post<Reservation>(`${this.reservationUrl}/${idMed}`, reservation);
  }

  // tslint:disable-next-line:typedef
  public delete(id: number){
    return this.http.delete(`${this.reservationUrl}/${id}`);
  }
  // tslint:disable-next-line:typedef
  public update(reservation: Reservation) {
    return this.http.put<Reservation>(this.reservationUrl, reservation);
  }
}
