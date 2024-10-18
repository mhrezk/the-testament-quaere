import {Component, OnInit} from '@angular/core';
import {DataState} from "../../../../enums/data-state";
import {faTrash, faEdit} from "@fortawesome/free-solid-svg-icons";
import {BehaviorSubject, map, Observable, of, startWith} from "rxjs";
import {AppState} from "../../../../interfaces/app-state";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {Gender} from "../../../../enums/gender";
import {Community} from "../../../../interfaces/models/society/family-node/community";
import {CommunityService} from "../../../../services/models/society/community/community.service";
import {catchError} from "rxjs/operators";
import {NgForm} from "@angular/forms";
import {Router} from "@angular/router";

@Component({
  selector: 'app-community',
  templateUrl: './community.component.html',
  styleUrl: './community.component.css'
})
export class CommunityComponent implements OnInit {
  currentPage: number  = 1;
  tableSize: number = 5;
  count: number = 0;
  tableSizes: number[] = [5, 10, 20];

  checkedCommunities: Community[];
  communities: Community[];
  selectedCommunity: Community;

  faTrash = faTrash;
  faEdit = faEdit;

  appState$: Observable<AppState<CustomResponse>>;

  readonly gender = Gender;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  countSubject = new BehaviorSubject<number>(0)
  count$ = this.countSubject.asObservable();

  isClicked: boolean = false;
  isUpdated: boolean = false;
  isEverythingShown: boolean = false;
  isDisplayed: boolean = false;
  isTableShown: boolean = false;
  isMasterSelected: boolean = false;

  headers = [
    {
      key: 'name',
      value: 'Name',
    }
  ];

  constructor(private communityService: CommunityService,
              private router: Router) {}

  ngOnInit() {
    this.getPaginatedCommunities(this.currentPage, this.tableSize);
    this.getAllCommunitiesTotal();
  }

  getAllCommunitiesTotal() {
    this.communityService.getAllCommunitiesCount().subscribe(
      result => {
        this.countSubject.next(result.data.datumRetrieved);
      }
    )
  }

  getPaginatedCommunities(pageNumber: number, pageSize: number) {
    this.isTableShown = true;
    this.appState$ = this.communityService.getPaginatedCommunities$(pageNumber, pageSize)
      .pipe(
        map((result) => {
          this.communities = result.data.dataRetrieved;
          this.dataSubject.next(result); //stores result in dataSubject to be used in another method or later
          this.isTableShown = true;
          return {
            dataState: DataState.LOADED,
            appData: result,
          };
        }),
        startWith({
          dataState: DataState.LOADING,
          // appData: null
        }),
        catchError((caughtError: string) => {
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      );
  }

  getCommunityByID(communityID: number) {
    this.communityService.getCommunityByID(communityID).subscribe(result => {
      this.selectedCommunity = result.data.datumRetrieved;
    });
  }

  saveCommunity(communityForm: NgForm) {
    this.isLoading.next(true);
    this.appState$ = this.communityService
      .saveCommunity$(communityForm.value) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next({ //this lists everything in ascending insertion order
            ...result,
            data: {
              dataRetrieved: [
                ...this.dataSubject.value.data.dataRetrieved, // Keep the existing entries
                result.data.dataSaved, // Add the new entry at the end
              ],
            },
          });
          this.communities.push(result.data.dataSaved);
          this.isClicked = false;
          this.isTableShown = true;
          this.isLoading.next(false);
          communityForm.resetForm();
          return {
            dataState: DataState.LOADED,
            appData: this.dataSubject.value,
          };
        }),
        startWith({
          dataState: DataState.LOADED,
          appData: this.dataSubject.value, //begin with pre-loaded data
        }),
        catchError((caughtError: string) => {
          this.isLoading.next(false);
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      );
    this.getAllCommunitiesTotal();
  }


  modifyCommunity(community: Community) {
    this.isLoading.next(true);
    this.appState$ = this.communityService.modifyCommunity$(community.id, community).pipe(
      map((result) => {
        const index = this.dataSubject.value.data.dataRetrieved.findIndex(person => person.id === result.data.dataUpdated.id);
        this.dataSubject.value.data.dataRetrieved[index] = result.data.dataUpdated;
        this.isTableShown = true;
        this.isLoading.next(false);
        return {
          dataState: DataState.LOADED,
          appData: this.dataSubject.value
        };
      }),
      startWith({
        dataState: DataState.LOADED,
        appData: this.dataSubject.value
      }),
      catchError((caughtError: string) => {
        return of({
          dataState: DataState.ERROR,
          error: caughtError,
        });
      })
    );
  }

  deleteCommunity(community: Community) {
    this.appState$ = this.communityService
      .deleteCommunity$(community.id) //or dayForm.value as Day or <Day> dayForm.value
      .pipe(
        map((result) => {
          this.dataSubject.next(
            {
              ...result,
              data: {
                dataRetrieved: this.dataSubject.value.data.dataRetrieved.filter((c) =>
                  c.id !== community.id //delete the record that matches d.id === day.id
                )
              }
            }
          );
          return {
            dataState: DataState.LOADED,
            appData: this.dataSubject.value,
          };
        }),
        startWith({
          dataState: DataState.LOADED,
          appData: this.dataSubject.value, //begin with pre-loaded data
        }),
        catchError((caughtError: string) => {
          return of({
            dataState: DataState.ERROR,
            error: caughtError,
          });
        })
      );
    this.getAllCommunitiesTotal();
  }

  deleteCommunities(communities: Community[]) {
    for(let community of communities) {
      this.communityService.deleteCommunity(community.id).subscribe();
    }
    window.location.reload();
  }

  onTableDataChange(event: any) {
    this.currentPage = event;
    this.getPaginatedCommunities(this.currentPage, this.tableSize);
  }

  onTableSizeChange(event: any): void {
    this.tableSize = event.target.value;
    this.currentPage = 1;
    this.getPaginatedCommunities(this.currentPage, this.tableSize);
  }

  checkUncheckAll() {
    for(let community of this.communities) {
      community.isSelected = this.isMasterSelected;
    }
    this.getCheckedPeople();
  }

  isAllSelected() {
    this.isMasterSelected = this.communities.every(community => community.isSelected);
    this.getCheckedPeople();
  }

  getCheckedPeople() {
    this.checkedCommunities = [];
    for(let checkedCommunity of this.communities) {
      if(checkedCommunity.isSelected) {
        this.checkedCommunities.push(checkedCommunity);
      }
    }
  }

  hasSelected() {
    return this.communities.some(community => community.isSelected);
  }

  routeToCommunityDescription(communityID: number, communityName: string) {
    this.communityService.setCommunityID(communityID);
    this.communityService.setCommunityName(communityName);
    this.router.navigateByUrl(`/communities/${communityID}/${communityName}`);
  }
}
