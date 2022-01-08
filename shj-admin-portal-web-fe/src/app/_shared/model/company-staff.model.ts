import {ApplicantRitual} from "@model/applicant-ritual.model";
import {CompanyRitualMainDataStep} from "@model/company-ritual-step";
import {groupeLeader} from "./groupe-leader.model";
import {ApplicantPackageCatering} from "@model/applicant-package-catering.model";
import {ApplicantPackageHousing} from "@model/applicant-package-housing.model";
import {ApplicantPackageTransportation} from "@model/applicant-package-transportation.model";
import {CompanyLite} from "@model/company-lite.model";
import {ApplicantGroup} from "@model/applicant-group.model";

export class CompanyStaff {
    id: number;
    fullNameAr: string;
    fullNameEn: string;
    idNumberOriginal: string;
    passportNumber: string;
    dateOfBirthGregorian: Date;
    dateOfBirthHijri: number;
    gender: string;
    nationalityCode: string;
    photo: string;
    idNumber: string;
    titleCode: string;
    season: number;
    fullNameOrigin: string;
    mobileNumber: string;
    mobileNumberIntl: string;
    email: string;
    creationDate: Date;
    updateDate: Date;
}
