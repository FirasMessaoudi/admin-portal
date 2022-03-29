import {AfterViewInit, Component, OnInit} from '@angular/core';
import {DashboardService} from '@core/services';
import {dashboardItem} from "@model/dashboard-item";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

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
