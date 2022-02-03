import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cameras',
  templateUrl: './cameras.component.html',
  styleUrls: ['./cameras.component.scss'],
})
export class CamerasComponent implements OnInit {
  //TODO Dummy Data
  dashboardCameras = {
    totalCount: 782,
    activeCameras: 609,
    inactiveCameras: 176,
  };

  constructor() {}

  ngOnInit() {}
}
