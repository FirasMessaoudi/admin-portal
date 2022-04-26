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

ALTER TABLE shc_portal.shc_applicant_ritual
    ADD bus_number nvarchar(20);
GO

ALTER TABLE shc_portal.shc_applicant_ritual
    ADD seat_number nvarchar(10);
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
    label         nvarchar(50)  NOT NULL,
    description   nvarchar(255) NOT NULL,
    summary       nvarchar(100),
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
    label         nvarchar(50)  NOT NULL,
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
    label         nvarchar(50)  NOT NULL,
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
    label         nvarchar(50)  NOT NULL,
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
    label         nvarchar(50)  NOT NULL,
    type_code     varchar(20)   NULL,
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
    id       int          NOT NULL PRIMARY KEY IDENTITY (1,1),
    label_ar nvarchar(45) NOT NULL,
    label_en varchar(45)  NOT NULL,
    color    varchar(45)  NOT NULL,
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
    label         nvarchar(50)  NOT NULL,
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
    price            FLOAT         NULL,
    departure_city   varchar(45)   NULL,
    country_id       int           NULL,
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
    zone_id          int           NULL,
    reference_number varchar(45)   NULL,
    category_code    varchar(45)   NULL,
    type_code        varchar(20)   NULL,
    site_code        varchar(20)   NULL,
    location_name_ar nvarchar(100) NOT NULL,
    location_name_en varchar(100)  NOT NULL,
    validity_start   smalldatetime NULL,
    validity_end     smalldatetime NULL,
    address_ar       nvarchar(100) NULL,
    address_en       varchar(50)   NULL,
    is_default       bit           NULL,
    lat              varchar(20)   NULL,
    lng              varchar(20)   NULL,
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
    meal_code          nvarchar(50)  NULL,
    meal_time          Time          NOT NULL,
    meal_description   varchar(256)  NOT NULL,
    type               varchar(45) NULL,
    description_ar     nvarchar(250) NULL,
    description_en     varchar(125)  NULL,
    is_default         bit           NULL     DEFAULT 0,
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
    option_en                  varchar(125)  NULL,
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
    type_code             varchar(20)   NULL,
    validity_start        smalldatetime NULL,
    validity_end          smalldatetime NULL,
    location_from_name_ar nvarchar(255) NULL,
    location_from_name_en varchar(255)  NULL,
    location_to_name_ar   nvarchar(255) NULL,
    location_to_name_en   varchar(255)  NULL,
    ritual_step_code      varchar(20)   NULL,
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
    seat_number                      varchar(10)   NULL,
    wagon_number                     varchar(20)   NULL,
    vehicle_number                   varchar(20)   NULL,
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
    label_ar                   nvarchar(50)  NOT NULL,
    label_en                   varchar(25)   NOT NULL,
    mission_id                 int           NOT NULL,
    contact_number             varchar(20)   NOT NULL,
    website                    varchar(75)   NULL,
    accreditation_organization varchar(45)   NOT NULL,
    accreditation_number       varchar(45)   NOT NULL,
    accreditation_date         smalldatetime NOT NULL,
    accreditation_expiry       smalldatetime NOT NULL,
    email                      varchar(45)   NULL,
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
    total_quota      int           NULL,
    air_quota        int           NULL,
    sea_quota        int           NULL,
    land_quota       int           NULL,
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
    id                       int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    full_name_ar             nvarchar(255) NULL,
    full_name_en             varchar(255)  NULL,
    id_number                int           NOT NULL,
    company_id               int           NOT NULL,
    title_code               varchar(45)   NULL,
    mobile_number            varchar(20)   NOT NULL,
    email                    varchar(255)  NULL,
    company_ritual_season_id int           NULL,
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
    local_office_id                int           NULL,
    reference_number               varchar(45)   NOT NULL,
    arrival_date                   smalldatetime NULL,
    departure_date                 smalldatetime NULL,
    group_leader_id                int           NULL,
    company_ritual_season_id       int           NULL,
    group_type_code                varchar(20)   NULL,
    entry_transportation_type_code varchar(20)   NULL,
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
    transportation_type_code varchar(45)    NULL,
    step_index               int            NOT NULL,
    step_code                varchar(20)    NOT NULL,
    time                     smalldatetime  NOT NULL,
    location_lat             DECIMAL(10, 8) NOT NULL,
    location_lng             DECIMAL(11, 8) NOT NULL,
    location_name_ar         nvarchar(100)  NOT NULL,
    location_name_en         varchar(100)   NOT NULL,
    creation_date            smalldatetime  NOT NULL DEFAULT current_timestamp,
    update_date              smalldatetime  NULL,
    CONSTRAINT fk_shc_company_ritual_step_applicant_group FOREIGN KEY (applicant_group_id) REFERENCES shc_portal.shc_applicant_group (id),
);
GO

