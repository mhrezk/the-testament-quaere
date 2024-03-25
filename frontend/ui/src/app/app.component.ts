import {Component, computed, signal} from '@angular/core';
import {EditorConfig, ST_BUTTONS, UNDO_BUTTON, SEPARATOR, BOLD_BUTTON, ITALIC_BUTTON} from 'ngx-simple-text-editor';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  //title = 'ui';
  // content = '';
  // config: EditorConfig = {
  //   placeholder: 'Type something...',
  //   buttons: ST_BUTTONS,
  // };
  //You can pass all predefined buttons with predefined order, or you can use only buttons you want with order as you want.
  // config: EditorConfig = {
  //   buttons: [UNDO_BUTTON, SEPARATOR, BOLD_BUTTON, ITALIC_BUTTON],
  // };

  collapsed = signal(false);

  sidenavWidth = computed(() => this.collapsed() ? '65px' : '250px');

}
