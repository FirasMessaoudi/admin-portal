USE
shc_portal
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