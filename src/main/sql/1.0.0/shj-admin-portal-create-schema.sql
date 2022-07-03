create DATABASE "shc_portal";
GO
USE shc_portal
GO
create SCHEMA "shc_portal";
GO

/*--------------------------------------------------------
--  ddl for user and authorities tables
--------------------------------------------------------*/
if not exists (select * from sys.tables where name = 'shc_user')
create table shc_portal.shc_user
(
    id                       int           not null primary key identity(1,1),
    user_name                nvarchar(50) not null,
    nin                      bigint        not null,
    gender                   nvarchar(1) not null,
    mobile_number            int           not null,
    date_of_birth_gregorian  date,
    date_of_birth_hijri      int null default 0,
    password_hash            nvarchar(256) not null,
    email                    nvarchar(256),
    first_name               nvarchar(100) not null,
    father_name              nvarchar(100) null default '',
    grand_father_name        nvarchar(100) null default '',
    subtribe_name            nvarchar(100) null default '',
    family_name              nvarchar(100) not null,
    activated                bit                    default 0,
    deleted                  bit                    default 0,
    blocked                  bit                    default 0,
    block_date               smalldatetime null,
    number_of_tries          bit                    default 0,
    preferred_language       nvarchar(2) default 'en',
    change_password_required bit                    default 0,
    last_login_date          smalldatetime null,
    creation_date            smalldatetime not null default current_timestamp,
    update_date              smalldatetime null,
    avatar                   nvarchar(max),
    constraint shc_user_user_name_unique unique (user_name),
    constraint shc_user_nin_unique unique (nin)
);
GO
if not exists (select * from sys.tables where name = 'shc_user_authorities')
create table shc_portal.shc_user_authorities
(
    id        int not null primary key identity(1,1),
    user_id   int not null,
    authority nvarchar(50) not null,
    constraint fk_shc_authorities_users foreign key (user_id) references shc_portal.shc_user (id)
);
GO
/*---------------------------------------------------
--  ddl for shc_config table
---------------------------------------------------*/
if not exists (select * from sys.tables where name = 'shc_config')
create table shc_portal.shc_config
(
    id            int           not null primary key identity(1,1),
    conf_key      nvarchar(250) not null,
    conf_value    nvarchar(250) not null,
    creation_date smalldatetime not null default current_timestamp,
    update_date   smalldatetime null,
    constraint shc_config_key_unique unique (conf_key)
);
GO
/*---------------------------------------------------
--  ddl for shc_audit_event table
---------------------------------------------------*/
if not exists (select * from sys.tables where name = 'shc_audit_event')
create table shc_portal.shc_audit_event
(
    id               int           not null primary key identity(1,1),
    principal        nvarchar(250) not null,
    event_type       nvarchar(250) not null,
    start_time       smalldatetime not null,
    proccessing_time int           not null,
    channel          nvarchar(100) not null
);
GO
/*---------------------------------------------------
--  ddl for shc_audit_event_data table
---------------------------------------------------*/
if not exists (select * from sys.tables where name = 'shc_audit_event_data')
create table shc_portal.shc_audit_event_data
(
    id       int not null primary key identity(1,1),
    name     nvarchar(250) not null,
    data     nvarchar(250) not null,
    event_id int not null,
    constraint shc_audit_event_data_events foreign key (event_id) references shc_portal.shc_audit_event (id)
);
GO
-- update script for shc_portal aash version 1.2.0
ALTER TABLE shc_portal.shc_user
    ADD token nvarchar(255) null;
GO
-- update script for shc_portal aash version 1.3.0
if not exists (select * from sys.tables where name = 'shc_user_password_history')
create table shc_portal.shc_user_password_history
(
    id                int PRIMARY KEY                         NOT NULL identity(1,1),
    user_id           int                                     NOT NULL,
    old_password_hash nvarchar(256) NOT NULL,
    creation_date     smalldatetime DEFAULT current_timestamp NOT NULL,
    CONSTRAINT fk_shc_password_history_user FOREIGN KEY (user_id) REFERENCES shc_portal.shc_user (id)
);
GO
ALTER TABLE shc_portal.shc_user DROP COLUMN token;
GO
ALTER TABLE shc_portal.shc_user
    ADD token_expiry_date smalldatetime NULL;
