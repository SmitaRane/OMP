import { CommonModule } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';
import { User } from '../person';
import { PersonFormComponent } from '../person-form/person-form.component';
import { ResponseDialogComponent } from '../response-dialog/response-dialog.component';
import { PersonService } from '../services/person.service';

@Component({
  selector: 'app-person-dashboard',
  templateUrl: './person-dashboard.page.html',
  styleUrls: ['./person-dashboard.page.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, RouterModule, PersonFormComponent, ResponseDialogComponent],
})
export class PersonDashboardPage implements OnInit {

 @ViewChild(PersonFormComponent) personForm!: PersonFormComponent;

  user!: User;

  dialogOpen = false;
  dialogMessage = '';
  dialogSuccess = false;

  constructor(private personService: PersonService, private router: Router) {}

  ngOnInit(): void {
    const nav = this.router.getCurrentNavigation();
    this.user = nav?.extras?.state?.['user'] || null;
  }

  onUpdate(userData: User) {
    this.personService.updateUser(userData).subscribe({
      next: (res) => {
        this.dialogMessage = res.message || 'User updated successfully';
        this.dialogSuccess = true;
        this.dialogOpen = true;
      },
      error: (err) => {
        this.dialogMessage = err.error?.message || 'Failed to update user';
        this.dialogSuccess = false;
        this.dialogOpen = true;
      }
    });
  }

  onDialogClose() {
    this.dialogOpen = false;
    if (this.dialogSuccess) {
      this.personForm.resetForm(); // Clear form on success
    }
  }
}
