import {
  AfterViewInit,
  Component,
  ComponentFactoryResolver,
  HostListener,
  Inject,
  OnInit,
  ViewChild
} from '@angular/core';
import {DashboardService} from '@core/services';
import {LookupService} from '@core/utilities/lookup.service';
import {DOCUMENT} from "@angular/common";
import {dashboardItem} from "@model/dashboard-item";
import {SlideShowDirective} from "@shared/directives/slide-show.directive";
import {DashboardComponent} from "@pages/dashboard/slide-show/dashboard.component";
import {
  CamerasComponent,
  GeneralNumbersComponent,
  IncidentsComponent,
  MainComponent,
  MobileComponent, RatingComponent, TransactionsComponent
} from "@pages/dashboard";

@Component({
  selector: 'slide-show',
  templateUrl: './slide-show.component.html',
  styleUrls: ['./slide-show.component.scss'],
})
export class SlideShowComponent implements OnInit, AfterViewInit {
  dashboardItems: dashboardItem[] = [];
  currentAdIndex = -1;

  @ViewChild(SlideShowDirective, {static: true})
  slideShowHost!: SlideShowDirective;

  interval: number | undefined;

  elem: any;
  isFullScreen: boolean;

  @HostListener('document:fullscreenchange', ['$event'])
  @HostListener('document:webkitfullscreenchange', ['$event'])
  @HostListener('document:mozfullscreenchange', ['$event'])
  @HostListener('document:MSFullscreenChange', ['$event'])
  fullscreenmodes(event) {
    this.chkScreenMode();
  }

  chkScreenMode() {
    if (document.fullscreenElement) {
      //fullscreen
      this.isFullScreen = true;
    } else {
      //not in full screen
      this.isFullScreen = false;
    }
  }
  componentLookupRegistry: Map<string, any> = new Map();
  constructor(
    private dashboardService: DashboardService,
    private lookupService: LookupService,
    @Inject(DOCUMENT) private document: any,
    private componentFactoryResolve: ComponentFactoryResolver
  ) {
  }

  ngOnInit() {
    this.dashboardItems = this.dashboardService.getDashboardItems().filter(dashboard => {
      return dashboard.selected
    });
    this.componentLookupRegistry.set('MainComponent',MainComponent);
    this.componentLookupRegistry.set('GeneralNumbersComponent',GeneralNumbersComponent);
    this.componentLookupRegistry.set('IncidentsComponent',IncidentsComponent);
    this.componentLookupRegistry.set('CamerasComponent',CamerasComponent);
    this.componentLookupRegistry.set('MobileComponent',MobileComponent);
    this.componentLookupRegistry.set('RatingComponent',RatingComponent);
    this.componentLookupRegistry.set('TransactionsComponent',TransactionsComponent);

    this.chkScreenMode();
    this.elem = document.documentElement;
    this.openFullscreen()
    this.loadComponent();
    this.dashboardService.getSlideShowInterval().subscribe(newInterval => {
      console.log("Slide show interval = " + newInterval);
      this.interval = setInterval(() => {
        this.loadComponent();
      }, newInterval * 1000);
    });

  }

  ngOnDestroy() {
    clearInterval(this.interval);
  }

  openFullscreen() {
    if (this.elem.requestFullscreen) {
      this.elem.requestFullscreen();
    } else if (this.elem.mozRequestFullScreen) {
      /* Firefox */
      this.elem.mozRequestFullScreen();
    } else if (this.elem.webkitRequestFullscreen) {
      /* Chrome, Safari and Opera */
      this.elem.webkitRequestFullscreen();
    } else if (this.elem.msRequestFullscreen) {
      /* IE/Edge */
      this.elem.msRequestFullscreen();
    }
  }

  closeFullscreen() {
    if (this.document.exitFullscreen) {
      this.document.exitFullscreen();
    } else if (this.document.mozCancelFullScreen) {
      /* Firefox */
      this.document.mozCancelFullScreen();
    } else if (this.document.webkitExitFullscreen) {
      /* Chrome, Safari and Opera */
      this.document.webkitExitFullscreen();
    } else if (this.document.msExitFullscreen) {
      /* IE/Edge */
      this.document.msExitFullscreen();
    }
    window.history.back();
  }

  loadComponent() {
    this.currentAdIndex = (this.currentAdIndex + 1) % this.dashboardItems.length;
    const dashboardItem = this.dashboardItems[this.currentAdIndex];
    const viewContainerRef = this.slideShowHost.viewContainerRef;
    viewContainerRef.clear();
    const componentClass = this.componentLookupRegistry.get(dashboardItem.componentName);
    const componentRef =
      viewContainerRef.createComponent<DashboardComponent>(this.componentFactoryResolve.resolveComponentFactory(componentClass));
    componentRef.instance.isFullScreen = true;
  }

  ngAfterViewInit(): void {

  }

}
