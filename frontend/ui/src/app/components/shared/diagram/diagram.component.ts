import { Component } from '@angular/core';
import { FruitNode } from '../../../interfaces/fruits/fruit-node';
import { FruitConnection } from '../../../interfaces/fruits/fruit-connection';

@Component({
  selector: 'app-diagram',
  templateUrl: './diagram.component.html',
  styleUrls: ['./diagram.component.css']
})
export class DiagramComponent {
  fruitNodes: FruitNode[] = [
    { id: 'summer-banana', name: 'Summer Banana', imageUrl: '../../../../assets/images/anvil.png', description: 'Central fruit', color: '#FFEB3B' },
    { id: 'star-cassava', name: 'Star Cassava', imageUrl: '../../../../assets/images/anvil.png', description: '', color: '#FF9800' },
    { id: 'fire-collard', name: 'Fire Collard', imageUrl: '../../../../assets/images/anvil.png', description: '', color: '#F44336' },
    { id: 'void-lime', name: 'Void Lime', imageUrl: '../../../../assets/images/anvil.png', description: '', color: '#8BC34A' },
    { id: 'thunder-basil', name: 'Thunder Basil', imageUrl: '../../../../assets/images/anvil.png', description: '', color: '#3F51B5' },
    { id: 'drake-mango', name: 'Drake Mango', imageUrl: '../../../../assets/images/anvil.png', description: '', color: '#FF5722' }
  ];

  fruitConnections: FruitConnection[] = [
    { from: 'summer-banana', to: 'star-cassava', color: 'green' },
    { from: 'summer-banana', to: 'fire-collard', color: 'red' },
    { from: 'summer-banana', to: 'void-lime', color: 'yellow' },
    { from: 'summer-banana', to: 'thunder-basil', color: 'blue' },
    { from: 'summer-banana', to: 'drake-mango', color: 'orange' }
  ];

  getNodeX(nodeId: string): number {
    // Logic to retrieve the X position of the node by its ID
    const index = this.fruitNodes.findIndex(node => node.id === nodeId);
    return index * 100 + 50; // Example positioning logic
  }

  getNodeY(nodeId: string): number {
    // Logic to retrieve the Y position of the node by its ID
    const index = this.fruitNodes.findIndex(node => node.id === nodeId);
    return index < 2 ? 100 : 300; // Example positioning
  }
}
