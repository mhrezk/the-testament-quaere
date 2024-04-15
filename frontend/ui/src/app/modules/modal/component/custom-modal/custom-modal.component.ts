import {
  Component,
  ElementRef,
  EventEmitter,
  Input,
  Output,
} from '@angular/core';

@Component({
  selector: 'app-custom-modal',
  templateUrl: './custom-modal.component.html',
  styleUrl: './custom-modal.component.css'
})
export class CustomModalComponent {
  @Input() size? = 'md';
  @Input() title? = 'Modal title';

  @Output() closeEvent = new EventEmitter();
  @Output() submitEvent = new EventEmitter();

  constructor(private elementRef: ElementRef) {}

  close(): void {
    this.elementRef.nativeElement.remove();
    this.closeEvent.emit();
  }

  submit(): void {
    this.elementRef.nativeElement.remove();
    this.submitEvent.emit();
  }
}
