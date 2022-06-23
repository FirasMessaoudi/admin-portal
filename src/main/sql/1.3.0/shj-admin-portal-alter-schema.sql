USE shc_portal
GO
if not exists(select * from sys.tables where name = 'shc_applicant_complaint')
create table shc_portal.shc_applicant_complaint
(
    id                  int            NOT NULL PRIMARY KEY IDENTITY (1, 1),
    reference_number    varchar(45)    NOT NULL,
    applicant_ritual_id int            NOT NULL,
    status_code         varchar(20)    NOT NULL,
    type_code           varchar(20)    NOT NULL,
    description         nvarchar(1000) NOT NULL,
    area_code           varchar(20),
    location_lat        decimal(10, 8) NULL,
    location_lng        decimal(11, 8) NULL,
    resolution_comment  nvarchar(1000) NULL,
    city                varchar(20)    NOT NULL,
    camp_number         nvarchar(50)   NULL,
    crm_ticket_number   nvarchar(50)   NULL,
    creation_date       smalldatetime  NOT NULL default current_timestamp,
    update_date         smalldatetime  NULL,
    CONSTRAINT fk_applicant_complaint_applicant_ritual FOREIGN KEY (applicant_ritual_id) REFERENCES shc_portal.shc_applicant_ritual (id),
);
GO

if not exists(select * from sys.tables where name = 'shc_complaint_attachment')
create table shc_portal.shc_complaint_attachment
(
    id                    int            NOT NULL PRIMARY KEY IDENTITY (1, 1),
    file_path             nvarchar(1000) NOT NULL,
    applicant_complaint_id int            NOT NULL,
    creation_date         smalldatetime  NOT NULL default current_timestamp,
    CONSTRAINT fk_complaint_attachment_applicant_complaint FOREIGN KEY (applicant_complaint_id) REFERENCES shc_portal.shc_applicant_complaint (id),
);
GO
if not exists(select * from sys.tables where name = 'shc_complaint_status_lk')
create table shc_portal.shc_complaint_status_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    code          varchar(20)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(50)  NOT NULL,
    creation_date smalldatetime NOT NULL default current_timestamp,
    CONSTRAINT complaint_status_lk_unique unique (code ASC, lang ASC)
);
GO
if not exists(select * from sys.tables where name = 'shc_complaint_type_lk')
create table shc_portal.shc_complaint_type_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    code          varchar(20)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(50)  NOT NULL,
    creation_date smalldatetime NOT NULL default current_timestamp,
    CONSTRAINT complaint_type_lk_unique unique (code ASC, lang ASC)
);
GO

if not exists(select * from sys.tables where name = 'shc_city_lk')
create table shc_portal.shc_city_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    code          varchar(20)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(50)  NOT NULL,
    creation_date smalldatetime NOT NULL default current_timestamp,
    CONSTRAINT city_lk_unique unique (code ASC, lang ASC)
);

GO

ALTER TABLE shc_portal.shc_company_staff ADD country_phone_prefix varchar(17)
GO

ALTER TABLE shc_portal.shc_company_staff ADD deleted bit default 0
GO