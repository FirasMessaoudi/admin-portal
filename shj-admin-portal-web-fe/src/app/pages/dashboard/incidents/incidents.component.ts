import {
  AfterViewInit,
  Component,
  Inject,
  OnInit,
  Renderer2,
} from '@angular/core';
import { Label, PluginServiceGlobalRegistrationAndOptions } from 'ng2-charts';
import { ChartOptions, ChartType } from 'chart.js';
import { ChartsConfig } from '@pages/dashboard/charts.config';
import { AuthenticationService, DashboardService } from '@core/services';
import { Loader } from '@googlemaps/js-api-loader';
import {
  Cluster,
  ClusterStats,
  MarkerClusterer,
  Renderer,
} from '@googlemaps/markerclusterer';
//import {GoogleMap, MapMarkerClusterer} from '@angular/google-maps';
import { LookupService } from '@core/utilities/lookup.service';
import { Subscription } from 'rxjs';
import { Lookup } from '@model/lookup.model';
import { CountVo } from '@model/count-vo.model';
import { DatePipe, DOCUMENT } from '@angular/common';
import { DateFormatterService } from '@shared/modules/hijri-gregorian-datepicker/date-formatter.service';
import { I18nService } from '@dcc-commons-ng/services';
import { interpolateRgb } from 'd3-interpolate';
import { DashboardIncidentNumbersVo } from '@model/dashboard-incident-numbers-vo.model';
import { Position } from '@app/_shared/model/marker.model';
import { EAuthority } from '@shared/model';
import { ActivatedRoute } from '@angular/router';
import { DashboardComponent } from '@pages/dashboard/slide-show/dashboard.component';
import { LangChangeEvent, TranslateService } from '@ngx-translate/core';
import { LocalizedCountVo } from '@model/localized-count-vo.model';

