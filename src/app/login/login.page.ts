import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { IonicModule } from '@ionic/angular';
import { PersonService } from '../services/person.service';
import { ResponseDialogComponent } from '../response-dialog/response-dialog.component';

@Component({
  selector: 'app-home',
  templateUrl: 'login.page.html',
  styleUrls: ['login.page.scss'],
  standalone: true,
  imports: [IonicModule, CommonModule, FormsModule, RouterModule, ResponseDialogComponent],
})


export class LoginPage {

  showPassword = false;

  credentials = {
    primaryEmail: '',
    password: ''
  };

  user: any;
  showDialog = false;
  dialogMessage = '';
  dialogSuccess = false;

  constructor(private personService: PersonService, private router: Router) {}

  login() {
    this.personService.login(this.credentials).subscribe({
      next: (res) => {
        this.user = res.data;
        this.dialogMessage = res.message || 'Login successful';
        this.dialogSuccess = true;
        this.showDialog = true;
      },
      error: (err) => {
        this.dialogMessage = err.error?.message || 'Invalid credentials';
        this.dialogSuccess = false;
        this.showDialog = true;
      }
    });
  }

  onDialogClose() {
    this.showDialog = false;

    if (this.dialogSuccess) {
      this.router.navigate(['/person-dashboard'], {
        state: { user: this.user }
      });
    }
  }
}