GO

drop table if exists shc_portal.shc_audit_event_data;
drop table if exists shc_portal.shc_audit_event;
GO

declare
@schema_name nvarchar(256)
declare
@table_name nvarchar(256)
declare
@col_name nvarchar(256)
declare
@Command  nvarchar(1000)
set @schema_name = N'shc_portal'
set @table_name = N'shc_user'
set @col_name = N'number_of_tries'
select @Command = 'ALTER TABLE ' + @schema_name + '.[' + @table_name + '] DROP CONSTRAINT ' + d.name
from sys.tables t
         join sys.default_constraints d on d.parent_object_id = t.object_id
         join sys.columns c on c.object_id = t.object_id and c.column_id = d.parent_column_id
where t.name = @table_name
  and t.schema_id = schema_id(@schema_name)
  and c.name = @col_name execute (@Command)

ALTER TABLE shc_portal.shc_user ALTER COLUMN number_of_tries int;
GO

CREATE
INDEX ix_shc_user_nin ON shc_portal.shc_user(nin);
GO

if not exists (select * from sys.tables where name = 'shc_user_role')
create table shc_portal.shc_user_role
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    name_arabic   nvarchar(50) NOT NULL,
    name_english  nvarchar(50) NOT NULL,
    creation_date smalldatetime   not null default current_timestamp
);
GO
--------------------------------------------------------

EXEC sp_rename 'shc_portal.shc_user_authorities', 'shc_user_roles';
EXEC sp_rename 'shc_portal.shc_user_role', 'shc_user_role_lk';
ALTER TABLE shc_portal.shc_user_roles DROP COLUMN authority;
ALTER TABLE shc_portal.shc_user_roles
    ADD role_id int;
ALTER TABLE shc_portal.shc_user_roles
    ADD CONSTRAINT fk_shc_user_roles_role_lk FOREIGN KEY (role_id) REFERENCES shc_portal.shc_user_role_lk (id);
EXEC sp_rename 'shc_portal.fk_shc_authorities_users', 'fk_shc_user_roles_user', 'OBJECT';
GO

ALTER TABLE shc_portal.shc_user_role_lk
    ADD code nvarchar(50);
DELETE
FROM shc_portal.shc_user_role_lk
WHERE id > 0;
ALTER TABLE shc_portal.shc_user_role_lk
    ADD level int;
GO

ALTER TABLE shc_portal.shc_user_role_lk
    ADD sub_roles varchar(50);
GO

declare
@schema_name nvarchar(256)
declare
@table_name nvarchar(256)
declare
@col_name nvarchar(256)
declare
@Command  nvarchar(1000)
set @schema_name = N'shc_portal'
set @table_name = N'shc_user'
set @col_name = N'subtribe_name'
select @Command = 'ALTER TABLE ' + @schema_name + '.[' + @table_name + '] DROP CONSTRAINT ' + d.name
from sys.tables t
         join sys.default_constraints d on d.parent_object_id = t.object_id
         join sys.columns c on c.object_id = t.object_id and c.column_id = d.parent_column_id
where t.name = @table_name
  and t.schema_id = schema_id(@schema_name)
  and c.name = @col_name execute (@Command)

ALTER TABLE shc_portal.shc_user DROP COLUMN subtribe_name;
GO


-- script for role and authority new implementation
if not exists (select * from sys.tables where name = 'shc_authority_lk')
create table shc_portal.shc_authority_lk
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    label_ar      nvarchar(50) NOT NULL,
    label_en      nvarchar(50) NOT NULL,
    code          nvarchar(50) NOT NULL,
    parent_id     int,
    creation_date smalldatetime   not null default current_timestamp
);
GO

if not exists (select * from sys.tables where name = 'shc_role')
create table shc_portal.shc_role
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    label_ar      nvarchar(50) NOT NULL,
    label_en      nvarchar(50) NOT NULL,
    deleted       bit             NOT NULL default 0,
    activated     bit             NOT NULL default 0,
    creation_date smalldatetime   not null default current_timestamp,
    update_date   smalldatetime null
);
GO

