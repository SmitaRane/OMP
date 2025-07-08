import { ComponentFixture, TestBed } from '@angular/core/testing';
import { PersonDashboardPage } from './person-dashboard.page';

describe('PersonDashboardPage', () => {
  let component: PersonDashboardPage;
  let fixture: ComponentFixture<PersonDashboardPage>;

  beforeEach(() => {
    fixture = TestBed.createComponent(PersonDashboardPage);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
