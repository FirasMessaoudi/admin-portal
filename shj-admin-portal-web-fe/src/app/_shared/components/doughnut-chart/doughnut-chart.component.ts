import {Component, ElementRef, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {ChartsConfig} from '@pages/dashboard/charts.config';
import {CountVo} from '@app/_shared/model/countVo.model';
import {ChartOptions, ChartType} from 'chart.js';
import {Label, PluginServiceGlobalRegistrationAndOptions, SingleDataSet} from 'ng2-charts';
import {I18nService} from "@dcc-commons-ng/services";
import 'chartjs-plugin-labels';

@Component({
  selector: 'app-doughnut-chart',
  templateUrl: './doughnut-chart.component.html',
  styleUrls: ['./doughnut-chart.component.scss']
})
export class DoughnutChartComponent implements OnInit, OnChanges {
  @ViewChild('mycanvas')
  canvas: ElementRef;

  @Input('countData') data: CountVo[];
  @Input('title') title: string;
  @Input('centerTitle') centerTitle: string;
  @Input('centerValue') centerValue: string;

  // Doughnut
  public doughnutChartLabels: Label[];
  public doughnutChartData: SingleDataSet;
  public doughnutChartType: ChartType = 'doughnut';
  chartsConfig: ChartsConfig = new ChartsConfig();
  public doughnutChartOptions: ChartOptions = {
    responsive: true,
    cutoutPercentage: 70,
    plugins: {
      labels: {
        render: 'label',
        fontColor: '#000',
        position: 'outside',
        fontStyle: 'bold',
      },
      datalabels: {
        display: false
      }
    },
    tooltips: {
      enabled: false
    }
  };
  public doughnutChartPlugins: PluginServiceGlobalRegistrationAndOptions[];

  constructor(private i18nService: I18nService) {
  }

  ngOnInit() {
    this.doughnutChartLabels = new Array(this.data.length).fill('')
      .map((v: any, i: number) => this.i18nService.language.startsWith('en') ? '\n' + this.data[i].label + "\n" + this.data[i].percentage + "%" : '\n' + this.data[i].label + "\n" + this.data[i].percentage + '%');
    this.doughnutChartData = new Array(this.data.length).fill('')
      .map((v: any, i: number) => this.data[i].count);


  }

  ngOnChanges(changes: SimpleChanges): void {
    if (changes.centerTitle && changes.centerValue) {
      this.doughnutChartPlugins = [{
        beforeDraw(chart) {
          var width = chart.width,
            height = (chart.chartArea.top + chart.chartArea.bottom),
            ctx = chart.ctx;
          ctx.restore();
          var valueFontSize = (height / 10).toFixed(2);
          ctx.font = 'bold ' + valueFontSize + "px Arial";
          ctx.textBaseline = "middle";
          var text = changes.centerValue.currentValue,
            textX = Math.round((width - ctx.measureText(text).width) / 2),
            textY = height / 2.2;
          var textZ = height / 2.3;
          ctx.fillText(text, textX, textZ);

          ctx.textBaseline = "middle";
          var labelFontSize = (height / 15).toFixed(2);
          ctx.font = labelFontSize + "px Arial";
          var textLabel = changes.centerTitle.currentValue,
            textLabelX = Math.round((width - ctx.measureText(textLabel).width) / 2),
            textLabelY = height / 1.9;
          var textLabelZ = height / 1.5;
          ctx.fillText(textLabel, textLabelX, textLabelY);
          ctx.save();
        }
      }];
    }
  }

}
