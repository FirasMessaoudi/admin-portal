import {ApplicantRitual} from "@model/applicant-ritual.model";
import {CompanyRitualMainDataStep} from "@model/company-ritual-step";
import {groupeLeader} from "./groupe-leader.model";
import {ApplicantPackageCatering} from "@model/applicant-package-catering.model";
import {ApplicantPackageHousing} from "@model/applicant-package-housing.model";
import {ApplicantPackageTransportation} from "@model/applicant-package-transportation.model";
import {CompanyLite} from "@model/company-lite.model";

export class ApplicantCard {
  id: number;
  applicantRitual: ApplicantRitual;
  companyRitualSteps: CompanyRitualMainDataStep[];
  groupLeaders: groupeLeader[];
  referenceNumber: number;
  batchId: number;
  statusCode: any;

  applicantPackageCaterings: ApplicantPackageCatering [] = [];
  applicantPackageHousings: ApplicantPackageHousing [] = [];
  applicantPackageTransportations: ApplicantPackageTransportation [] = [];
  companyLite: CompanyLite;
}
