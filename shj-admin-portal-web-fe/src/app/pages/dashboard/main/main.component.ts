import {Component, OnInit} from '@angular/core';
import {GeneralDashboardVo} from '@model/dashboard-general-numbers-vo.model';
import {Subscription, timer} from 'rxjs';
import {DashboardService} from '@core/services';
import {DashboardIncidentNumbersVo} from '@model/dashboard-incident-numbers-vo.model';
import {Lookup} from '@model/lookup.model';
import {Label, PluginServiceGlobalRegistrationAndOptions} from 'ng2-charts';
import {ChartOptions, ChartType} from 'chart.js';
import {ChartsConfig} from '@pages/dashboard/charts.config';
import {LookupService} from '@core/utilities/lookup.service';
import {DatePipe} from '@angular/common';
import {DateFormatterService} from '@shared/modules/hijri-gregorian-datepicker/date-formatter.service';
import {I18nService} from '@dcc-commons-ng/services';
import {NgbModal, NgbModalConfig} from '@ng-bootstrap/ng-bootstrap';
import {interpolateRgb} from 'd3-interpolate';
import {Loader} from '@googlemaps/js-api-loader';
import {Position} from '@app/_shared/model/marker.model';
import {Cluster, ClusterStats, MarkerClusterer, Renderer,} from '@googlemaps/markerclusterer';

import * as moment_ from 'moment-hijri';

import {LangChangeEvent, TranslateService} from '@ngx-translate/core';
import {ApplicantMobileTracking} from '@app/_shared/model/applicant-mobile-tracking.model';
import {DashboardComponent} from '@pages/dashboard/slide-show/dashboard.component';
import {DashboardCameraNumbersVoModel} from '@model/dashboard-camera-numbers-vo.model';

const momentHijri = moment_;

@Component({
  selector: 'app-main',
  templateUrl: './main.component.html',
  styleUrls: ['./main.component.scss'],
  providers: [NgbModalConfig, NgbModal],
})
export class MainComponent implements OnInit, DashboardComponent {
  dashboardCamerasData: DashboardCameraNumbersVoModel;
  private CameraSubscription: Subscription;
  totalCameras: any = 0 ;
  currentSeasonData: GeneralDashboardVo;
  previousSeasonData: GeneralDashboardVo;
  currentSeasonPercentage: number;
  previousSeasonPercentage: number;
  ritualSeasons: any[] = [];
  locations: ApplicantMobileTracking[];
  private currentSeasonSubscription: Subscription;
  private previousSeasonSubscription: Subscription;
  isSeasonYearSelected: boolean = true;
  private incidentSubscription: Subscription;
  incidents: DashboardIncidentNumbersVo;
  incidentStatusList: Lookup[];
  housingSites: Lookup[];
  public incidentDoughnutChartLabels: Label[];
  public incidentDoughnutChartData: Array<any>;
  public incidentDoughnutChartType: ChartType = 'doughnut';
  chartsConfig: ChartsConfig = new ChartsConfig();
  mostIncidentDate: any;
  mostIncidentsArea: string;
  public incidentDoughnutChartOptions: ChartOptions = {
    responsive: true,
    cutoutPercentage: 70,
    rotation: Math.PI,
    circumference: Math.PI,
    plugins: {
      labels: [
        {
          render: 'label',
          fontColor: '#000',
          position: 'outside',
          textMargin: 10,
          fontStyle: 'bold',
        },
        {
          render: function (args) {
            return '\n\n\n' + args.percentage + '%';
          },
          fontColor: '#000',
          position: 'outside',
          textMargin: 12,
          fontStyle: 'normal',
          precision: 2,
        },
      ],
      datalabels: {
        display: false,
      },
    },
    tooltips: {
      enabled: false,
    },
  };
  public incidentDoughnutChartPlugins: PluginServiceGlobalRegistrationAndOptions[];

  private refreshSubscription: Subscription;
  seasonYear: number;

  constructor(
    private dashboardService: DashboardService,
    private lookupService: LookupService,
    private dateFormatterService: DateFormatterService,
    private translate: TranslateService,
    private i18nService: I18nService,
    config: NgbModalConfig
  ) {
    config.backdrop = 'static';
    config.keyboard = false;
  }

