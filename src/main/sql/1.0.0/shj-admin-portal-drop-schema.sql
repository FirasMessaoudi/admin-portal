/*--------------------------------------------------------
--  ddl for to drop tables
--------------------------------------------------------*/
USE shc_portal
GO
drop table if exists shc_portal.shc_user_role;
drop table if exists shc_portal.shc_role_authority;
drop table if exists shc_portal.shc_user_authorities;
drop table if exists shc_portal.shc_user_password_history;
drop table if exists shc_portal.shc_user;
drop table if exists shc_portal.shc_role;
drop table if exists shc_portal.shc_user_role_lk;
drop table if exists shc_portal.shc_config;
drop table if exists shc_portal.shc_config;
drop table if exists shc_portal.shc_audit_log;
drop table if exists shc_portal.shc_applicant_relative;
drop table if exists shc_portal.shc_relative_relationship_lk;
drop table if exists shc_portal.shc_applicant_contact;
drop table if exists shc_portal.shc_applicant_digital_id;
drop table if exists shc_portal.shc_print_request_card;
drop table if exists shc_portal.shc_print_request_batch_card;
drop table if exists shc_portal.shc_applicant_card;
drop table if exists shc_portal.shc_card_batch;
drop table if exists shc_portal.shc_card_status_lk;
drop table if exists shc_portal.shc_applicant_health_special_needs;
drop table if exists shc_portal.shc_health_special_needs_type_lk;
drop table if exists shc_portal.shc_applicant_health_immunization;
drop table if exists shc_portal.shc_health_immunization_lk;
drop table if exists shc_portal.shc_applicant_health_disease;
drop table if exists shc_portal.shc_applicant_health;
drop table if exists shc_portal.shc_country_lk;
drop table if exists shc_portal.shc_decision_rule;
drop table if exists shc_portal.shc_print_request_batch_applicant;
drop table if exists shc_portal.shc_print_request_batch;
drop table if exists shc_portal.shc_print_request_applicant;
drop table if exists shc_portal.shc_print_request;
drop table if exists shc_portal.shc_print_batch_type_lk;
drop table if exists shc_portal.shc_print_request_status_lk;
drop table if exists shc_portal.shc_applicant_ritual;
drop table if exists shc_portal.shc_applicant;
drop table if exists shc_portal.shc_data_request_record;
drop table if exists shc_portal.shc_data_request;
drop table if exists shc_portal.shc_data_segment;

drop table if exists shc_portal.shc_authority_lk;
drop table if exists shc_portal.shc_data_request_status_lk;
drop table if exists shc_portal.shc_language_lk;
drop table if exists shc_portal.shc_marital_status_lk;
drop table if exists shc_portal.shc_ritual_type_lk;
drop table if exists shc_portal.shc_scheduled_tasks_lock;
GO

USE master
GO
drop schema if exists shc_portal;
GO
drop database if exists shc_portal;
GO
drop login shc_user;
GO
drop user if exists shc_user;
GO
