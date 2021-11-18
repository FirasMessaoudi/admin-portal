USE
shc_portal
GO
ALTER TABLE shc_portal.shc_applicant_health
    ADD applicant_ritual_id int;
GO

ALTER TABLE shc_portal.shc_applicant_health
    ADD CONSTRAINT fk_applicant_health_applicant_ritual FOREIGN KEY (applicant_ritual_id) REFERENCES shc_portal.shc_applicant_ritual (id);
GO

-- update audit log table to handle big number for user id
ALTER TABLE shc_portal.shc_audit_log ALTER COLUMN user_id_number bigint;
GO

ALTER TABLE shc_portal.shc_applicant_contact
    ADD applicant_ritual_id int;
GO
ALTER TABLE shc_portal.shc_applicant_contact
    ADD CONSTRAINT fk_applicant_contact_applicant_ritual
        FOREIGN KEY (applicant_ritual_id) REFERENCES shc_portal.shc_applicant_ritual (id);
GO


ALTER TABLE shc_portal.shc_applicant_relative
    ADD applicant_ritual_id int;
GO
ALTER TABLE shc_portal.shc_applicant_relative
    ADD CONSTRAINT fk_applicant_relative_applicant_ritual
        FOREIGN KEY (applicant_ritual_id) REFERENCES shc_portal.shc_applicant_ritual (id);
GO

GO
USE shc_portal
GO
ALTER TABLE shc_portal.shc_applicant_ritual
    ADD bus_number nvarchar(20);

ALTER TABLE shc_portal.shc_applicant_ritual
    ADD seat_number nvarchar(10);
GO

USE
shc_portal
GO

/*---------------------------------------------------
--  ddl for shc_company_ritual_step_lk table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_company_ritual_step_lk')
CREATE TABLE shc_portal.shc_company_ritual_step_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          varchar(20)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(50) NOT NULL,
    description   varchar(255)  NOT NULL,
    creation_date smalldatetime NOT NULL DEFAULT current_timestamp,
    CONSTRAINT tafweej_step_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

/*---------------------------------------------------
--  ddl for shc_applicant_group_type_lk table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_applicant_group_type_lk')
CREATE TABLE shc_portal.shc_applicant_group_type_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          varchar(20)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(50) NOT NULL,
    creation_date smalldatetime NOT NULL DEFAULT current_timestamp,
    CONSTRAINT applicant_group_type_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

/*---------------------------------------------------
--  ddl for shc_transportation_type_lk table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_transportation_type_lk')
CREATE TABLE shc_portal.shc_transportation_type_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          varchar(20)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(50) NOT NULL,
    creation_date smalldatetime NOT NULL DEFAULT current_timestamp,
    CONSTRAINT transportation_type_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

/*---------------------------------------------------
--  ddl for shc_housing_type_lk table
---------------------------------------------------*/

if not exists(select * from sys.tables where name = 'shc_housing_type_lk')
CREATE TABLE shc_portal.shc_housing_type_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          varchar(20)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(50) NOT NULL,
    creation_date smalldatetime NOT NULL DEFAULT current_timestamp,
    CONSTRAINT housing_type_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

/*---------------------------------------------------
--  ddl for shc_housing_category_lk table
---------------------------------------------------*/

if not exists(select * from sys.tables where name = 'shc_housing_category_lk')
CREATE TABLE shc_portal.shc_housing_category_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          varchar(20)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(50) NOT NULL,
    type_code     varchar(20) NULL,
    creation_date smalldatetime NOT NULL DEFAULT current_timestamp,
    CONSTRAINT housing_category_lk_unique UNIQUE (code ASC, lang ASC),
);
GO



