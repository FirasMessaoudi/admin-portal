export class Authority {
  id: number;
  nameArabic: string;
  nameEnglish: string;
  parentId: number;
  parent: Authority;
  creationDate: Date;
  children: Array<Authority>;
}
