import {Component, Input} from '@angular/core';
import {FruitNode} from "../../../interfaces/fruits/fruit-node";

@Component({
  selector: 'app-fruit',
  templateUrl: './fruit.component.html',
  styleUrl: './fruit.component.css'
})
export class FruitComponent {
  @Input() node!: FruitNode;
}