/*---------------------------------------------------
--  ddl for shc_housing_zone table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_housing_zone')
CREATE TABLE shc_portal.shc_housing_zone
(
    id       int         NOT NULL PRIMARY KEY IDENTITY (1,1),
    label_ar nvarchar(45) NOT NULL,
    label_en varchar(45) NOT NULL,
    color    varchar(45) NOT NULL,
);
GO

/*---------------------------------------------------
--  ddl for shc_package_type_lk table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_package_type_lk')
CREATE TABLE shc_portal.shc_package_type_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          varchar(20)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(50) NOT NULL,
    creation_date smalldatetime NOT NULL DEFAULT current_timestamp,
    CONSTRAINT package_type_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

/*---------------------------------------------------
--  ddl for shc_ritual_package table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_ritual_package')
CREATE TABLE shc_portal.shc_ritual_package
(
    id               int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    type_code        varchar(20)   NOT NULL,
    reference_number varchar(45)   NOT NULL,
    price            FLOAT NULL,
    departure_city   varchar(45) NULL,
    country_id       int NULL,
    creation_date    smalldatetime NOT NULL DEFAULT current_timestamp,
    update_date      smalldatetime NULL,
);
GO

/*---------------------------------------------------
--  ddl for shc_package_housing table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_package_housing')
CREATE TABLE shc_portal.shc_package_housing
(
    id               int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    package_id       int           NOT NULL,
    zone_id          int NULL,
    reference_number varchar(45) NULL,
    category_code    varchar(45) NULL,
    type_code        varchar(20) NULL,
    site_code        varchar(20) NULL,
    location_name_ar nvarchar(100) NOT NULL,
    location_name_en varchar(100)  NOT NULL,
    validity_start   smalldatetime NULL,
    validity_end     smalldatetime NULL,
    address_ar       nvarchar(100) NULL,
    address_en       varchar(50) NULL,
    is_default       bit NULL,
    lat              varchar(20) NULL,
    lng              varchar(20) NULL,
    creation_date    smalldatetime NOT NULL default current_timestamp,
    update_date      smalldatetime NULL,
    CONSTRAINT fk_shc_package_housing_package FOREIGN KEY (package_id) REFERENCES shc_portal.shc_ritual_package (id),
    CONSTRAINT fk_shc_package_housing_zone FOREIGN KEY (zone_id) REFERENCES shc_portal.shc_housing_zone (id),
);
GO

/*---------------------------------------------------
--  ddl for shc_package_catering table
---------------------------------------------------*/
CREATE TABLE shc_portal.shc_package_catering
(
    id                 int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    package_housing_id int           NOT NULL,
    meal_code          nvarchar(50) NULL,
    meal_time          Time          NOT NULL,
    meal_description   varchar(256)  NOT NULL,
    type               varchar(45) NULL,
    description_ar     nvarchar(250) NULL,
    description_en     varchar(125) NULL,
    creation_date      smalldatetime NOT NULL DEFAULT current_timestamp,
    update_date        smalldatetime NULL,
    CONSTRAINT fk_shc_package_catering_housing FOREIGN KEY (package_housing_id) REFERENCES shc_portal.shc_package_housing (id)
);
GO