  ngOnInit() {
    this.loadLookups();
    //Get current hijri year
    this.seasonYear = parseInt(localStorage.getItem('seasonYear'));
    if (isNaN(this.seasonYear)  ){
      this.seasonYear = momentHijri(new Date()).iYear();
      localStorage.setItem('seasonYear', String(this.seasonYear));
    }
    this.loadActiveApplicantWithLocations();
    this.lookupService
      .loadDashboardRefreshInterval()
      .subscribe((timeInterval) => {
        this.refreshSubscription = timer(0, timeInterval).subscribe(() => {
          this.loadDashboardData();
        });
      });

    //Update chart labels on language change
    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.incidentDoughnutChartLabels = [
        this.lookupService.localizedLabel(this.incidentStatusList, 'RESOLVED'),
        this.lookupService.localizedLabel(
          this.incidentStatusList,
          'UNDER_PROCESSING'
        ),
      ];
      this.setIncidentCenterTitle(
        this.translate.instant('dashboard.main.total_incidents')
      );
    });
  }

  ngOnDestroy() {
    if (this.currentSeasonSubscription) {
      this.currentSeasonSubscription.unsubscribe();
    }
    if (this.previousSeasonSubscription) {
      this.previousSeasonSubscription.unsubscribe();
    }
    if (this.refreshSubscription) {
      this.refreshSubscription.unsubscribe();
    }
  }

  setIncidentCenterTitle(title: string) {
    this.incidentDoughnutChartPlugins = [
      {
        afterDatasetsDraw(chart) {
          var data = chart.data.datasets[0].data;
          var total = 0;
          data.forEach((element) => {
            total += element;
          });
          var height = chart.chartArea.top + chart.chartArea.bottom,
            ctx = chart.ctx;
          ctx.restore();
          var valueFontSize = (height / 10).toFixed(2);
          ctx.font = 'bold ' + valueFontSize + 'px Arial';
          ctx.textAlign = 'center';
          ctx.textBaseline = 'middle';
          var text = total.toString() + '';
          const centerX = (chart.chartArea.left + chart.chartArea.right) / 2;
          const centerY = (chart.chartArea.top + chart.chartArea.bottom) / 2;
          ctx.fillText(text, centerX, centerY - 10);

          var labelFontSize = (height / 11).toFixed(2);
          ctx.font = labelFontSize + 'px Arial';
          var textLabel = title;
          ctx.fillText(textLabel, centerX, centerY + 15);
          ctx.save();
        },
      },
    ];
  }

  loadLookups() {
    this.dashboardService
      .findIncidentStatus()
      .subscribe((data) => (this.incidentStatusList = data));
    this.dashboardService.findRitualSeasonYears().subscribe((result) => {
      this.ritualSeasons = result;
    });
    this.dashboardService.findHousingSites().subscribe((result) => {
      this.housingSites = result;
    });
  }

  formatHijriDate(date: Date): string {
    const datePipe = new DatePipe('en-US');
    let hijriDate = this.dateFormatterService.toDate(
      this.dateFormatterService.toHijri(
        this.dateFormatterService.fromDate(date)
      )
    );
    const formattedHijri = momentHijri(date).locale('ar-sa').format('iYYYY iMMMM iDD');
    const finalHijri = this.dateFormatterService.toEnglishDigits(formattedHijri);
    return this.currentLanguage.startsWith('ar')
      ? finalHijri
      : datePipe.transform(hijriDate, 'dd MMM yyyy');
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  loadDashboardData() {
    this.currentSeasonSubscription = this.dashboardService
      .loadGeneralNumbersForHijriSeason(this.seasonYear)
      .subscribe((data) => {
        this.currentSeasonData = data;
        this.currentSeasonPercentage =
          (100 * data.totalNumberOfExternalApplicants) /
          data.totalNumberOfApplicants;
        this.currentSeasonData.totalNumberOfApplicants =
          this.currentSeasonData.totalNumberOfExternalApplicants +
          this.currentSeasonData.totalNumberOfInternalApplicants;
      });

    this.previousSeasonSubscription = this.dashboardService
      .loadGeneralNumbersForPreviousSeason(this.seasonYear - 1)
      .subscribe((data) => {
        this.previousSeasonData = data;
        this.previousSeasonPercentage =
          (100 * data.totalNumberOfExternalApplicants) /
          data.totalNumberOfApplicants;
        this.previousSeasonData.totalNumberOfApplicants =
          this.previousSeasonData.totalNumberOfExternalApplicants +
          this.previousSeasonData.totalNumberOfInternalApplicants;
      });

    // *************************Get camera data*****************************
    this.CameraSubscription = this.dashboardService
      .loadCamerasNumbers(this.seasonYear)
      .subscribe((data) => {
        this.dashboardCamerasData = data;
        this.totalCameras = this.dashboardCamerasData.totalNumberOfInactiveCameras + this.dashboardCamerasData.totalNumberOfActiveCameras;

      });
    this.incidentSubscription = this.dashboardService
      .loadIncidents(this.seasonYear)
      .subscribe((data) => {
        this.incidents = data;
        this.incidentDoughnutChartLabels = [
          this.lookupService.localizedLabel(
            this.incidentStatusList,
            'RESOLVED'
          ),
          this.lookupService.localizedLabel(
            this.incidentStatusList,
            'UNDER_PROCESSING'
          ),
        ];
        this.incidentDoughnutChartData = [
          {
            data: [
              this.incidents.totalNumberOfResolvedIncidents,
              this.incidents.totalNumberOfUnResolvedIncidents,
            ],
            steppedLine: 'after',
          },
        ];
        this.setIncidentCenterTitle(
          this.translate.instant('dashboard.main.total_incidents')
        );
        this.mostIncidentDate = this.formatHijriDate(
          this.incidents.mostIncidentDate
        );
        this.mostIncidentsArea = this.lookupService.localizedLabel(
          this.housingSites,
          this.incidents.mostIncidentsArea
        );
      });
  }

  loadActiveApplicantWithLocations() {
    this.dashboardService
      .findActiveApplicantWithLocationBySeason(this.seasonYear)
      .subscribe((data) => {
        this.locations = data;
        this.loadMapkey();
      });
  }

  loadPilgrimsMap() {
    this.loadActiveApplicantWithLocations();
  }

  async loadMapkey() {
    this.lookupService.loadGoogleMapsApiKey().subscribe((result) => {
      let loader = new Loader({
        apiKey: result,
        libraries: ['visualization', 'geometry'],
      });
      loader.load().then(() => {
        const map = new google.maps.Map(document.getElementById('map'), {
          center: { lat: 21.423461874376475, lng: 39.825553299746616 },
          zoom: 5,
          scrollwheel: true,
        });
        let markersArray: Position[] = [];
        this.locations.forEach((applicant) => {
          markersArray.push(new Position(applicant.lat, applicant.lng));
        });
        // Add some markers to the map.
        const markers = markersArray.map((position, i) => {
          return new google.maps.Marker({
            position,
          });
        });
        const interpolatedRenderer = {
          palette: interpolateRgb('white', '#BF0C0C'),
          render: function (
            { count, position }: Cluster,
            stats: ClusterStats
          ): google.maps.Marker {
            // use d3-interpolateRgb to interpolate between red and blue
            const color = this.palette(count / stats.clusters.markers.max);

            // create svg url with fill color
            const svg = window.btoa(`
          <svg fill="${color}" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 240 240">
            <circle cx="120" cy="120" opacity=".8" r="70" />
          </svg>`);

            // create marker using svg icon
            return new google.maps.Marker({
              position,
              icon: {
                url: `data:image/svg+xml;base64,${svg}`,
                scaledSize: new google.maps.Size(75, 75),
              },
              label: {
                text: String(count),
                color: 'rgba(255,255,255,0.9)',
                fontSize: '12px',
              },
              // adjust zIndex to be above other markers
              zIndex: Number(google.maps.Marker.MAX_ZINDEX) + count,
            });
          },
        };
        const panel: [Renderer] = [interpolatedRenderer];
        const render = panel[0];
        // Add a marker clusterer to manage the markers.
        new MarkerClusterer({ markers, map, renderer: render });
      });
    });
  }

  onCurrentSeasonYearsSelected(value: string) {
    if (value != null) {
      this.seasonYear = +value;
      this.isSeasonYearSelected = true;
      localStorage.setItem('seasonYear', String(this.seasonYear));
      this.loadDashboardData();
    }
  }

  isFullScreen: boolean;

}
