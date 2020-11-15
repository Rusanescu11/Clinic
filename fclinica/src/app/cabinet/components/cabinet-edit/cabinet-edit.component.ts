import {Component, OnInit} from '@angular/core';
import {Cabinet} from '../../model/cabinet';
import {ActivatedRoute, Router} from '@angular/router';
import {CabinetService} from '../../service/cabinet.service';
import {HttpEventType, HttpResponse} from '@angular/common/http';
import {FormControl, FormGroup} from '@angular/forms';
import {Address} from '../../model/address';

@Component({
  selector: 'app-cabinet-edit',
  templateUrl: './cabinet-edit.component.html',
  styleUrls: ['./cabinet-edit.component.css']
})
export class CabinetEditComponent implements OnInit {
  cabinet: Cabinet = new Cabinet();
  id: number;
  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';
  // preview photo
  fileData: File = null;
  previewUrl: any = null;
  uploadedFilePath: string = null;
  myGroup: FormGroup;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private serviceCabinet: CabinetService) {
  }

  ngOnInit(): void {
    this.myGroup = new FormGroup({
      name: new FormControl(),
      phone: new FormControl(),
      description: new FormControl(),
      street: new FormControl(),
      city: new FormControl(),
      country: new FormControl(),
      number: new FormControl(),
    });
    this.cabinet = new Cabinet();
    this.cabinet.address = new Address();
    this.id = this.route.snapshot.params.id;
    this.serviceCabinet.getById(this.id).subscribe(data => {
      this.cabinet = new Cabinet();
      this.cabinet = data;
      this.cabinet.photo = this.getPhotoById(this.cabinet.id);
      this.myGroup = new FormGroup({
        name: new FormControl(this.cabinet.name),
        phone: new FormControl(this.cabinet.phone),
        description: new FormControl(this.cabinet.description),
        street: new FormControl(this.cabinet.address.street),
        city: new FormControl(this.cabinet.address.city),
        country: new FormControl(this.cabinet.address.country),
        number: new FormControl(this.cabinet.address.number),
      });
    });
  }

// tslint:disable-next-line:typedef
  getCabinet() {
    this.router.navigate(['cabinet']);

  }

  // tslint:disable-next-line:typedef
  getPhotoById(id: number) {
    return this.serviceCabinet.getPhotos(id);
  }

// tslint:disable-next-line:typedef
  onSubmit() {
    this.cabinet.name = this.myGroup.get('name').value;
    this.cabinet.phone = this.myGroup.get('phone').value;
    this.cabinet.description = this.myGroup.get('description').value;
    this.cabinet.address.street = this.myGroup.get('street').value;
    this.cabinet.address.city = this.myGroup.get('city').value;
    this.cabinet.address.number = this.myGroup.get('number').value;
    this.cabinet.address.country = this.myGroup.get('country').value;
    this.serviceCabinet.update(this.cabinet).subscribe(result => this.getCabinet());
  }

  // tslint:disable-next-line:typedef
  upload() {
    this.progress = 0;
    this.currentFile = this.selectedFiles.item(0);
    this.serviceCabinet.upload(this.currentFile).subscribe(
      event => {
        if (event.type === HttpEventType.UploadProgress) {
          this.progress = Math.round(100 * event.loaded / event.total);
        } else if (event instanceof HttpResponse) {
          this.message = event.body.message;
          const a = event.body.id;
        }
      },
      err => {
        this.progress = 0;
        this.message = 'Could not upload the file!';
        this.currentFile = undefined;
      });
  }

  // tslint:disable-next-line:typedef
  selectFile(event) {
    this.selectedFiles = event.target.files;
    this.fileProgress(event);
  }

  // tslint:disable-next-line:typedef
  fileProgress(fileInput: any) {
    this.fileData = (fileInput.target.files[0] as File);
    this.preview();
  }

  // tslint:disable-next-line:typedef
  preview() {
    // Show preview
    const mimeType = this.fileData.type;
    if (mimeType.match(/image\/*/) == null) {
      return;
    }

    const reader = new FileReader();
    reader.readAsDataURL(this.fileData);
    // tslint:disable-next-line:variable-name
    reader.onload = (_event) => {
      this.previewUrl = reader.result;
    };
  }

  // tslint:disable-next-line:typedef
  changePhoto() {
    this.serviceCabinet.deletePhoto(this.cabinet.idPhoto).subscribe(data => {
      this.upload();
    });
    setTimeout(() => {
        this.getCabinet();
      },
      4000);
  }

  formCompleted(): boolean {
    const name: string = this.myGroup.get('name').value;
    const phone: string = this.myGroup.get('phone').value;
    const description: string = this.myGroup.get('description').value;
    const street: string = this.myGroup.get('street').value;
    const city: string = this.myGroup.get('city').value;
    const numberr: string = this.myGroup.get('number').value;
    const country: string = this.myGroup.get('country').value;
    if (name !== '' && phone !== '' && street !== '' && city !== '' && numberr !== '' && country !== '' && description !== '') {
      return true;
    }
    return false;
  }

  ifHavePhoto(cabinet: Cabinet): boolean {
    if (cabinet.idPhoto !== null) {
      return true;
    }
    return false;
  }

}