/*---------------------------------------------------
--  ddl for shc_company_staff_title_lk table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_company_staff_title_lk')
CREATE TABLE shc_portal.shc_company_staff_title_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          varchar(45)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         varchar(45)   NOT NULL,
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
    label         nvarchar(50)  NOT NULL,
    creation_date smalldatetime NOT NULL DEFAULT current_timestamp,
    CONSTRAINT housing_site_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

ALTER TABLE shc_portal.shc_ritual_package
    ADD company_ritual_season_id int NULL CONSTRAINT fk_shc_ritual_package_company_season FOREIGN KEY (company_ritual_season_id) REFERENCES shc_portal.shc_company_ritual_season(id);
GO

ALTER TABLE shc_portal.shc_applicant_ritual
    ADD applicant_package_id int NULL
        CONSTRAINT fk_shc_applicant_ritual_applicant_package FOREIGN KEY (applicant_package_id) REFERENCES shc_portal.shc_applicant_package (id);
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
GO

alter table shc_portal.shc_applicant_ritual
drop
column tafweej_code
GO

alter table shc_portal.shc_applicant_ritual
drop
column hijri_season
GO

alter table shc_portal.shc_applicant_ritual
drop
column date_start_gregorian
GO

alter table shc_portal.shc_applicant_ritual
drop
column date_end_gregorian
GO

alter table shc_portal.shc_applicant_ritual
drop
column date_start_hijri
GO

alter table shc_portal.shc_applicant_ritual
drop
column date_end_hijri
GO

alter table shc_portal.shc_applicant_ritual
drop
column type_code
GO

alter table shc_portal.shc_applicant_ritual alter column permit_number varchar(50) null
GO

alter table shc_portal.shc_applicant_ritual alter column insurance_number varchar(50) null
GO

alter table shc_portal.shc_applicant_ritual
drop
column zone_code
GO

alter table shc_portal.shc_applicant_ritual
drop
column group_code
GO

alter table shc_portal.shc_applicant_ritual
drop
column unit_code
GO

alter table shc_portal.shc_applicant_ritual
drop
column bus_number
GO

alter table shc_portal.shc_applicant_ritual
drop
column seat_number
GO

alter table shc_portal.shc_company_staff_title_lk alter column label nvarchar(50) not null
GO

-- notification tables
if not exists(select * from sys.tables where name = 'shc_notification_template_name_lk')
CREATE TABLE shc_portal.shc_notification_template_name_lk
(
    id            INT           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          VARCHAR(20)   NOT NULL,
    lang          VARCHAR(45)   NOT NULL,
    label         NVARCHAR(50)  NOT NULL,
    creation_date SMALLDATETIME NOT NULL DEFAULT current_timestamp,
    CONSTRAINT notification_template_name_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

if not exists(select * from sys.tables where name = 'shc_notification_processing_status_lk')
CREATE TABLE shc_portal.shc_notification_processing_status_lk
(
    id            INT           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          VARCHAR(20)   NOT NULL,
    description   NVARCHAR(50)  NOT NULL,
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
    label         NVARCHAR(50)  NOT NULL,
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
    label         NVARCHAR(50)  NOT NULL,
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
    label         NVARCHAR(50)  NOT NULL,
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
    is_processed    BIT           NULL,
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
    label         NVARCHAR(50)  NOT NULL,
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

ALTER TABLE shc_portal.shc_user_notification
    ADD user_lang varchar(45) not null;
GO

ALTER TABLE shc_portal.shc_notification_template
    ADD expiration_period_in_minutes INT NULL;
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
    id            int PRIMARY KEY NOT NULL identity (1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50)    NOT NULL,
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
    update_date   SMALLDATETIME   NULL
);
GO
alter table shc_portal.shc_applicant_package_transportation alter column vehicle_number nvarchar(50);
GO

if not exists (select * from sys.tables where name = 'shc_religious_occasions_day_lk')
create table shc_portal.shc_religious_occasions_day_lk
(
    id            int PRIMARY KEY NOT NULL identity (1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50)    NOT NULL,
    creation_date smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint religious_occasions_day_lk_unique unique (code ASC, lang ASC)
);
GO

if not exists (select * from sys.tables where name = 'shc_meal_type_lk')
create table shc_portal.shc_meal_type_lk
(
    id            int PRIMARY KEY NOT NULL identity (1,1),
    code          VARCHAR(20)     NOT NULL,
    lang          VARCHAR(45)     NOT NULL,
    label         NVARCHAR(50)    NOT NULL,
    creation_date smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    constraint shc_meal_type_lk_unique unique (code ASC, lang ASC)
);
GO

ALTER TABLE shc_portal.shc_applicant_package
    ADD start_date DATE;
GO

ALTER TABLE shc_portal.shc_applicant_package
    ADD end_date DATE;
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

/*--------------------------------------------------------
--  ddl for incident tables
--------------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_applicant_incident')
create table shc_portal.shc_applicant_incident
(
    id                  int            NOT NULL PRIMARY KEY IDENTITY (1, 1),
    reference_number    varchar(45)    NOT NULL,
    applicant_ritual_id int            NOT NULL,
    status_code         varchar(20)    NOT NULL,
    type_code           varchar(20)    NOT NULL,
    description         nvarchar(1000) NOT NULL,
    -- Latitudes range from -90 to +90 (degrees), so DECIMAL(10,8) is ok for that, but longitudes range from -180 to +180 (degrees) so we need DECIMAL(11,8).
    location_lat        decimal(10, 8) NOT NULL,
    location_lng        decimal(11, 8) NOT NULL,
    resolution_comment  nvarchar(1000) NULL,
    creation_date       smalldatetime  NOT NULL default current_timestamp,
    update_date         smalldatetime  NULL,
    CONSTRAINT fk_applicant_incident_applicant_ritual FOREIGN KEY (applicant_ritual_id) REFERENCES shc_portal.shc_applicant_ritual (id),
);
GO

if not exists(select * from sys.tables where name = 'shc_incident_attachment')
create table shc_portal.shc_incident_attachment
(
    id                    int            NOT NULL PRIMARY KEY IDENTITY (1, 1),
    file_path             nvarchar(1000) NOT NULL,
    applicant_incident_id int            NOT NULL,
    creation_date         smalldatetime  NOT NULL default current_timestamp,
    CONSTRAINT fk_incident_attachment_applicant_incident FOREIGN KEY (applicant_incident_id) REFERENCES shc_portal.shc_applicant_incident (id),
);
GO

if not exists(select * from sys.tables where name = 'shc_incident_type_lk')
create table shc_portal.shc_incident_type_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    code          varchar(20)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(50)  NOT NULL,
    creation_date smalldatetime NOT NULL default current_timestamp,
    CONSTRAINT incident_type_lk_unique unique (code ASC, lang ASC)
);
GO

if not exists(select * from sys.tables where name = 'shc_incident_status_lk')
create table shc_portal.shc_incident_status_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    code          varchar(20)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(50)  NOT NULL,
    creation_date smalldatetime NOT NULL default current_timestamp,
    CONSTRAINT incident_status_lk_unique unique (code ASC, lang ASC)
);
GO

/*--------------------------------------------------------
--  ddl for applicant chat contact tables
--------------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_contact_type_lk')
create table shc_portal.shc_contact_type_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    code          varchar(20)   NOT NULL,
    creation_date smalldatetime NOT NULL default current_timestamp,
    CONSTRAINT contact_type_lk_unique unique (code ASC)
);
GO

if not exists(select * from sys.tables where name = 'shc_applicant_chat_contact')
create table shc_portal.shc_applicant_chat_contact
(
    id                   int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    applicant_uin        varchar(45)   NOT NULL,
    contact_uin          varchar(45)   NOT NULL,
    type_id              int           NOT NULL,
    alias                nvarchar(500) NULL,
    avatar               varchar(max)  NULL,
    system_defined       bit           NOT NULL default 0,
    staff_title_code     varchar(20)   NULL,
    relationship_code    varchar(20)   NULL,
    mobile_number        varchar(20)   NULL,
    country_phone_prefix varchar(10)   NULL,
    country_code         varchar(10)   NULL,
    deleted              bit           NOT NULL default 0,
    applicant_ritual_id  int           NOT NULL,
    creation_date        smalldatetime NOT NULL default current_timestamp,
    update_date          smalldatetime NULL,
    CONSTRAINT fk_applicant_chat_contact_applicant_ritual FOREIGN KEY (applicant_ritual_id) REFERENCES shc_portal.shc_applicant_ritual (id),
    CONSTRAINT fk_applicant_chat_contact_chat_contact_type FOREIGN KEY (type_id) REFERENCES shc_portal.shc_contact_type_lk (id)
);
GO

if not exists(select * from sys.tables where name = 'shc_chat_message_type_lk')
create table shc_portal.shc_chat_message_type_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    code          varchar(20)   NOT NULL,
    creation_date smalldatetime NOT NULL default current_timestamp,
    CONSTRAINT chat_message_type_lk_unique unique (code ASC)
);
GO

if not exists(select * from sys.tables where name = 'shc_chat_message')
create table shc_portal.shc_chat_message
(
    id                int            NOT NULL PRIMARY KEY IDENTITY (1, 1),
    text              nvarchar(4000) NOT NULL,
    type_id           int            NOT NULL,
    content_file_path nvarchar(500)  NULL,
    sender_id         int            NOT NULL,
    receiver_id       int            NOT NULL,
    sent_date         smalldatetime  NULL,
    received_date     smalldatetime  NULL,
    read_date         smalldatetime  NULL,
    deleted           bit            NOT NULL default 0,
    creation_date     smalldatetime  NOT NULL default current_timestamp,
    update_date       smalldatetime  NULL,
    CONSTRAINT fk_chat_message_chat_message_type FOREIGN KEY (type_id) REFERENCES shc_portal.shc_chat_message_type_lk (id),
    CONSTRAINT fk_chat_message_applicant_chat_contact_sender FOREIGN KEY (sender_id) REFERENCES shc_portal.shc_applicant_chat_contact (id),
    CONSTRAINT fk_chat_message_applicant_chat_contact_receiver FOREIGN KEY (receiver_id) REFERENCES shc_portal.shc_applicant_chat_contact (id)
);
GO

alter table shc_portal.shc_package_housing drop column lat;
GO

alter table shc_portal.shc_package_housing drop column lng;
GO

alter table shc_portal.shc_package_housing
    add lat decimal(10, 8);
GO

alter table shc_portal.shc_package_housing
    add lng decimal(11, 8);
GO

if not exists(select * from sys.tables where name = 'shc_company_staff_digital_id')
create table shc_portal.shc_company_staff_digital_id
(
    id               int PRIMARY KEY NOT NULL identity (1,1),
    company_staff_id int             NOT NULL,
    suin             VARCHAR(45),
    season_year      int             NOT NULL,
    creation_date    smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date      smalldatetime   NULL,
    CONSTRAINT fk_company_staff_company_staff_digital_id FOREIGN KEY (company_staff_id) REFERENCES shc_portal.shc_company_staff (id),
);
GO

if not exists(select * from sys.tables where name = 'shc_company_staff_card')
create table shc_portal.shc_company_staff_card
(
    id                          int PRIMARY KEY NOT NULL identity (1,1),
    company_ritual_season_id    int             NOT NULL,
    company_staff_digital_id_id int             NOT NULL,
    reference_number            VARCHAR(20)     NOT NULL,
    status_code                 VARCHAR(20)     NOT NULL,
    creation_date               smalldatetime   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    update_date                 smalldatetime   NULL,
    CONSTRAINT fk_company_staff_card_company_ritual_season FOREIGN KEY (company_ritual_season_id) REFERENCES shc_portal.shc_company_ritual_season (id),
    CONSTRAINT fk_company_staff_card_company_staff_digital_id FOREIGN KEY (company_staff_digital_id_id) REFERENCES shc_portal.shc_company_staff_digital_id (id)
);
GO

ALTER TABLE shc_portal.shc_company_staff
    ADD  id_number_original      VARCHAR(30) NULL;
GO

ALTER TABLE shc_portal.shc_company_staff
    ADD gender  VARCHAR(1) NULL;
GO

ALTER TABLE shc_portal.shc_company_staff
    ADD  date_of_birth_hijri     INT NULL;
GO

ALTER TABLE shc_portal.shc_company_staff
    ADD  date_of_birth_gregorian DATE   NULL;
GO

ALTER TABLE shc_portal.shc_company_staff
    ADD  passport_number         VARCHAR(30) NULL;
GO

ALTER TABLE shc_portal.shc_company_staff
    ADD  nationality_code     VARCHAR(20) NULL;
GO

ALTER TABLE shc_portal.shc_company_staff
    ADD  full_name_origin  NVARCHAR(150) NULL;
GO

ALTER TABLE shc_portal.shc_company_staff
    ADD  photo  varchar(max) NULL;
GO

ALTER TABLE shc_portal.shc_company_staff
    ADD  mobile_number_intl     VARCHAR(20) NULL;
GO

ALTER TABLE shc_portal.shc_company_staff
    ADD  data_request_record_id int NULL;
GO

ALTER TABLE shc_portal.shc_company_staff
    ADD CONSTRAINT fk_company_staff_data_request_record FOREIGN KEY (data_request_record_id) REFERENCES shc_portal.shc_data_request_record (id);
GO

declare
@schema_name nvarchar(256)
declare
@table_name nvarchar(256)
declare
@col_name nvarchar(256)
declare
@Command nvarchar(1000)
set @schema_name = N'shc_portal'
set @table_name = N'shc_company_staff'
set @col_name = N'company_id'
select @Command = 'ALTER TABLE ' + @schema_name + '.[' + @table_name + '] DROP CONSTRAINT ' + d.name
from sys.tables t
         join sys.default_constraints d on d.parent_object_id = t.object_id
         join sys.columns c on c.object_id = t.object_id and c.column_id = d.parent_column_id
where t.name = @table_name
  and t.schema_id = schema_id(@schema_name)
  and c.name = @col_name execute (@Command)
GO

alter table shc_portal.shc_company_staff drop CONSTRAINT fk_shc_company_representative_company ;
GO

alter table shc_portal.shc_company_staff drop column company_id;
GO

ALTER TABLE shc_portal.shc_company_staff
    ADD company_id INT NULL;

GO

ALTER TABLE shc_portal.shc_company_staff
    ADD CONSTRAINT fk_company_staff_company FOREIGN KEY (company_id) REFERENCES shc_portal.shc_company (id);
GO

alter table shc_portal.shc_company_staff drop column id_number;
GO

ALTER TABLE shc_portal.shc_company_staff
    ADD id_number VARCHAR(16) NOT NULL;
GO

ALTER TABLE shc_portal.shc_company_staff_digital_id
    ADD status_code varchar(20);
GO

ALTER TABLE shc_portal.shc_company_staff_card ALTER COLUMN reference_number varchar(20) NULL
GO

Alter table shc_portal.shc_company_staff_card
    add company_staff_suin VARCHAR(45) NULL;
GO

ALTER table shc_portal.shc_company_staff_card drop constraint fk_company_staff_card_company_staff_digital_id;
GO

ALTER table shc_portal.shc_company_staff_card drop column company_staff_digital_id_id;
GO

ALTER TABLE shc_portal.shc_company_staff DROP CONSTRAINT fk_company_staff_company;
GO

ALTER TABLE shc_portal.shc_company_staff DROP CONSTRAINT fk_shc_company_staff_company_season;
GO

alter table shc_portal.shc_company_staff
drop
column company_id;
GO

alter table shc_portal.shc_company_staff
drop
column company_ritual_season_id
GO

Alter table shc_portal.shc_notification_category_lk
    add mandatory bit NOT NULL default 0;
GO

ALTER TABLE shc_portal.shc_applicant
    ADD preferred_language VARCHAR(2) NULL;
GO

ALTER TABLE shc_portal.shc_applicant
    ADD registered bit NOT NULL default 0;
GO

/*--------------------------------------------------------
--  ddl for notification template categorizing table
--------------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_notification_template_categorizing')
create table shc_portal.shc_notification_template_categorizing
(
    id                       int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    notification_template_id int           NOT NULL,
    camp_id                  int           NULL,
    company_id               int           NULL,
    nationality_code         VARCHAR(20)   NULL,
    min_age                  int           NULL,
    max_age                  int           NULL,
    gender                   varchar(1)    NULL,
    selected_applicants      nvarchar(max) NULL,
    creation_date            smalldatetime NOT NULL default current_timestamp,
    update_date              smalldatetime NULL,
    CONSTRAINT fk_notification_template_categorizing_notification_template FOREIGN KEY (notification_template_id) REFERENCES shc_portal.shc_notification_template (id)
);
GO

ALTER TABLE shc_portal.shc_applicant_chat_contact
    ADD auto_added bit DEFAULT 0 NOT NULL;
GO

ALTER TABLE shc_portal.shc_chat_message ALTER COLUMN type_id int NULL;
GO

ALTER TABLE shc_portal.shc_chat_message ALTER COLUMN sent_date datetime;
GO

ALTER TABLE shc_portal.shc_chat_message ALTER COLUMN received_date datetime;
GO

ALTER TABLE shc_portal.shc_company_ritual_step_lk ALTER COLUMN description NVARCHAR(255);
GO

ALTER TABLE shc_portal.shc_print_request
    ADD target VARCHAR(20) NOT NULL DEFAULT 'APPLICANT';
GO

alter table shc_portal.shc_company_staff_card
    add company_staff_digital_id_id int;
GO

alter table shc_portal.shc_company_staff_card
    add constraint fk_company_staff_card_company_staff_digital_id FOREIGN KEY (company_staff_digital_id_id) REFERENCES shc_portal.shc_company_staff_digital_id (id);
GO

alter table shc_portal.shc_company_staff_card
drop
column company_staff_suin;
GO

alter table shc_portal.shc_company_staff_card
    add batch_number int;
GO

alter table shc_portal.shc_print_batch_type_lk
    add target varchar(20) null;
GO

ALTER TABLE shc_portal.shc_applicant_ritual ADD package_reference_number VARCHAR(45);
GO

ALTER TABLE shc_portal.shc_applicant ADD package_reference_number VARCHAR(45);
GO

EXEC sp_rename 'shc_portal.shc_applicant.package_reference_number', 'first_package_reference_number', 'COLUMN';
GO

ALTER TABLE shc_portal.shc_applicant_health ADD package_reference_number VARCHAR(45);
GO
ALTER TABLE shc_portal.shc_applicant_relative ADD package_reference_number VARCHAR(45);
GO
ALTER TABLE shc_portal.shc_company_staff ADD registered bit default 0 not null;
GO
ALTER TABLE shc_portal.shc_company_staff ADD country_code varchar(20);
GO

if not exists(select * from sys.tables where name = 'shc_applicant_emergency_data_upload')
CREATE TABLE shc_portal.shc_applicant_emergency_data_upload
(
    id                       int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    id_number                varchar(16)   NULL,
    passport_number          varchar(30)   NULL,
    date_of_birth_hijri      int           NULL,
    date_of_birth_gregorian  date          NULL,
    package_reference_number VARCHAR(45)   NOT NULL,
    bus_number               nvarchar(50)  NULL,
    seat_number              varchar(10)   NULL,
    creation_date            smalldatetime NOT NULL DEFAULT current_timestamp
);
GO

ALTER TABLE shc_portal.shc_ritual_package
    ADD start_date DATE, end_date DATE;
GO

ALTER TABLE shc_portal.shc_applicant
    ADD mobile_logged_in bit NULL
GO

ALTER TABLE shc_portal.shc_applicant_incident
    ADD area_code varchar(20)
GO

alter table shc_portal.shc_print_request_batch_card drop CONSTRAINT fk_print_request_batch_card_card;
GO

alter table shc_portal.shc_print_request_card drop CONSTRAINT fk_print_request_card_applicant_card;
GO

if not exists (select * from sys.tables where name = 'shc_user_location')
create table shc_portal.shc_user_location
(
    id                int PRIMARY KEY NOT NULL identity (1,1),
    user_id           VARCHAR(14)     NOT NULL,
    user_type         VARCHAR(20)     not null,
    latitude          DECIMAL(10, 8),
    longitude         DECIMAL(11, 8),
    altitude          DECIMAL(10, 5),
    heading           DECIMAL(10, 5),
    speed             DECIMAL(7, 4),
    speed_accuracy    DECIMAL(7, 4),
    location_accuracy DECIMAL(7, 4),
    gps_time          smalldatetime   null,
    creation_date     smalldatetime   not null default current_timestamp,
    update_date       smalldatetime   null
);

ALTER TABLE shc_portal.shc_applicant_health ADD CONSTRAINT applicant_health_unique UNIQUE (applicant_id, applicant_ritual_id, package_reference_number);
GO

/*---------------------------------------------------
--  ddl for shc_applicant_mobile_audit_log
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_mobile_audit_log')
CREATE TABLE shc_portal.shc_mobile_audit_log
(
    id         int          NOT NULL PRIMARY KEY IDENTITY (1,1),
    user_id    varchar(45)  NOT NULL,
    event      varchar(255) NULL,
    event_date smalldatetime DEFAULT CURRENT_TIMESTAMP,
);
GO

ALTER TABLE shc_portal.shc_applicant_chat_contact ALTER COLUMN applicant_ritual_id INT NULL;
GO

/*---------------------------------------------------
--  ddl for area layers table
---------------------------------------------------*/
if not exists(select * from sys.tables where name = 'shc_area_layers_lk')
CREATE TABLE shc_portal.shc_area_layers_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          varchar(20)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(50)  NOT NULL,
    creation_date smalldatetime NOT NULL DEFAULT current_timestamp,
    CONSTRAINT area_layers_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