/*---------------------------------------------------
--  ddl for shc_applicant_package table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_applicant_package')
CREATE TABLE shc_portal.shc_applicant_package
(
    id                int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    ritual_package_id int           NOT NULL,
    applicant_uin     BIGINT        NOT NULL,
    creation_date     smalldatetime NOT NULL DEFAULT current_timestamp,
    update_date       smalldatetime NULL,
    CONSTRAINT fk_shc_applicant_package_ritual_package FOREIGN KEY (ritual_package_id) REFERENCES shc_portal.shc_ritual_package (id)
);
GO

/*---------------------------------------------------
--  ddl for shc_applicant_package_catering table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_applicant_package_catering')
CREATE TABLE shc_portal.shc_applicant_package_catering
(
    id                         int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    applicant_package_id       int           NOT NULL,
    ritual_package_catering_id int           NOT NULL,
    option_ar                  nvarchar(250) NULL,
    option_en                  varchar(125) NULL,
    creation_date              smalldatetime NOT NULL DEFAULT current_timestamp,
    update_date                smalldatetime NULL,
    CONSTRAINT fk_shc_applicant_package_catering_ritual_catering FOREIGN KEY (ritual_package_catering_id) REFERENCES shc_portal.shc_package_catering (id),
    CONSTRAINT fk_shc_applicant_package_catering_ritual_package FOREIGN KEY (applicant_package_id) REFERENCES shc_portal.shc_applicant_package (id)
);
GO

/*---------------------------------------------------
--  ddl for shc_package_transportation table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_package_transportation')
CREATE TABLE shc_portal.shc_package_transportation
(
    id                    int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    package_id            int           NOT NULL,
    type_code             varchar(20) NULL,
    validity_start        smalldatetime NULL,
    validity_end          smalldatetime NULL,
    location_from_name_ar nvarchar(255) NULL,
    location_from_name_en varchar(255) NULL,
    location_to_name_ar   nvarchar(255) NULL,
    location_to_name_en   varchar(255) NULL,
    ritual_step_code      varchar(20) NULL,
    creation_date         smalldatetime NOT NULL DEFAULT current_timestamp,
    update_date           smalldatetime NULL,
    CONSTRAINT fk_shc_package_transportation_package FOREIGN KEY (package_id) REFERENCES shc_portal.shc_ritual_package (id),
);
GO

/*---------------------------------------------------
--  ddl for shc_applicant_package_transportation table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_applicant_package_transportation')
CREATE TABLE shc_portal.shc_applicant_package_transportation
(
    id                               int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    applicant_package_id             int           NOT NULL,
    ritual_package_transportation_id int           NOT NULL,
    seat_number                      varchar(10) NULL,
    wagon_number                     varchar(20) NULL,
    vehicle_number                   varchar(20) NULL,
    creation_date                    smalldatetime NOT NULL DEFAULT current_timestamp,
    update_date                      smalldatetime NULL,
    CONSTRAINT fk_shc_applicant_package_transportation_applicant_package FOREIGN KEY (applicant_package_id) REFERENCES shc_portal.shc_applicant_package (id),
    CONSTRAINT fk_shc_applicant_package_transportation_ritual_package_transportation FOREIGN KEY (ritual_package_transportation_id) REFERENCES shc_portal.shc_package_transportation (id)
);
GO

/*---------------------------------------------------
--  ddl for shc_applicant_package_housing table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_applicant_package_housing')
CREATE TABLE shc_portal.shc_applicant_package_housing
(
    id                   int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    applicant_package_id int           NOT NULL,
    package_housing_id   int           NOT NULL,
    room_number          nvarchar(100) NULL,
    bed_number           nvarchar(100) NULL,
    creation_date        smalldatetime NOT NULL DEFAULT current_timestamp,
    update_date          smalldatetime NULL,
    CONSTRAINT fk_shc_applicant_package_housing_ritual_package FOREIGN KEY (applicant_package_id) REFERENCES shc_portal.shc_applicant_package (id),
    CONSTRAINT fk_shc_applicant_package_housing_ritual_package_housing FOREIGN KEY (package_housing_id) REFERENCES shc_portal.shc_package_housing (id)
);
GO

/*---------------------------------------------------
--  ddl for shc_ritual_season table
---------------------------------------------------*/

if not exists(select * from sys.tables where name = 'shc_ritual_season')
CREATE TABLE shc_portal.shc_ritual_season
(
    id               int         NOT NULL PRIMARY KEY IDENTITY (1,1),
    season_year      int         NOT NULL,
    ritual_type_code varchar(20) NOT NULL,
    season_start     INT         NOT NULL,
    season_end       INT         NOT NULL,
    active           bit         NOT NULL,
);
GO

/*---------------------------------------------------
--  ddl for shc_company table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_company')
CREATE TABLE shc_portal.shc_company
(
    id                         int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code                       varchar(20)   NOT NULL,
    label_ar                   nvarchar(50) NOT NULL,
    label_en                   varchar(25)   NOT NULL,
    mission_id                 int           NOT NULL,
    contact_number             varchar(20)   NOT NULL,
    website                    varchar(75) NULL,
    accreditation_organization varchar(45)   NOT NULL,
    accreditation_number       varchar(45)   NOT NULL,
    accreditation_date         smalldatetime NOT NULL,
    accreditation_expiry       smalldatetime NOT NULL,
    email                      varchar(45) NULL,
    creation_date              smalldatetime NOT NULL DEFAULT current_timestamp,
    update_date                smalldatetime NULL,
);
GO

/*---------------------------------------------------
--  ddl for shc_company_ritual_season table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_company_ritual_season')
CREATE TABLE shc_portal.shc_company_ritual_season
(
    id               int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    company_id       int           NOT NULL,
    ritual_season_id int           NOT NULL,
    active           bit           NOT NULL,
    season_start     INT           NOT NULL,
    season_end       INT           NOT NULL,
    total_quota      int NULL,
    air_quota        int NULL,
    sea_quota        int NULL,
    land_quota       int NULL,
    creation_date    smalldatetime NOT NULL DEFAULT current_timestamp,
    update_date      smalldatetime NULL,
    CONSTRAINT fk_shc_company_season_company FOREIGN KEY (company_id) REFERENCES shc_portal.shc_company (id),
    CONSTRAINT fk_shc_company_season_season FOREIGN KEY (ritual_season_id) REFERENCES shc_portal.shc_ritual_season (id)
);
GO

/*---------------------------------------------------
--  ddl for shc_company_staff table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_company_staff')
CREATE TABLE shc_portal.shc_company_staff
(
    id                       int         NOT NULL PRIMARY KEY IDENTITY (1,1),
    full_name_ar             nvarchar(255) NULL,
    full_name_en             varchar(255) NULL,
    id_number                int         NOT NULL,
    company_id               int         NOT NULL,
    title_code               varchar(45) NULL,
    mobile_number            varchar(20) NOT NULL,
    email                    varchar(255) NULL,
    company_ritual_season_id int NULL,
    creation_date            smalldatetime NULL DEFAULT current_timestamp,
    update_date              smalldatetime NULL,
    CONSTRAINT fk_shc_company_representative_company FOREIGN KEY (company_id) REFERENCES shc_portal.shc_company (id),
    CONSTRAINT fk_shc_company_staff_company_season FOREIGN KEY (company_ritual_season_id) REFERENCES shc_portal.shc_company_ritual_season (id)
);
GO

/*---------------------------------------------------
--  ddl for shc_company_season_package table
---------------------------------------------------*/

