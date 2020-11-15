import {Component, OnInit} from '@angular/core';
import {Reservation} from '../../model/reservation';
import {ActivatedRoute, Router} from '@angular/router';
import {MedicService} from '../../service/medic.service';
import {ReservationService} from '../../service/reservation.service';
import {NgbDate, NgbTimeStruct} from '@ng-bootstrap/ng-bootstrap';
import {FormControl, FormGroup} from '@angular/forms';

@Component({
  selector: 'app-medic-reserve',
  templateUrl: './medic-reserve.component.html',
  styleUrls: ['./medic-reserve.component.css']
})
export class MedicReserveComponent implements OnInit {
  id: number;
  reservation: Reservation = new Reservation();
  time2: NgbTimeStruct;
  time1: NgbTimeStruct;
  date1: NgbDate;
  myGroup: FormGroup;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private reservationService: ReservationService) {
  }

  ngOnInit(): void {
    this.myGroup = new FormGroup({
      date1: new FormControl(),
      time1: new FormControl(),
      time2: new FormControl()
    });
    this.reservation = new Reservation();
    this.id = this.route.snapshot.params.id;
  }

  // tslint:disable-next-line:typedef
  onSubmit() {
    this.date1 = this.myGroup.get('date1').value;
    this.time1 = this.myGroup.get('time1').value;
    this.time2 = this.myGroup.get('time2').value;
    this.reservation.date = this.date1.year + '-' + this.date1.month + '-' + (this.date1.day + 1);
    this.reservation.startConsultation = this.time1.hour + ':' + this.time1.minute + ':0' + this.time1.second;
    this.reservation.endConsultation = this.time2.hour + ':' + this.time2.minute + ':0' + this.time2.second;
    this.reservationService.save(this.reservation, this.id).subscribe(result => this.getMedicList());
  }

  // tslint:disable-next-line:typedef
  getMedicList() {
    this.router.navigate(['medic']);
  }
}
