import {Component, EventEmitter, Input, Output} from '@angular/core';
import {Book} from "../../../../../../../interfaces/models/history/library/book";
import {EditorConfig, ST_BUTTONS} from "ngx-simple-text-editor";
import {BookService} from "../../../../../../../services/models/history/library/book/book.service";

@Component({
  selector: 'app-book-description',
  templateUrl: './book-description.component.html',
  styleUrl: './book-description.component.css'
})
export class BookDescriptionComponent {
  @Input() book: Book;
  @Input() authorID: number;
  @Output() isDescription = new EventEmitter<boolean>();

  config: EditorConfig = {
    placeholder: 'Type something...',
    buttons: ST_BUTTONS,
  };

  constructor(private bookService: BookService) {}

  isDescriptionEdited(editable: boolean) {
    this.isDescription.emit(editable);
  }

  updateDescription() {
    this.bookService.updateBook$(this.book.id, this.authorID, this.book).subscribe();
    this.isDescription.emit(false);
  }
}
