import {Component, OnInit} from '@angular/core';
import {Medic} from '../../model/medic';
import {ActivatedRoute, Router} from '@angular/router';
import {CabinetService} from '../../../cabinet/service/cabinet.service';
import {MedicService} from '../../service/medic.service';
import {IDropdownSettings} from 'ng-multiselect-dropdown';
import {Cabinet} from '../../../cabinet/model/cabinet';
import {FormGroup} from '@angular/forms';
import {HttpEventType, HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-medic-edit',
  templateUrl: './medic-edit.component.html',
  styleUrls: ['./medic-edit.component.css']
})
export class MedicEditComponent implements OnInit {
  medic: Medic = new Medic();
  id: number;
  dropdownSettings: IDropdownSettings = {};
  cabinet: Cabinet [] ;
  selectedCabinet: Cabinet [] = [];
  selectedFiles: FileList;
  currentFile: File;
  progress = 0;
  message = '';
  fileData: File = null;
  previewUrl: any = null;
  uploadedFilePath: string = null;
  myGroup: FormGroup;

  constructor(private route: ActivatedRoute,
              private router: Router,
              private serviceMedic: MedicService,
              private serviceCabinet: CabinetService) {
  }

  ngOnInit(): void {
    this.cabinet = [];
    this.selectedCabinet = [];
    this.id = this.route.snapshot.params.id;
    this.serviceMedic.getById(this.id).subscribe(data => {
      this.medic = new Medic();
      this.selectedCabinet = [];
      this.medic = data;
      this.medic.photo = this.getPhotoById(this.medic.id);
      this.selectedCabinet.push(this.medic.cabinet);
    });
    this.serviceCabinet.findAll().subscribe(data => {
      this.cabinet = [];
      this.cabinet = data;
    });
    this.dropdownSettings = {
      singleSelection: true,
      idField: 'id',
      textField: 'name',
      selectAllText: 'Select All',
      unSelectAllText: 'UnSelect All',
      itemsShowLimit: 3,
      allowSearchFilter: true
    };
  }

// tslint:disable-next-line:typedef
  getMedicList() {
    this.router.navigate(['medic']);
  }

  // tslint:disable-next-line:typedef
  getPhotoById(id: number) {
    return this.serviceMedic.getPhotos(id);
  }

  // tslint:disable-next-line:typedef
  onSubmit() {
    this.medic.cabinet = this.selectedCabinet[0];
    console.log(this.medic);
    this.serviceMedic.update(this.medic).subscribe(result => this.getMedicList());
  }

  // tslint:disable-next-line:typedef
  onItemSelect(item: any) {
    console.log(item);
  }

  // tslint:disable-next-line:typedef
  onSelectAll(items: any) {
    console.log(items);
  }

  // tslint:disable-next-line:typedef
  upload() {
    this.progress = 0;
    this.currentFile = this.selectedFiles.item(0);
    this.serviceMedic.upload(this.currentFile).subscribe(
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
    this.serviceMedic.deletePhoto(this.medic.idPhoto).subscribe(data => {
      this.upload();
    });
    setTimeout(() => {
        this.getMedicList();
      },
      4000);
  }

}
