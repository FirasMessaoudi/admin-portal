USE MASTER
GO
CREATE LOGIN "shj_portal_user" WITH PASSWORD=N'Aa123456789', DEFAULT_DATABASE="shj_portal", DEFAULT_LANGUAGE=US_ENGLISH
GO
ALTER LOGIN "shj_portal_user" ENABLE
GO

USE shj_portal
GO
CREATE USER "shj_portal_user" FOR LOGIN "shj_portal_user" WITH DEFAULT_SCHEMA="shj_portal"
GO
ALTER ROLE [db_owner] ADD MEMBER [shj_portal_user]
GO
