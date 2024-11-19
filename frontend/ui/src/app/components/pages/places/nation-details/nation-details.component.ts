import {Component, OnInit, ViewChild} from '@angular/core';
import {
  faTrash,
  faEdit, faCircleArrowLeft,
} from '@fortawesome/free-solid-svg-icons';
import {NationDetailsService} from "../../../../services/models/places/nation-details/nation-details.service";
import {NationDetails} from "../../../../interfaces/models/places/nation-details";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-nation-details',
  templateUrl: './nation-details.component.html',
  styleUrl: './nation-details.component.css'
})
export class NationDetailsComponent implements OnInit {
  isEditing: boolean = false;
  isHistory: boolean = false;

  // tooltipVisible = false;
  // tooltipContent = '';
  //
  // @ViewChild('tooltip') tooltip!: TooltipComponent;

  languages: string[] = ['English', 'Spanish', 'French', 'German', 'Mandarin', 'Japanese', 'Arabic', 'English', 'Spanish', 'French', 'German', 'Mandarin', 'Japanese', 'Arabic']

  faTrash = faTrash;
  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;

  selectedNationDetails: NationDetails;
  nationName: string;

  constructor(private nationDetailsService: NationDetailsService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {}

  async ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => this.nationName = result.get("nationName")
    )
    await this.getNationDetailsByNationName(this.nationName);
  }

  async getNationDetailsByNationName(nationName: string) {
    const result = await this.nationDetailsService.getNationDetailsByNationName(nationName).toPromise();
    this.selectedNationDetails = result.data.datumRetrieved;
    console.log(this.selectedNationDetails);
  }

  deleteNationDetails(nationDetailsID: number) {
    this.nationDetailsService.deleteNationDetailsByID(nationDetailsID).subscribe((result) => {
      console.log(result);
      this.router.navigateByUrl("/nations");
    });
  }

  removeLanguage(index: number): void {
    this.languages.splice(index, 1);
  }

  // showTooltip(event: MouseEvent, content: string) {
  //   this.tooltipContent = content;
  //   this.tooltipVisible = true;
  //
  //   // Call setPosition on the tooltip component with cursor coordinates
  //   const x = event.clientX;
  //   const y = event.clientY + 10; // Offset to appear slightly below the cursor
  //   this.tooltip.setPosition(x, y);
  // }
  //
  // hideTooltip() {
  //   this.tooltipVisible = false;
  // }

  closeEditing(editable: boolean) {
    this.isEditing = editable;
  }

  closeHistoryEditing(editable: boolean) {
    this.isHistory = editable;
  }

  routeToNations() {
    this.router.navigate(['/nations']);
  }
}
