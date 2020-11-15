import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicReserveComponent } from './medic-reserve.component';

describe('MedicReserveComponent', () => {
  let component: MedicReserveComponent;
  let fixture: ComponentFixture<MedicReserveComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicReserveComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicReserveComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
