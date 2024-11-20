import {Component, OnInit} from '@angular/core';
import {
  faTrash,
  faEdit, faCircleArrowLeft,
} from '@fortawesome/free-solid-svg-icons';
import {Religion} from "../../../../../interfaces/models/dogma/religion";
import {ReligionService} from "../../../../../services/models/dogma/religion/religion.service";
import {ActivatedRoute, Router} from "@angular/router";
import {NationService} from "../../../../../services/models/places/nation/nation.service";
import {map, Observable, of, startWith} from "rxjs";
import {catchError} from "rxjs/operators";
import {AppState} from "../../../../../interfaces/app-state";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {DataState} from "../../../../../enums/data-state";

@Component({
  selector: 'app-religion-display',
  templateUrl: './religion-display.component.html',
  styleUrl: './religion-display.component.css'
})
export class ReligionDisplayComponent implements OnInit {
  isEditing: boolean = false;
  isDescription: boolean = false;

  imageWidth: number | null = null;
  imageHeight: number | null = null;

  appState$: Observable<AppState<CustomResponse>>;
  protected readonly DATA_STATE = DataState;

  faTrash = faTrash;
  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;

  selectedReligion: Religion;
  religionID: string;
  religionName: string;

  constructor(private religionService: ReligionService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {
  }

  async ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => {
        this.religionID = result.get("id");
        this.religionName = result.get("name");
      }
    )
    await this.getReligionByID(+this.religionID);
  }

  async getReligionByID(religionID: number) {
    const result = await this.religionService.getReligionByID(religionID).toPromise();
    this.selectedReligion = result.data.datumRetrieved;
    this.getImageDimensions(this.selectedReligion.symbolURL);
    console.log(this.selectedReligion);
  }

  getImageDimensions(url: string): void {
    const img = new Image();
    img.src = url;
    img.onload = () => {
      this.imageWidth = img.width;
      this.imageHeight = img.height;
      console.log(`Width: ${this.imageWidth}, Height: ${this.imageHeight}`);
    };
    img.onerror = () => {
      console.error('Failed to load image.');
    };
  }

  deleteReligion(religionID: number) {
    this.religionService.deleteReligion$(religionID).subscribe((result) => {
      console.log(result);
      this.router.navigateByUrl("/religions");
    });
  }

  removeNation(nationName: string, religion: Religion): void {
    console.log(`Attempting to remove nation ${nationName} from religion ${religion.id}`);

    // Send the request to delete the nation from the religion
    this.religionService
      .deleteNationFromReligion$(religion.id, nationName)
      .pipe(
        map((response) => {
          console.log('Nation deleted:', response);

          // Update the religion's nations array locally after deletion
          religion.nations = religion.nations.filter(nation => nation !== nationName);

          // Return the updated state (if needed for state management)
          return {
            dataState: DataState.LOADED,
            appData: {
              ...response, // Or any other data you need
              dataUpdated: religion,
            },
          };
        }),
        startWith({
          dataState: DataState.LOADED,
          appData: religion, // Initial data state before operation
        }),
        catchError((caughtError: string) => {
          console.error('Error deleting nation:', caughtError);
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      )
      .subscribe(
        (state) => {
          // Handle the new state if you are using a store, subject, or state management
          console.log('State after nation removal:', state);
        }
      );
  }

  // removeNation(nationName: string, religion: Religion): void {
  //   // Step 1: Remove the nation from the religion
  //   this.religionService.modifyReligion$(religion.id, religion).subscribe({
  //     next: (updatedReligion) => {
  //       // Step 2: Delete the nation only after updating the religion
  //       this.nationService.deleteNationByName$(nationName).subscribe({
  //         next: () => console.log(`${nationName} successfully deleted`),
  //         error: (err) => console.error(`Error deleting nation: ${err}`)
  //       });
  //     },
  //     error: (err) => console.error(`Error updating religion: ${err}`)
  //   });
  // }

  closeEditing(editable: boolean) {
    this.isEditing = editable;
  }

  closeDescriptionEditing(editable: boolean) {
    this.isDescription = editable;
  }

  routeToReligions() {
    this.router.navigate(['/religions']);
  }

  routeToProphets(religionID: number, religionName: string) {
    this.router.navigate(['/religions', religionID, religionName, '/prophets']);
  }
}
