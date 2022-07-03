import {ApplicantRitual} from "@model/applicant-ritual.model";
import {CompanyRitualMainDataStep} from "@model/company-ritual-step";
import {groupeLeader} from "./groupe-leader.model";
import {ApplicantPackageCatering} from "@model/applicant-package-catering.model";
import {ApplicantPackageHousing} from "@model/applicant-package-housing.model";
import {ApplicantPackageTransportation} from "@model/applicant-package-transportation.model";
import {CompanyLite} from "@model/company-lite.model";
import {CompanyRitualSeason} from "@model/company-ritual-season.model";
import {CompanyStaff} from '@model/company-staff.model';

export class CompanyStaffDigitalId {
    id: number;
    suin: string;
    statusCode: string;
    seasonYear: number;
    companyStaff: CompanyStaff;
    creationDate: Date;
    updateDate: Date;

}