if not exists (select * from sys.tables where name = 'shc_role_authority')
create table shc_portal.shc_role_authority
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    role_id       int             NOT NULL,
    authority_id  int             NOT NULL,
    creation_date smalldatetime   not null default current_timestamp,
    CONSTRAINT fk_shc_role_authority_role FOREIGN KEY (role_id) REFERENCES shc_portal.shc_role (id),
    CONSTRAINT fk_shc_role_authority_authority_lk FOREIGN KEY (authority_id) REFERENCES shc_portal.shc_authority_lk (id)
);
GO

drop table if exists shc_portal.shc_user_roles;
drop table if exists shc_portal.shc_user_role_lk;

ALTER TABLE shc_portal.shc_user
    ADD role_id int;
ALTER TABLE shc_portal.shc_user
    ADD CONSTRAINT fk_shc_user_role FOREIGN KEY (role_id) REFERENCES shc_portal.shc_role (id);

ALTER TABLE shc_portal.shc_user
    ADD action_date smalldatetime NULL;

-- Dropping unused column
ALTER TABLE shc_portal.shc_user DROP CONSTRAINT shc_user_user_name_unique;
ALTER TABLE shc_portal.shc_user DROP COLUMN user_name;
GO

if not exists(select * from sys.tables where name = 'shc_audit_log')
create table shc_portal.shc_audit_log
(
    id              int           not null primary key identity (1,1),
    user_id_number  int           not null,
    handler         varchar(100)  not null,
    action          varchar(100)  not null,
    params          nvarchar(1000) null,
    host            varchar(100)  not null,
    origin          nvarchar(256) not null,
    start_time      smalldatetime not null,
    processing_time int           not null,
    channel         varchar(256) null,
    http_status     int           not null,
    error_details   nvarchar(512) null
);
GO

if not exists (select * from sys.tables where name = 'shc_user_role')
create table shc_portal.shc_user_role
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    user_id       int             NOT NULL,
    role_id       int             NOT NULL,
    is_main_role  bit             NOT NULL default 0,
    creation_date smalldatetime   not null default current_timestamp,
    CONSTRAINT fk_shc_user_role_user FOREIGN KEY (user_id) REFERENCES shc_portal.shc_user (id),
    CONSTRAINT fk_shc_user_role_role FOREIGN KEY (role_id) REFERENCES shc_portal.shc_role (id)
);
GO

ALTER TABLE shc_portal.shc_user DROP CONSTRAINT fk_shc_user_role;
ALTER TABLE shc_portal.shc_user DROP COLUMN role_id;
GO

if not exists (select * from sys.tables where name = 'shc_country_lk')
create table shc_portal.shc_country_lk
(
    id                   int PRIMARY KEY NOT NULL identity(1,1),
    code                 VARCHAR(20)     NOT NULL,
    nic_code             INT             NOT NULL,
    lang                 VARCHAR(45)     NOT NULL,
    label                NVARCHAR(50) NOT NULL,
    country_phone_prefix varchar(17)     NOT NULL,
    creation_date        smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint country_lk_unique unique (code ASC, lang ASC)
);
GO

if not exists (select * from sys.tables where name = 'shc_data_segment')
create table shc_portal.shc_data_segment
(
    id                 int PRIMARY KEY NOT NULL identity (1,1),
    template_file_name NVARCHAR(100) NOT NULL,
    label_ar           NVARCHAR(100) NOT NULL,
    label_en           VARCHAR(100)    NOT NULL,
    creation_date      smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date        smalldatetime NULL,
);
GO

if not exists (select * from sys.tables where name = 'shc_decision_rule')
create table shc_portal.shc_decision_rule
(
    id              int PRIMARY KEY NOT NULL identity(1,1),
    dmn             NVARCHAR(max) NOT NULL,
    data_segment_id INT             NOT NULL,
    label_ar        NVARCHAR(50) NOT NULL,
    label_en        VARCHAR(50)     NOT NULL,
    creation_date   smalldatetime   not null default current_timestamp,
    update_date     smalldatetime null,
    CONSTRAINT fk_decision_rule_segment FOREIGN KEY (data_segment_id) REFERENCES shc_portal.shc_data_segment (id)
);
GO

if not exists (select * from sys.tables where name = 'shc_data_request_status_lk')
create table shc_portal.shc_data_request_status_lk
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    label_ar      NVARCHAR(50) NOT NULL,
    label_en      VARCHAR(50)     NOT NULL,
    creation_date smalldatetime   not null default current_timestamp
);
GO

