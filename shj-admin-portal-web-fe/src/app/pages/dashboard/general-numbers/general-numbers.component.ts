import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-general-numbers',
  templateUrl: './general-numbers.component.html',
  styleUrls: ['./general-numbers.component.scss']
})
export class GeneralNumbersComponent implements OnInit {

  //this is a variable that hold number
  public externalpilgrims: number = 1934323;
  public internalpilgrims: number = 512312;

  constructor() { }

  ngOnInit() {
  }

}
