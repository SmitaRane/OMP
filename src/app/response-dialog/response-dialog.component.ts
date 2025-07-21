import { Component, Input, Output, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { IonicModule } from '@ionic/angular';

@Component({
  selector: 'app-response-dialog',
  templateUrl: './response-dialog.component.html',
  styleUrls: ['./response-dialog.component.scss'],
  imports: [IonicModule]
})
export class ResponseDialogComponent {
  @Input() message: string = '';
  @Input() isSuccess: boolean = false;
  @Input() successRedirectRoute: string | null = null;

  @Output() closed = new EventEmitter<void>();

  constructor(private router: Router) {}

  onClose() {
    if (this.isSuccess && this.successRedirectRoute) {
      this.router.navigate([this.successRedirectRoute]);
    } else {
      this.closed.emit();
    }
  }
}
