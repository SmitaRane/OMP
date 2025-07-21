import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { Router } from '@angular/router';
import { User } from '../person';
import { ResponseDialogComponent } from '../response-dialog/response-dialog.component';
import { PersonService } from '../services/person.service';

@Component({
  selector: 'app-person',
  templateUrl: './person.page.html',
  styleUrls: ['./person.page.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule, ResponseDialogComponent]
})
export class PersonPage implements OnInit {

  user: User = {
    firstname: '',
    lastname: '',
    primaryEmail: '',
    secondaryEmail: '',
    contact: '',
    education: '',
    password: ''
  };

  isUpdateMode = false;
  buttonLabel = '';
  dialogOpen = false;
  dialogMessage = '';
  dialogSuccess = false;

  constructor(private router: Router, private personService: PersonService) {}

  ngOnInit() {
    const nav = this.router.getCurrentNavigation();
    const stateUser = nav?.extras?.state?.['user'];

    if (stateUser) {
      this.user = { ...stateUser }; // Pre-fill form
      this.isUpdateMode = true;
      this.buttonLabel = 'Update';
    } else {
      this.isUpdateMode = false;
      this.buttonLabel = 'Submit';
    }
  }

  submitForm() {
    if (this.isUpdateMode) {
      this.personService.updateUser(this.user).subscribe({
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
    } else {
      this.personService.createUser(this.user).subscribe({
        next: (res) => {
          this.dialogMessage = res.message || 'User registered successfully';
          this.dialogSuccess = true;
          this.dialogOpen = true;
          this.resetForm();
        },
        error: (err) => {
          this.dialogMessage = err.error?.message || 'Registration failed';
          this.dialogSuccess = false;
          this.dialogOpen = true;
        }
      });
    }
  }

  resetForm() {
    this.user = {
      firstname: '',
      lastname: '',
      primaryEmail: '',
      secondaryEmail: '',
      contact: '',
      education: '',
      password: ''
    };
  }
}
