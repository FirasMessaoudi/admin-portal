import { Component, Input, OnInit } from '@angular/core';
import { ChartsConfig } from '@app/pages/home/charts.config';
import { CountVo } from '@app/_shared/model/countVo.model';
import { ChartOptions, ChartType } from 'chart.js';
import { Label, SingleDataSet } from 'ng2-charts';

@Component({
  selector: 'app-doughnut-chart',
  templateUrl: './doughnut-chart.component.html',
  styleUrls: ['./doughnut-chart.component.scss']
})
export class DoughnutChartComponent implements OnInit {

  @Input('countData')  data: CountVo[];
    // Doughnut
    public doughnutChartLabels: Label[];
    public doughnutChartData: SingleDataSet;
    public doughnutChartType: ChartType = 'doughnut';
    chartsConfig: ChartsConfig = new ChartsConfig();
    currentSeasonPercentage: number;
    previousSeasonPercentage: number;
    public doughnutChartOptions: ChartOptions = {
      responsive: true,
      cutoutPercentage: 70
    };
  constructor() { }

  ngOnInit(){
    this.doughnutChartLabels = new Array(this.data.length).fill('')
    .map((v: any, i: number) => this.data[i].label + ": %"+ this.data[i].percentage);
    this.doughnutChartData = new Array(this.data.length).fill('')
    .map((v: any, i: number) =>this.data[i].count);
  }

}
