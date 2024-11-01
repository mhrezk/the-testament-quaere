import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Book} from "../../../../../../../interfaces/models/history/library/book";
import {BookService} from "../../../../../../../services/models/history/library/book/book.service";

@Component({
  selector: 'app-book-display-edit',
  templateUrl: './book-display-edit.component.html',
  styleUrl: './book-display-edit.component.css'
})
export class BookDisplayEditComponent implements OnInit {
  @Input() book: Book;
  @Output() isEditing = new EventEmitter<boolean>();
  @Input() authorID: number;

  constructor(private bookService: BookService) {}

  ngOnInit() {
  }

  onClose(editable: boolean) {
    this.isEditing.emit(editable);
  }

  onSubmit(book: Book) {
    this.bookService.modifyBook$(this.book.id, this.authorID, book).subscribe();
    this.onClose(false);
  }
}
