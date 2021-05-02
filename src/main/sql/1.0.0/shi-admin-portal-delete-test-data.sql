USE shj_portal
GO

-- DELETE APPLICANT
DELETE FROM shj_portal.sha_applicant_digital_id;
DELETE FROM shj_portal.sha_applicant_card;
DELETE FROM shj_portal.sha_applicant_ritual;
DELETE FROM shj_portal.sha_applicant_health_special_needs;
DELETE FROM shj_portal.sha_applicant_health_immunization;
DELETE FROM shj_portal.sha_applicant_health_disease;
DELETE FROM shj_portal.sha_applicant_health;
DELETE FROM shj_portal.sha_applicant_relative;
DELETE FROM shj_portal.sha_applicant_contact;
DELETE FROM shj_portal.sha_applicant ;

-- DELETE CARD
DELETE FROM shj_portal.sha_data_request ;
DELETE FROM shj_portal.sha_card_batch;
DELETE FROM shj_portal.sha_decision_rule;


-- DELETE USER
DELETE FROM shj_portal.sha_user_password_history;
DELETE FROM shj_portal.sha_user_role;
DELETE FROM shj_portal.sha_user ;