/*---------------------------------------------------
--  ddl for shc_camera table
---------------------------------------------------*/

if not exists(select * from sys.tables where name = 'shc_camera')
create table shc_portal.shc_camera
(
    id                  int           not null primary key identity(1,1),
    status              nvarchar(50),
    url                 nvarchar(256),
    creation_hijri_year int null,
    creation_date       smalldatetime not null default current_timestamp,
    update_date         smalldatetime null
);
GO

exec sp_rename 'shc_portal.shc_applicant_chat_contact','shc_chat_contact'
Go

exec sp_rename 'shc_portal.shc_chat_contact.applicant_uin','digital_id','COLUMN'
Go

exec sp_rename 'shc_portal.shc_chat_contact.contact_uin','contact_digital_id','COLUMN'
Go

ALTER TABLE shc_portal.shc_camera ADD area_code int NULL;
GO
exec sp_rename 'shc_portal.shc_camera.creation_hijri_year', 'season_year', 'COLUMN';
GO
ALTER TABLE shc_portal.shc_camera ALTER COLUMN url varchar(256);

GO

alter table shc_portal.shc_chat_message
drop CONSTRAINT fk_chat_message_chat_message_type;
GO

alter table shc_portal.shc_chat_message
alter column type_id varchar(20);
GO

exec sp_rename 'shc_portal.shc_chat_message.type_id','type_code','COLUMN'
Go

