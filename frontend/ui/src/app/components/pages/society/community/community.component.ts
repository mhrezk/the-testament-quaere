import { Component } from '@angular/core';
import {DataState} from "../../../../enums/data-state";
import {faTrash} from "@fortawesome/free-solid-svg-icons";
import {BehaviorSubject, Observable} from "rxjs";
import {AppState} from "../../../../interfaces/app-state";
import {CustomResponse} from "../../../../interfaces/custom-response";
import {Gender} from "../../../../enums/gender";

@Component({
  selector: 'app-community',
  templateUrl: './community.component.html',
  styleUrl: './community.component.css'
})
export class CommunityComponent {
  currentPage: number  = 1;
  tableSize: number = 5;
  count: number = 0;
  tableSizes: number[] = [5, 10, 20];

  appState$: Observable<AppState<CustomResponse>>;

  readonly gender = Gender;
  protected readonly DATA_STATE = DataState;
  private dataSubject = new BehaviorSubject<CustomResponse>(null);
  private isLoading = new BehaviorSubject<boolean>(false);
  isLoading$ = this.isLoading.asObservable();

  isClicked: boolean = false;
  isTableShown: boolean = false;
  isMasterSelected: boolean = false;

  headers = [
    {
      key: 'name',
      value: 'Name',
    },
    {
      key: 'description',
      value: 'Description',
    }
  ];
}
