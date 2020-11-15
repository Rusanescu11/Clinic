import {Injectable} from '@angular/core';
import {HttpClient, HttpEvent, HttpRequest} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Cabinet} from '../model/cabinet';

@Injectable({
  providedIn: 'root'
})
export class CabinetService {
  private cabinetUrl: string;
  private photoUrl: string;

  constructor(private http: HttpClient) {
    this.cabinetUrl = 'http://localhost:8080/cabinet';
    this.photoUrl = 'http://localhost:8080/photoc';
  }

  public findAll(): Observable<Cabinet[]> {
    return this.http.get<Cabinet[]>(this.cabinetUrl);
  }

  // tslint:disable-next-line:typedef
  public save(cabinet: Cabinet) {
    return this.http.post<Cabinet>(this.cabinetUrl, cabinet);
  }

  // tslint:disable-next-line:typedef
  public update(cabinet: Cabinet) {
    return this.http.put<Cabinet>(this.cabinetUrl, cabinet);
  }

  public getById(id: number): Observable<any> {
    return this.http.get(`${this.cabinetUrl}/${id}`);
  }

  // tslint:disable-next-line:typedef
  public delete(id: number) {
    return this.http.delete(`${this.cabinetUrl}/${id}`);
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
    return this.http.get(`http://localhost:8080/photoC/${id}`);
  }
  // tslint:disable-next-line:typedef
  deletePhoto(photoId: string){
    return this.http.delete(`${this.cabinetUrl}/${photoId}/delete`);
  }
}

