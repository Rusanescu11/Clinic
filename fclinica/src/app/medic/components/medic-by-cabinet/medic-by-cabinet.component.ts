import {Component, OnInit} from '@angular/core';
import {Medic} from '../../model/medic';
import {ActivatedRoute, Router} from '@angular/router';
import {MedicService} from '../../service/medic.service';
import {Cabinet} from '../../../cabinet/model/cabinet';

@Component({
  selector: 'app-medic-by-cabinet',
  templateUrl: './medic-by-cabinet.component.html',
  styleUrls: ['./medic-by-cabinet.component.css']
})
export class MedicByCabinetComponent implements OnInit {
  medic: Medic[] = [];
  id: number;
  med: Medic = new Medic();

  constructor(private route: ActivatedRoute,
              private router: Router,
              private  medicService: MedicService) {
  }

  ngOnInit(): void {
    this.id = this.route.snapshot.params.id;
    this.getMedicByCabinet(this.id);
  }

  // tslint:disable-next-line:typedef
  getMedicByCabinet(id: number) {
    this.medicService.getDoctorByCabinet(id).subscribe(data => {
      this.medic = [];
      this.medic = data;
      for (const medic of this.medic) {
        medic.photo = this.medicService.getPhotos(medic.id);
      }
    });
  }

  // tslint:disable-next-line:typedef
  edit(id: number) {
    this.router.navigate(['editMedic/' + id]);
  }

  // tslint:disable-next-line:typedef
  delete(id: number) {
    this.medicService.delete(id).subscribe(data => this.getMedicByCabinet(this.id));
  }

  getOneMedic(id: number): Medic {
    this.medicService.getById(id).subscribe(data => {
      this.med = new Medic();
      this.med = data;
    });
    return this.med;
  }

  ifHasPhoto(medic: Medic): boolean {
    if (medic.idPhoto !== null) {
      return true;
    }
    return false;
  }

  // tslint:disable-next-line:typedef
  add() {
    this.router.navigate(['addMedic']);
  }

  // tslint:disable-next-line:typedef
  addReservation(id: number) {
    this.router.navigate(['reservation/' + id]);
  }
}
