export class Authority {
  id: number;
  labelAr: string;
  labelEn: string;
  code: string;
  parentId: number;
  parent: Authority;
  creationDate: Date;
  children: Array<Authority>;
}
