import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {
  faTrash,
  faEdit, faCircleArrowLeft,
} from '@fortawesome/free-solid-svg-icons';
import {ActivatedRoute, Router} from "@angular/router";
import {ChapterService} from "../../../../../../../../services/models/history/library/chapter/chapter.service";
import {Chapter} from "../../../../../../../../interfaces/models/history/library/chapter";

@Component({
  selector: 'app-chapter-display',
  templateUrl: './chapter-display.component.html',
  styleUrl: './chapter-display.component.css'
})
export class ChapterDisplayComponent implements OnInit {
  selectedChapter: Chapter;

  authorID: string;
  authorFirstName: string;
  authorLastName: string;
  chapterID: string;
  bookID: string;
  bookName: string;

  isText: boolean = false;
  isEditing: boolean = false;

  faTrash = faTrash;
  faEdit = faEdit;
  faCircleArrowLeft = faCircleArrowLeft;

  constructor(private chapterService: ChapterService,
              private router: Router,
              private activatedRoute: ActivatedRoute) {}

  async ngOnInit() {
    await this.getChapterByID();
  }

  async getChapterByID() {
    this.activatedRoute.paramMap.subscribe(
      result => {
        this.bookID = result.get("bookID");
        this.bookName = result.get("name");
        this.chapterID = result.get("chapterID");
        this.authorID = result.get("id");
        this.authorFirstName = result.get("firstName");
        this.authorLastName = result.get("lastName");
      }
    )
    const result = await this.chapterService.getChapterByID$(+this.chapterID).toPromise();
    this.selectedChapter = result.data.datumRetrieved;
  }

  deleteChapter(chapterID: number) {
    this.chapterService.deleteChapter$(chapterID).subscribe(
      result => this.routeToChapters(+this.bookID, this.bookName)
    );
  }

  closeEditing(editable: boolean) {
    this.isEditing = editable;
  }

  closeTextEditor(editable: boolean) {
    this.isText = editable;
  }

  routeToChapters(bookID: number, bookName: string) {
    this.router.navigateByUrl(`author/${+this.authorID}/${this.authorFirstName}/${this.authorLastName}/books/book/${bookID}/${bookName}/chapters`);
  }

  routeToChapterDisplayEdit() {
    this.router.navigateByUrl(`author/${+this.authorID}/${this.authorFirstName}/${this.authorFirstName}/books/book/${+this.bookID}/${this.bookName}/chapters/chapter/${this.chapterID}/edit`);
  }
}
