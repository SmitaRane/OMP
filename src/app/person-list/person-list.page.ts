import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonService } from '../services/person.service';
import { User } from '../person';
import { ApiResponse } from '../response.object';
import { IonicModule } from '@ionic/angular';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ResponseDialogComponent } from '../response-dialog/response-dialog.component';

@Component({
  selector: 'app-person-list',
  templateUrl: './person-list.page.html',
  styleUrls: ['./person-list.page.scss'],
  imports: [IonicModule, CommonModule, FormsModule, ResponseDialogComponent],
})
export class PersonListPage implements OnInit {
  persons: User[] = [];
  isLoading = true;

  // Dialog state
  showDialog = false;
  dialogMessage = '';
  dialogSuccess = false;

  constructor(private personService: PersonService, private router: Router) { }

  ngOnInit() {
    this.fetchPersons();
  }

  fetchPersons() {
    this.isLoading = true;
    this.personService.getAllEmployees().subscribe({
      next: (res: ApiResponse<User[]>) => {
        this.persons = res.data || [];
        this.isLoading = false;
      },
      error: () => {
        this.persons = [];
        this.isLoading = false;
      },
    });
  }

  editPerson(person: User) {
    this.router.navigate(['/person'], { state: { user: person } });
  }

  deletePerson(person: User) {
    if (person.uuid && confirm(`Are you sure you want to delete ${person.firstname}?`)) {
      this.personService.deleteUser(person.uuid).subscribe({
        next: (res: ApiResponse<null>) => {
          this.dialogMessage = res.message; // ✅ message from API
          this.dialogSuccess = res.success;
          this.showDialog = true;
        },
        error: (err) => {
          this.dialogMessage = err.error?.message || 'An error occurred';
          this.dialogSuccess = false;
          this.showDialog = true;
        }
      });
    }
  }

  onDialogClose() {
    this.showDialog = false;
    if (this.dialogSuccess) {
      this.fetchPersons(); // Refresh the list after successful deletion
    }
  }
}
