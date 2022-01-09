import {CompanyStaffCard} from "@model/staff-card.model";
import {CompanyStaff} from "@model/company-staff.model";

export class ApplicantGroup {

    id: number;
    localOfficeId: number;
    referenceNumber: String;
    arrivalDate: Date;
    departureDate: Date;
    groupLeader: CompanyStaff;
    groupTypeCode: string;
    entryTransportationTypeCode: string;
    creationDate: Date;
    updateDate: Date;
}
