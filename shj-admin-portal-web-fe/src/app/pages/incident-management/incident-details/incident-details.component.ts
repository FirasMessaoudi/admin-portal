import { Component, Inject, OnInit, Renderer2 } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';
import { combineLatest } from 'rxjs';
import { map } from 'rxjs/operators';
import { Loader } from '@googlemaps/js-api-loader';
import { ApplicantIncident } from '@model/applicant-incident.model';
import { IncidentService } from '@core/services/incident/incident.service';
import { TranslateService } from '@ngx-translate/core';
import { ToastService } from '@shared/components/toast';
import { I18nService } from '@dcc-commons-ng/services';
import { Lookup } from '@model/lookup.model';
import { LookupService } from '@core/utilities/lookup.service';
import { Marker } from '@model/marker.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ConfirmDialogService } from '@shared/components/confirm-dialog';

@Component({
  selector: 'app-incident-details',
  templateUrl: './incident-details.component.html',
  styleUrls: ['./incident-details.component.scss'],
})
export class IncidentDetailsComponent implements OnInit {
  incident: ApplicantIncident;
  incidentId: number;
  isLoading: boolean;
  isLocationSelected = false;
  incidentTypes: Lookup[] = [];
  incidentStatuses: Lookup[] = [];
  mapIsReady = false;
  MAP_ZOOM_OUT = 10;
  mapOptions: google.maps.MapOptions = {
    center: { lat: 21.423461874376475, lng: 39.825553299746616 },
    zoom: this.MAP_ZOOM_OUT,
    disableDefaultUI: true,
  };
  marker: Marker;
  MARK_AS_RESOLVED: string = 'MARK_AS_RESOLVED';
  MARK_AS_CLOSED: string = 'MARK_AS_CLOSED';
  UNDER_PROCESSING: string = 'UNDER_PROCESSING';
  RESOLVED: string = 'RESOLVED';
  CLOSED: string = 'CLOSED';
  incidentForm: FormGroup;
  icon: any;

  constructor(
    private incidentService: IncidentService,
    private router: Router,
    private i18nService: I18nService,
    private translate: TranslateService,
    private toastr: ToastService,
    private lookupsService: LookupService,
    private route: ActivatedRoute,
    private confirmDialogService: ConfirmDialogService,
    private formBuilder: FormBuilder,
    @Inject(DOCUMENT) private document: Document,
    private renderer2: Renderer2
  ) {}

  ngOnInit(): void {

    combineLatest([this.route.params, this.route.queryParams])
      .pipe(
        map((results) => ({
          params: results[0].id,
          qParams: results[1],
        }))
      )
      .subscribe((results) => {
        this.incidentId = +results.params; // (+) converts string 'id' to a number
        if (this.incidentId) {
          this.isLoading = true;
          // load incident details
          this.incidentService.find(this.incidentId).subscribe((data) => {
            if (data && data.id) {
              this.isLoading = false;
              this.incident = data;
              if(this.incident?.locationLat && this.incident?.locationLng){
                this.isLocationSelected = true;
              this.loadGoogleMapsApiKey();}
            } else {
              this.toastr.error(
                this.translate.instant('general.route_item_not_found', {
                  itemId: this.incidentId,
                }),
                this.translate.instant('general.dialog_error_title')
              );
              this.navigateToList();
            }
          });
        } else {
          this.toastr.error(
            this.translate.instant('general.route_id_param_not_found'),
            this.translate.instant('general.dialog_error_title')
          );
          this.navigateToList();
        }
      });
    this.initForm();
    this.loadLookups();
  }

  loadLookups() {
    this.incidentService.findIncidentTypes().subscribe((result) => {
      this.incidentTypes = result;
    });
    this.incidentService.findIncidentStatuses().subscribe((result) => {
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

  async loadGoogleMapsApiKey() {
    this.lookupsService.loadGoogleMapsApiKey().subscribe((result) => {
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
        if(this.incident?.locationLat && this.incident?.locationLng){
        new google.maps.Marker({
          position: {
            lat: this.incident?.locationLat,
            lng: this.incident?.locationLng,
          },
          map,
        });}
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
    });
  }

  cancel() {
    this.confirmDialogService
      .confirm(
        this.translate.instant(
          'notification-management.cancel_confirmation_text'
        ),
        this.translate.instant('general.dialog_confirmation_title')
      )
      .then((confirm) => {
        if (confirm) {
          this.goBackToList();
        }
      });
  }

  goBackToList() {
    this.router.navigate(['/incidents/list']);
  }

  get f() {
    return this.incidentForm.controls;
  }

  save() {
    Object.keys(this.incidentForm.controls).forEach((field) => {
      const control = this.incidentForm.get(field);
      control.markAsTouched({ onlySelf: true });
    });

    if (this.incidentForm.invalid) {
      return;
    }

    let confirmationText, successText;
    if (this.incidentForm.controls.operation.value === this.MARK_AS_RESOLVED) {
      confirmationText =
        'incident-management.dialog_resolve_incident_confirmation_text';
      successText = 'incident-management.dialog_resolve_incident_success_text';
    } else if (
      this.incidentForm.controls.operation.value === this.MARK_AS_CLOSED
    ) {
      confirmationText =
        'incident-management.dialog_close_incident_confirmation_text';
      successText = 'incident-management.dialog_close_incident_success_text';
    }
    this.confirmDialogService
      .confirm(
        this.translate.instant(confirmationText),
        this.translate.instant('general.dialog_confirmation_title')
      )
      .then((confirm) => {
        let payload = this.incidentForm.value;
        payload.resolutionComment = payload.resolutionComment
          .replace(/\s/g, ' ')
          .trim();
        if (confirm) {
          this.isLoading = true;
          this.incidentService.handle(this.incidentId, payload).subscribe(
            (_) => {
              this.isLoading = false;
              this.toastr.success(
                this.translate.instant(successText),
                this.translate.instant(
                  'incident-management.incident_resolution'
                )
              );
              this.navigateToList();
            },
            (error) => {
              this.isLoading = false;
              this.toastr.error(
                this.translate.instant('general.dialog_error_text'),
                this.translate.instant(
                  'incident-management.incident_resolution'
                )
              );
            }
          );
        }
      });
  }

  private initForm() {
    this.incidentForm = this.formBuilder.group({
      operation: [this.MARK_AS_RESOLVED, Validators.required],
      resolutionComment: ['', [Validators.required, Validators.maxLength(500)]],
    });
  }

  isUnderProcessing(incident): boolean {
    return incident?.statusCode === this.UNDER_PROCESSING;
  }

  downloadAttachment(id) {
    this.incidentService.downloadIncidentAttachment(id).subscribe(
      (data) => {
        this.downloadFile(data);
        console.log(data);
      },
      (error) => console.log('Error downloading the file.'),
      () => console.info('OK')
    );
  }

  downloadFile(data) {
    const blob = new Blob([data], { type: 'image/jpg' });
    const url = window.URL.createObjectURL(blob);
    window.open(url);
  }
}
