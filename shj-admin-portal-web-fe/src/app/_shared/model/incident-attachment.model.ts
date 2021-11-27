import {ApplicantIncident} from "@model/incident.model";

export class IncidentAttachment {
  id: number;
  filePath: string;
  applicantIncident: ApplicantIncident;
  creationDate: Date;
}
