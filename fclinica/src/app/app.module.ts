import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {CabinetListComponent} from './cabinet/components/cabinet-list/cabinet-list.component';
import {CabinetAddComponent} from './cabinet/components/cabinet-add/cabinet-add.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MenuAppComponent} from './commom/menu-app/menu-app.component';
import {CabinetEditComponent} from './cabinet/components/cabinet-edit/cabinet-edit.component';
import {Ng2SearchPipeModule} from 'ng2-search-filter';
import {NgMultiSelectDropDownModule} from 'ng-multiselect-dropdown';
import { MedicAddComponent } from './medic/components/medic-add/medic-add.component';
import { MedicListComponent } from './medic/components/medic-list/medic-list.component';
import { MedicEditComponent } from './medic/components/medic-edit/medic-edit.component';
import { MedicReserveComponent } from './medic/components/medic-reserve/medic-reserve.component';
import {NgxPaginationModule} from 'ngx-pagination';
import { AboutListComponent } from './cabinet/components/about-list/about-list.component';
import { MedicByCabinetComponent } from './medic/components/medic-by-cabinet/medic-by-cabinet.component';


@NgModule({
  declarations: [
    AppComponent,
    CabinetListComponent,
    CabinetAddComponent,
    MenuAppComponent,
    CabinetEditComponent,
    MedicAddComponent,
    MedicListComponent,
    MedicEditComponent,
    MedicReserveComponent,
    AboutListComponent,
    MedicByCabinetComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    NgbModule,
    BrowserAnimationsModule,
    FormsModule,
    Ng2SearchPipeModule,
    ReactiveFormsModule,
    NgMultiSelectDropDownModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
