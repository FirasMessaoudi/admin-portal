import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cameras',
  templateUrl: './cameras.component.html',
  styleUrls: ['./cameras.component.scss']
})
export class CamerasComponent implements OnInit {
  public cctv: number = 982;
  constructor() { }

  ngOnInit() {
  }

}
