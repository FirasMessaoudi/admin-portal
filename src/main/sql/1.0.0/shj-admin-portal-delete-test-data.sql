USE
shc_portal
GO

-- DELETE APPLICANT
DELETE
FROM shc_portal.shc_applicant_digital_id;
DELETE
FROM shc_portal.shc_applicant_card;
DELETE
FROM shc_portal.shc_applicant_ritual;
DELETE
FROM shc_portal.shc_applicant_health_special_needs;
DELETE
FROM shc_portal.shc_applicant_health_immunization;
DELETE
FROM shc_portal.shc_applicant_health_disease;
DELETE
FROM shc_portal.shc_applicant_health;
DELETE
FROM shc_portal.shc_applicant_relative;
DELETE
FROM shc_portal.shc_applicant_contact;
DELETE
FROM shc_portal.shc_applicant;

-- DELETE CARD
DELETE
FROM shc_portal.shc_data_request;
DELETE
FROM shc_portal.shc_card_batch;
DELETE
FROM shc_portal.shc_decision_rule;


-- DELETE USER
DELETE
FROM shc_portal.shc_user_password_history;
DELETE
FROM shc_portal.shc_user_role;
DELETE
FROM shc_portal.shc_user;






