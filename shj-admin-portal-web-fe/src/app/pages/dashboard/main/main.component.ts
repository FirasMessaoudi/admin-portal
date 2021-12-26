import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss']
})
export class MainComponent implements OnInit {

  //this is a variable that hold number
  public externalpilgrims: number = 1934323;
  public internalpilgrims: number = 512312;
  public cctv: number = 982;
  public appdownloads: number = 1103402;
  
  constructor() { }

  ngOnInit() {
  }

}
