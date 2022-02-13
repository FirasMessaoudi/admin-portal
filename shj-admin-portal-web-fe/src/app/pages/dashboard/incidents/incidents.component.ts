import {AfterViewInit, Component, Inject, OnChanges, OnInit, Renderer2, SimpleChanges, ViewChild} from '@angular/core';
import {Label, PluginServiceGlobalRegistrationAndOptions, SingleDataSet} from "ng2-charts";
import {ChartOptions, ChartType} from "chart.js";
import {ChartsConfig} from "@pages/dashboard/charts.config";
import {AuthenticationService, DashboardService} from "@core/services";
import { Loader } from "@googlemaps/js-api-loader";
import { Cluster, ClusterStats, MarkerClusterer , Renderer} from "@googlemaps/markerclusterer";
//import {GoogleMap, MapMarkerClusterer} from '@angular/google-maps';
import {LookupService} from "@core/utilities/lookup.service";
import {Subscription} from "rxjs";
import {Lookup} from "@model/lookup.model";
import {CountVo} from "@model/countVo.model";
import {DatePipe, DOCUMENT} from "@angular/common";
import {DateFormatterService} from "@shared/modules/hijri-gregorian-datepicker/date-formatter.service";
import {I18nService} from "@dcc-commons-ng/services";
import { interpolateRgb } from "d3-interpolate";
import {DashboardIncidentNumbersVo} from "@model/dashboardIncidentNumbersVo.model";
import { Position } from '@app/_shared/model/marker.model';
import {EAuthority} from "@shared/model";

const FONTS: string = '"Elm-font", sans-serif';

@Component({
  selector: 'app-incidents',
  templateUrl: './incidents.component.html',
  styleUrls: ['./incidents.component.scss']
})
export class IncidentsComponent implements OnInit, AfterViewInit  {
  private incidentSubscription: Subscription;
  incidents: DashboardIncidentNumbersVo;
  incidentTypeList: Lookup[];
  incidentStatusList: Lookup[];
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
  mostIncidentDate: any;
  mostIncidentsArea: string;
  minCompanies: boolean;
  mapOptions: google.maps.MapOptions = {
    center: {lat: 21.423461874376475, lng: 39.825553299746616},
    zoom: this.MAP_ZOOM_OUT,
    disableDefaultUI: true,
    zoomControl: true,
    scrollwheel: true,
  }
  public incidentDoughnutChartOptions: ChartOptions = {
    responsive: true,
    cutoutPercentage: 70,
    rotation: 1 * Math.PI,
    circumference: 1 * Math.PI,
  };
  public incidentTypeDoughnutChartOptions: ChartOptions = {
    responsive: true,
    cutoutPercentage: 70
  };
  public incidentDoughnutChartPlugins: PluginServiceGlobalRegistrationAndOptions[];
  public incidentTypeDoughnutChartPlugins: PluginServiceGlobalRegistrationAndOptions[];

  constructor(private authenticationService: AuthenticationService,
              private dashboardService: DashboardService,
              private lookupService: LookupService,
              private dateFormatterService: DateFormatterService,
              private i18nService: I18nService,
              @Inject(DOCUMENT) private document: Document,
              private renderer2: Renderer2) {

  }
  ngAfterViewInit(): void {
  }

  ngOnInit() {
   /* this.dashboardService.loadIncidentsLocationsForCurrentSeason().subscribe(
      data =>{ this.locations = data;
        this.loadMapkey();                 }
    );*/
    this.loadLookups();
    this.chartsConfig.barChartOptions = {
      ...this.chartsConfig.barChartOptions,
      legend: false,
      scales: {
        ...this.chartsConfig.barChartOptions.scales,
        xAxes: [
          {
            gridLines: {
              color: 'rgba(0, 0, 0, 0)',
            },
          },
        ],
        yAxes: [
          {
            gridLines: {
              borderDash: [8, 6],
              color: '#F3F5F2',
            },
            ticks: {
              fontFamily: FONTS,
              beginAtZero: true,
              callback: function (value) {
                if (value % 1 === 0) {
                  return value;
                }
              },
            },
          },
        ],
      },
    };

    this.incidentSubscription = this.dashboardService.loadIncidents(1443).subscribe((data)=> {
      this.incidents = data;

      this.incidentDoughnutChartLabels = [this.lookupService.localizedLabel(this.incidentStatusList, 'RESOLVED'), this.lookupService.localizedLabel(this.incidentStatusList, 'UNDER_PROCESSING')];
      this.incidentDoughnutChartData = [{ data: [this.incidents.totalNumberOfResolvedIncidents, this.incidents.totalNumberOfUnResolvedIncidents], steppedLine: 'after'}];
      this.incidentByTypes = this.incidents.countIncidentByTypes;
      this.incidentTypeCounts = this.incidents.countIncidentByTypes.map((i) => i.count);
      this.incidentTypeLabels = this.incidents.countIncidentByTypes.map((d) => this.lookupService.localizedLabel(this.incidentTypeList, d.label));
      this.setIncidentCenterTitle('مجموع البلاغات',this.incidents.totalNumberOfRegisteredIncidents);
      this.setIncidentTypeCenterTitle('مجموع البلاغات',this.incidents.totalNumberOfRegisteredIncidents);

      this.mostIncidentDate = this.formatHijriDate(this.incidents.mostIncidentDate);
      this.mostIncidentsArea = this.incidents.mostIncidentsArea;
    })

    this.loadMaxCompanies();
    this.loadMinCompanies();
  }

