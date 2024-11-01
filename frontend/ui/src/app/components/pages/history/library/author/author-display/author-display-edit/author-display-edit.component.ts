import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Author} from "../../../../../../../interfaces/models/history/library/author";
import {AuthorService} from "../../../../../../../services/models/history/library/author/author.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-author-display-edit',
  templateUrl: './author-display-edit.component.html',
  styleUrl: './author-display-edit.component.css'
})
export class AuthorDisplayEditComponent implements OnInit {
  @Input() author: Author;
  @Output() isEditing = new EventEmitter<boolean>();

  constructor(private authorService: AuthorService,
              private router: Router) {}

  ngOnInit() {
  }

  onClose(editable: boolean) {
    this.isEditing.emit(editable);
  }

  onSubmit(author: Author) {
    this.authorService.modifyAuthor$(this.author.id, author).subscribe();
    this.onClose(false);
    //this.router.navigateByUrl(`/library/author/${this.author.id}/${author.firstName}/${author.lastName}`);
  }
}
