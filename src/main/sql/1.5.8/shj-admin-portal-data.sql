USE shc_portal
GO
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('scheduler.load.lookups.cron', '0 0 22 * * *');
GO