ALTER TABLE shc_portal.shc_chat_message
ADD CONSTRAINT fk_chat_message_chat_message_type
FOREIGN KEY (type_code) REFERENCES  shc_portal.shc_chat_message_type_lk (code);
GO

ALTER TABLE shc_portal.shc_applicant_incident ALTER COLUMN location_lat decimal(10, 8) NULL;
GO
ALTER TABLE shc_portal.shc_applicant_incident ALTER COLUMN location_lng decimal(11, 8) NULL;
GO

if not exists(select * from sys.tables where name = 'shc_area_layers')
CREATE TABLE shc_portal.shc_area_layers
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    area_code          varchar(20)   NOT NULL,
    layer		  nvarchar(255) NOT NULL,
    parent_layer_id     int,
    creation_date smalldatetime NOT NULL DEFAULT current_timestamp,
);







/*--------------------------------------------------------
--  ddl for survey tables
--------------------------------------------------------*/


if not exists(select * from sys.tables where name = 'shc_survey_type_lk')
create table shc_portal.shc_survey_type_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    code          varchar(20)   NOT NULL,
    creation_date smalldatetime NOT NULL default current_timestamp,
    CONSTRAINT survey_type_lk_unique unique (code ASC)
);
GO

if not exists(select * from sys.tables where name = 'shc_survey_question_lk')
create table shc_portal.shc_survey_question_lk
(
    id                      int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    code                    varchar(20)   NOT NULL,
    survey_type_code        varchar(20)   NOT NULL,
    lang                    varchar(45)   NOT NULL,
    label                   nvarchar(100)  NOT NULL,
    creation_date           smalldatetime NOT NULL default current_timestamp,
    CONSTRAINT survey_question_lk_unique unique (code ASC, lang ASC, survey_type_code ASC),
    CONSTRAINT fk_survey_question_type_code FOREIGN KEY (survey_type_code) REFERENCES shc_portal.shc_survey_type_lk (code)
);
GO

