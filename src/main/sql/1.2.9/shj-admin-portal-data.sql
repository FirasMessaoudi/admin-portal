USE shc_portal
GO
UPDATE shc_portal.shc_config SET conf_value = '180000' WHERE conf_key = 'scheduler.generate.digital.ids.delay.milliseconds';
UPDATE shc_portal.shc_config SET conf_value = '300000' WHERE conf_key = 'scheduler.generate.card.applicant.ritual.delay.milliseconds';
GO