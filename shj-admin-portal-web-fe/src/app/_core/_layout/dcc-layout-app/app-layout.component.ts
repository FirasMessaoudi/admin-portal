import { Component } from '@angular/core';
import { $animations } from '@shared/animate/animate.animations';

@Component({
  selector: 'dcc-app-layout',
  templateUrl: './app-layout.component.html',
  styleUrls: ['./app-layout.component.scss'],
  host: { 'class': 'dcc__wrapper' },
  animations: $animations
})
export class AppLayoutComponent {

  constructor() { }

}
