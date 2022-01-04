import {Component, Input} from "@angular/core";
import {Lookup} from "@model/lookup.model";
import {LookupService} from "@core/utilities/lookup.service";
import {groupeLeader} from "@app/_shared/model/groupe-leader.model";
import {I18nService} from "@dcc-commons-ng/services";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'app-group-leaders',
  templateUrl: './group-leaders.component.html',
  styleUrls: ['./group-leaders.component.scss']
})
export class GroupLeadersComponent {

  @Input('applicantGroupLeaders')  groupLeaders: groupeLeader[]
  @Input('groupLeadersTitle') groupLeaderTitleLookups: Lookup[]
  lookupService: LookupService

  constructor(lookupsService: LookupService,
              private translate: TranslateService,
              private i18nService: I18nService) {
    this.lookupService = lookupsService;
  }

  get currentLanguage(): string {
    return this.i18nService.language;
  }

}
