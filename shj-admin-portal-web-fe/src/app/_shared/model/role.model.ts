export class Role {
  id: number;
  labelAr: string;
  labelEn: string;
  deleted: boolean;
  activated: boolean;
  roleAuthorities: Array<any>;
  creationDate: Date;
  updateDate: Date;
}
