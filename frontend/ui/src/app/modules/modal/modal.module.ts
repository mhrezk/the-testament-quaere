import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CustomModalComponent } from './component/custom-modal/custom-modal.component';
import {ModalService} from "./service/custom-modal.service";



@NgModule({
  declarations: [
    CustomModalComponent
  ],
  imports: [
    CommonModule
  ],
  providers: [ModalService]
})
export class ModalModule { }
