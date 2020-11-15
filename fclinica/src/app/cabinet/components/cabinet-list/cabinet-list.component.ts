import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {CabinetService} from '../../service/cabinet.service';
import {Cabinet} from '../../model/cabinet';
import {Address} from '../../model/address';
import {ModalDismissReasons, NgbModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'app-cabinet-list',
  templateUrl: './cabinet-list.component.html',
  styleUrls: ['./cabinet-list.component.css']
})
export class CabinetListComponent implements OnInit {
  cabinetlist: Cabinet [] = [];
  address: Address = new Address();
  cabinet: Cabinet = new Cabinet();
  closeResult = '';
  searchValue = '';
  numberOfItemsPerP = 10;
  p = 1;            // pt paginare si urmatoarea la fel


  constructor(private route: ActivatedRoute,
              private router: Router,
              private modalService: NgbModal,
              private serviceCabinet: CabinetService) {
  }

  ngOnInit(): void {
    this.getCabinet();
  }


  // tslint:disable-next-line:typedef
  add() {
    this.router.navigate(['addCabinet']);
  }

  // tslint:disable-next-line:typedef
  getCabinet() {
    this.serviceCabinet.findAll().subscribe(data => {
      this.cabinetlist = [];
      this.cabinetlist = data;
      for (const cab of this.cabinetlist) {
        cab.photo = this.serviceCabinet.getPhotos(cab.id);
      }
    });
  }

  // tslint:disable-next-line:typedef
  edit(id: number) {
    this.router.navigate(['editCabinet/' + id]);
  }

  // tslint:disable-next-line:typedef
  goToMedicCabinet(id: number) {
    this.router.navigate(['view/' + id]);
  }

  // tslint:disable-next-line:typedef
  delete(id: number) {
    this.serviceCabinet.delete(id).subscribe(data => this.getCabinet());
  }

  getCab(id: number): Cabinet {
    this.serviceCabinet.getById(id).subscribe(data => {
      this.cabinet = new Cabinet();
      this.cabinet = data;
    });
    return this.cabinet;
  }


}

