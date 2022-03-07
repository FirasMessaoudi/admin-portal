import { Component, OnInit } from '@angular/core';
import { EAuthority } from '@shared/model';
import {
  AuthenticationService,
  CardService,
  DashboardService,
} from '@core/services';
import { DashboardMobileNumbersVo } from '@model/dashboard-mobile-numbers-vo.model';
import { ChartsConfig } from '@pages/dashboard/charts.config';
import { ChartDataSets } from 'chart.js';

import * as momentjs from 'moment';
import { I18nService } from '@dcc-commons-ng/services';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';
import { ActivatedRoute } from '@angular/router';
import { CompanyLite } from '@model/company-lite.model';
import { Lookup } from '@model/lookup.model';
import { Loader } from '@googlemaps/js-api-loader';
import { LookupService } from '@core/utilities/lookup.service';
import { ApplicantMobileTracking } from '@model/applicant-mobile-tracking.model';
import { DatePipe } from '@angular/common';
import { DashboardComponent } from '@pages/dashboard/slide-show/dashboard.component';
import { AreaLayerLookup } from '@app/_shared/model/area-layer-lookup.model';
import { Position } from '@app/_shared/model/marker.model';
import {interpolateRgb} from 'd3-interpolate';
import { Cluster, ClusterStats, MarkerClusterer, Renderer } from '@googlemaps/markerclusterer';

const moment = momentjs;
const barChartBackgroundColors = [
  '#2B7127',
  '#4F8B4B',
  '#79A476',
  '#8CB189',
  '#E1EAE0',
];

@Component({
  selector: 'app-mobile',
  templateUrl: './mobile.component.html',
  styleUrls: ['./mobile.component.scss'],
  providers: [DatePipe],
})
export class MobileComponent implements OnInit, DashboardComponent {
  model = 1;
  mobileAppDownloadsData: DashboardMobileNumbersVo;
  heatMap: google.maps.Map;

  chartsConfig: ChartsConfig = new ChartsConfig();
  weekDays: Array<any> = [];
  datasets: ChartDataSets[];

  minCompanies: boolean;
  companyLabels: Array<any>;
  companyCounts: Array<any>;
  seasonYear: any;
  applicantMobileTrackings: ApplicantMobileTracking[];
  applicantMobileTrackingsFiltred: ApplicantMobileTracking[];
  applicantMobileTrackingsLastFiltred: ApplicantMobileTracking[];
  locations: Array<any> = [];

  MAP_ZOOM_OUT = 10;

  areaLayers: AreaLayerLookup[] = [];
  companyNames: CompanyLite[];
  nationalities: Lookup[] = [];
  loggedInUsers: Array<number> = [];

  appUsersCount: Array<any>;
  appUsersLabels: Array<any>;
  backgroundColors: Array<any> = [];
  lastCompanyCode = 'all';
  lastNationalityCode = 'all';

  constructor(
    private authenticationService: AuthenticationService,
    private dashboardService: DashboardService,
    private cardService: CardService,
    private i18nService: I18nService,
    private translate: TranslateService,
    private lookupsService: LookupService,
    public datePipe: DatePipe,
    private route: ActivatedRoute
  ) {}

