import {Component, EventEmitter, Input, Output} from '@angular/core';
import {EditorConfig, ST_BUTTONS} from "ngx-simple-text-editor";
import {AuthorService} from "../../../../../../../services/models/history/library/author/author.service";
import {Author} from "../../../../../../../interfaces/models/history/library/author";

@Component({
  selector: 'app-author-biography',
  templateUrl: './author-biography.component.html',
  styleUrl: './author-biography.component.css'
})
export class AuthorBiographyComponent {
  @Input() author: Author;
  @Output() isBiography = new EventEmitter<boolean>();

  config: EditorConfig = {
    placeholder: 'Type something...',
    buttons: ST_BUTTONS,
  };

  constructor(private authorService: AuthorService) {}

  isBiographyEdited(editable: boolean) {
    this.isBiography.emit(editable);
  }

  updateBiography() {
    this.authorService.updateAuthor$(this.author.id, this.author).subscribe();
    this.isBiography.emit(false);
  }
}
