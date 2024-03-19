import {Component, EventEmitter, Input, Output} from '@angular/core';

@Component({
  selector: 'app-custom-header',
  templateUrl: './custom-header.component.html',
  styleUrl: './custom-header.component.css'
})
export class CustomHeaderComponent {
  @Input() sidebarActive = false;
  @Output() toggleSidebar = new EventEmitter();
}
