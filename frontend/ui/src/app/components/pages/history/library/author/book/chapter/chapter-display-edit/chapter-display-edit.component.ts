import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Chapter} from "../../../../../../../../interfaces/models/history/library/chapter";
import {ChapterService} from "../../../../../../../../services/models/history/library/chapter/chapter.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-chapter-display-edit',
  templateUrl: './chapter-display-edit.component.html',
  styleUrl: './chapter-display-edit.component.css'
})
export class ChapterDisplayEditComponent implements OnInit {
  // @Input() chapter: Chapter;
  // @Output() isEditing = new EventEmitter<boolean>();

  chapter: Chapter;

  chapterID: string;
  bookID: string;
  bookName: string;
  authorID: string;
  authorFirstName: string;
  authorLastName: string;

  constructor(private chapterService: ChapterService,
              private activatedRoute: ActivatedRoute,
              private router: Router) {}

  async ngOnInit() {
    await this.getChapterByID();
  }

  async getChapterByID() {
    this.activatedRoute.paramMap.subscribe(
      result => {
        this.chapterID = result.get("chapterID");
        this.bookID = result.get("bookID");
        this.bookName = result.get("name");
        this.authorID = result.get("id");
        this.authorFirstName = result.get("firstName");
        this.authorLastName = result.get("lastName");
      }
    )
    const result = await this.chapterService.getChapterByID$(+this.chapterID).toPromise();
    this.chapter = result.data.datumRetrieved;
  }

  // onClose(editable: boolean) {
  //   this.isEditing.emit(editable);
  // }

  returnToChapterDisplay() {
    this.router.navigateByUrl(`author/${+this.authorID}/${this.authorFirstName}/${this.authorLastName}/books/book/${+this.bookID}/${this.bookName}/chapters/chapter/${+this.chapterID}`);
  }

  onSubmit(chapter: Chapter) {
    this.chapterService.modifyChapter$(this.chapter.id, chapter).subscribe();
    this.returnToChapterDisplay();
    //this.onClose(false);
  }

}
