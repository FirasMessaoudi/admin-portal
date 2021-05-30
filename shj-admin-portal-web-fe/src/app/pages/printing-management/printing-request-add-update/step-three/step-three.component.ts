import {Component, Input, OnInit} from '@angular/core';
import {PrintRequest} from "@model/print-request.model";

@Component({
  selector: 'app-step-three',
  templateUrl: './step-three.component.html',
  styleUrls: ['./step-three.component.scss']
})
export class StepThreeComponent implements OnInit {

  @Input()
  printRequest: PrintRequest;

  constructor() { }

  ngOnInit(): void {
  }

}