if not exists(select * from sys.tables where name = 'shc_company_season_package')
CREATE TABLE shc_portal.shc_company_season_package
(
    id                       int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    company_ritual_season_id int           NOT NULL,
    basic_package_id         int           NOT NULL,
    creation_date            smalldatetime NOT NULL DEFAULT current_timestamp,
    update_date              smalldatetime NULL,
    CONSTRAINT fk_shc_company_season_package_company_season FOREIGN KEY (company_ritual_season_id) REFERENCES shc_portal.shc_company_ritual_season (id),
    CONSTRAINT fk_shc_company_season_package_package FOREIGN KEY (basic_package_id) REFERENCES shc_portal.shc_ritual_package (id)
);
GO

/*---------------------------------------------------
--  ddl for shc_applicant_group table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_applicant_group')
CREATE TABLE shc_portal.shc_applicant_group
(
    id                             int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    local_office_id                int NULL,
    reference_number               varchar(45)   NOT NULL,
    arrival_date                   smalldatetime NULL,
    departure_date                 smalldatetime NULL,
    group_leader_id                int NULL,
    company_ritual_season_id       int NULL,
    group_type_code                varchar(20) NULL,
    entry_transportation_type_code varchar(20) NULL,
    creation_date                  smalldatetime NOT NULL,
    update_date                    smalldatetime NULL,
    CONSTRAINT fk_shc_applicant_group_company_season FOREIGN KEY (company_ritual_season_id) REFERENCES shc_portal.shc_company_ritual_season (id),
    CONSTRAINT fk_shc_applicant_group_representative FOREIGN KEY (group_leader_id) REFERENCES shc_portal.shc_company_staff (id)
);
GO

/*---------------------------------------------------
--  ddl for shc_company_ritual_step table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_company_ritual_step')
CREATE TABLE shc_portal.shc_company_ritual_step
(
    id                       int            NOT NULL PRIMARY KEY IDENTITY (1,1),
    applicant_group_id       int            NOT NULL,
    transportation_type_code varchar(45) NULL,
    step_index               int            NOT NULL,
    step_code                varchar(20)    NOT NULL,
    time                     smalldatetime  NOT NULL,
    location_lat             DECIMAL(10, 8) NOT NULL,
    location_lng             DECIMAL(11, 8) NOT NULL,
    location_name_ar         nvarchar(100) NOT NULL,
    location_name_en         varchar(100)   NOT NULL,
    creation_date            smalldatetime  NOT NULL DEFAULT current_timestamp,
    update_date              smalldatetime NULL,
    CONSTRAINT fk_shc_company_ritual_step_applicant_group FOREIGN KEY (applicant_group_id) REFERENCES shc_portal.shc_applicant_group (id),
);
GO

/*---------------------------------------------------
--  ddl for shc_company_staff_title_lk table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_company_staff_title_lk')
CREATE TABLE shc_portal.shc_company_staff_title_lk
(
    id            int         NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          varchar(45) NOT NULL,
    lang          varchar(45) NOT NULL,
    label         varchar(45) NOT NULL,
    creation_date smalldatetime NULL,
);
GO

/*---------------------------------------------------
--  ddl for shc_group_applicant_list table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_group_applicant_list')
CREATE TABLE shc_portal.shc_group_applicant_list
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    group_id      int           NOT NULL,
    applicant_uin varchar(45)   NOT NULL,
    creation_date smalldatetime NOT NULL DEFAULT current_timestamp,
    CONSTRAINT group_applicant_list_lk_unique UNIQUE (group_id ASC),
    CONSTRAINT fk_shc_applicant_group_list_group FOREIGN KEY (group_id) REFERENCES shc_portal.shc_applicant_group (id)
);
GO

/*---------------------------------------------------
--  ddl for shc_housing_site_lk table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_housing_site_lk')
CREATE TABLE shc_portal.shc_housing_site_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          varchar(20)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(50) NOT NULL,
    creation_date smalldatetime NOT NULL DEFAULT current_timestamp,
    CONSTRAINT housing_site_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

ALTER TABLE shc_portal.shc_ritual_package
    ADD company_ritual_season_id int NULL CONSTRAINT fk_shc_ritual_package_company_season FOREIGN KEY (company_ritual_season_id) REFERENCES shc_portal.shc_company_ritual_season(id);


ALTER TABLE shc_portal.shc_applicant_ritual
    ADD applicant_package_id int NULL
        CONSTRAINT fk_shc_applicant_ritual_applicant_package FOREIGN KEY (applicant_package_id) REFERENCES shc_portal.shc_applicant_package (id);
GO

ALTER TABLE shc_portal.shc_company_ritual_step_lk
    ADD summary VARCHAR(100);
GO

ALTER TABLE shc_portal.shc_print_request
    ADD description NVARCHAR(150) NULL;
GO

ALTER TABLE shc_portal.shc_group_applicant_list
DROP
CONSTRAINT group_applicant_list_lk_unique;

GO

alter table shc_portal.shc_applicant_ritual
drop
column hamlah_package_code
go

alter table shc_portal.shc_applicant_ritual
drop
column tafweej_code
go


alter table shc_portal.shc_applicant_ritual
drop
column hijri_season
go


alter table shc_portal.shc_applicant_ritual
drop
column date_start_gregorian
go


alter table shc_portal.shc_applicant_ritual
drop
column date_end_gregorian
go

alter table shc_portal.shc_applicant_ritual
drop
column date_start_hijri
go


alter table shc_portal.shc_applicant_ritual
drop
column date_end_hijri
go


alter table shc_portal.shc_applicant_ritual
drop
column type_code
go

alter table shc_portal.shc_applicant_ritual alter column permit_number varchar(50) null
go

alter table shc_portal.shc_applicant_ritual alter column insurance_number varchar(50) null
go


alter table shc_portal.shc_applicant_ritual
drop
column zone_code
go


alter table shc_portal.shc_applicant_ritual
drop
column group_code
go


alter table shc_portal.shc_applicant_ritual
drop
column unit_code
go


alter table shc_portal.shc_applicant_ritual
drop
column bus_number
go


alter table shc_portal.shc_applicant_ritual
drop
column seat_number
go

alter table shc_portal.shc_company_staff_title_lk alter column label nvarchar(50) not null
GO

-- notification tables
if not exists(select * from sys.tables where name = 'shc_notification_template_name_lk')
CREATE TABLE shc_portal.shc_notification_template_name_lk
(
    id            INT           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          VARCHAR(20)   NOT NULL,
    lang          VARCHAR(45)   NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date SMALLDATETIME NOT NULL DEFAULT current_timestamp,
    CONSTRAINT notification_template_name_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

if not exists(select * from sys.tables where name = 'shc_notification_processing_status_lk')
CREATE TABLE shc_portal.shc_notification_processing_status_lk
(
    id            INT           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          VARCHAR(20)   NOT NULL,
    description   NVARCHAR(50) NOT NULL,
    creation_date SMALLDATETIME NOT NULL DEFAULT current_timestamp,
    CONSTRAINT notification_processing_status_lk_unique UNIQUE (code ASC)
);
GO

if not exists(select * from sys.tables where name = 'shc_notification_template_status_lk')
CREATE TABLE shc_portal.shc_notification_template_status_lk
(
    id            INT           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          VARCHAR(20)   NOT NULL,
    lang          VARCHAR(45)   NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date SMALLDATETIME NOT NULL DEFAULT current_timestamp,
    CONSTRAINT notification_template_status_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

if not exists(select * from sys.tables where name = 'shc_notification_template_type_lk')
CREATE TABLE shc_portal.shc_notification_template_type_lk
(
    id            INT           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          VARCHAR(20)   NOT NULL,
    lang          VARCHAR(45)   NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date SMALLDATETIME NOT NULL DEFAULT current_timestamp,
    CONSTRAINT notification_template_type_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

if not exists(select * from sys.tables where name = 'shc_notification_category_lk')
CREATE TABLE shc_portal.shc_notification_category_lk
(
    id            INT           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          VARCHAR(20)   NOT NULL,
    lang          VARCHAR(45)   NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date SMALLDATETIME NOT NULL DEFAULT current_timestamp,
    CONSTRAINT notification_category_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

if not exists(select * from sys.tables where name = 'shc_notification_template')
CREATE TABLE shc_portal.shc_notification_template
(
    id              INT           NOT NULL PRIMARY KEY IDENTITY (1,1),
    category_code   VARCHAR(20)   NOT NULL,
    name_code       VARCHAR(20)   NULL,
    status_code     VARCHAR(20)   NOT NULL,
    type_code       VARCHAR(20)   NOT NULL,
    description     NVARCHAR(500) NULL,
    important       BIT           NOT NULL,
    action_required BIT           NOT NULL,
    enabled         BIT           NOT NULL,
    user_specific   BIT           NOT NULL,
    sending_date    SMALLDATETIME NULL,
    force_sending   BIT           NOT NULL,
    creation_date   SMALLDATETIME NOT NULL DEFAULT current_timestamp,
    update_date     SMALLDATETIME NULL
);
GO

if not exists(select * from sys.tables where name = 'shc_notification_template_content')
CREATE TABLE shc_portal.shc_notification_template_content
(
    id                       INT            NOT NULL PRIMARY KEY IDENTITY (1,1),
    notification_template_id INT            NOT NULL,
    lang                     VARCHAR(45)    NOT NULL,
    title                    NVARCHAR(100)  NOT NULL,
    body                     NVARCHAR(1000) NOT NULL,
    action_label             NVARCHAR(45)   NULL,
    creation_date            SMALLDATETIME  NOT NULL DEFAULT current_timestamp,
    update_date              SMALLDATETIME  NULL,
    CONSTRAINT fk_notification_template_content_notification_template FOREIGN KEY (notification_template_id) REFERENCES shc_portal.shc_notification_template (id)
);
GO

if not exists(select * from sys.tables where name = 'shc_notification_request')
CREATE TABLE shc_portal.shc_notification_request
(
    id                       INT           NOT NULL PRIMARY KEY IDENTITY (1,1),
    notification_template_id INT           NOT NULL,
    user_id                  VARCHAR(45)   NOT NULL,
    processing_status_id     INT           NOT NULL,
    user_lang                VARCHAR(45)   NOT NULL,
    sending_date             SMALLDATETIME NOT NULL,
    creation_date            SMALLDATETIME NOT NULL DEFAULT current_timestamp,
    update_date              SMALLDATETIME NULL,
    CONSTRAINT fk_notification_request_processing_status_lk FOREIGN KEY (processing_status_id) REFERENCES shc_portal.shc_notification_processing_status_lk (id),
    CONSTRAINT fk_notification_request_notification_template FOREIGN KEY (notification_template_id) REFERENCES shc_portal.shc_notification_template (id)
);
GO

if not exists(select * from sys.tables where name = 'shc_notification_template_parameter')
CREATE TABLE shc_portal.shc_notification_template_parameter
(
    id                       INT           NOT NULL PRIMARY KEY IDENTITY (1,1),
    notification_template_id INT           NOT NULL,
    parameter_name           VARCHAR(45)   NOT NULL,
    creation_date            SMALLDATETIME NOT NULL DEFAULT current_timestamp,
    CONSTRAINT fk_notification_template_parameter_notification_template FOREIGN KEY (notification_template_id) REFERENCES shc_portal.shc_notification_template (id)
);
GO

if not exists(select * from sys.tables where name = 'shc_notification_request_parameter_value')
CREATE TABLE shc_portal.shc_notification_request_parameter_value
(
    id                                    INT           NOT NULL PRIMARY KEY IDENTITY (1,1),
    notification_request_id               INT           NOT NULL,
    notification_template_parameter_id    INT           NOT NULL,
    notification_template_parameter_value NVARCHAR(255) NULL,
    creation_date                         SMALLDATETIME NOT NULL DEFAULT current_timestamp,
    CONSTRAINT fk_notification_param_value_notification_request FOREIGN KEY (notification_request_id) REFERENCES shc_portal.shc_notification_request (id),
    CONSTRAINT fk_notification_param_value_notification_template FOREIGN KEY (notification_template_parameter_id) REFERENCES shc_portal.shc_notification_template_parameter (id)
);
GO

if not exists(select * from sys.tables where name = 'shc_user_notification_status_lk')
CREATE TABLE shc_portal.shc_user_notification_status_lk
(
    id            INT           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          VARCHAR(20)   NOT NULL,
    lang          VARCHAR(45)   NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date SMALLDATETIME NOT NULL DEFAULT current_timestamp,
    CONSTRAINT user_notification_status_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

if not exists(select * from sys.tables where name = 'shc_user_notification')
CREATE TABLE shc_portal.shc_user_notification
(
    id                       INT            NOT NULL PRIMARY KEY IDENTITY (1,1),
    notification_template_id INT            NOT NULL,
    user_id                  VARCHAR(45)    NOT NULL,
    resolved_body            NVARCHAR(1000) NOT NULL,
    status_code              VARCHAR(20)    NOT NULL,
    creation_date            SMALLDATETIME  NOT NULL DEFAULT current_timestamp,
    update_date              SMALLDATETIME  NULL,
    CONSTRAINT fk_user_notification_notification_template FOREIGN KEY (notification_template_id) REFERENCES shc_portal.shc_notification_template (id)
);
GO


GO
ALTER TABLE shc_portal.shc_user_notification
    ADD user_lang varchar(45) not null;
GO


ALTER TABLE shc_portal.shc_notification_template
    ADD expiration_period_in_minutes INT;
GO


ALTER TABLE shc_portal.shc_applicant_digital_id
    ADD status_code varchar(20);
GO

ALTER TABLE shc_portal.shc_applicant
DROP
COLUMN status;
GO

if not exists (select * from sys.tables where name = 'shc_applicant_digital_id_status_lk')
CREATE TABLE shc_portal.shc_applicant_digital_id_status_lk
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint shc_applicant_digital_id_status_lk_unique unique (code ASC, lang ASC)
);
GO

if not exists(select * from sys.tables where name = 'shc_user_notification_category_preference')
CREATE TABLE shc_portal.shc_user_notification_category_preference
(
    id            int PRIMARY KEY NOT NULL identity (1,1),
    user_id       VARCHAR(45)     NOT NULL,
    category_code VARCHAR(20)     NOT NULL,
    enabled       bit             NOT NULL,
    creation_date smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date   SMALLDATETIME NULL
);
GO
alter table shc_portal.shc_applicant_package_transportation alter column vehicle_number nvarchar(50);
GO

if not exists (select * from sys.tables where name = 'shc_religious_occasions_day_lk')
create table shc_portal.shc_religious_occasions_day_lk
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint religious_occasions_day_lk_unique unique (code ASC, lang ASC)
);
GO

if not exists (select * from sys.tables where name = 'shc_meal_type_lk')
create table shc_portal.shc_meal_type_lk
(
    id            int PRIMARY KEY NOT NULL identity(1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50) NOT NULL,
    creation_date smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint shc_meal_type_lk_unique unique (code ASC, lang ASC)
);
GO

ALTER TABLE shc_portal.shc_applicant_package
    ADD start_date DATE;

ALTER TABLE shc_portal.shc_applicant_package
    ADD end_date DATE;
GO

alter table shc_portal.shc_company_ritual_step_lk alter column summary nvarchar(100);
GO


/*---------------------------------------------------
--  ddl for shc_user_card_status_audit table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_user_card_status_audit')
CREATE TABLE shc_portal.shc_user_card_status_audit


(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    user_id       int           NOT NULL,
    card_id       int           NOT NULL,
    uin           varchar(45)   NOT NULL,
    status_code   varchar(20)   NOT NULL,
    creation_date smalldatetime NOT NULL DEFAULT CURRENT_TIMESTAMP,

);
GO

ALTER TABLE shc_portal.shc_notification_category_lk
    ADD sample nvarchar(500);
GO
