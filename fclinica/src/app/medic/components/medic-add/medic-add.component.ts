import {Component, OnInit} from '@angular/core';
import {Medic} from '../../model/medic';
import {ActivatedRoute, Router} from '@angular/router';
import {MedicService} from '../../service/medic.service';
import {FormControl, FormGroup} from '@angular/forms';
import {Cabinet} from '../../../cabinet/model/cabinet';
import {CabinetService} from '../../../cabinet/service/cabinet.service';
import {IDropdownSettings} from 'ng-multiselect-dropdown';
import {HttpEventType, HttpResponse} from '@angular/common/http';

@Component({
  selector: 'app-medic-add',
  templateUrl: './medic-add.component.html',
  styleUrls: ['./medic-add.component.css']
})
export class MedicAddComponent implements OnInit {
  medic: Medic = new Medic();
  myGroup: FormGroup;
  cabinet: Cabinet [] = [];
  selectedCabinet: Cabinet [] = [];
  dropdownSettings: IDropdownSettings = {};
  specializationModel: string [] = [];
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
              private  medicService: MedicService,
              private cabinetService: CabinetService) {
  }

  ngOnInit(): void {
    this.medic = new Medic();
    this.myGroup = new FormGroup({
      name: new FormControl(),
      phone: new FormControl(),
      selectedCabinet: new FormControl(),
      specializationModel: new FormControl()
    });
    this.specializationModel.push('cardiology');
    this.specializationModel.push('surgery');
    this.cabinetService.findAll().subscribe(data => {
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


  formCompleted(): boolean {
    const name: string = this.myGroup.get('name').value;
    const phone: string = this.myGroup.get('phone').value;
    const specializationModel: string = this.myGroup.get('specializationModel').value;
    const selectedCabinet: string = this.myGroup.get('selectedCabinet').value;
    if (name !== '' && phone !== '' && selectedCabinet !== '' && specializationModel !== '') {
      if (this.previewUrl) {
        return true;
      }
    }
    return false;
  }


  // tslint:disable-next-line:typedef
  changePhoto() {
    this.upload();
    setTimeout(() => {
        this.getMedicList();
      },
      4000);
  }

  // tslint:disable-next-line:typedef
  onSubmit() {
    this.medic.name = this.myGroup.get('name').value;
    const num: number = this.myGroup.get('phone').value;
    this.medic.phone = JSON.parse(String(num));
    this.medic.specializationModel = this.myGroup.get('specializationModel').value;
    this.selectedCabinet = this.myGroup.get('selectedCabinet').value;
    this.medic.cabinet = this.selectedCabinet[0];
    this.medicService.save(this.medic).subscribe(result => {
      if (this.previewUrl) {
        this.changePhoto();
      } else {
        this.getMedicList();
      }
    });
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
    this.medicService.upload(this.currentFile).subscribe(
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
