import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicEditComponent } from './medic-edit.component';

describe('MedicEditComponent', () => {
  let component: MedicEditComponent;
  let fixture: ComponentFixture<MedicEditComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicEditComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicEditComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
