import {CountVo} from "@model/count-vo.model";

export class DashboardIncidentNumbersVo{
  totalNumberOfRegisteredIncidents: number;
  totalNumberOfResolvedIncidents: number;
  totalNumberOfUnResolvedIncidents: number;
  countIncidentByCompany: CountVo[];
  countIncidentByTypes: CountVo[];
  mostIncidentDate: Date;
  mostIncidentsArea: string;
}
