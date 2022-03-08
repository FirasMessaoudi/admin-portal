import {
  AfterViewInit,
  Component,
  ComponentFactoryResolver,
  HostListener,
  Inject,
  OnInit,
  ViewChild
} from '@angular/core';
import {DashboardService} from '@core/services';
import {LookupService} from '@core/utilities/lookup.service';
import {DOCUMENT} from "@angular/common";
import {dashboardItem} from "@model/dashboard-item";
import {SlideShowDirective} from "@shared/directives/slide-show.directive";
import {DashboardComponent} from "@pages/dashboard/slide-show/dashboard.component";
import {
  CamerasComponent,
  GeneralNumbersComponent,
  IncidentsComponent,
  MainComponent,
  MobileComponent, RatingComponent, TransactionsComponent
} from "@pages/dashboard";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {TranslateService} from "@ngx-translate/core";
import {I18nService} from "@dcc-commons-ng/services";

@Component({
  selector: 'auto-preview',
  templateUrl: './auto-preview.component.html',
  styleUrls: ['./auto-preview.component.scss'],
})
export class AutoPreviewComponent implements OnInit, AfterViewInit {
  dashboards: dashboardItem[] = [];
  slideShowInterval: number;

  constructor(private dashboardService: DashboardService,
              private modalService: NgbModal) {
  }

  ngOnInit() {
    this.dashboards = this.dashboardService.getDashboardItems();
    this.dashboardService
      .getSlideShowInterval()
      .subscribe((interval) => (this.slideShowInterval = interval));
  }

  disableSlideShow(): boolean {
    return this.dashboards.filter((dashboard) => dashboard.selected).length < 1;
  }

  updateInterval(newValue) {
    this.dashboardService.getSlideShowInterval().next(newValue);
  }

  onNumberChange(value: number) {
    if(value < 5) {
      this.dashboardService.getSlideShowInterval().next(5);
    }
  }

  open(content) {
    this.modalService.open(content);
  }

  ngAfterViewInit(): void {

  }
}
