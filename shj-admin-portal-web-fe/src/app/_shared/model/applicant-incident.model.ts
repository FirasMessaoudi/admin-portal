import {ApplicantRitual} from "@model/applicant-ritual.model";
import {IncidentAttachment} from "@model/incident-attachment.model";

export class ApplicantIncident {
  id: number;
  applicantRitual: ApplicantRitual;
  statusCode: string;
  referenceNumber: string;
  typeCode: string;
  description: string;
  locationLat: number;
  locationLng: number;
  resolutionComment: string;
  incidentAttachments: IncidentAttachment[];
  creationDate: Date;
  updateDate: Date;
}
