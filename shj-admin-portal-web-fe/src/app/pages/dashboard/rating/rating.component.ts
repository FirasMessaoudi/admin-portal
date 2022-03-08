import { Component, OnInit } from '@angular/core';
import {DashboardComponent} from "@pages/dashboard/slide-show/dashboard.component";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-rating',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.scss']
})
export class RatingComponent implements OnInit,DashboardComponent {

  constructor(private translate: TranslateService) { }

  ngOnInit() {
  }

  isFullScreen: boolean;

}
