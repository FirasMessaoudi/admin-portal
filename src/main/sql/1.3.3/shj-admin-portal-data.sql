USE shc_portal
GO
INSERT INTO shc_portal.shc_company (label_ar, label_en, code) values (N'اوقاف ابناء عبدالعزيز الراجحي الخيرية', 'SONS OF ABDUL AZIZ AL RAJHI', '39_GOVERNMENT_AGENCY');
GO
INSERT INTO shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end, total_quota, air_quota, sea_quota, land_quota)
VALUES ((SELECT id FROM shc_portal.shc_company WHERE code = '39_GOVERNMENT_AGENCY'), 1, 'true', 14431101, 14431230, 0, 0, 0, 0);
INSERT INTO shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end, total_quota, air_quota, sea_quota, land_quota)
VALUES ((SELECT id FROM shc_portal.shc_company WHERE code = '39_GOVERNMENT_AGENCY'), 2, 'true', 14431101, 14431230, 0, 0, 0, 0);
INSERT INTO shc_portal.shc_company_ritual_season (company_id, ritual_season_id, active, season_start, season_end, total_quota, air_quota, sea_quota, land_quota)
VALUES ((SELECT id FROM shc_portal.shc_company WHERE code = '39_GOVERNMENT_AGENCY'), 3, 'true', 14431101, 14431230, 0, 0, 0, 0);
GO