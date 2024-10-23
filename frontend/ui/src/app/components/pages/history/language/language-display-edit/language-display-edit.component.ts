import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {LanguageService} from "../../../../../services/models/history/language/language.service";
import {ActivatedRoute, Router} from "@angular/router";
import {BehaviorSubject} from "rxjs";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {Language} from "../../../../../interfaces/models/history/language";
import {Race} from "../../../../../interfaces/models/history/race";

@Component({
  selector: 'app-language-display-edit',
  templateUrl: './language-display-edit.component.html',
  styleUrl: './language-display-edit.component.css'
})
export class LanguageDisplayEditComponent implements OnInit {
  @Output() isEditing = new EventEmitter<boolean>();

  languageID: string;
  languageName: string;

  language = new BehaviorSubject<CustomResponse>(null);

  selectedLanguage: Language;
  modifiedLanguage: Language;

  constructor(private languageService: LanguageService,
              private router: Router,
              private activatedRoute: ActivatedRoute,) {}

  async ngOnInit() {
    this.activatedRoute.paramMap.subscribe(
      result => this.languageID = result.get("id")
    );

    this.activatedRoute.paramMap.subscribe(
      result => this.languageName = result.get("name")
    );

    console.log(+this.languageID);
    console.log(this.languageName);

    await this.getLanguageByID(+this.languageID);
  }

  async getLanguageByID(languageID: number) {
    const result = await this.languageService.getLanguageByID(languageID).toPromise();
    this.language.next(result.data.datumRetrieved);
    console.log(result);
    console.log(this.language);
    this.selectedLanguage = result.data.datumRetrieved;
  }

  closeEditing(doneEditing: boolean) {
    console.log(doneEditing);
    this.isEditing.emit(doneEditing);
    this.router.navigateByUrl(`/languages/${this.languageID}/${this.languageName}`);
  }

  async onSubmit(language: Language) {
    const result = await this.languageService.modifyLanguage(+this.languageID, language).toPromise();
    this.modifiedLanguage = result.data.dataUpdated;
    this.closeEditing(false);
    this.router.navigateByUrl(`/languages/${language.id}/${this.modifiedLanguage.name}`);
  }
}
