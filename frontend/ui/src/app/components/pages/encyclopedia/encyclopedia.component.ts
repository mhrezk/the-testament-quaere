import { Component } from '@angular/core';

@Component({
  selector: 'app-encyclopedia',
  templateUrl: './encyclopedia.component.html',
  styleUrl: './encyclopedia.component.css'
})
export class EncyclopediaComponent {
  routes = [
    { path: 'angels', label: 'Angels' },
    { path: 'demons', label: 'Demons' },
    { path: 'fae', label: 'Fae' },
    { path: 'minerals', label: 'Minerals' },
    { path: 'materials', label: 'Materials' },
    { path: 'animals', label: 'Animals' },
    { path: 'plants', label: 'Plants' },
    { path: 'bacteria', label: 'Bacteria' },
    { path: 'fungi', label: 'Fungi' },
    { path: 'protozoa', label: 'Protozoa' },
    { path: 'viruses', label: 'Viruses' },
  ].sort((a, b) => a.label.localeCompare(b.label)); // Sort alphabetically by label;
}
