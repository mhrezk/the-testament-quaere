import {Component, Input} from '@angular/core';

@Component({
  selector: 'app-tooltip',
  templateUrl: './tooltip.component.html',
  styleUrl: './tooltip.component.css'
})
export class TooltipComponent {
  @Input() content: string = '';
  top: number = 0;
  left: number = 0;

  setPosition(x: number, y: number) {
    this.top = y;
    this.left = x;
  }
}
