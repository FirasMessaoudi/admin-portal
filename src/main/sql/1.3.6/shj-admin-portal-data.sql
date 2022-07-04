USE shc_portal
GO

INSERT INTO shc_portal.shc_config(conf_key, conf_value) VALUES('bi.server.url','10.33.191.24');
INSERT INTO shc_portal.shc_config(conf_key, conf_value) VALUES('bi.server.domain','10.33.191.24');
INSERT INTO shc_portal.shc_config(conf_key, conf_value) VALUES('bi.server.client.id','hajj');
INSERT INTO shc_portal.shc_config(conf_key, conf_value) VALUES('bi.dashboard.uri','views/SMARTGATEHAJJDASHBOARDS/HAJJ1433');

INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('scheduler.notification.template.processing.active.nodes', '127.0.0.1,localhost');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('scheduler.notification.processing.active.nodes', '127.0.0.1,localhost');
GO
