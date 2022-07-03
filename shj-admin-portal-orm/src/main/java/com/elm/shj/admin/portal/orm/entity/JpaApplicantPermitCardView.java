package com.elm.shj.admin.portal.orm.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "shc_v_tawakkalna_processing")
public class JpaApplicantPermitCardView implements Serializable {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_serial")
    private String cardSerial;

    @Column(name = "front_qr")
    private String frontQr;

    @Column(name = "iqama_nin")
    private String iqamaNin;

    @Column(name = "passport_number")
    private String passportNumber;

    @Column(name = "nationality_code")
    private long nationalityCode;

    @Column(name = "nationality_ar")
    private String nationalityAr;

    @Column(name = "nationality_en")
    private String nationalityEn;

    @Column(name = "establishment_ref_code")
    private String establishmentRefCode;

    @Column(name = "establishment_ar")
    private String establishmentAr;

    @Column(name = "establishment_en")
    private String establishmentEn;

    @Column(name = "back_phone_number")
    private String backPhoneNumber;

    @Column(name = "service_office_number")
    private String serviceOfficeNumber;

    @Column(name = "company_name_ar")
    private String companyNameAr;

    @Column(name = "company_name_en")
    private String companyNameEn;

    @Column(name = "card_status")
    private int cardStatus;
}