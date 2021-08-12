USE shc_portal
GO

ALTER TABLE shc_portal.shc_applicant_health
    ADD applicant_ritual_id int;
GO

ALTER TABLE shc_portal.shc_applicant_health
    ADD CONSTRAINT fk_applicant_health_applicant_ritual
        FOREIGN KEY (applicant_ritual_id) REFERENCES shc_portal.shc_applicant_ritual (id);
GO