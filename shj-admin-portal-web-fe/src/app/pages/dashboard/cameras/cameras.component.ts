import { Component, OnInit } from '@angular/core';
import {DashboardComponent} from "@pages/dashboard/slide-show/dashboard.component";

@Component({
  selector: 'app-cameras',
  templateUrl: './cameras.component.html',
  styleUrls: ['./cameras.component.scss'],
})
export class CamerasComponent implements OnInit, DashboardComponent {
  //TODO Dummy Data
  dashboardCameras = {
    totalCount: 782,
    activeCameras: 609,
    inactiveCameras: 176,
  };

  constructor() {}

  ngOnInit() {}

  isFullScreen: boolean;
}