if not exists (select * from sys.tables where name = 'shc_data_request')
create table shc_portal.shc_data_request
(
    id                   int PRIMARY KEY NOT NULL identity(1,1),
    reference_number     NVARCHAR(48) NOT NULL,
    channel              NVARCHAR(20) NOT NULL,
    data_segment_id      INT             NOT NULL,
    item_count           INT NULL,
    error_count          INT NULL DEFAULT 0,
    original_source_path NVARCHAR(256) NOT NULL,
    error_file_path      VARCHAR(256) NULL,
    status_id            INT             NOT NULL,
    creation_date        smalldatetime   not null default current_timestamp,
    update_date          smalldatetime null,
    CONSTRAINT fk_data_request_segment FOREIGN KEY (data_segment_id) REFERENCES shc_portal.shc_data_segment (id),
    CONSTRAINT fk_data_request_status_lk FOREIGN KEY (status_id) REFERENCES shc_portal.shc_data_request_status_lk (id)
);
GO

if not exists (select * from sys.tables where name = 'shc_data_request_record')
create table shc_portal.shc_data_request_record
(
    id                               int PRIMARY KEY NOT NULL identity(1,1),
    create_data_request_id           int NULL,
    last_update_data_request_id      int NULL,
    create_data_request_row_num      int NULL,
    last_update_data_request_row_num int NULL,
    item_id                          int             NOT NULL,
    creation_date                    smalldatetime   not null default current_timestamp,
    update_date                      smalldatetime NULL,
    CONSTRAINT fk_data_record_data_request_create FOREIGN KEY (create_data_request_id) REFERENCES shc_portal.shc_data_request (id),
    CONSTRAINT fk_data_record_data_request_update FOREIGN KEY (last_update_data_request_id) REFERENCES shc_portal.shc_data_request (id)
);
GO

if not exists (select * from sys.tables where name = 'shc_applicant')
create table shc_portal.shc_applicant
(
    id                      int PRIMARY KEY NOT NULL identity(1,1),
    gender                  VARCHAR(1) NULL,
    nationality_code        VARCHAR(20) NULL,
    id_number               VARCHAR(16) NULL,
    id_number_original      VARCHAR(30) NULL,
    passport_number         VARCHAR(30) NULL,
    date_of_birth_gregorian DATE            NOT NULL,
    date_of_birth_hijri     INT NULL,
    full_name_ar            NVARCHAR(150) NULL,
    full_name_en            VARCHAR(150) NULL,
    full_name_origin        NVARCHAR(150) NULL,
    marital_status_code     VARCHAR(20) NULL,
    photo                   varchar(max) NULL,
    biometric_data_finger varchar(max) NULL,
    biometric_data_face varchar(max) NULL,
    education_level_code varchar(20) NULL,
    data_request_record_id int NULL,
    status INT NOT NULL,
    creation_date smalldatetime not null default current_timestamp,
    update_date smalldatetime NULL,
    CONSTRAINT fk_applicant_data_request_record FOREIGN KEY (data_request_record_id) REFERENCES shc_portal.shc_data_request_record (id)
);
GO

if not exists (select * from sys.tables where name = 'shc_applicant_relative')
create table shc_portal.shc_applicant_relative
(
    id                     int PRIMARY KEY NOT NULL identity(1,1),
    relationship_code      VARCHAR(20)     NOT NULL,
    applicant_id           int             NOT NULL,
    relative_applicant_id  int             NOT NULL,
    data_request_record_id int NULL,
    creation_date          smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date            smalldatetime NULL,
    CONSTRAINT fk_applicant_relative_applicant FOREIGN KEY (applicant_id) REFERENCES shc_portal.shc_applicant (id),
    CONSTRAINT fk_applicant_relative_applicant_self FOREIGN KEY (relative_applicant_id) REFERENCES shc_portal.shc_applicant (id),
    CONSTRAINT fk_applicant_relative_data_request_record FOREIGN KEY (data_request_record_id) REFERENCES shc_portal.shc_data_request_record (id)
);
GO

