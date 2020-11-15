import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MedicAddComponent } from './medic-add.component';

describe('MedicAddComponent', () => {
  let component: MedicAddComponent;
  let fixture: ComponentFixture<MedicAddComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MedicAddComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MedicAddComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
