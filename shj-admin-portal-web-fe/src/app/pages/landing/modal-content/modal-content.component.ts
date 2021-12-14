import {Component} from '@angular/core';
import {NgbActiveModal} from '@ng-bootstrap/ng-bootstrap';

@Component({
  selector: 'dcc-modal-content',
  templateUrl: './modal-content.component.html'
})
export class ModalContentComponent {

  constructor(public activeModal: NgbActiveModal) { }

}