if not exists (select * from sys.tables where name = 'shc_applicant_contact')
create table shc_portal.shc_applicant_contact
(
    id                  int PRIMARY KEY NOT NULL identity (1,1),
    applicant_id        int             NOT NULL,
    language_list       NVARCHAR(50) NULL,
    email               VARCHAR(50) NULL,
    local_mobile_number VARCHAR(20) NULL,
    intl_mobile_number  VARCHAR(20) NULL,
    country_code        VARCHAR(20)     NOT NULL,
    street_name         NVARCHAR(100) NULL,
    district_name       NVARCHAR(50) NULL,
    city_name           NVARCHAR(50) NULL,
    building_number     VARCHAR(30) NULL,
    postal_code         VARCHAR(30) NULL,
    creation_date       smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date         smalldatetime NULL,
    CONSTRAINT fk_applicant_contact_applicant FOREIGN KEY (applicant_id) REFERENCES shc_portal.shc_applicant (id)
);
GO

if not exists (select * from sys.tables where name = 'shc_applicant_digital_id')
create table shc_portal.shc_applicant_digital_id
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    uin           VARCHAR(45)     NOT NULL,
    applicant_id  int             NOT NULL,
    creation_date smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_applicant_digital_id_applicant FOREIGN KEY (applicant_id) REFERENCES shc_portal.shc_applicant (id),
    CONSTRAINT applicant_digital_id_unique unique (uin)
);
GO

if not exists (select * from sys.tables where name = 'shc_ritual_type_lk')
create table shc_portal.shc_ritual_type_lk
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint ritual_type_lk_unique unique (code ASC, lang ASC)
);
GO

if not exists (select * from sys.tables where name = 'shc_applicant_ritual')
create table shc_portal.shc_applicant_ritual
(
    id                     int PRIMARY KEY NOT NULL identity(1,1),
    applicant_id           int             NOT NULL,
    hamlah_package_code    VARCHAR(20) NULL,
    tafweej_code           VARCHAR(20) NULL,
    hijri_season           INT             NOT NULL,
    date_start_gregorian   DATE            NOT NULL,
    date_end_gregorian     DATE            NOT NULL,
    date_start_hijri       INT             NOT NULL,
    date_end_hijri         INT             NOT NULL,
    type_code              VARCHAR(20)     NOT NULL,
    visa_number            VARCHAR(50) NULL,
    permit_number          VARCHAR(50)     NOT NULL,
    insurance_number       VARCHAR(50)     NOT NULL,
    border_number          VARCHAR(20) NULL,
    zone_code              VARCHAR(20) NULL,
    group_code             VARCHAR(20) NULL,
    unit_code              VARCHAR(20) NULL,
    data_request_record_id int NULL,
    creation_date          smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date            smalldatetime NULL,
    CONSTRAINT fk_ritual_data_request_record FOREIGN KEY (data_request_record_id) REFERENCES shc_portal.shc_data_request_record (id),
    CONSTRAINT fk_applicant_ritual_applicant FOREIGN KEY (applicant_id) REFERENCES shc_portal.shc_applicant (id)
);

if not exists (select * from sys.tables where name = 'shc_card_batch')
create table shc_portal.shc_card_batch
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    number        VARCHAR(20)     NOT NULL,
    creation_date smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT card_batch_unique unique (number)
);
GO


if not exists (select * from sys.tables where name = 'shc_card_status_lk')
create table shc_portal.shc_card_status_lk
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint card_status_lk_unique unique (code ASC, lang ASC)
);
GO

if not exists (select * from sys.tables where name = 'shc_applicant_card')
create table shc_portal.shc_applicant_card
(
    id                  int PRIMARY KEY NOT NULL identity(1,1),
    applicant_ritual_id int             NOT NULL,
    reference_number    VARCHAR(20) NULL,
    batch_id            int NULL,
    status_code         VARCHAR(20)     NOT NULL,
    creation_date       smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date         smalldatetime NULL,
    CONSTRAINT fk_applicant_card_applicant_ritual FOREIGN KEY (applicant_ritual_id) REFERENCES shc_portal.shc_applicant_ritual (id),
    CONSTRAINT fk_applicant_card_batch FOREIGN KEY (batch_id) REFERENCES shc_portal.shc_card_batch (id)
);
GO