if not exists(select * from sys.tables where name = 'shc_user_survey')
create table shc_portal.shc_user_survey
(
    id                   int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    digital_id           varchar(45)   NOT NULL,
    type_code            varchar(20)   NOT NULL,
    creation_date        smalldatetime NOT NULL default current_timestamp,
    CONSTRAINT fk_user_survey_type_code FOREIGN KEY (type_code) REFERENCES shc_portal.shc_survey_type_lk (code),
);
GO

if not exists(select * from sys.tables where name = 'shc_user_survey_question')
create table shc_portal.shc_user_survey_question
(
    id                          int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    user_survey_id              int           NOT NULL,
    question_code               varchar(20)   NOT NULL,
    rate                        int           NULL,
    creation_date               smalldatetime NOT NULL default current_timestamp,
    CONSTRAINT user_survey_unique unique (user_survey_id ASC, question_code ASC),
    CONSTRAINT fk_user_survey_id FOREIGN KEY (user_survey_id) REFERENCES shc_portal.shc_user_survey (id)
);
GO

ALTER TABLE shc_portal.shc_applicant_contact DROP CONSTRAINT fk_applicant_contact_applicant_ritual;
GO
ALTER TABLE shc_portal.shc_applicant_contact DROP COLUMN applicant_ritual_id;
GO

