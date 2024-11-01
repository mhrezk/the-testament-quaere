import {Component, Input, OnInit} from '@angular/core';
import {
  faTrash,
  faEdit, faCircleArrowLeft,
} from '@fortawesome/free-solid-svg-icons';
import {Author} from "../../../../../../interfaces/models/history/library/author";
import {AuthorService} from "../../../../../../services/models/history/library/author/author.service";
import {ActivatedRoute, Router} from "@angular/router";
import {BehaviorSubject} from "rxjs";

@Component({
  selector: 'app-author-display',
  templateUrl: './author-display.component.html',
  styleUrl: './author-display.component.css'
})
export class AuthorDisplayComponent implements OnInit {
  //@Input() author: Author;
  selectedAuthor: Author;
  authorID: string;

  isEditing: boolean = false;
  isBiography: boolean = false;

  countSubject = new BehaviorSubject<number>(0);
  count$ = this.countSubject.asObservable();

  faTrash = faTrash;
  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;

  constructor(private authorService: AuthorService,
              //private bookService: BookService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {}

  async ngOnInit() {
    await this.getAuthorByID();
    // this.bookService.bookCount$.subscribe(
    //   result => this.countSubject.next(result)
    // );
    // console.log(this.countSubject.value);
  }

  async getAuthorByID() {
    this.authorID = this.activatedRoute.snapshot.paramMap.get("id");
    const result = await this.authorService.getAuthorByID(+this.authorID).toPromise();
    this.selectedAuthor = result.data.datumRetrieved;
    console.log(this.selectedAuthor);
  }

  deleteAuthor(authorID: number) {
    this.authorService.deleteAuthor$(authorID).subscribe();
    this.routeToAuthors();
  }

  closeEditing(editable: boolean) {
    this.isEditing = editable;
  }

  closeBiographyEditing(editable: boolean) {
    this.isBiography = editable;
  }

  routeToAuthors() {
    this.router.navigate(['/library']);
  }

  routeToBooks(authorID: number, firstName: string, lastName: string) {
    this.router.navigateByUrl(`/author/${authorID}/${firstName}/${lastName}/books`);
  }
}