if not exists (select * from sys.tables where name = 'shc_applicant_health')
create table shc_portal.shc_applicant_health
(
    id                      int PRIMARY KEY NOT NULL identity(1,1),
    applicant_id            int             NOT NULL,
    blood_type              VARCHAR(3) NULL,
    insurance_policy_number VARCHAR(50) NULL,
    has_special_needs       bit                      default 0,
    data_request_record_id  int NULL,
    creation_date           smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date             smalldatetime NULL,
    CONSTRAINT fk_applicant_health_data_request_record FOREIGN KEY (data_request_record_id) REFERENCES shc_portal.shc_data_request_record (id),
    CONSTRAINT fk_applicant_health_applicant FOREIGN KEY (applicant_id) REFERENCES shc_portal.shc_applicant (id)
);
GO

if not exists (select * from sys.tables where name = 'shc_health_special_needs_type_lk')
create table shc_portal.shc_health_special_needs_type_lk
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date smalldatetime NULL DEFAULT CURRENT_TIMESTAMP,
    constraint health_special_needs_type_lk_unique unique (code ASC, lang ASC)
);
GO

if not exists (select * from sys.tables where name = 'shc_applicant_health_special_needs')
create table shc_portal.shc_applicant_health_special_needs
(
    id                     int PRIMARY KEY NOT NULL identity(1,1),
    applicant_health_id    int             NOT NULL,
    special_need_type_code VARCHAR(20)     NOT NULL,
    creation_date          smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_applicant_health_special_needs_health FOREIGN KEY (applicant_health_id) REFERENCES shc_portal.shc_applicant_health (id)
);
GO

if not exists (select * from sys.tables where name = 'shc_health_immunization_lk')
create table shc_portal.shc_health_immunization_lk
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint health_immunization_lk_unique unique (code ASC, lang ASC)
);
GO

if not exists (select * from sys.tables where name = 'shc_applicant_health_immunization')
create table shc_portal.shc_applicant_health_immunization
(
    id                     int PRIMARY KEY NOT NULL identity(1,1),
    applicant_health_id    int             NOT NULL,
    immunization_code      VARCHAR(20)     NOT NULL,
    immunization_date      smalldatetime   NOT NULL,
    mandatory              BIT             NOT NULL,
    data_request_record_id int NULL,
    creation_date          smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date            smalldatetime NULL,
    CONSTRAINT fk_applicant_health_immunization_data_request_record FOREIGN KEY (data_request_record_id) REFERENCES shc_portal.shc_data_request_record (id),
    CONSTRAINT fk_applicant_health_immunization_health FOREIGN KEY (applicant_health_id) REFERENCES shc_portal.shc_applicant_health (id)
);
GO

if not exists (select * from sys.tables where name = 'shc_applicant_health_disease')
create table shc_portal.shc_applicant_health_disease
(
    id                     int PRIMARY KEY NOT NULL identity(1,1),
    applicant_health_id    int             NOT NULL,
    label_ar               NVARCHAR(50) NOT NULL,
    label_en               VARCHAR(50)     NOT NULL,
    data_request_record_id int NULL,
    creation_date          smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date            smalldatetime NULL,
    CONSTRAINT fk_applicant_health_disease_data_request_record FOREIGN KEY (data_request_record_id) REFERENCES shc_portal.shc_data_request_record (id),
    CONSTRAINT fk_applicant_health_disease_health FOREIGN KEY (applicant_health_id) REFERENCES shc_portal.shc_applicant_health (id)
);
GO

if not exists (select * from sys.tables where name = 'shc_relative_relationship_lk')
create table shc_portal.shc_relative_relationship_lk
(
    id          int PRIMARY KEY NOT NULL identity(1,1),
    code        VARCHAR(20)     NOT NULL,
    lang        VARCHAR(45)     NOT NULL,
    label       NVARCHAR(50) NOT NULL,
    creation_date smalldatetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT relative_relationship_lk_unique unique (code ASC, lang ASC)
);
GO

if not exists (select * from sys.tables where name = 'shc_marital_status_lk')
create table shc_portal.shc_marital_status_lk
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint marital_status_lk_unique unique (code ASC, lang ASC)
);
GO

