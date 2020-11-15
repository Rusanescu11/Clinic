import {Component, OnInit} from '@angular/core';
import {Cabinet} from '../../model/cabinet';
import {ActivatedRoute, Router} from '@angular/router';
import {CabinetService} from '../../service/cabinet.service';
import {IDropdownSettings} from 'ng-multiselect-dropdown';
import {Address} from '../../model/address';
import {FormControl, FormGroup} from '@angular/forms';
import {HttpEventType, HttpResponse} from '@angular/common/http';


@Component({
  selector: 'app-cabinet-add',
  templateUrl: './cabinet-add.component.html',
  styleUrls: ['./cabinet-add.component.css']
})
export class CabinetAddComponent implements OnInit {
  cabinet: Cabinet = new Cabinet();
  dropdownSettings: IDropdownSettings = {};
  address: Address;
  myGroup: FormGroup;
  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';
  // preview photo
  fileData: File = null;
  previewUrl: any = null;
  uploadedFilePath: string = null;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private serviceCabinet: CabinetService,
  ) {
  }

  ngOnInit(): void {
    this.cabinet = new Cabinet();
    this.address = new Address();
    this.myGroup = new FormGroup({
      name: new FormControl(),
      description: new FormControl(),
      phone: new FormControl(),
      street: new FormControl(),
      city: new FormControl(),
      country: new FormControl(),
      number: new FormControl()
    });
  }

// tslint:disable-next-line:typedef
  getCabinet() {
    this.router.navigate(['cabinet']);
  }

// tslint:disable-next-line:typedef
  onSubmit() {
    this.cabinet.name = this.myGroup.get('name').value;
    this.cabinet.description = this.myGroup.get('description').value;
    const num: number = this.myGroup.get('phone').value;
    this.cabinet.phone = JSON.parse(String(num));
    this.address.street = this.myGroup.get('street').value;
    this.address.city = this.myGroup.get('city').value;
    this.address.country = this.myGroup.get('country').value;
    const numb: number = this.myGroup.get('number').value;
    this.address.number = JSON.parse(String(numb));
    this.cabinet.address = this.address;
    this.serviceCabinet.save(this.cabinet).subscribe(result => {
      if (this.previewUrl) {
        this.changePhoto();
      } else {
        this.getCabinet();
      }
    });
  }

  // tslint:disable-next-line:typedef
  changePhoto() {
    this.upload();
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
    const numb: string = this.myGroup.get('number').value;
    const country: string = this.myGroup.get('country').value;
    if (name !== '' && phone !== '' && description && street !== '' && city !== '' && numb !== '' && country !== '') {
      if (this.previewUrl) {
        return true;
      }
    }
    return false;
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
}
