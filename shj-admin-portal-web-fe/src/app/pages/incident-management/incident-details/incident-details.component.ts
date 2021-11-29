import {Component, Inject, OnInit, Renderer2} from '@angular/core';
import {DOCUMENT} from "@angular/common";
import {ActivatedRoute, Router} from "@angular/router";
import {combineLatest} from "rxjs";
import {map} from "rxjs/operators";
import {ApplicantIncident} from "@model/applicant-incident.model";
import {IncidentService} from "@core/services/incident/incident.service";
import {TranslateService} from "@ngx-translate/core";
import {ToastService} from "@shared/components/toast";
import {I18nService} from "@dcc-commons-ng/services";
import {Lookup} from "@model/lookup.model";
import {LookupService} from "@core/utilities/lookup.service";
import {Marker} from "@model/marker.model";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-incident-details',
  templateUrl: './incident-details.component.html',
  styleUrls: ['./incident-details.component.scss']
})
export class IncidentDetailsComponent implements OnInit {
  incident: ApplicantIncident;
  incidentId: number;
  isLoading: boolean;
  incidentTypes: Lookup[] = [];
  incidentStatuses: Lookup[] = [];
  mapIsReady = false;
  MAP_ZOOM_OUT = 10;
  mapOptions: google.maps.MapOptions = {
    center: {lat: 21.423461874376475, lng: 39.825553299746616},
    zoom: this.MAP_ZOOM_OUT,
    disableDefaultUI: true
  }
  marker: Marker;
  MARK_AS_RESOLVED: string = 'MARK_AS_RESOLVED';
  MARK_AS_CLOSED: string = 'MARK_AS_CLOSED';
  incidentForm: FormGroup;

  constructor(private incidentService: IncidentService,
              private router: Router,
              private i18nService: I18nService,
              private translate: TranslateService,
              private toastr: ToastService,
              private lookupsService: LookupService,
              private route: ActivatedRoute,
              private formBuilder: FormBuilder,
              @Inject(DOCUMENT) private document: Document,
              private renderer2: Renderer2) {
  }

  ngOnInit(): void {
    combineLatest([this.route.params, this.route.queryParams]).pipe(map(results => ({
      params: results[0].id,
      qParams: results[1]
    }))).subscribe(results => {
      this.incidentId = +results.params; // (+) converts string 'id' to a number
      if (this.incidentId) {
        this.isLoading = true;
        // load incident details
        this.incidentService.find(this.incidentId).subscribe(data => {
          if (data && data.id) {
            this.isLoading = false;
            this.incident = data;
          } else {
            this.toastr.error(this.translate.instant('general.route_item_not_found', {itemId: this.incidentId}),
              this.translate.instant('general.dialog_error_title'));
            this.navigateToList();
          }
        });
      } else {
        this.toastr.error(this.translate.instant('general.route_id_param_not_found'),
          this.translate.instant('general.dialog_error_title'));
        this.navigateToList();
      }
    });
    this.initForm();
    this.loadLookups();
    this.loadMapkey();
  }

  loadLookups() {
    this.incidentService.findIncidentTypes().subscribe(result => {
      this.incidentTypes = result;
    });
    this.incidentService.findIncidentStatuses().subscribe(result => {
      this.incidentStatuses = result;
    });
  }

  lookupService(): LookupService {
    return this.lookupsService;
  }

  navigateToList() {
    this.router.navigate(['/incidents/list']);
  }

  get canSeeAddUpdateIncidents(): boolean {
    //TODO Update authorities
    // return this.authenticationService.hasAuthority(EAuthority.ADD_USER) || this.authenticationService.hasAuthority(EAuthority.EDIT_USER);
    return true;
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

  /**
   * Returns the css class for the given status
   *
   * @param status the current applicant incident status
   */
  buildStatusClass(status: any): string {
    switch (status) {
      case 'UNDER_PROCESSING':
        return 'ready';
      case 'RESOLVED':
        return 'done';
      case 'CLOSED':
        return 'warning';
      default:
        return 'new';
    }
  }

  async loadMapkey() {
    /*  this.lookupsService.loadGoogleMapKey().subscribe(result => {*/
    this.loadScript(1).then(() => {
      this.mapIsReady = true
    });
  }

  private loadScript(key) {
    return new Promise((resolve, reject) => {
      const script = this.renderer2.createElement('script');
      script.type = 'text/javascript';
      // script.src = 'https://maps.googleapis.com/maps/api/js?key=' + key;
      script.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyAC78ugAlOF9B2YK8-ukki2IQTyNAgUSO0';
      script.text = ``;
      script.async = true;
      script.defer = true;
      script.onload = resolve;
      script.onerror = reject;
      this.renderer2.appendChild(this.document.body, script);
    });
  }

  cancel() {

  }

  save() {
    this.incidentService.handle(this.incidentId, this.incidentForm.value).subscribe(_ => {
      this.toastr.success(this.translate.instant('user-management.dialog_delete_success_text'), this.translate.instant('user-management.dialog_delete_title'));
      this.navigateToList();
    }, error => {
      this.toastr.error(this.translate.instant('general.dialog_error_text'), this.translate.instant('user-management.dialog_delete_title'));
    });
  }

  private initForm() {
    this.incidentForm = this.formBuilder.group(
      {
        operation: [this.MARK_AS_RESOLVED, Validators.required],
        resolutionComment: ['', [Validators.required, Validators.maxLength(500)]]
      }
    );
  }
}
