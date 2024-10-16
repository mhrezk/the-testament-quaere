import {Component, computed, Input, signal} from '@angular/core';

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
      label: "Family Trees",
      route: "family-tree-balkan"
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
}

