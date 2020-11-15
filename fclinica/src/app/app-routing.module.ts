import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CabinetListComponent} from './cabinet/components/cabinet-list/cabinet-list.component';
import {CabinetAddComponent} from './cabinet/components/cabinet-add/cabinet-add.component';
import {CabinetEditComponent} from './cabinet/components/cabinet-edit/cabinet-edit.component';
import {MedicListComponent} from './medic/components/medic-list/medic-list.component';
import {MedicAddComponent} from './medic/components/medic-add/medic-add.component';
import {MedicEditComponent} from './medic/components/medic-edit/medic-edit.component';
import {Reservation} from './medic/model/reservation';
import {MedicReserveComponent} from './medic/components/medic-reserve/medic-reserve.component';
import {AboutListComponent} from './cabinet/components/about-list/about-list.component';
import {MedicByCabinetComponent} from './medic/components/medic-by-cabinet/medic-by-cabinet.component';


const routes: Routes = [{path: 'cabinet', component: CabinetListComponent},
  {path: 'addCabinet', component: CabinetAddComponent},
  {path: 'editCabinet/:id', component: CabinetEditComponent},
  {path: 'medic', component: MedicListComponent},
  {path: 'addMedic', component: MedicAddComponent},
  {path: 'editMedic/:id', component: MedicEditComponent},
  {path: 'reservation/:id', component: MedicReserveComponent},
  {path: 'about', component: AboutListComponent},
  {path: 'view/:id', component: MedicByCabinetComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
