import {CountVo} from "@model/countVo.model";

export class DashboardIncidentNumbersVo{
  totalNumberOfRegisteredIncidents: number;
  totalNumberOfResolvedIncidents: number;
  totalNumberOfUnResolvedIncidents: number;
  countIncidentByCompany: CountVo[];
  countIncidentByTypes: CountVo[];
  mostIncidentDate: Date;
  mostIncidentsArea: string;
}
