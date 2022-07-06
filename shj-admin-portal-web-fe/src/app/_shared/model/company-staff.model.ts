import {ApplicantDigitalId} from "@model/applicant-digital-id.model";
import {CompanyStaffDigitalId} from "@model/company-staff-digital-id.model";

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
    digitalIds: Array<CompanyStaffDigitalId>;
    email: string;
    creationDate: Date;
    updateDate: Date;
    suin:string
}
