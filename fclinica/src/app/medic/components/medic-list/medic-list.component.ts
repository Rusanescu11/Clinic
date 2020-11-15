import {Component, OnInit} from '@angular/core';
import {Medic} from '../../model/medic';
import {ActivatedRoute, Router} from '@angular/router';
import {MedicService} from '../../service/medic.service';
import {Cabinet} from '../../../cabinet/model/cabinet';


@Component({
  selector: 'app-medic-list',
  templateUrl: './medic-list.component.html',
  styleUrls: ['./medic-list.component.css']
})
export class MedicListComponent implements OnInit {
  medicList: Medic[] = [];
  medic: Medic = new Medic();

  constructor(private route: ActivatedRoute,
              private router: Router,
              private  medicService: MedicService) {
  }

  ngOnInit(): void {
    this.medic.cabinet = new Cabinet();
    this.getMedicList();

  }

  // tslint:disable-next-line:typedef
  add() {
    this.router.navigate(['addMedic']);
  }

  // tslint:disable-next-line:typedef
  addReservation(id: number) {
    this.router.navigate(['reservation/' + id]);
  }


  // tslint:disable-next-line:typedef
  getMedicList() {
    this.medicService.findAll().subscribe(data => {
      this.medicList = [];
      this.medicList = data;
      for (const medic of this.medicList) {
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
    this.medicService.delete(id).subscribe(data => this.getMedicList());
  }

  getOneMedic(id: number): Medic {
    this.medicService.getById(id).subscribe(data => {
      this.medic = new Medic();
      this.medic = data;
    });
    return this.medic;
  }

  ifHasPhoto(medic: Medic): boolean {
    if (medic.idPhoto !== null) {
      return true;
    }
    return false;
  }

}
