import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Cabinet} from '../../cabinet/model/cabinet';
import {Medic} from '../model/medic';

@Injectable({
  providedIn: 'root'
})
export class MedicService {
  private medicurl: string;
  private photoUrl: string;

  constructor(private http: HttpClient) {
    this.medicurl = 'http://localhost:8080/doctor';
    this.photoUrl = 'http://localhost:8080/photom';
  }

  public findAll(): Observable<Medic[]> {
    return this.http.get<Medic[]>(this.medicurl);
  }

  // tslint:disable-next-line:typedef
  public save(medic: Medic) {
    return this.http.post<Medic>(this.medicurl, medic);
  }

  // tslint:disable-next-line:typedef
  public update(medic: Medic) {
    return this.http.put<Medic>(this.medicurl, medic);
  }

  public getById(id: number): Observable<any> {
    return this.http.get(`http://localhost:8080/doctor/${id}`);
  }

  // tslint:disable-next-line:typedef
  public delete(id: number) {
    return this.http.delete(`${this.medicurl}/${id}`);
  }

  // tslint:disable-next-line:typedef
  public getDoctorByCabinet(id: number): Observable<Medic[]> {
    return this.http.get<Medic[]>(`http://localhost:8080/doctor/cabinet/${id}`);
  }

  public upload(photo: File): Observable<HttpEvent<any>> {
    const formData: FormData = new FormData();
    formData.append('photo', photo);
    const req = new HttpRequest('POST', this.photoUrl, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
  }

  getPhotos(id: number): Observable<any> {
    return this.http.get(`http://localhost:8080/photoM/${id}`);
  }

  // tslint:disable-next-line:typedef
  deletePhoto(photoId: string) {
    return this.http.delete(`${this.medicurl}/${photoId}/delete`);
  }
}

