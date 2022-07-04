USE shc_portal
GO

INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('scheduler.notification.template.processing.active.nodes', '127.0.0.1,localhost');
INSERT INTO shc_portal.shc_config (conf_key, conf_value) VALUES ('scheduler.notification.processing.active.nodes', '127.0.0.1,localhost');
GO