  ngOnInit() {
    this.seasonYear = this.route.snapshot.paramMap.get('seasonYear');
    this.loadActiveApplicantWithLocations();

    // Hide bar chart legend
    this.chartsConfig.lineChartOptions.legend = false;
    this.dashboardService
      .loadMobileAppDownloadsNumbers(this.seasonYear)
      .subscribe((data) => (this.mobileAppDownloadsData = data));
    this.getWeekDays();

    this.dashboardService
      .getMobileLoggedInUsers(this.seasonYear)
      .subscribe((data) => (this.datasets[0].data = data));

    this.dashboardService
      .getMobileLoggedOutUsers(this.seasonYear)
      .subscribe((data) => (this.datasets[1].data = data));

    this.datasets = [
      {
        fill: false,
        borderColor: '#BA9430',
        borderWidth: 2,
        // @ts-ignore
        tension: 0,
      },
      {
        fill: false,
        borderColor: '#D5D5DD',
        borderWidth: 2,
        // @ts-ignore
        tension: 0,
      },
    ];

    this.chartsConfig.barChartOptions.legend = false;

    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.getWeekDays();
    });
    this.cardService.findCountries().subscribe((result) => {
      this.nationalities = result;
    });
    this.dashboardService.loadHajCompaniesList(this.seasonYear).subscribe((result) => {
      this.companyNames = result;
    });
    this.loadMaxCompanies();
    this.loadMobileAppUsersByAgeRange();
  }

  loadMinCompanies() {
    this.minCompanies = true;
    this.dashboardService
      .loadCompaniesWithMinApplicantsRegisteredCount(this.seasonYear)
      .subscribe((data) => {
        this.companyCounts = data.map((d) => d.count);
        this.companyLabels = data.map((d) => d.label);
      });
  }

  loadMaxCompanies() {
    this.minCompanies = false;
    this.dashboardService
      .loadCompaniesWithMaxApplicantsRegisteredCount(this.seasonYear)
      .subscribe((data) => {
        this.companyCounts = data.map((d) => d.count);
        this.companyLabels = data.map((d) => d.label);
      });
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  getWeekDays() {
    this.weekDays = [];
    let day = moment();
    for (let i = 1; i < 8; i++) {
      this.weekDays.unshift(
        day
          .locale(this.currentLanguage.toLowerCase().substr(0, 2))
          .format('dddd')
      );
      day = day.clone().subtract(1, 'd');
    }
  }

  get canSeeMobileTrackingDashboard(): boolean {
    return this.authenticationService.hasAuthority(
      EAuthority.MOBILE_TRACKING_DASHBOARD
    );
  }

  loadMobileAppUsersByAgeRange() {
    this.dashboardService
      .loadMobileAppUsersByAgeRangeAndSeason(this.seasonYear)
      .subscribe((data) => {
        this.appUsersCount = data.map((d) => d.count);
        this.appUsersLabels = data.map((d) => d.label);
        this.changeChartColorsBasedOnValue(
          this.appUsersCount,
          barChartBackgroundColors
        );
      });
  }

  changeChartColorsBasedOnValue(data: Array<any>, colors: Array<string>) {
    const sortedData = data.slice().sort((a, b) => a - b);
    this.backgroundColors = this.appUsersCount.map((v) => {
      if (sortedData.indexOf(v) >= data.length - 1) {
        return colors[0];
      }
      if (sortedData.indexOf(v) >= data.length - 2) {
        return colors[1];
      }
      if (sortedData.indexOf(v) >= data.length - 3) {
        return colors[2];
      }
      if (sortedData.indexOf(v) >= data.length - 4) {
        return colors[3];
      } else {
        return colors[4];
      }
    });
  }

  loadActiveApplicantWithLocations() {
    this.dashboardService.findAreaLayers().subscribe((result) => {
      this.areaLayers = result;
      let latLng;
      this.areaLayers.forEach((a) => {
        let polygoneCoords = [];
        let layerArray = a.layer.split('-');
        layerArray.forEach((l) => {
          latLng = l.split(',');
          polygoneCoords.push({ lat: +latLng[0], lng: +latLng[1] });
        });
        a.layer = polygoneCoords;
      });
      console.log(this.areaLayers);
    });
    this.dashboardService
      .findActiveApplicantWithLocationBySeason(this.seasonYear)
      .subscribe((data) => {
        this.applicantMobileTrackings = data;
        this.applicantMobileTrackingsFiltred = data;
        this.applicantMobileTrackingsLastFiltred = data;
        console.log(this.applicantMobileTrackings);
        this.loadMapkey();
      });
  }

  loadHeatMap() {
    this.loadActiveApplicantWithLocations();
  }

  loadGroupMap(){
    this.lookupService()
    .loadGoogleMapsApiKey()
    .subscribe((result) => {
      let loader = new Loader({
        apiKey: result,
        libraries: ['visualization', 'geometry'],
      });
      loader.load().then(() => {
        let map = new google.maps.Map(document.getElementById('gmap'), {
          center: {
            lat: this.applicantMobileTrackings[0].lat,
            lng: this.applicantMobileTrackings[0].lng,
          },
          zoom: 5,
          scrollwheel: true,
        });
        let markersArray: Position[] = [];
        this.applicantMobileTrackingsFiltred.forEach((applicant) => {
          markersArray.push(new Position(applicant.lat, applicant.lng));
        });
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

  async loadMapkey() {
    this.lookupService()
      .loadGoogleMapsApiKey()
      .subscribe((result) => {
        let loader = new Loader({
          apiKey: result,
          libraries: ['visualization', 'geometry'],
        });
        loader.load().then(() => {
          this.heatMap = new google.maps.Map(document.getElementById('map'), {
            center: {
              lat: this.applicantMobileTrackings[0].lat,
              lng: this.applicantMobileTrackings[0].lng,
            },
            zoom: 8,
            scrollwheel: true,
          });
          this.getPoints();
          let heatmap = new google.maps.visualization.HeatmapLayer({
            data: this.locations,
            map: this.heatMap,
          });
        });
      });
  }

  filterMap(param: string, type: string) {
    console.log(type);
    switch (type) {
      case 'nationality':
        this.lastNationalityCode = param;
        break;
      case 'company':
        this.lastCompanyCode = param;
        break;
      default:
    }
    if (this.lastNationalityCode == 'all' && this.lastCompanyCode == 'all')
      this.applicantMobileTrackingsFiltred = this.applicantMobileTrackings;
    if (this.lastNationalityCode != 'all' && this.lastCompanyCode == 'all')
      this.applicantMobileTrackingsFiltred =
        this.applicantMobileTrackings.filter(
          (c) => c.nationalityCode == this.lastNationalityCode
        );
    if (this.lastNationalityCode == 'all' && this.lastCompanyCode != 'all')
      this.applicantMobileTrackingsFiltred =
        this.applicantMobileTrackings.filter(
          (c) => c.companyCode == this.lastCompanyCode
        );
    if (this.lastNationalityCode != 'all' && this.lastCompanyCode != 'all')
      this.applicantMobileTrackingsFiltred =
        this.applicantMobileTrackings.filter(
          (c) =>
            c.nationalityCode == this.lastNationalityCode &&
            c.companyCode == this.lastCompanyCode
        );
        this.applicantMobileTrackingsLastFiltred = this.applicantMobileTrackingsFiltred;
    this.loadMapkey();
    this.loadGroupMap();
  }

  selectedFromDateChange(event: Date) {
    this.applicantMobileTrackingsFiltred = this.applicantMobileTrackings.filter(
      (c) => new Date(c.lastLoginDate).getTime() > event.getTime()
    );
    this.applicantMobileTrackingsLastFiltred = this.applicantMobileTrackingsFiltred;
    this.loadMapkey();
    this.loadGroupMap();
  }

  selectedToDateChange(event: Date) {
    this.applicantMobileTrackingsFiltred = this.applicantMobileTrackings.filter(
      (c) => new Date(c.lastLoginDate).getTime() < event.getTime()
    );
    this.applicantMobileTrackingsLastFiltred = this.applicantMobileTrackingsFiltred;
    this.loadMapkey();
    this.loadGroupMap();
  }

  getPoints() {
    this.locations = [];
    this.applicantMobileTrackingsFiltred.forEach((applicant) => {
      this.locations.push(new google.maps.LatLng(applicant.lat, applicant.lng));
    });
  }

  filterMapByArea(areaCode: number){
    console.log(areaCode);

    if(areaCode != 0){
      let area : AreaLayerLookup = this.areaLayers.find(c=> c.id == areaCode);
      //bermudaTriangle.setMap(this.map);
      this.applicantMobileTrackingsFiltred = this.applicantMobileTrackingsLastFiltred.filter(c=>{
      let polygone = new google.maps.Polygon({
      paths: area.layer,
        });
     if(google.maps.geometry.poly.containsLocation(
      new google.maps.LatLng(c.lat, c.lng),
      polygone)){
      return c;
      }
      });

  }
  if(areaCode == 0){
    this.applicantMobileTrackingsFiltred = this.applicantMobileTrackingsLastFiltred;
  }
  this.loadMapkey();
  this.loadGroupMap();
  }

  isFullScreen: boolean;
}
