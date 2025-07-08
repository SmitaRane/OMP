import { CommonModule } from '@angular/common';
import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { FormsModule, NgForm } from '@angular/forms';
import { IonicModule } from '@ionic/angular';
import { User } from '../person';

@Component({
  selector: 'app-person-form',
  templateUrl: './person-form.component.html',
  styleUrls: ['./person-form.component.scss'],
  imports: [IonicModule, CommonModule, FormsModule]
})
export class PersonFormComponent{

  @Input() user: User = {
    firstname: '',
    lastname: '',
    primaryEmail: '',
    secondaryEmail: '',
    contact: '',
    education: '',
    password: ''
  };

  @Input() buttonLabel: string = 'Submit';
  @Input() showPassword: boolean = true;
  @Input() isUpdateMode: boolean = false;
  @Input() secondButtonLable: string = '';

  @Output() formSubmit = new EventEmitter<User>();

  @ViewChild('formRef') formRef!: NgForm;

  resetForm() {
    if (this.formRef) {
      this.formRef.resetForm();
    }
  }

  emitForm() {
    this.formSubmit.emit(this.user);
  }

  constructor() { }

}
