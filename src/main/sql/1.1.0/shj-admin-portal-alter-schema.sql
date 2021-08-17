USE shc_portal
GO
ALTER TABLE shc_portal.shc_applicant_health ADD applicant_ritual_id int;
GO

ALTER TABLE shc_portal.shc_applicant_health ADD CONSTRAINT fk_applicant_health_applicant_ritual FOREIGN KEY (applicant_ritual_id) REFERENCES shc_portal.shc_applicant_ritual (id);
GO

-- update audit log table to handle big number for user id
ALTER TABLE shc_portal.shc_audit_log ALTER COLUMN user_id_number bigint;
GO

ALTER TABLE shc_portal.shc_applicant_contact
    ADD applicant_ritual_id int;
GO
ALTER TABLE shc_portal.shc_applicant_contact
    ADD CONSTRAINT fk_applicant_contact_applicant_ritual
        FOREIGN KEY (applicant_ritual_id) REFERENCES shc_portal.shc_applicant_ritual (id);
GO


ALTER TABLE shc_portal.shc_applicant_relative
    ADD applicant_ritual_id int;
GO
ALTER TABLE shc_portal.shc_applicant_relative
    ADD CONSTRAINT fk_applicant_relative_applicant_ritual
        FOREIGN KEY (applicant_ritual_id) REFERENCES shc_portal.shc_applicant_ritual (id);
GO