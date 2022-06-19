USE
shc_portal
GO

insert into shc_portal.shc_company (label_ar,label_en,code) values (N'منظم حجاج الضيوف', 'Organizer of Pilgrims', '10_ESTABLISHMENT');
GO

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active,season_start, season_end, total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code='1_ESTABLISHMENT'),1,'true',
        (select season_start from shc_portal.shc_ritual_season where id = 1),
        (select season_end from shc_portal.shc_ritual_season where id = 1), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '1_ESTABLISHMENT'), 2, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 2),
        (select season_end from shc_portal.shc_ritual_season where id = 2), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '1_ESTABLISHMENT'), 3, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 3),
        (select season_end from shc_portal.shc_ritual_season where id = 3), 0, 0, 0, 0);


insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '2_ESTABLISHMENT'), 1, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 1),
        (select season_end from shc_portal.shc_ritual_season where id = 1), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '2_ESTABLISHMENT'), 2, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 2),
        (select season_end from shc_portal.shc_ritual_season where id = 2), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '2_ESTABLISHMENT'), 3, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 3),
        (select season_end from shc_portal.shc_ritual_season where id = 3), 0, 0, 0, 0);



insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '3_ESTABLISHMENT'), 1, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 1),
        (select season_end from shc_portal.shc_ritual_season where id = 1), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '3_ESTABLISHMENT'), 2, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 2),
        (select season_end from shc_portal.shc_ritual_season where id = 2), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '3_ESTABLISHMENT'), 3, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 3),
        (select season_end from shc_portal.shc_ritual_season where id = 3), 0, 0, 0, 0);



insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '4_ESTABLISHMENT'), 1, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 1),
        (select season_end from shc_portal.shc_ritual_season where id = 1), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '4_ESTABLISHMENT'), 2, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 2),
        (select season_end from shc_portal.shc_ritual_season where id = 2), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '4_ESTABLISHMENT'), 3, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 3),
        (select season_end from shc_portal.shc_ritual_season where id = 3), 0, 0, 0, 0);



insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '5_ESTABLISHMENT'), 1, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 1),
        (select season_end from shc_portal.shc_ritual_season where id = 1), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '5_ESTABLISHMENT'), 2, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 2),
        (select season_end from shc_portal.shc_ritual_season where id = 2), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '5_ESTABLISHMENT'), 3, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 3),
        (select season_end from shc_portal.shc_ritual_season where id = 3), 0, 0, 0, 0);



insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '6_ESTABLISHMENT'), 1, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 1),
        (select season_end from shc_portal.shc_ritual_season where id = 1), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '6_ESTABLISHMENT'), 2, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 2),
        (select season_end from shc_portal.shc_ritual_season where id = 2), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '6_ESTABLISHMENT'), 3, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 3),
        (select season_end from shc_portal.shc_ritual_season where id = 3), 0, 0, 0, 0);

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



insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '10_ESTABLISHMENT'), 1, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 1),
        (select season_end from shc_portal.shc_ritual_season where id = 1), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '10_ESTABLISHMENT'), 2, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 2),
        (select season_end from shc_portal.shc_ritual_season where id = 2), 0, 0, 0, 0);

insert into shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end,
                                                  total_quota, air_quota, sea_quota, land_quota)
values ((select id from shc_portal.shc_company where code = '10_ESTABLISHMENT'), 3, 'true',
        (select season_start from shc_portal.shc_ritual_season where id = 3),
        (select season_end from shc_portal.shc_ritual_season where id = 3), 0, 0, 0, 0);


/*
 establishment 1
 */
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('9999991', (select cr.id
                    from shc_portal.shc_company_ritual_season cr,
                         shc_portal.shc_company c
                    where cr.ritual_season_id = 1
                      and c.code = '1_ESTABLISHMENT'
                      and c.id = cr.company_id), 'NORMAL', CONVERT(date, '06-01-2022', 110),
        CONVERT(date, '07-31-2022', 110))
    insert
into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date, end_date)
values ('9999992', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 2 and c.code='1_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('9999993', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 3 and c.code='1_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))

/*
 establishment 2
 */
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('9999994', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 1 and c.code='2_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('9999995', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 2 and c.code='2_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('9999996', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 3 and c.code='2_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))

/*
 establishment 3
 */
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('9999997', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 1 and c.code='3_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('9999998', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 2 and c.code='3_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('9999999', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 3 and c.code='3_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))

/*
 establishment 4
 */
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999910', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 1 and c.code='4_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999911', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 2 and c.code='4_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999912', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 3 and c.code='4_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))

/*
 establishment 5
 */
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999913', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 1 and c.code='5_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999914', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 2 and c.code='5_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999915', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 3 and c.code='5_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))

/*
 establishment 6
 */
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999916', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 1 and c.code='6_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999917', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 2 and c.code='6_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999918', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 3 and c.code='6_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))

/*
 establishment 10
 */
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999919', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 1 and c.code='10_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999920', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 2 and c.code='10_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999921', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 3 and c.code='10_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))

/*
 establishment_9
 */

insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999922', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 1 and c.code='9_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))

insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999923', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 2 and c.code='9_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))

insert into shc_portal.shc_ritual_package (reference_number, company_ritual_season_id, package_type_code, start_date,
                                           end_date)
values ('99999924', (select cr.id from shc_portal.shc_company_ritual_season cr, shc_portal.shc_company c where cr.ritual_season_id = 3 and c.code='9_ESTABLISHMENT' and c.id=cr.company_id), 'NORMAL', CONVERT (date, '06-01-2022', 110), CONVERT (date, '07-31-2022', 110))
    GO
    GO