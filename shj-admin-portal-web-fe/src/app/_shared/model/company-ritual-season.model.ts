import {CompanyLite} from "@model/company-lite.model";
import {RitualSeason} from "@model/ritual-season.model";
import {RitualPackage} from "@model/ritual-package.model";
import {ApplicantGroup} from "@model/applicant-group.model";

export class CompanyRitualSeason {
    id: number;
    company: CompanyLite;
    ritualSeason: RitualSeason;
    ritualPackages: RitualPackage[];
    applicantGroups: ApplicantGroup[];
    seasonStart: number;
    seasonEnd: number;
    totalQuota: number;
    airQuota: number;
    seaQuota: number;
    landQuota: number;
    creationDate: Date;
    updateDate: Date;
    isActive: boolean

}
