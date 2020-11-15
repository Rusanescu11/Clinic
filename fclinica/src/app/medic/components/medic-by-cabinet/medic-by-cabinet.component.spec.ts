import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicByCabinetComponent } from './medic-by-cabinet.component';

describe('MedicByCabinetComponent', () => {
  let component: MedicByCabinetComponent;
  let fixture: ComponentFixture<MedicByCabinetComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicByCabinetComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicByCabinetComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
