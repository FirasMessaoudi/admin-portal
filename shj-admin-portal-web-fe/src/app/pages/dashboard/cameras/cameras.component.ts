import { Component, OnInit } from '@angular/core';
import { DashboardComponent } from '@pages/dashboard/slide-show/dashboard.component';
import { ActivatedRoute } from '@angular/router';
import { DashboardService } from '@core/services';
import { DashboardCameraNumbersVoModel } from '@model/dashboard-camera-numbers-vo.model';
import { I18nService } from '@dcc-commons-ng/services';

@Component({
  selector: 'app-cameras',
  templateUrl: './cameras.component.html',
  styleUrls: ['./cameras.component.scss'],
})
export class CamerasComponent implements OnInit, DashboardComponent {
  totalCameras: any = 0 ;
  cameraNumbers: DashboardCameraNumbersVoModel;
  seasonYear: any;

  constructor(
    private dashboardService: DashboardService,
    private route: ActivatedRoute,
    private i18nService: I18nService
  ) {}

  ngOnInit() {
    this.seasonYear = this.route.snapshot.paramMap.get('seasonYear');
    this.dashboardService
      .loadCamerasNumbers(this.seasonYear)
      .subscribe((data) => {
        this.cameraNumbers = data;
        this.totalCameras = this.cameraNumbers.totalNumberOfInactiveCameras + this.cameraNumbers.totalNumberOfActiveCameras;
      });
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  isFullScreen: boolean;
}
