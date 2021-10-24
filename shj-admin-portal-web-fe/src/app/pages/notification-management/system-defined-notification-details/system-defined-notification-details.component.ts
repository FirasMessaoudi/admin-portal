import {Component, OnInit} from '@angular/core';
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {I18nService} from "@dcc-commons-ng/services";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastService} from "@shared/components/toast";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-system-defined-notification-details',
  templateUrl: './system-defined-notification-details.component.html',
  styleUrls: ['./system-defined-notification-details.component.scss']
})
export class SystemDefinedNotificationDetailsComponent implements OnInit {
  notificationId: number;

  constructor(private i18nService: I18nService,
              private route: ActivatedRoute,
              private router: Router,
              private toastr: ToastService,
              private translate: TranslateService,) {
  }


  ngOnInit(): void {

    combineLatest([this.route.params, this.route.queryParams]).pipe(map(results => ({
      params: results[0].id,
      qParams: results[1]
    }))).subscribe(results => {
      this.notificationId = +results.params; // (+) converts string 'id' to a number


    });
  }


}
