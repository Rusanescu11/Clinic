import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CabinetEditComponent } from './cabinet-edit.component';

describe('CabinetEditComponent', () => {
  let component: CabinetEditComponent;
  let fixture: ComponentFixture<CabinetEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CabinetEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CabinetEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
