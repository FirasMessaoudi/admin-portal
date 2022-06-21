USE shc_portal
GO

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota,
                                                  air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '9_ESTABLISHMENT'), 1, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 1),
        (select season_end from shc_portal.shc_ritual_season where id = 1), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota,
                                                  air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '9_ESTABLISHMENT'), 2, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 2),
        (select season_end from shc_portal.shc_ritual_season where id = 2), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota,
                                                  air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '9_ESTABLISHMENT'), 3, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 3),
        (select season_end from shc_portal.shc_ritual_season where id = 3), 0, 0, 0, 0);

/*
 establishment_9
 */

insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999922', (select cr.id
                     from shc_portal.shc_company_ritual_season cr,
                          shc_portal.shc_company c
                     where cr.ritual_season_id = 1
                       and c.code = '9_ESTABLISHMENT'
                       and c.id = cr.company_id), 'NORMAL', CONVERT(date, '06-01-2022', 110),
        CONVERT(date, '07-31-2022', 110))
    insert
into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                    end_date)
values ('99999923', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 2 and c.code='9_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))

insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999924', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 3 and c.code='9_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
GO

UPDATE shc_portal.shc_config set conf_value = 'true' where conf_key = 'data.request.company.staff.override';
GO


update shc_portal.shc_company_ritual_step_lk set edit_mode = 'DATE_TIME' where code= 'TAWAF_AL_QUDOM'
update shc_portal.shc_company_ritual_step_lk set edit_mode = 'TIME' where code= 'STAY_IN_MINA'
update shc_portal.shc_company_ritual_step_lk set edit_mode = 'TIME' where code= 'ARAFAT'
update shc_portal.shc_company_ritual_step_lk set edit_mode = 'TIME' where code= 'STAY_IN_MUZDALIFA'
update shc_portal.shc_company_ritual_step_lk set edit_mode = 'TIME' where code= 'THROUGH_GAMARAT_STONES_10'
update shc_portal.shc_company_ritual_step_lk set edit_mode = 'DATE_TIME' where code= 'TAWAF_ELFADAH'
update shc_portal.shc_company_ritual_step_lk set edit_mode = 'TIME' where code= 'THROUGH_GAMARAT_STONES_11'
update shc_portal.shc_company_ritual_step_lk set edit_mode = 'TIME' where code= 'THROUGH_GAMARAT_STONES_12'
update shc_portal.shc_company_ritual_step_lk set edit_mode = 'TIME' where code= 'THROUGH_GAMARAT_STONES_13'
update shc_portal.shc_company_ritual_step_lk set edit_mode = 'DATE_TIME' where code= 'TAWAF_AL_WADAA'
GO

INSERT INTO shc_portal.shc_company (label_ar, label_en, code) values (N'شركة كِدانة للتنمية والتطوير', 'KIDANA', '38_GOVERNMENT_AGENCY');
GO
INSERT INTO shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end, total_quota, air_quota, sea_quota, land_quota)
VALUES ((SELECT id FROM shc_portal.shc_company WHERE code = '38_GOVERNMENT_AGENCY'), 1, 'true', 14431101, 14431230, 0, 0, 0, 0);
INSERT INTO shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end, total_quota, air_quota, sea_quota, land_quota)
VALUES ((SELECT id FROM shc_portal.shc_company WHERE code = '38_GOVERNMENT_AGENCY'), 2, 'true', 14431101, 14431230, 0, 0, 0, 0);
INSERT INTO shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end, total_quota, air_quota, sea_quota, land_quota)
VALUES ((SELECT id FROM shc_portal.shc_company WHERE code = '38_GOVERNMENT_AGENCY'), 3, 'true', 14431101, 14431230, 0, 0, 0, 0);
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('generate.digital.ids.scheduler.active.nodes', '127.0.0.1,localhost');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('generate.applicant.card.scheduler.active.nodes', '127.0.0.1,localhost');
GO