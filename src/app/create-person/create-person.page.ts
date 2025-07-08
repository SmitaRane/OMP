import { Component, Input, ViewChild } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { IonContent, IonHeader, IonTitle, IonToolbar, IonToast } from '@ionic/angular/standalone';
import { PersonFormComponent } from '../person-form/person-form.component';
import { User } from '../person';
import { PersonService } from '../services/person.service';
import { ResponseDialogComponent } from '../response-dialog/response-dialog.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-create-person',
  templateUrl: './create-person.page.html',
  styleUrls: ['./create-person.page.scss'],
  standalone: true,
  imports: [ 
    IonContent,
    IonHeader,
    IonTitle,
    IonToolbar,
    CommonModule,
    FormsModule,
    PersonFormComponent,
    ResponseDialogComponent
  ]
})
export class CreatePersonPage {

  @Input() user: User = {
    firstname: '',
    lastname: '',
    primaryEmail: '',
    secondaryEmail: '',
    contact: '',
    education: '',
    password: ''
  };

  @ViewChild(PersonFormComponent) personForm!: PersonFormComponent;

  showDialog = false;
  dialogMessage = '';
  dialogSuccess = false;

  constructor(private personService: PersonService, private router: Router) {}

  onFormSubmit(user: User) {
    this.personService.createUser(user).subscribe({
      next: (res) => {
        this.dialogMessage = res.message || 'User registered successfully';
        this.dialogSuccess = true;
        this.showDialog = true;
      },
      error: (err) => {
        this.dialogMessage = err.error?.message || 'Registration failed';
        this.dialogSuccess = false;
        this.showDialog = true;
      }
    });
  }

  onDialogClose() {
    this.showDialog = false;
    if (this.dialogSuccess) {
      this.personForm.resetForm(); // Clear form on success
    }
  }
}