alter table shc_portal.shc_survey_question_lk
    add question_index int not null
alter table shc_portal.shc_survey_question_lk alter column code VARCHAR(50)
alter table shc_portal.shc_user_survey_question alter column question_code VARCHAR(50)

alter table shc_portal.shc_applicant alter column education_level_code varchar(100) null
GO

alter table shc_portal.shc_applicant alter column id_number_original varchar(50) null
GO

alter table shc_portal.shc_applicant_health_disease alter column label_ar nvarchar(50) null
GO

alter table shc_portal.shc_applicant_relative alter column relationship_code varchar(20) null
GO

if not exists(select * from sys.tables where name = 'shc_collection_status_lk')
CREATE TABLE shc_portal.shc_collection_status_lk
(
    id            int           NOT NULL PRIMARY KEY IDENTITY (1,1),
    code          varchar(20)   NOT NULL,
    lang          varchar(45)   NOT NULL,
    label         nvarchar(50) NOT NULL,
    creation_date smalldatetime NOT NULL DEFAULT current_timestamp,
    CONSTRAINT shc_collection_status_lk_unique UNIQUE (code ASC, lang ASC)
);
GO

if not exists(select * from sys.tables where name = 'shc_batch_main_collection')
create table shc_portal.shc_batch_main_collection
(
    id               int           not null primary key identity(1,1),
    reference_number varchar(50)   not null,
    status_code       varchar(50),
    url              varchar(256),
    creation_date    smalldatetime not null default current_timestamp,
    update_date      smalldatetime null
);
GO

