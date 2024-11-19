import {Component, computed, Input, signal} from '@angular/core';
import {CdkDragDrop, moveItemInArray} from "@angular/cdk/drag-drop";

export type MenuItem = {
  icon: string;
  label: string;
  route?: string;
}
@Component({
  selector: 'app-custom-sidenav',
  templateUrl: './custom-sidenav.component.html',
  styleUrl: './custom-sidenav.component.css'
})
export class CustomSidenavComponent {
  menuItems = signal<MenuItem[]>([
    {
      icon: "public",
      label: "World Map",
      route: "home"
    },
    {
      icon: "timeline",
      label: "Timelines",
      route: "timelines"
    },
    {
      icon: "language",
      label: "Languages",
      route: "languages"
    },
    {
      icon: "calendar_month",
      label: "Calendar",
      route: "calendar"
    },
    {
      icon: "flag",
      label: "Nations",
      route: "nations"
    },
    {
      icon: "diversity_2",
      label: "Religions",
      route: "religions"
    },
    {
      icon: "people",
      label: "People",
      route: "people"
    },
    {
      icon: "diversity_1",
      label: "Races",
      route: "races"
    },
    {
      icon: "workspaces",
      label: "Organizations",
      route: "organizations"
    },
    {
      icon: "diversity_3",
      label: "Communities",
      route: "communities"
    },
    {
      icon: "books",
      label: "Library",
      route: "library"
    },
    {
      icon: "account_tree",
      label: "Hierarchy",
      route: "hierarchy"
    },
    {
      icon: "account_tree",
      label: "Custom Family Tree",
      route: "custom-family-tree-balkan"
    }
  ]);

  sidenavCollapsed = signal(false);
  @Input() set collapsed(value: boolean) {
    this.sidenavCollapsed.set(value);
  };

  profilePictureSize = computed(() => this.sidenavCollapsed() ? '32' : '100');

  // Method to handle the rearranging of menu items
  drop(event: CdkDragDrop<MenuItem[]>) {
    const currentItems = this.menuItems();
    moveItemInArray(currentItems, event.previousIndex, event.currentIndex);
    this.menuItems.set([...currentItems]); // Update the signal with the new order
  }
}

