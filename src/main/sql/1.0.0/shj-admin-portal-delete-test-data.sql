USE
shc_portal
GO

-- DELETE APPLICANT
DELETE
FROM shc_portal.shc_applicant_digital_id
where id > 0;
DELETE
FROM shc_portal.shc_print_request_batch_card
where id > 0;
DELETE
FROM shc_portal.shc_print_request_batch
where id > 0;
DELETE
FROM shc_portal.shc_print_request_card
where id > 0;
DELETE
FROM shc_portal.shc_print_request
where id > 0;
DELETE
FROM shc_portal.shc_applicant_card
where id > 0;
DELETE
FROM shc_portal.shc_applicant_ritual
where id > 0;
DELETE
FROM shc_portal.shc_applicant_health_special_needs
where id > 0;
DELETE
FROM shc_portal.shc_applicant_health_immunization
where id > 0;
DELETE
FROM shc_portal.shc_applicant_health_disease
where id > 0;
DELETE
FROM shc_portal.shc_applicant_health
where id > 0;
DELETE
FROM shc_portal.shc_applicant_relative
where id > 0;
DELETE
FROM shc_portal.shc_applicant_contact
where id > 0;
DELETE
FROM shc_portal.shc_applicant
where id > 0;
Go
USE shc_portal
GO
-- DELETE DATA REQUEST
DELETE
FROM shc_portal.shc_data_request_record
where id > 0;
DELETE
FROM shc_portal.shc_data_request
where id > 0;
Go

-- DELETE APPLICANT
DELETE
FROM shc_portal.shc_applicant_digital_id
where id > 0;
DELETE
FROM shc_portal.shc_print_request_batch_card
where id > 0;
DELETE
FROM shc_portal.shc_print_request_batch
where id > 0;
DELETE
FROM shc_portal.shc_ritual_group
where id > 0;
DELETE
FROM shc_portal.shc_ritual_unit
where id > 0;
DELETE
FROM shc_portal.shc_card_batch
where id > 0;
DELETE
FROM shc_portal.shc_decision_rule
where id > 0;
DELETE
FROM shc_portal.shc_ritual_zone
where id > 0;
-- DELETE USER
DELETE
FROM shc_portal.shc_user_password_history
where id > 0;
DELETE
FROM shc_portal.shc_user_role
where id > 0;
DELETE
FROM shc_portal.shc_user
where id > 0;
DELETE
FROM shc_portal.shc_role_authority
where id > 0;
DELETE
FROM shc_portal.shc_role
where id > 0;
DELETE
FROM shc_portal.shc_ritual_type_lk
where id > 0;
DELETE
FROM shc_portal.shc_data_request_status_lk
where id > 0;
DELETE
FROM shc_portal.shc_authority_lk
where id > 0;
DELETE
FROM shc_portal.shc_config
where id > 0;
DELETE
FROM shc_portal.shc_data_segment
where id > 0;
DELETE
FROM shc_portal.shc_relative_relationship_lk
where id > 0;
DELETE
FROM shc_portal.shc_health_immunization_lk
where id > 0;
DELETE
FROM shc_portal.shc_health_special_needs_type_lk
where id > 0;
DELETE
FROM shc_portal.shc_card_status_lk
where id > 0;
DELETE
FROM shc_portal.shc_country_lk
where id > 0;
DELETE
FROM shc_portal.shc_marital_status_lk
where id > 0;
DELETE
FROM shc_portal.shc_language_lk
where id > 0;
DELETE
FROM shc_portal.shc_print_request_status_lk
where id > 0;
DELETE
FROM shc_portal.shc_print_batch_type_lk
where id > 0;
DELETE
FROM shc_portal.shc_config
where id > 0;
