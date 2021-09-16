import {ApplicantRitual} from "@model/applicant-ritual.model";
import {ApplicantPackageCatering} from "@model/applicant-package-catering.model";
import {ApplicantPackageHousing} from "@model/applicant-package-housing.model";
import {ApplicantPackageTransportation} from "@model/applicant-package-transportation.model";
import {CompanyLite} from "@model/company-lite.model";

export class ApplicantCard {
    id: number;
    applicantRitual: ApplicantRitual;
    referenceNumber: number;
    batchId: number;
    statusCode: any;

    applicantPackageCaterings: ApplicantPackageCatering [] = [];
    applicantPackageHousings: ApplicantPackageHousing [] = [];
    applicantPackageTransportations: ApplicantPackageTransportation [] = [];
    companyLite: CompanyLite;
}
