import { Component } from '@angular/core';

@Component({
  selector: 'app-trading-card',
  templateUrl: './trading-card.component.html',
  styleUrl: './trading-card.component.css'
})
export class TradingCardComponent {
  cardTitle = 'VK';
  cardImage = 'assets/images/vk.png';  // path to the image
  cardBackImage = 'assets/images/vk.png';
  cardDescription = 'A brave warrior skilled in sword fighting.';

  // Options: 'worse', 'bad', 'good', 'great'
  cardPowerLevel = 'worse';
  cardHealthLevel = 'bad';
  // cardPower = 8;
  // cardHealth = 12;

  isFlipped = false;

  flipCard() {
    this.isFlipped = !this.isFlipped;
  }
}
