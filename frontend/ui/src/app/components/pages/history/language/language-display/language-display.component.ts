import {Component, OnInit} from '@angular/core';
import {faEdit, faCircleArrowLeft} from "@fortawesome/free-solid-svg-icons";
import {BehaviorSubject} from "rxjs";
import {CustomResponse} from "../../../../../interfaces/custom-response";
import {EditorConfig, ST_BUTTONS} from "ngx-simple-text-editor";
import {LanguageService} from "../../../../../services/models/history/language/language.service";
import {Router} from "@angular/router";
import {Language} from "../../../../../interfaces/models/history/language";

@Component({
  selector: 'app-language-display',
  templateUrl: './language-display.component.html',
  styleUrl: './language-display.component.css'
})
export class LanguageDisplayComponent implements OnInit {
  isEditing: boolean = false;
  isDescription: boolean = false;

  languageID: number;
  languageName: string;

  language = new BehaviorSubject<CustomResponse>(null);

  selectedLanguage: Language;

  config: EditorConfig = {
    placeholder: 'Type something...',
    buttons: ST_BUTTONS,
  };

  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;

  constructor(private languageService: LanguageService,
              private router: Router) {}

  async ngOnInit() {
    this.languageService.getLanguageID$.subscribe(result => this.languageID = result);
    this.languageService.getLanguageName$.subscribe(result => this.languageName = result);
    await this.getLanguageByID(this.languageID);
  }

  async getLanguageByID(languageID: number) {
    const result = await this.languageService.getLanguageByID(languageID).toPromise();
    this.selectedLanguage = result.data.datumRetrieved;
    console.log(this.selectedLanguage);
    console.log(this.selectedLanguage.alphabetSize);
  }

  updateDescription() {
    this.languageService.updateLanguage(this.languageID, this.selectedLanguage).subscribe();
    this.isDescription = false;
  }

  routeToLanguages() {
    this.router.navigateByUrl(`/languages`);
  }

  routeToLanguageEdit(languageID: number, languageName: string) {
    this.router.navigateByUrl(`/languages/${languageID}/${languageName}/edit`);
  }

  routeToLetters(languageID: number, languageName: string, alphabetSize: number) {
    if(alphabetSize === null) {
      alphabetSize = 0;
    }
    this.router.navigateByUrl(`/letters/language/${languageID}/${languageName}/${alphabetSize}`);
  }

  closeEditing(doneEditing: boolean) {
    this.isEditing = doneEditing;
  }
}
