import {ApplicantRitual} from "@model/applicant-ritual.model";
import {CompanyRitualMainDataStep} from "@model/company-ritual-step";
import {groupeLeader} from "./groupe-leader.model";
import {ApplicantPackageCatering} from "@model/applicant-package-catering.model";
import {ApplicantPackageHousing} from "@model/applicant-package-housing.model";
import {ApplicantPackageTransportation} from "@model/applicant-package-transportation.model";
import {CompanyLite} from "@model/company-lite.model";
import {RitualSeason} from "@model/ritual-season.model";
import {RitualPackage} from "@model/ritual-package.model";
import {ApplicantCard} from "@model/card.model";
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