Use shc_portal

exec sp_rename 'shc_portal.shc_supplication_Lk','shc_supplication_lk';
Go

if not exists(select * from sys.tables where name = 'shc_supplication_lk')
create table shc_portal.shc_supplication_lk
(
    id                      int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    code                    varchar(20)   NOT NULL,
    lang                    varchar(45)   NOT NULL,
    label                   nvarchar(255)  NOT NULL,
    type                    nvarchar(100)  NOT NULL,
    counter                 int            NOT NULL ,
    creation_date           smalldatetime NOT NULL default current_timestamp,
);
GO



/*--------------------------------------------------------
--  ddl for islamic rosary table
--------------------------------------------------------*/
USE shc_portal
GO
if not exists(select * from sys.tables where name = 'shc_user_supplication')
create table shc_portal.shc_user_supplication
(
    id int PRIMARY KEY NOT NULL identity (1,1),
    digital_id VARCHAR(45) NOT NULL,
    code varchar(20) NOT NULL,
    lang varchar(45) NOT NULL,
    label nvarchar(100) NOT NULL,
    deleted bit NOT NULL default 0,
    creation_date smalldatetime NOT NULL DEFAULT CURRENT_TIMESTAMP,

);
GO
if not exists(select * from sys.tables where name = 'shc_suggested_supplication_lk')
create table shc_portal.shc_suggested_supplication_lk
(
    id                      int           NOT NULL PRIMARY KEY IDENTITY (1, 1),
    code                    varchar(20)   NOT NULL,
    lang                    varchar(45)   NOT NULL,
    label                   nvarchar(100)  NOT NULL,
    creation_date           smalldatetime NOT NULL default current_timestamp,
);
GO
if not exists(select * from sys.tables where name = 'shc_supplication_user_counter')
create table shc_portal.shc_supplication_user_counter
(
    id                          int             NOT NULL PRIMARY KEY IDENTITY (1, 1),
    digital_id                  VARCHAR(45)     NOT NULL,
    code                        varchar(20)     NOT NULL,
    supplication_total_count    int              ,
    supplication_last_count     int              ,
    suggested                   bit             NOT NULL default 0,
    creation_date               smalldatetime   NOT NULL default current_timestamp,
    update_date                 smalldatetime
);
GO
USE shc_portal
GO
ALTER TABLE shc_portal.shc_suggested_supplication_lk ALTER COLUMN code varchar(100) NOT NULL
ALTER TABLE shc_portal.shc_suggested_supplication_lk ALTER COLUMN label varchar(300) NOT NULL
GO
alter table shc_company_staff alter column id_number varchar(16) null
go


