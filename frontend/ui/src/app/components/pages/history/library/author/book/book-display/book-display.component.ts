import {Component, OnInit} from '@angular/core';
import {
  faTrash,
  faEdit, faCircleArrowLeft,
} from '@fortawesome/free-solid-svg-icons';
import {Book} from "../../../../../../../interfaces/models/history/library/book";
import {BehaviorSubject} from "rxjs";
import {BookService} from "../../../../../../../services/models/history/library/book/book.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-book-display',
  templateUrl: './book-display.component.html',
  styleUrl: './book-display.component.css'
})
export class BookDisplayComponent implements OnInit {
  selectedBook: Book;
  bookID: string;
  authorID: string;
  authorFirstName: string;
  authorLastName: string;

  isEditing: boolean = false;
  isDescription: boolean = false;

  countSubject = new BehaviorSubject<number>(0);
  count$ = this.countSubject.asObservable();

  faTrash = faTrash;
  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;

  constructor(private bookService: BookService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {}

  async ngOnInit() {
    await this.getBookByID();
  }

  async getBookByID() {
    this.authorID = this.activatedRoute.snapshot.paramMap.get("id");
    this.authorFirstName = this.activatedRoute.snapshot.paramMap.get("firstName");
    this.authorLastName = this.activatedRoute.snapshot.paramMap.get("lastName");
    this.bookID = this.activatedRoute.snapshot.paramMap.get("bookID");
    const result = await this.bookService.getBookByID$(+this.bookID).toPromise();
    this.selectedBook = result.data.datumRetrieved;
  }

  deleteBook(bookID: number) {
    this.bookService.deleteBook$(bookID).subscribe(
      result => this.routeToBooks(+this.authorID, this.authorFirstName, this.authorLastName)
    );
  }

  closeEditing(editable: boolean) {
    this.isEditing = editable;
  }

  closeDescriptionEditing(editable: boolean) {
    this.isDescription = editable;
  }

  routeToBooks(authorID: number, authorFirstName: string, authorLastName: string) {
    this.router.navigateByUrl(`author/${authorID}/${authorFirstName}/${authorLastName}/books`);
  }

  routeToChapters(bookID: number, bookName: string) {
    this.router.navigateByUrl(`author/${+this.authorID}/${this.authorFirstName}/${this.authorLastName}/books/book/${bookID}/${bookName}/chapters`);
  }

  readAllChapters(bookID: number, bookName: string) {
    this.router.navigateByUrl(`author/${+this.authorID}/${this.authorFirstName}/${this.authorLastName}/books/book/${bookID}/${bookName}/chapters/read/all`);
  }
}
