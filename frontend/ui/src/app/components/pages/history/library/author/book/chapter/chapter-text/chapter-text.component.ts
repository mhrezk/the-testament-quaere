import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Chapter} from "../../../../../../../../interfaces/models/history/library/chapter";
import {EditorConfig, ST_BUTTONS} from "ngx-simple-text-editor";
import {ChapterService} from "../../../../../../../../services/models/history/library/chapter/chapter.service";
import {faCircleArrowLeft, faEdit, faTrash} from "@fortawesome/free-solid-svg-icons";

@Component({
  selector: 'app-chapter-text',
  templateUrl: './chapter-text.component.html',
  styleUrl: './chapter-text.component.css'
})
export class ChapterTextComponent {
  @Input() chapter: Chapter;
  @Output() isText = new EventEmitter<boolean>();

  config: EditorConfig = {
    placeholder: 'Type something...',
    buttons: ST_BUTTONS,
  };

  constructor(private chapterService: ChapterService) {}

  isTextEdited(editable: boolean) {
    this.isText.emit(editable);
  }

  updateText() {
    this.chapterService.updateChapter$(this.chapter.id, this.chapter).subscribe();
    this.isText.emit(false);
  }

  protected readonly faCircleArrowLeft = faCircleArrowLeft;
  protected readonly faEdit = faEdit;
  protected readonly faTrash = faTrash;
}