if not exists (select * from sys.tables where name = 'shc_language_lk')
create table shc_portal.shc_language_lk
(
    id            INT PRIMARY KEY NOT NULL IDENTITY(1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint language_lk_unique unique (code ASC, lang ASC)
);
GO
-- PRINT REQUEST TABLES
if not exists (select * from sys.tables where name = 'shc_print_request_status_lk')
create table shc_portal.shc_print_request_status_lk
(
    id            INT PRIMARY KEY NOT NULL IDENTITY(1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint print_request_status_lk_unique unique (code ASC, lang ASC)
);
GO

if not exists (select * from sys.tables where name = 'shc_print_batch_type_lk')
create table shc_portal.shc_print_batch_type_lk
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    label_ar      nvarchar(50) NOT NULL,
    label_en      nvarchar(50) NOT NULL,
    code          nvarchar(50) NOT NULL,
    creation_date smalldatetime   not null default current_timestamp
        constraint print_batch_type_lk_unique unique (code ASC)
);
GO

if not exists (select * from sys.tables where name = 'shc_print_request')
create table shc_portal.shc_print_request
(
    id                INT PRIMARY KEY NOT NULL IDENTITY(1,1),
    reference_number  VARCHAR(20)     NOT NULL,
    status_code       VARCHAR(20)     NOT NULL,
    update_date       smalldatetime   NULL,
    confirmation_date smalldatetime,
    creation_date     smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP
);
GO

if not exists (select * from sys.tables where name = 'shc_print_request_card')
create table shc_portal.shc_print_request_card
(
    id               INT PRIMARY KEY NOT NULL IDENTITY(1,1),
    print_request_id INT             NOT NULL,
    card_id          INT             NOT NULL,
    creation_date    smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint fk_print_request_card_print_request foreign key (print_request_id) references shc_portal.shc_print_request (id),
    constraint fk_print_request_card_applicant_card foreign key (card_id) references shc_portal.shc_applicant_card (id)
);
GO

if not exists (select * from sys.tables where name = 'shc_print_request_batch')
create table shc_portal.shc_print_request_batch
(
    id                INT PRIMARY KEY NOT NULL IDENTITY(1,1),
    print_request_id  INT             NOT NULL,
    sequence_number   INT             NOT NULL,
    batch_type_codes  VARCHAR(255),
    batch_type_values VARCHAR(255),
    creation_date     smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint fk_print_request_batch_print_request foreign key (print_request_id) references shc_portal.shc_print_request (id)
);
GO

if not exists (select * from sys.tables where name = 'shc_print_request_batch_card')
create table shc_portal.shc_print_request_batch_card
(
    id                     INT PRIMARY KEY NOT NULL IDENTITY (1,1),
    print_request_batch_id INT             NOT NULL,
    card_id                INT             NOT NULL,
    creation_date          smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint fk_print_request_batch_card_print_request_batch foreign key (print_request_batch_id) references shc_portal.shc_print_request_batch (id),
    constraint fk_print_request_batch_card_card foreign key (card_id) references shc_portal.shc_applicant_card (id)
);
GO

if not exists(select * from sys.tables where name = 'shc_scheduled_tasks_lock')
create table shc_portal.shc_scheduled_tasks_lock
(
    name       varchar(255) NOT NULL,
    lock_until datetime NULL,
    locked_at  datetime NULL,
    locked_by  varchar(255) NOT NULL,
);
GO

if not exists (select * from sys.tables where name = 'shc_ritual_zone')
create table shc_portal.shc_ritual_zone
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    season_id     INT NULL,
    creation_date smalldatetime NULL DEFAULT CURRENT_TIMESTAMP,
    constraint ritual_zone_unique unique (code ASC, lang ASC)
);
GO

if not exists (select * from sys.tables where name = 'shc_ritual_unit')
create table shc_portal.shc_ritual_unit
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    season_id     INT NULL,
    creation_date smalldatetime NULL DEFAULT CURRENT_TIMESTAMP,
    constraint ritual_unit_unique unique (code ASC, lang ASC)
);
GO

if not exists (select * from sys.tables where name = 'shc_ritual_group')
create table shc_portal.shc_ritual_group
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    season_id     INT NULL,
    creation_date smalldatetime NULL DEFAULT CURRENT_TIMESTAMP,
    constraint ritual_group_unique unique (code ASC, lang ASC)
);
GO
