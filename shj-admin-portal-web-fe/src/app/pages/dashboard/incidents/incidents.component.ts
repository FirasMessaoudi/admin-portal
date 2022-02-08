import {AfterViewInit, Component, Inject, OnChanges, OnInit, Renderer2, SimpleChanges, ViewChild} from '@angular/core';
import {Label, PluginServiceGlobalRegistrationAndOptions, SingleDataSet} from "ng2-charts";
import {ChartOptions, ChartType} from "chart.js";
import {ChartsConfig} from "@pages/dashboard/charts.config";
import {DashboardService} from "@core/services";
import {GoogleMap} from '@angular/google-maps';
import {LookupService} from "@core/utilities/lookup.service";
import {Subscription} from "rxjs";
import {Lookup} from "@model/lookup.model";
import {CountVo} from "@model/countVo.model";
import {DatePipe, DOCUMENT} from "@angular/common";
import {DateFormatterService} from "@shared/modules/hijri-gregorian-datepicker/date-formatter.service";
import {I18nService} from "@dcc-commons-ng/services";
import {DashboardIncidentNumbersVo} from "@model/dashboardIncidentNumbersVo.model";

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
  @ViewChild(GoogleMap, { static: false }) map: GoogleMap;
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

  constructor(private dashboardService: DashboardService,
              private lookupService: LookupService,
              private dateFormatterService: DateFormatterService,
              private i18nService: I18nService,
              @Inject(DOCUMENT) private document: Document,
              private renderer2: Renderer2) {

  }
  ngAfterViewInit(): void {
    throw new Error('Method not implemented.');
  }

  ngOnInit() {
    this.loadMapkey();
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

    this.incidentSubscription = this.dashboardService.loadIncidents().subscribe((data)=> {
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

      this.loadMaxCompanies();
      this.loadMinCompanies();
    })
  }

  loadMinCompanies() {
    this.minCompanies = true;
    this.dashboardService
      .loadCompaniesWithMinIncidentCount()
      .subscribe((data) => (this.companyCounts = data.map((i) => i.count)));
    this.dashboardService
      .loadCompaniesWithMinIncidentCount()
      .subscribe((data) => (this.companyLabels = data.map((d) => d.label)));
  }

  loadMaxCompanies() {
    this.minCompanies = false;
    this.dashboardService
      .loadCompaniesWithMaxIncidentCount()
      .subscribe((data) => (this.companyCounts = data.map((i) => i.count)));
    this.dashboardService
      .loadCompaniesWithMaxIncidentCount()
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
      this.loadScript(result).then(() => {
        this.mapIsReady = true;
        console.log(JSON.stringify(this.map.getCenter()))

      });
    });
  }

  private loadScript(key) {
    return new Promise((resolve, reject) => {
      const script = this.renderer2.createElement('script');
      script.type = 'text/javascript';
      script.src = 'https://maps.googleapis.com/maps/api/js?key=' + key;
      script.text = ``;
      script.async = true;
      script.defer = true;
      script.onload = resolve;
      script.onerror = reject;
      this.renderer2.appendChild(this.document.body, script);
    })
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
}
