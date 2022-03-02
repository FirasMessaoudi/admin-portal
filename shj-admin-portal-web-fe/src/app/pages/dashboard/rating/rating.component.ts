import { Component, OnInit } from '@angular/core';
import {DashboardComponent} from "@pages/dashboard/slide-show/dashboard.component";

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.scss']
})
export class RatingComponent implements OnInit,DashboardComponent {

  constructor() { }

  ngOnInit() {
  }

  isFullScreen: boolean;

}
