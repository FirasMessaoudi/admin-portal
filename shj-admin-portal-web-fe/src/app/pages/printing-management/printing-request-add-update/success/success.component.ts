import {Component, Input, OnInit} from '@angular/core';
import {PrintRequest} from "@model/print-request.model";

@Component({
  selector: 'app-success',
  templateUrl: './success.component.html',
  styleUrls: ['./success.component.scss']
})
export class SuccessComponent implements OnInit {

  @Input()
  printRequest: PrintRequest;

  constructor() {
  }

  ngOnInit() {
  }

}
