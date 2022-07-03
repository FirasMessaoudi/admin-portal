/*
 * Copyright (c) 2021 ELM. All rights reserved.
 */
package com.elm.shj.admin.portal.services.company;

import com.elm.shj.admin.portal.orm.entity.*;
import com.elm.shj.admin.portal.orm.repository.CompanyStaffRepository;
import com.elm.shj.admin.portal.services.applicant.ApplicantGroupBasicService;
import com.elm.shj.admin.portal.services.card.CompanyStaffCardService;
import com.elm.shj.admin.portal.services.data.reader.EExcelItemReaderErrorType;
import com.elm.shj.admin.portal.services.data.validators.DataValidationResult;
import com.elm.shj.admin.portal.services.digitalid.CompanyStaffDigitalIdBasicService;
import com.elm.shj.admin.portal.services.digitalid.CompanyStaffDigitalIdService;
import com.elm.shj.admin.portal.services.dto.*;
import com.elm.shj.admin.portal.services.generic.GenericService;
import com.elm.shj.admin.portal.services.utils.ImageUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
@Slf4j
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class CompanyStaffService extends GenericService<JpaCompanyStaff, CompanyStaffDto, Long> {

    private final CompanyStaffRepository companyStaffRepository;
    public final static String SAUDI_MOBILE_NUMBER_REGEX = "^(009665|9665|\\+9665|05|5)([0-9]{8})$";
    public final static Pattern  EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private final CompanyRitualSeasonService companyRitualSeasonService;
    private final CompanyStaffDigitalIdService companyStaffDigitalIdService;
    private final CompanyStaffCardService companyStaffCardService;
    private final CompanyStaffDigitalIdBasicService companyStaffDigitalIdBasicService;
    private final MessageSource messageSource;
    private final ApplicantGroupBasicService applicantGroupBasicService;

    @Value("${ritual.season.year}")
    private int seasonYear;

    private final static String DEFAULT_AVATAR_MALE = "avatar/staff-male.png";
    private final static String DEFAULT_AVATAR_FEMALE = "avatar/applicant-staff-female.png";

    public Optional<CompanyStaffLiteDto> findBySuin(String suin) {
        CompanyStaffDto companyStaff = getMapper().fromEntity(companyStaffRepository.findBySuin(suin, EDigitalIdStatus.VALID.name()), mappingContext);
        if (companyStaff != null) {
            CompanyStaffLiteDto companyStaffLite = CompanyStaffLiteDto.builder()
                    .id(companyStaff.getId())
                    .suin(suin)
                    .fullNameAr(companyStaff.getFullNameAr())
                    .fullNameEn(companyStaff.getFullNameEn())
                    .countryCode(companyStaff.getCountryCode())
                    .mobileNumber(companyStaff.getMobileNumber())
                    .email(companyStaff.getEmail())
                    .nationalityCode(companyStaff.getNationalityCode())
                    .photo(companyStaff.getPhoto())
                    .titleCode(companyStaff.getTitleCode())
                    .dateOfBirthGregorian(companyStaff.getDateOfBirthGregorian())
                    .dateOfBirthHijri(companyStaff.getDateOfBirthHijri())
                    .gender(companyStaff.getGender())
                    .idNumber(companyStaff.getIdNumber())
                    .passportNumber(companyStaff.getPassportNumber())
                    .digitalIds(companyStaff.getDigitalIds())
                    .mobileNumberIntl(companyStaff.getMobileNumberIntl())
                    .countryPhonePrefix(companyStaff.getCountryPhonePrefix())
                    .build();
            return Optional.of(companyStaffLite);
        } else return Optional.empty();
    }

    public Optional<CompanyStaffLiteDto> findByPassportNumber(String passportNumber, String nationalityCode) {
        CompanyStaffDto companyStaff = getMapper().fromEntity(companyStaffRepository.findByPassportAndNationalityCode(passportNumber, nationalityCode, EDigitalIdStatus.VALID.name()), mappingContext);
        if (companyStaff != null) {
            CompanyStaffLiteDto companyStaffLite = CompanyStaffLiteDto.builder()
                    .id(companyStaff.getId())
                    .suin(companyStaff.getDigitalIds().get(0).getSuin())
                    .fullNameAr(companyStaff.getFullNameAr())
                    .fullNameEn(companyStaff.getFullNameEn())
                    .countryCode(companyStaff.getCountryCode())
                    .mobileNumber(companyStaff.getMobileNumber())
                    .email(companyStaff.getEmail())
                    .nationalityCode(companyStaff.getNationalityCode())
                    .photo(companyStaff.getPhoto())
                    .titleCode(companyStaff.getTitleCode())
                    .dateOfBirthGregorian(companyStaff.getDateOfBirthGregorian())
                    .dateOfBirthHijri(companyStaff.getDateOfBirthHijri())
                    .gender(companyStaff.getGender())
                    .idNumber(companyStaff.getIdNumber())
                    .passportNumber(companyStaff.getPassportNumber())
                    .digitalIds(companyStaff.getDigitalIds())
                    .mobileNumberIntl(companyStaff.getMobileNumberIntl())
                    .countryPhonePrefix(companyStaff.getCountryPhonePrefix())
                    .build();
            return Optional.of(companyStaffLite);
        } else return Optional.empty();
    }

    public Optional<CompanyStaffLiteDto> findByIdNumber(String idNumber) {
        CompanyStaffDto companyStaff = getMapper().fromEntity(companyStaffRepository.findByIdNumber(idNumber, EDigitalIdStatus.VALID.name()), mappingContext);
        if (companyStaff != null) {
            CompanyStaffLiteDto companyStaffLite = CompanyStaffLiteDto.builder()
                    .id(companyStaff.getId())
                    .suin(companyStaff.getDigitalIds().get(0).getSuin())
                    .fullNameAr(companyStaff.getFullNameAr())
                    .fullNameEn(companyStaff.getFullNameEn())
                    .countryCode(companyStaff.getCountryCode())
                    .mobileNumber(companyStaff.getMobileNumber())
                    .email(companyStaff.getEmail())
                    .nationalityCode(companyStaff.getNationalityCode())
                    .photo(companyStaff.getPhoto())
                    .titleCode(companyStaff.getTitleCode())
                    .dateOfBirthGregorian(companyStaff.getDateOfBirthGregorian())
                    .dateOfBirthHijri(companyStaff.getDateOfBirthHijri())
                    .gender(companyStaff.getGender())
                    .idNumber(companyStaff.getIdNumber())
                    .passportNumber(companyStaff.getPassportNumber())
                    .digitalIds(companyStaff.getDigitalIds())
                    .mobileNumberIntl(companyStaff.getMobileNumberIntl())
                    .countryPhonePrefix(companyStaff.getCountryPhonePrefix())
                    .build();
            return Optional.of(companyStaffLite);
        } else return Optional.empty();
    }

    public List<CompanyStaffDto> findRelatedEmployeesByApplicantUinAndSeasonId(String uin, long sid) {
        return mapList(companyStaffRepository.findApplicantCompanyStaff(uin, sid));
    }

    public Optional<CompanyStaffDto> findGroupLeaderByApplicantUin(String applicantUin, long companyRitualSeasonId) {
        JpaCompanyStaff companyStaff = companyStaffRepository.findByApplicantGroupsGroupApplicantListsApplicantUinAndApplicantGroupsCompanyRitualSeasonIdAndTitleCode(applicantUin, companyRitualSeasonId, ECompanyStaffTitle.GROUP_LEADER.name());
        if (companyStaff != null) {
            return Optional.of(getMapper().fromEntity(companyStaff, mappingContext));
        }
        return Optional.empty();
    }

    public Optional<CompanyStaffDto> findGroupLeaderByApplicantUin(String applicantUin) {
        JpaCompanyStaff companyStaff = companyStaffRepository.findByApplicantGroupsGroupApplicantListsApplicantUinAndTitleCode(applicantUin, ECompanyStaffTitle.GROUP_LEADER.name());
        if (companyStaff != null) {
            return Optional.of(getMapper().fromEntity(companyStaff, mappingContext));
        }
        return Optional.empty();
    }

    public String findGroupLeaderMobileByApplicantUin(String applicantUin) {
        return companyStaffRepository.findGroupLeaderMobileNumberByApplicantUin(applicantUin);
    }

    /**
     * @param idNumber
     * @param passportNumber
     * @param nationalityCode
     * @return
     */
    public boolean existsByBasicInfo(String idNumber, String passportNumber, String nationalityCode) {
        return ((CompanyStaffRepository) getRepository()).existsByBasicInfo(idNumber, passportNumber, nationalityCode);
    }

    /**
     * @param idNumber
     * @param passportNumber
     * @param dateGreg
     * @param dateHijri
     * @return
     */
    public boolean existsByBasicInfo(String idNumber, String passportNumber, Date dateGreg, Long dateHijri) {
        return ((CompanyStaffRepository) getRepository()).existsByBasicInfo(idNumber, dateHijri, passportNumber, dateGreg);
    }

    /**
     * @param idNumber
     * @param passportNumber
     * @param dateGreg
     * @param dateHijri
     * @return
     */
    public boolean existsByBasicInfoAndTitleIsGroupLeader(String idNumber, String passportNumber, Date dateGreg, Long dateHijri) {
        return ((CompanyStaffRepository) getRepository()).existsByBasicInfoAndTitleIsGroupLeader(idNumber, dateHijri, passportNumber, dateGreg, ECompanyStaffTitle.GROUP_LEADER.name());
    }

    public boolean existsByBasicInfoAndTitleIsGroupLeader(String idNumber, String passportNumber, String nationalityCode) {
        return ((CompanyStaffRepository) getRepository()).existsByBasicInfoAndTitleIsGroupLeader(idNumber, passportNumber, nationalityCode, ECompanyStaffTitle.GROUP_LEADER.name());
    }

    /**
     * @param idNumber
     * @param passportNumber
     * @param dateGreg
     * @param dateHijri
     * @return companyStaffDto
     */
    public CompanyStaffDto findByBasicInfo(String idNumber, String passportNumber, Date dateGreg, Long dateHijri) {
        return getMapper().fromEntity(companyStaffRepository.findByBasicInfo(idNumber, dateHijri, passportNumber, dateGreg), mappingContext);
    }

    /**
     * @param idNumber
     * @param passportNumber
     * @param nationalityCode
     * @return CompanyStaffDto
     */
    public CompanyStaffDto findByBasicInfo(String idNumber, String passportNumber, String nationalityCode) {
        return getMapper().fromEntity(companyStaffRepository.findByBasicInfo(idNumber, passportNumber, nationalityCode), mappingContext);
    }

    public Long findIdByBasicInfo(String idNumber, String passportNumber, String nationalityCode) {
        return companyStaffRepository.findIdByBasicInfo(idNumber, passportNumber, nationalityCode);
    }

    /**
     * @param idNumber
     * @param passportNumber
     * @param dateGreg
     * @param dateHijri
     * @return companyStaffDto
     */
    public CompanyStaffDto findGroupLeaderByBasicInfo(String idNumber, String passportNumber, Date dateGreg, Long dateHijri) {
        return getMapper().fromEntity(companyStaffRepository.findGroupLeaderByBasicInfo(idNumber, dateHijri, passportNumber, dateGreg, ECompanyStaffTitle.GROUP_LEADER.name()), mappingContext);
    }

    @Transactional
    public int updateCompanyStaff(long staffId, UpdateStaffCmd command) {
        int updatedRowsCount = 0;

        if (command.getCountryPhonePrefix().matches(SAUDI_MOBILE_NUMBER_REGEX)) {
            updatedRowsCount += companyStaffRepository.updateCompanyStaffLocalNumber(command.getEmail(), command.getMobileNumber(), staffId);
        } else {
            updatedRowsCount += companyStaffRepository.updateCompanyStaffIntlNumber(command.getEmail(), command.getCountryPhonePrefix(), command.getMobileNumber(), staffId);
        }
        return updatedRowsCount;
    }

    public Optional<CompanyStaffVO> findStaffRitualBySuin(String suin) {
        CompanyStaffVO staffRitual = companyStaffRepository.findStaffMainData(suin);
        return staffRitual == null? Optional.empty(): Optional.of(staffRitual);
    }

    public Optional<ApplicantStaffVO> findStaffByIdNumber(String idNumber) {
        ApplicantStaffVO staffByIdNumber = companyStaffRepository.findStaffByIdNumber(idNumber, EDigitalIdStatus.VALID.name(), ECardStatus.CANCELLED.name(), ECardStatus.SUSPENDED.name());
        return staffByIdNumber == null? Optional.empty(): Optional.of(staffByIdNumber);
    }

    public Optional<ApplicantStaffVO> findStaffBySuin(String suin) {
        ApplicantStaffVO staffByIdNumber = companyStaffRepository.findStaffBySuin(suin, EDigitalIdStatus.VALID.name(), ECardStatus.CANCELLED.name(), ECardStatus.SUSPENDED.name());
        return staffByIdNumber == null? Optional.empty(): Optional.of(staffByIdNumber);
    }

    public Optional<ApplicantStaffVO> findStaffBySuinAndCardId(String value) {
        String suin = value.substring(0,value.length()-1);
        long cardId = Long.parseLong(value.substring(value.length()-1));
        ApplicantStaffVO staffByIdNumber = companyStaffRepository.findStaffBySuinAndCardId(suin,cardId);
        return staffByIdNumber == null? Optional.empty(): Optional.of(staffByIdNumber);
    }

    @Transactional
    public Page<CompanyStaffLiteDto> searchStaff(CompanyStaffFilterDto companyStaffFilterDto, Pageable pageable) {

        Page<JpaCompanyStaff> companyStaffs = companyStaffRepository.findAll(withStaffFilter(companyStaffFilterDto), pageable);
        List<CompanyStaffLiteDto> companyStaffList = new ArrayList<>();

        if (companyStaffs.getContent() != null && !companyStaffs.getContent().isEmpty()) {
            companyStaffs.getContent().forEach(companyStaff -> {
                CompanyStaffLiteDto companyStaffLite = CompanyStaffLiteDto.builder()
                        .id(companyStaff.getId())
                        .suin(companyStaff.getDigitalIds().isEmpty() ? "" : companyStaff.getDigitalIds().get(0).getSuin())
                        .fullNameAr(companyStaff.getFullNameAr())
                        .fullNameEn(companyStaff.getFullNameEn())
                        .countryCode(companyStaff.getCountryCode())
                        .mobileNumber(companyStaff.getMobileNumber())
                        .email(companyStaff.getEmail())
                        .nationalityCode(companyStaff.getNationalityCode())
                        .titleCode(companyStaff.getTitleCode())
                        .customJobTitle(companyStaff.getCustomJobTitle())
                        .dateOfBirthGregorian(companyStaff.getDateOfBirthGregorian())
                        .dateOfBirthHijri(companyStaff.getDateOfBirthHijri())
                        .gender(companyStaff.getGender())
                        .passportNumber(companyStaff.getPassportNumber())
                        .idNumber(companyStaff.getIdNumber())
                        .linkedWithGroup(applicantGroupBasicService.existsByGroupLeader(companyStaff.getId()))
                        .photo(companyStaff.getPhoto())
                        .build();

                companyStaffList.add(companyStaffLite);
            });

        }
        Page<CompanyStaffLiteDto> companyStaff = new PageImpl<>(companyStaffList, pageable, companyStaffs.getTotalElements());
        return companyStaff;
    }

    private Specification<JpaCompanyStaff> withStaffFilter(final CompanyStaffFilterDto criteria) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            //Create atomic predicates
            List<Predicate> predicates = new ArrayList<>();
            Join<JpaCompanyStaff, JpaCompanyStaffDigitalId> digitalId = root.join("digitalIds");
            Join<JpaCompanyStaffDigitalId, JpaCompanyStaffCard> companyStaffCards = digitalId.join("companyStaffCards");
            Join<JpaCompanyStaffCard, JpaCompanyRitualSeason> companyRitualSeason = companyStaffCards.join("companyRitualSeason");

            predicates.add(criteriaBuilder.equal(root.get("deleted"), false));
            predicates.add(criteriaBuilder.notEqual(companyStaffCards.get("statusCode"), ECardStatus.EXPIRED.name()));
            predicates.add(criteriaBuilder.notEqual(companyStaffCards.get("statusCode"), ECardStatus.REISSUED.name()));

            if (criteria.getSuin() != null && !criteria.getSuin().equals("")) {
                Path<String> suin = digitalId.get("suin");
                predicates.add(criteriaBuilder.equal(suin, criteria.getSuin()));
            }

            if (criteria.getJobTitle() != null && !criteria.getJobTitle().equals("")) {
                Path<String> jobTitle = root.get("titleCode");
                predicates.add(criteriaBuilder.equal(jobTitle, criteria.getJobTitle()));
            }

            if (criteria.getPassportNumber() != null && !criteria.getPassportNumber().equals("")) {
                Path<String> passportNumber = root.get("passportNumber");
                predicates.add(criteriaBuilder.equal(passportNumber, criteria.getPassportNumber()));
            }

            if (criteria.getIdNumber() != null && !criteria.getIdNumber().equals("")) {
                Path<String> idNumber = root.get("idNumber");
                predicates.add(criteriaBuilder.equal(idNumber, criteria.getIdNumber()));
            }

            if (criteria.getGender() != null && !criteria.getGender().equals("")) {
                Path<String> gender = root.get("gender");
                predicates.add(criteriaBuilder.equal(gender, criteria.getGender()));
            }

            Join<JpaCompanyRitualSeason, JpaCompany> company = companyRitualSeason.join("company");
            Path<String> companyCode = company.get("code");
            predicates.add(criteriaBuilder.equal(companyCode, criteria.getCompanyCode()));

            if (criteria.getRitualType() != null && !criteria.getRitualType().equals("")) {
                Join<JpaCompanyRitualSeason, JpaRitualSeason> ritualSeason = companyRitualSeason.join("ritualSeason");
                predicates.add(criteriaBuilder.equal(ritualSeason.get("ritualTypeCode"), criteria.getRitualType()));
            }

            if(criteria.getLanguage() != null && !criteria.getLanguage().equals("")){
                if(criteria.getLanguage().contains("en")){
                    if(criteria.getName() != null && !criteria.getName().equals("")){
                        predicates.add(criteriaBuilder.like(root.get("fullNameEn"), "%" + criteria.getName().trim() + "%"));
                    }
                } else {
                    if(criteria.getName() != null && !criteria.getName().equals("")){
                        predicates.add(criteriaBuilder.like(root.get("fullNameAr"), "%" + criteria.getName().trim() + "%"));
                    }
                }
            }


            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    public Optional<CompanyStaffFullVO> searchStaffById(Long id) {
        List<CompanyStaffFullVO> staff = companyStaffRepository.findOrganizerStaffById(id);
        CompanyStaffFullVO companyStaffFullVO = staff.get(staff.size() - 1);
        // split the company and set only company ref code
        if(companyStaffFullVO.getCompanyCode() != null && !companyStaffFullVO.getCompanyCode().equals(""))
            companyStaffFullVO.setCompanyCode(companyStaffFullVO.getCompanyCode().contains("_") ? companyStaffFullVO.getCompanyCode().substring(0, companyStaffFullVO.getCompanyCode().indexOf("_")) : companyStaffFullVO.getCompanyCode());
        return companyStaffFullVO == null? Optional.empty(): Optional.of(companyStaffFullVO);
    }

    @Transactional
    public UpdateStaffTitleCmd updateCompanyStaffTitle(UpdateStaffTitleCmd command) {
        int updatedRowsCount = 0;
        updatedRowsCount += companyStaffRepository.updateCompanyStaffJobTitle(command.getJobTitle(), command.getCustomJobTitle(), command.getId());
        if(updatedRowsCount < 1){
            return new UpdateStaffTitleCmd();
        }
        return command;
    }

    public List<CompanyStaffDto> findRegisteredStaff(){
        return mapList(companyStaffRepository.findAllByRegisteredTrue());
    }

    public long countRegisteredStaff(){
        return companyStaffRepository.countJpaCompanyStaffByRegisteredTrue();
    }

    public List<CompanyStaffDto> findAllByIds(List<Long> selectedStaff) {
        log.info("CompanyStaffService ::: Start findAllByIds selectedStaffListSize: {} ", selectedStaff.size());
        List<CompanyStaffDto> CompanyStaffDtos = mapList(companyStaffRepository.findAllByIds(selectedStaff));
        log.info("CompanyStaffService ::: Finish findAllByIds applicantDtosListSize: {} ", CompanyStaffDtos .size());
        return CompanyStaffDtos ;
    }
    public Page<CompanyStaffDto> findByIds(@RequestParam List<Long> selectedStaffs, Pageable pageable) {
        return mapPage(companyStaffRepository.findByIds(selectedStaffs, pageable));
    }

    public List<CompanyStaffDto> findAllByCriteria(NotificationTemplateCategorizingDto applicantSearchCriteria, List<Long> excludedIds) {

        return mapList(companyStaffRepository.findAll(withStaffFilter(applicantSearchCriteria ,excludedIds)));
    }

    public Page<CompanyStaffDto> findAllByCriteriaAndNotInExcludedIds(NotificationTemplateCategorizingDto applicantSearchCriteria, List<Long> excludedIds, Pageable pageable) {

        return mapPage(companyStaffRepository.findAll(withStaffFilter(applicantSearchCriteria ,excludedIds), pageable));
    }
    private Specification<JpaCompanyStaff> withStaffFilter(final NotificationTemplateCategorizingDto criteria, List<Long> excludedIds) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            //Create atomic predicates
            List<Predicate> predicates = new ArrayList<>();

            //Filter registered staff
            predicates.add(criteriaBuilder.equal(root.get("registered"), true));

            if (criteria.getSuin() != null && criteria.getSuin().trim().length() > 0) {
                Join<JpaCompanyStaff, JpaCompanyStaffDigitalId> digitalIds = root.join("digitalIds");
                Path<String> suin = digitalIds.get("suin");
                predicates.add(criteriaBuilder.equal(suin, criteria.getSuin()));
                //predicates.add(criteriaBuilder.like(digitalIds.get("suin"), "%" + criteria.getSuin().trim() + "%"));
            }

            if (criteria.getStaffTitle() != null ) {
                predicates.add(criteriaBuilder.equal(root.get("titleCode"), criteria.getStaffTitle()));
            }

            if (excludedIds != null && !excludedIds.isEmpty()) {
                predicates.add(criteriaBuilder.not(root.get("id").in(excludedIds)));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }


    public CompanyStaffMainFullDataDto saveOrUpdateStaffFullMainData(CompanyStaffMainFullDataDto companyStaffMainFullData, MultipartFile avatar){

        CompanyStaffDto staff = mapCompanyStaffDto(companyStaffMainFullData);
        CompanyStaffDto existingStaff = findByBasicInfo(staff.getIdNumber(), staff.getPassportNumber(), staff.getNationalityCode());

        // if record exists already in DB we need to update it
        if (existingStaff != null) {
            if(avatar == null){
                staff.setPhoto(existingStaff.getPhoto());
            } else {
                try {
                    BufferedImage image = ImageIO.read(avatar.getInputStream());
                    staff.setPhoto(ImageUtils.imgToBase64String(image));
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            staff.setId(existingStaff.getId());
            staff.setDigitalIds(existingStaff.getDigitalIds());
            staff = save(staff);
            companyStaffMainFullData.setId(staff.getId());
        } else {
            if(avatar == null){
                BufferedImage defaultImage;
                if(companyStaffMainFullData.getGender().equals("M")) {
                    defaultImage = ImageUtils.loadFromClasspath(DEFAULT_AVATAR_MALE);
                } else {
                    defaultImage = ImageUtils.loadFromClasspath(DEFAULT_AVATAR_FEMALE);
                }

                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                final String defaultAvatar;

                try {
                    ImageIO.write(defaultImage, "png", bos);
                    byte[] bytes = bos.toByteArray();

                    defaultAvatar = Base64.getEncoder().encodeToString(bytes).replace(System.lineSeparator(), "");
                } catch (IOException e) {
                    throw new RuntimeException();
                }

                staff.setPhoto(defaultAvatar);
            } else {
                try {
                    BufferedImage image = ImageIO.read(avatar.getInputStream());
                    staff.setPhoto(ImageUtils.imgToBase64String(image));
                } catch (IOException e) {
                    throw new RuntimeException();
                }
            }
            staff = save(staff);
            companyStaffMainFullData.setId(staff.getId());
        }

        // start adding staff ritual data
        CompanyStaffRitualDto companyStaffRitual = mapCompanyStaffRitualDto(companyStaffMainFullData);
        saveStaffFullRitual(companyStaffRitual, staff.getId());

        return companyStaffMainFullData;
    }

    private void saveStaffFullRitual(CompanyStaffRitualDto companyStaffRitual, long staffId) {
        CompanyStaffDto existingStaff = findOne(staffId);
        CompanyStaffDigitalIdBasicDto companyStaffDigitalId = companyStaffDigitalIdBasicService.findByBasicInfoWithoutDigitalIdStatus(existingStaff.getId(), companyStaffRitual.getSeason());
        if (companyStaffDigitalId != null) {
            //set digital id status is valid
            companyStaffDigitalId.setStatusCode(EDigitalIdStatus.VALID.name());
            companyStaffDigitalIdBasicService.save(companyStaffDigitalId);
            // if he has a digital id for that same season
            List<CompanyStaffCardDto> companyStaffCardDtos = companyStaffCardService.findByDigitalId(companyStaffDigitalId.getSuin());
            // if no cards for digitalId and SEASON
            if (companyStaffCardDtos.isEmpty()) {
                CompanyStaffCardDto companyStaffCardDto = new CompanyStaffCardDto();
                companyStaffCardDto.setCompanyStaffDigitalId(CompanyStaffDigitalIdDto.builder().id(companyStaffDigitalId.getId()).build());
                companyStaffCardDto.setStatusCode(ECardStatus.READY_TO_PRINT.name());
                companyStaffCardDto.setCompanyRitualSeason(CompanyRitualSeasonDto.builder()
                        .id(companyRitualSeasonService.getCompanyRitualSeasonId(companyStaffRitual.getCompanyCode(), companyStaffRitual.getTypeCode(), companyStaffRitual.getSeason())).build());
                companyStaffCardService.save(companyStaffCardDto);
                return;

            }

            //find staff cards for different company or different ritual
            List<CompanyStaffCardDto> companyStaffCards2 = companyStaffCardService.findByDigitalIdAndDifferentCompanyOrRitual(companyStaffDigitalId.getSuin(), companyStaffRitual.getCompanyCode(), companyStaffRitual.getTypeCode());
            if (CollectionUtils.isNotEmpty(companyStaffCards2)) {
                companyStaffCards2.forEach(c -> {
                    c.setStatusCode(ECardStatus.EXPIRED.name());
                });
                companyStaffCardService.saveAll(companyStaffCards2);
                CompanyStaffCardDto companyStaffCardDto = new CompanyStaffCardDto();
                companyStaffCardDto.setCompanyStaffDigitalId(CompanyStaffDigitalIdDto.builder().id(companyStaffDigitalId.getId()).build());
                companyStaffCardDto.setStatusCode(ECardStatus.READY_TO_PRINT.name());
                companyStaffCardDto.setCompanyRitualSeason(CompanyRitualSeasonDto.builder()
                        .id(companyRitualSeasonService.getCompanyRitualSeasonId(companyStaffRitual.getCompanyCode(), companyStaffRitual.getTypeCode(), companyStaffRitual.getSeason())).build());
                companyStaffCardService.save(companyStaffCardDto);
                return;
            }

            // find staff cards for same company and same ritual
            List<CompanyStaffCardDto> companyStaffCards = companyStaffCardService.findByDigitalIdCompanyCodeRitualType(companyStaffDigitalId.getSuin(), companyStaffRitual.getCompanyCode(), companyStaffRitual.getTypeCode());
            if (companyStaffCards.isEmpty()) {
                CompanyStaffCardDto companyStaffCardDto = new CompanyStaffCardDto();
                companyStaffCardDto.setCompanyStaffDigitalId(CompanyStaffDigitalIdDto.builder().id(companyStaffDigitalId.getId()).build());
                companyStaffCardDto.setStatusCode(ECardStatus.READY_TO_PRINT.name());
                companyStaffCardDto.setCompanyRitualSeason(CompanyRitualSeasonDto.builder()
                        .id(companyRitualSeasonService.getCompanyRitualSeasonId(companyStaffRitual.getCompanyCode(), companyStaffRitual.getTypeCode(), companyStaffRitual.getSeason())).build());
                companyStaffCardService.save(companyStaffCardDto);
                return;
            }


        } else {
            // create new digital id for that staff in case he has no digital id for that same season
            CompanyStaffDigitalIdDto staffDigitalId = new CompanyStaffDigitalIdDto();
            staffDigitalId.setCompanyStaff(existingStaff);
            staffDigitalId.setSeasonYear(companyStaffRitual.getSeason());
            staffDigitalId.setSuin(companyStaffDigitalIdService.generate(existingStaff, companyStaffRitual.getSeason()));
            staffDigitalId.setStatusCode(EStaffDigitalIdStatus.VALID.name());
            CompanyStaffDigitalIdDto savedDigitalId = companyStaffDigitalIdService.save(staffDigitalId);
            CompanyStaffCardDto companyStaffCardDto = new CompanyStaffCardDto();
            companyStaffCardDto.setCompanyStaffDigitalId(savedDigitalId);
            companyStaffCardDto.setStatusCode(ECardStatus.READY_TO_PRINT.name());
            companyStaffCardDto.setCompanyRitualSeason(CompanyRitualSeasonDto.builder()
                    .id(companyRitualSeasonService.getCompanyRitualSeasonId(companyStaffRitual.getCompanyCode(), companyStaffRitual.getTypeCode(), companyStaffRitual.getSeason())).build());
            companyStaffCardService.save(companyStaffCardDto);

        }
    }

    private CompanyStaffDto mapCompanyStaffDto(CompanyStaffMainFullDataDto companyStaffFullData){
        CompanyStaffDto companyStaff = CompanyStaffDto.builder()
                .idNumber(companyStaffFullData.getIdNumber())
                .passportNumber(companyStaffFullData.getPassportNumber())
                .dateOfBirthGregorian(companyStaffFullData.getDateOfBirthGregorian())
                .dateOfBirthHijri(companyStaffFullData.getDateOfBirthHijri())
                .fullNameAr(companyStaffFullData.getFullNameAr())
                .fullNameEn(companyStaffFullData.getFullNameEn())
                .fullNameOrigin(companyStaffFullData.getFullNameOrigin())
                .gender(companyStaffFullData.getGender())
                .nationalityCode(companyStaffFullData.getNationalityCode())
                .idNumberOriginal(companyStaffFullData.getIdNumberOriginal())
                .titleCode(companyStaffFullData.getJobTitle())
                .customJobTitle(companyStaffFullData.getCustomJobTitle())
                .email(companyStaffFullData.getEmail())
                .mobileNumber(companyStaffFullData.getMobileNumber())
                .mobileNumberIntl(companyStaffFullData.getCountryPhonePrefix() + companyStaffFullData.getMobileNumber())
                .countryPhonePrefix(companyStaffFullData.getCountryPhonePrefix())
                .deleted(Boolean.FALSE)
                .build();
        return companyStaff;
    }

    private CompanyStaffRitualDto mapCompanyStaffRitualDto(CompanyStaffMainFullDataDto companyStaffFullData){
        CompanyStaffRitualDto companyStaffRitual = CompanyStaffRitualDto.builder()
                .idNumber(companyStaffFullData.getIdNumber())
                .passportNumber(companyStaffFullData.getPassportNumber())
                .dateOfBirthGregorian(companyStaffFullData.getDateOfBirthGregorian())
                .dateOfBirthHijri(companyStaffFullData.getDateOfBirthHijri())
                .companyCode(companyStaffFullData.getCompanyCode())
                .typeCode(companyStaffFullData.getRitualTypeCode())
                .season(seasonYear)
                .build();
        return companyStaffRitual;
    }

    public List<DataValidationResult> isValidCompanyRitualSeason(CompanyStaffMainFullDataDto companyStaffMainFullData){
        String attributeName = "ritualTypeCode";
        String idNumberAttribute = "idNumber";
        List<DataValidationResult> dataValidationResults = new ArrayList<>();
        // check company ritual season exist for the ritual type, seasson and company
        CompanyRitualSeasonDto companyRitualSeasonDto = companyRitualSeasonService.getCompanyRitualSeason(companyStaffMainFullData.getCompanyCode(), companyStaffMainFullData.getRitualTypeCode(), seasonYear);
        if(companyRitualSeasonDto == null){
            dataValidationResults.add(DataValidationResult.builder().valid(false)
                    .errorMessages(Collections.singletonList(messageSource.getMessage(EExcelItemReaderErrorType.NOT_RITUAL_TYPE_FOUND.getMessage(), null, Locale.forLanguageTag("en")) + " \n " + messageSource.getMessage(EExcelItemReaderErrorType.NOT_RITUAL_TYPE_FOUND.getMessage(), null, Locale.forLanguageTag("ar"))))
                    .attributeName(attributeName).build());
        }

        CompanyStaffDto existingStaff = findByBasicInfo(companyStaffMainFullData.getIdNumber(), companyStaffMainFullData.getPassportNumber(), companyStaffMainFullData.getNationalityCode());

        if(existingStaff != null){
            if(applicantGroupBasicService.existsByGroupLeader(existingStaff.getId()) && !exitsStaffInCompany(existingStaff.getId(), companyRitualSeasonDto.getId())){
                dataValidationResults.add(DataValidationResult.builder().valid(false)
                        .errorMessages(Collections.singletonList(messageSource.getMessage(EExcelItemReaderErrorType.STAFF_ID_ALREADY_EXITS_IN_COMPANY.getMessage(), null, Locale.forLanguageTag("en")) + " \n " + messageSource.getMessage(EExcelItemReaderErrorType.NOT_RITUAL_TYPE_FOUND.getMessage(), null, Locale.forLanguageTag("ar"))))
                        .attributeName(idNumberAttribute).build());
            }
        }
        return dataValidationResults;
    }

    @Transactional
    public boolean deleteStaff(Long staffId){
        log.info("Start delete staff by staff id. {}", staffId);
        if(staffId == null) return false;
        // update staff as deleted true
        CompanyStaffDto staff = findOne(staffId);
        staff.setDeleted(Boolean.TRUE);
        save(staff);
        log.info("Update staff. {}", staff);
        // update digital id status INVALID
        if(staff == null) return false;

        log.info("Start update digital id status by staff id. {}", staff.getId());
        companyStaffDigitalIdService.updateDigitalIdStatus(staffId);

        List<Long> staffCardIdList = companyStaffCardService.findStaffCardByStaffId(staffId);
        if(staffCardIdList.isEmpty()) return false;
        // update staff car status Cancelled
        companyStaffCardService.updateStaffCardStatusByStaffId(staffCardIdList);
        log.info("Update staff successfully ..");

        return true;
    }

    public Boolean exitsStaffInCompany(Long staffId, Long companyRitualSeasonId) {
        return companyStaffRepository.exitsStaffInCompany(staffId, companyRitualSeasonId);
    }

}
