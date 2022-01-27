import {Component, Input, OnInit} from '@angular/core';
import {ChartsConfig} from '@app/pages/home/charts.config';
import {CountVo} from '@app/_shared/model/countVo.model';
import {ChartOptions, ChartType} from 'chart.js';
import {Label, PluginServiceGlobalRegistrationAndOptions, SingleDataSet} from 'ng2-charts';

@Component({
  selector: 'app-doughnut-chart',
  templateUrl: './doughnut-chart.component.html',
  styleUrls: ['./doughnut-chart.component.scss']
})
export class DoughnutChartComponent implements OnInit {

  @Input('countData') data: CountVo[];
  @Input('title') title: string;
  @Input('centerText') centerText: string;
  // Doughnut
  public doughnutChartLabels: Label[];
  public doughnutChartData: SingleDataSet;
  public doughnutChartType: ChartType = 'doughnut';
  chartsConfig: ChartsConfig = new ChartsConfig();
  currentSeasonPercentage: number;
  previousSeasonPercentage: number;
  public doughnutChartOptions: ChartOptions = {
    responsive: true,
    cutoutPercentage: 70,
    };
    public doughnutChartPlugins: PluginServiceGlobalRegistrationAndOptions[] = [{
      beforeDraw(chart) {
        console.log(this.centerText);
        var data = chart.data.datasets[0].data;
        var width = chart.width,
            height = (chart.chartArea.top + chart.chartArea.bottom) ,
            ctx = chart.ctx;
        ctx.restore();
        var fontSize = (height / 15).toFixed(2);
        ctx.font = fontSize + "px Arial";
        ctx.textBaseline = "middle";
        var text = 'balabalab',
          textX = Math.round((width - ctx.measureText(text).width) / 2),
          textY = height / 2;
        var textZ = height / 2.5;
        ctx.fillText(text, textX, textZ);
        ctx.fillText(text, textX, textY);
        ctx.save();
      }
    }];
  constructor() { }

  ngOnInit(){
    this.doughnutChartLabels = new Array(this.data.length).fill('')
    .map((v: any, i: number) => this.data[i].label + ": %"+ this.data[i].percentage);
    this.doughnutChartData = new Array(this.data.length).fill('')
    .map((v: any, i: number) =>this.data[i].count);
  }

}