@Component({
  selector: 'app-incidents',
  templateUrl: './incidents.component.html',
  styleUrls: ['./incidents.component.scss'],
})
export class IncidentsComponent
  implements OnInit, AfterViewInit, DashboardComponent
{
  private incidentSubscription: Subscription;
  incidents: DashboardIncidentNumbersVo;
  incidentTypeList: Lookup[];
  incidentStatusList: Lookup[];
  housingSites: Lookup[];
  public incidentDoughnutChartLabels: Label[];
  incidentByTypes: CountVo[];
  public incidentDoughnutChartData: Array<any>;
  public incidentDoughnutChartType: ChartType = 'doughnut';
  chartsConfig: ChartsConfig = new ChartsConfig();
  incidentTypeLabels: Array<any>;
  incidentTypeCounts: Array<any>;
  companyLabels: Array<any>;
  companyCounts: Array<any>;
  locations: Position[];
  MAP_ZOOM_OUT = 10;
  MAP_ZOOM_IN = 25;
  mapIsReady = false;
  seasonYear: any;
  mostIncidentDate: any;
  mostIncidentsArea: string;
  minCompanies: boolean;
  mapOptions: google.maps.MapOptions = {
    center: { lat: 21.423461874376475, lng: 39.825553299746616 },
    zoom: this.MAP_ZOOM_OUT,
    disableDefaultUI: true,
    zoomControl: true,
    scrollwheel: true,
  };
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
            return '\n\n' + args.percentage + '%';
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
  public incidentTypeDoughnutChartOptions: ChartOptions = {
    responsive: true,
    cutoutPercentage: 70,
    plugins: {
      labels: [
        {
          render: 'label',
          fontColor: '#000',
          position: 'outside',
          outsidePadding: 10,
          textMargin: 10,
          fontStyle: 'bold',
        },
        {
          render: function (args) {
            return '\n\n' + args.percentage + '%';
          },
          fontColor: '#000',
          position: 'outside',
          outsidePadding: 10,
          textMargin: 10,
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
  public incidentTypeDoughnutChartPlugins: PluginServiceGlobalRegistrationAndOptions[];
  companyData: LocalizedCountVo[] = [];

  constructor(
    private authenticationService: AuthenticationService,
    private dashboardService: DashboardService,
    private lookupService: LookupService,
    private dateFormatterService: DateFormatterService,
    private i18nService: I18nService,
    private translate: TranslateService,
    @Inject(DOCUMENT) private document: Document,
    private route: ActivatedRoute,
    private renderer2: Renderer2
  ) {}

  ngAfterViewInit(): void {}

  ngOnInit() {
    this.seasonYear = this.route.snapshot.paramMap.get('seasonYear');
    this.dashboardService
      .loadIncidentsLocationsForHijriSeason(this.seasonYear)
      .subscribe((data) => {
        this.locations = data;
        this.loadMapkey();
      });
    this.loadLookups();

    // Hide bar chart legend
    this.chartsConfig.barChartOptions.legend = false;

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
        this.incidentByTypes = this.incidents.countIncidentByTypes;
        this.incidentTypeCounts = this.incidents.countIncidentByTypes.map(
          (i) => i.count
        );
        this.incidentTypeLabels = this.incidents.countIncidentByTypes.map((d) =>
          this.lookupService.localizedLabel(this.incidentTypeList, d.label)
        );
        this.setIncidentCenterTitle(
          this.i18nService.language.startsWith('en')
            ? 'Total Incidents'
            : 'مجموع البلاغات',
          this.incidents.totalNumberOfRegisteredIncidents
        );
        this.setIncidentTypeCenterTitle(
          this.i18nService.language.startsWith('en')
            ? 'Total Incidents'
            : 'مجموع البلاغات',
          this.incidents.totalNumberOfRegisteredIncidents
        );

        this.mostIncidentDate = this.formatHijriDate(
          this.incidents.mostIncidentDate
        );
        this.mostIncidentsArea = this.lookupService.localizedLabel(
          this.housingSites,
          this.incidents.mostIncidentsArea
        );
      });

    this.translate.onLangChange.subscribe((event: LangChangeEvent) => {
      this.companyLabels = this.currentLanguage.startsWith('ar')
        ? this.companyData.map((d) => d.labelAr)
        : this.companyData.map((d) => d.labelEn);
    });

    this.loadMaxCompanies();
  }

  loadMinCompanies() {
    this.minCompanies = true;
    this.dashboardService
      .loadCompaniesWithMinIncidentCount(this.seasonYear)
      .subscribe((data) => {
        this.companyData = data;
        this.companyCounts = data.map((d) => d.count);
        this.companyLabels = this.currentLanguage.startsWith('ar')
          ? data.map((d) => d.labelAr)
          : data.map((d) => d.labelEn);
      });
  }

  loadMaxCompanies() {
    this.minCompanies = false;
    this.dashboardService
      .loadCompaniesWithMaxIncidentCount(this.seasonYear)
      .subscribe((data) => {
        this.companyData = data;
        this.companyCounts = data.map((d) => d.count);
        this.companyLabels = this.currentLanguage.startsWith('ar')
          ? data.map((d) => d.labelAr)
          : data.map((d) => d.labelEn);
      });
  }

  setIncidentCenterTitle(title: string, countText: number) {
    this.incidentDoughnutChartPlugins = [
      {
        beforeDraw(chart) {
          var height = chart.chartArea.top + chart.chartArea.bottom,
            ctx = chart.ctx;
          ctx.restore();
          var valueFontSize = (height / 10).toFixed(2);
          ctx.font = 'bold ' + valueFontSize + 'px Arial';
          ctx.textAlign = 'center';
          ctx.textBaseline = 'middle';
          var text = countText.toString();
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

  setIncidentTypeCenterTitle(title: string, countText: number) {
    this.incidentTypeDoughnutChartPlugins = [
      {
        beforeDraw(chart) {
          var height = chart.chartArea.top + chart.chartArea.bottom,
            ctx = chart.ctx;
          ctx.restore();
          var valueFontSize = (height / 10).toFixed(2);
          ctx.font = 'bold ' + valueFontSize + 'px Arial';
          ctx.textAlign = 'center';
          ctx.textBaseline = 'middle';
          var text = countText.toString();
          const centerX = (chart.chartArea.left + chart.chartArea.right) / 2;
          const centerY = (chart.chartArea.top + chart.chartArea.bottom) / 2;
          ctx.fillText(text, centerX, centerY - 15);

          var labelFontSize = (height / 15).toFixed(2);
          ctx.font = labelFontSize + 'px Arial';
          var textLabel = title;
          ctx.fillText(textLabel, centerX, centerY + 10);
          ctx.save();
        },
      },
    ];
  }

  loadLookups() {
    this.dashboardService
      .findIncidentTypes()
      .subscribe((data) => (this.incidentTypeList = data));
    this.dashboardService
      .findIncidentStatus()
      .subscribe((data) => (this.incidentStatusList = data));
    this.dashboardService.findHousingSites().subscribe((result) => {
      this.housingSites = result;
    });
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
        // Add some markers to the map.
        const markers = this.locations.map((position, i) => {
          const marker = new google.maps.Marker({
            position,
          });
          return marker;
        });
        const interpolatedRenderer = {
          palette: interpolateRgb('blue', 'red'),
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

  formatHijriDate(date: Date): string {
    const datePipe = new DatePipe('en-US');
    let hijriDate = this.dateFormatterService.toDate(
      this.dateFormatterService.toHijri(
        this.dateFormatterService.fromDate(date)
      )
    );
    return this.currentLanguage.startsWith('ar')
      ? datePipe.transform(hijriDate, 'yyyy MM, dd')
      : datePipe.transform(hijriDate, 'dd, MM yyyy');
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  get canSeeIncidentDashboard(): boolean {
    return this.authenticationService.hasAuthority(
      EAuthority.INCIDENT_DASHBOARD
    );
  }

  isFullScreen: boolean;
}