  loadMinCompanies() {
    this.minCompanies = true;
    this.dashboardService
      .loadCompaniesWithMinIncidentCount(1443)
      .subscribe((data) => (this.companyCounts = data.map((i) => i.count)));
    this.dashboardService
      .loadCompaniesWithMinIncidentCount(1443)
      .subscribe((data) => (this.companyLabels = data.map((d) => d.label)));
  }

  loadMaxCompanies() {
    this.minCompanies = false;
    this.dashboardService
      .loadCompaniesWithMaxIncidentCount(1443)
      .subscribe((data) => (this.companyCounts = data.map((i) => i.count)));
    this.dashboardService
      .loadCompaniesWithMaxIncidentCount(1443)
      .subscribe((data) => (this.companyLabels = data.map((d) => d.label)));
  }

  setIncidentCenterTitle(title:string, countText:number) {
      this.incidentDoughnutChartPlugins = [{
        beforeDraw(chart) {
          var data = chart.data.datasets[0].data;
          var width = chart.width,
            height = (chart.chartArea.top + chart.chartArea.bottom),
            ctx = chart.ctx;
          ctx.restore();
          var fontSize = (height / 15).toFixed(2);
          ctx.font = fontSize + "px Arial";
          ctx.textBaseline = "middle";
          var text = countText+"",
            textX = Math.round((width - ctx.measureText(text).width) / 2),
            textY = height / 2;
          var textZ = height / 2.5;
          ctx.fillText(text, textX, textY);
          ctx.textBaseline = "middle";
          var textLabel = title,
            textLabelX = Math.round((width - ctx.measureText(textLabel).width) / 2),
            textLabelY = height / 1.5;
          var textLabelZ = height / 1.5;
          ctx.fillText(textLabel, textLabelX, textLabelY);
          ctx.save();
        }
      }];
  }

  setIncidentTypeCenterTitle(title:string, countText:number) {
    this.incidentTypeDoughnutChartPlugins = [{
      beforeDraw(chart) {
        var data = chart.data.datasets[0].data;
        var width = chart.width,
          height = (chart.chartArea.top + chart.chartArea.bottom),
          ctx = chart.ctx;
        ctx.restore();
        var fontSize = (height / 15).toFixed(2);
        ctx.font = fontSize + "px Arial";
        ctx.textBaseline = "middle";
        var text = countText+"",
          textX = Math.round((width - ctx.measureText(text).width) / 2),
          textY = height / 2.5;
        var textZ = height / 2.5;
        ctx.fillText(text, textX, textY);
        ctx.textBaseline = "middle";
        var textLabel = title,
          textLabelX = Math.round((width - ctx.measureText(textLabel).width) / 2),
          textLabelY = height / 2;
        var textLabelZ = height / 1.5;
        ctx.fillText(textLabel, textLabelX, textLabelY);
        ctx.save();
      }
    }];
  }

  loadLookups() {
    this.dashboardService.findIncidentTypes().subscribe(
      data => this.incidentTypeList = data
    )
    this.dashboardService.findIncidentStatus().subscribe(
      data => this.incidentStatusList = data
    )
  }

  async loadMapkey() {
    this.lookupService.loadGoogleMapsApiKey().subscribe(result => {
      let loader = new Loader({apiKey: result})
      loader.load().then(()=>{
        const map = new google.maps.Map(document.getElementById("map"),{
          center:{lat: 21.423461874376475, lng: 39.825553299746616},
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
          palette: interpolateRgb("blue", "red"),
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
                color: "rgba(255,255,255,0.9)",
                fontSize: "12px",
              },
              // adjust zIndex to be above other markers
              zIndex: Number(google.maps.Marker.MAX_ZINDEX) + count,
            });
          },
        };
        const panel: [Renderer] = [ interpolatedRenderer]
        const render = panel[0];
        // Add a marker clusterer to manage the markers.
        new MarkerClusterer({ markers, map, renderer : render });
      })
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
      ? datePipe.transform(hijriDate, 'yyyy/MM/dd')
      : datePipe.transform(hijriDate, 'dd/MM/yyyy');
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  get canSeeIncidentDashboard(): boolean {
    return this.authenticationService.hasAuthority(EAuthority.INCIDENT_DASHBOARD);
  }
}
