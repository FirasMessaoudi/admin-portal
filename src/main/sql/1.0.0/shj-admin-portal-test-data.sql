USE shj_portal
GO
-- insert data request
SET IDENTITY_INSERT shj_portal.sha_data_request ON;
INSERT INTO shj_portal.sha_data_request (id, reference_number, channel, data_segment_id, original_source_path,
                                         error_file_path, status_id)
VALUES (1, '345345', 'WEB_SERVICE', 1, 'dummy/path', NULL, 1);
SET IDENTITY_INSERT shj_portal.sha_data_request OFF;
GO

-- insert applicant
SET IDENTITY_INSERT shj_portal.sha_applicant ON;
INSERT INTO shj_portal.sha_applicant (id, gender, nationality_code, id_number, id_number_original, passport_number,
                                      date_of_birth_gregorian, date_of_birth_hijri, full_name_ar, full_name_en,
                                      full_name_origin, marital_status_code, request_id, status)
VALUES (1, 'M', 'SA', 2541235487, '9184542873', '7O8512', convert(date, '05/11/1981', 103), 14400505,
        N'عبد الله محمد عبد العزيز القحطاني', 'AbdAllah Moh AbdAlaziz AlQahtani', NULL, 'MARRIED', 1, 1);

INSERT INTO shj_portal.sha_applicant (id, gender, nationality_code, id_number, id_number_original, passport_number,
                                      date_of_birth_gregorian, date_of_birth_hijri, full_name_ar, full_name_en,
                                      full_name_origin, marital_status_code, request_id, status)
VALUES (2, 'F', 'SA', 2581275408, '9104542173', '712518', convert(date, '12/04/1986', 103), 14060721,
        N'ميساء بنت عبد الله بن عيسى البشراوي', 'Maysaa AbdAllah Eissa AlBachraoui', NULL, 'WIDOWED', 1, 1);

INSERT INTO shj_portal.sha_applicant (id, gender, nationality_code, id_number, id_number_original, passport_number,
                                      date_of_birth_gregorian, date_of_birth_hijri, full_name_ar, full_name_en,
                                      full_name_origin, marital_status_code, request_id, status)
VALUES (3, 'F', 'SA', 2401275409, '9304542120', '733518', convert(date, '01/06/1955', 103), 13741010,
        N'وضحة بنت منصور بن عثمان العتيبي', 'Wadha Mansour Othman AlOtaibi', NULL, 'MARRIED', 1, 1);

SET IDENTITY_INSERT shj_portal.sha_applicant OFF;
GO

-- insert applicant contact
INSERT INTO shj_portal.sha_applicant_contact (applicant_id, language_list, email, local_mobile_number,
                                              intl_mobile_number, country_code, street_name, district_name, city_name,
                                              building_number, postal_code)
VALUES (1, 'AR,EN', 'app@elm.sa', 558572465, 962796322285, 'SA', N'شارع الأمير بندر', N'الغدير',
        N'مدغشقر', 325, 32145);
GO

-- insert applicant relatives
INSERT INTO shj_portal.sha_applicant_relative (relationship_code, applicant_id, relative_applicant_id)
VALUES ('MOTHER', 1, 2);
INSERT INTO shj_portal.sha_applicant_relative (relationship_code, applicant_id, relative_applicant_id)
VALUES ('WIFE', 1, 3);
GO

-- insert applicant digital id
INSERT INTO shj_portal.sha_applicant_digital_id (uin, applicant_id)
VALUES ('521468547', 1);
GO

-- insert applicant relatives' digital ids
INSERT INTO shj_portal.sha_applicant_digital_id (uin, applicant_id)
VALUES ('529417044', 2);
GO

INSERT INTO shj_portal.sha_applicant_digital_id (uin, applicant_id)
VALUES ('523096627', 3);
GO

-- insert applicant ritual
SET IDENTITY_INSERT shj_portal.sha_applicant_ritual ON;
INSERT INTO shj_portal.sha_applicant_ritual (id, applicant_id, hamlah_package_code, hijri_season, date_start_gregorian,
                                             date_end_gregorian, date_start_hijri, date_end_hijri, type_code,
                                             visa_number, permit_number, insurance_number, border_number)
VALUES (1, 1, NULL, 1442, convert(date, '14/07/2021', 103), convert(date, '03/09/2021', 103), 01101442, 15121442,
        'INTERNAL_HAJJ', NULL, '32458', '5214587', '687445');
SET IDENTITY_INSERT shj_portal.sha_applicant_ritual OFF;
GO

-- insert applicant health
SET IDENTITY_INSERT shj_portal.sha_applicant_health ON;
INSERT INTO shj_portal.sha_applicant_health (id, applicant_id, blood_type)
VALUES (1, 1, 'AB+');
SET IDENTITY_INSERT shj_portal.sha_applicant_health OFF;
GO

-- insert applicant health immunization
INSERT INTO shj_portal.sha_applicant_health_immunization (applicant_health_id, immunization_code, immunization_date, mandatory)
VALUES (1, 'COVID-19', convert(date, '03/06/2021', 103), 1);
INSERT INTO shj_portal.sha_applicant_health_immunization (applicant_health_id, immunization_code, immunization_date, mandatory)
VALUES (1, 'MENINGITIS', convert(date, '27/05/2021', 103), 0);
GO

-- insert applicant health disease
INSERT INTO shj_portal.sha_applicant_health_disease (applicant_health_id, disease_name_ar, disease_name_en)
VALUES (1, N'ضغط الدم', 'Hypertension');
GO

-- insert applicant health special needs
INSERT INTO shj_portal.sha_applicant_health_special_needs (applicant_health_id, special_need_type_code)
VALUES (1, 'WHEELCHAIR');
GO

-- insert applicant card batch
SET IDENTITY_INSERT shj_portal.sha_card_batch ON;
INSERT INTO shj_portal.sha_card_batch (id, number)
VALUES (1, '317');
SET IDENTITY_INSERT shj_portal.sha_card_batch OFF;
GO

-- insert applicant card
INSERT INTO shj_portal.sha_applicant_card (applicant_ritual_id, reference_number, batch_id, status_code)
VALUES (1, '362145', 1, 'SENT_FOR_PRINT');
GO

-- insert decision rule
INSERT INTO shj_portal.sha_decision_rule (dmn, data_segment_id, label_ar, label_en, creation_date, update_date)
VALUES (N'<?xml version="1.0" encoding="UTF-8"?>
                                <definitions xmlns="https://www.omg.org/spec/DMN/20191111/MODEL/" xmlns:dmndi="https://www.omg.org/spec/DMN/20191111/DMNDI/" xmlns:dc="http://www.omg.org/spec/DMN/20180521/DC/" xmlns:di="http://www.omg.org/spec/DMN/20180521/DI/" id="dish" name="Dish" namespace="http://camunda.org/schema/1.0/dmn" exporter="dmn-js (https://demo.bpmn.io/dmn)" exporterVersion="8.3.0">
                                <inputData id="dayType_id" name="Type of day">
                                <variable id="dayType_ii" name="Type of day" typeRef="string" />
                                </inputData>
                                <inputData id="temperature_id" name="Weather in Celsius">
                                <variable id="temperature_ii" name="Weather in Celsius" typeRef="integer" />
                                </inputData>
                                <knowledgeSource id="host_ks" name="Host" />
                                <knowledgeSource id="guest_ks" name="Guest Type">
                                <authorityRequirement id="AuthorityRequirement_0vkhray">
                                <requiredDecision href="#guestCount" />
                                </authorityRequirement>
                                </knowledgeSource>
                                <businessKnowledgeModel id="elMenu" name="The Menu" />
                                <decision id="dish-decision" name="Dish Decision">
                                <informationRequirement id="InformationRequirement_1d56kg6">
                                <requiredDecision href="#guestCount" />
                                </informationRequirement>
                                <informationRequirement id="InformationRequirement_11onl5b">
                                <requiredDecision href="#season" />
                                </informationRequirement>
                                <authorityRequirement id="AuthorityRequirement_142y75e">
                                <requiredAuthority href="#host_ks" />
                                </authorityRequirement>
                                <decisionTable id="dishDecisionTable">
                                <input id="seasonInput" label="Season">
                                <inputExpression id="seasonInputExpression" typeRef="string">
                                <text>season</text>
                                </inputExpression>
                                </input>
                                <input id="guestCountInput" label="How many guests">
                                <inputExpression id="guestCountInputExpression" typeRef="integer">
                                <text>guestCount</text>
                                </inputExpression>
                                </input>
                                <output id="output1" label="Dish" name="desiredDish" typeRef="string" />
                                <rule id="row-495762709-1">
                                <inputEntry id="UnaryTests_1nxcsjr">
                                <text>"Winter"</text>
                                </inputEntry>
                                <inputEntry id="UnaryTests_1r9yorj">
                                <text>&lt;= 8</text>
                                </inputEntry>
                                <outputEntry id="LiteralExpression_1mtwzqz">
                                <text>"Spareribs"</text>
                                </outputEntry>
                                </rule>
                                <rule id="row-495762709-2">
                                <inputEntry id="UnaryTests_1lxjbif">
                                <text>"Winter"</text>
                                </inputEntry>
                                <inputEntry id="UnaryTests_0nhiedb">
                                <text>&gt; 8</text>
                                </inputEntry>
                                <outputEntry id="LiteralExpression_1h30r12">
                                <text>"Pasta"</text>
                                </outputEntry>
                                </rule>
                                <rule id="row-495762709-3">
                                <inputEntry id="UnaryTests_0ifgmfm">
                                <text>"Summer"</text>
                                </inputEntry>
                                <inputEntry id="UnaryTests_12cib9m">
                                <text>&gt; 10</text>
                                </inputEntry>
                                <outputEntry id="LiteralExpression_0wgaegy">
                                <text>"Light salad"</text>
                                </outputEntry>
                                </rule>
                                <rule id="row-495762709-7">
                                <inputEntry id="UnaryTests_0ozm9s7">
                                <text>"Summer"</text>
                                </inputEntry>
                                <inputEntry id="UnaryTests_0sesgov">
                                <text>&lt;= 10</text>
                                </inputEntry>
                                <outputEntry id="LiteralExpression_1dvc5x3">
                                <text>"Beans salad"</text>
                                </outputEntry>
                                </rule>
                                <rule id="row-445981423-3">
                                <inputEntry id="UnaryTests_1er0je1">
                                <text>"Spring"</text>
                                </inputEntry>
                                <inputEntry id="UnaryTests_1uzqner">
                                <text>&lt; 10</text>
                                </inputEntry>
                                <outputEntry id="LiteralExpression_1pxy4g1">
                                <text>"Stew"</text>
                                </outputEntry>
                                </rule>
                                <rule id="row-445981423-4">
                                <inputEntry id="UnaryTests_06or48g">
                                <text>"Spring"</text>
                                </inputEntry>
                                <inputEntry id="UnaryTests_0wa71sy">
                                <text>&gt;= 10</text>
                                </inputEntry>
                                <outputEntry id="LiteralExpression_09ggol9">
                                <text>"Steak"</text>
                                </outputEntry>
                                </rule>
                                </decisionTable>
                                </decision>
                                <decision id="season" name="Season decision">
                                <informationRequirement id="InformationRequirement_1sdwefx">
                                <requiredInput href="#temperature_id" />
                                </informationRequirement>
                                <decisionTable id="seasonDecisionTable">
                                <input id="temperatureInput" label="Weather in Celsius">
                                <inputExpression id="temperatureInputExpression" typeRef="integer">
                                <text>temperature</text>
                                </inputExpression>
                                </input>
                                <output id="seasonOutput" label="season" name="season" typeRef="string" />
                                <rule id="row-495762709-5">
                                <inputEntry id="UnaryTests_1fd0eqo">
                                <text>&gt;30</text>
                                </inputEntry>
                                <outputEntry id="LiteralExpression_0l98klb">
                                <text>"Summer"</text>
                                </outputEntry>
                                </rule>
                                <rule id="row-495762709-6">
                                <inputEntry id="UnaryTests_1nz6at2">
                                <text>&lt;10</text>
                                </inputEntry>
                                <outputEntry id="LiteralExpression_08moy1k">
                                <text>"Winter"</text>
                                </outputEntry>
                                </rule>
                                <rule id="row-445981423-2">
                                <inputEntry id="UnaryTests_1a0imxy">
                                <text>[10..30]</text>
                                </inputEntry>
                                <outputEntry id="LiteralExpression_1poftw4">
                                <text>"Spring"</text>
                                </outputEntry>
                                </rule>
                                </decisionTable>
                                </decision>
                                <decision id="guestCount" name="Guest Count">
                                <informationRequirement id="InformationRequirement_0j60f3j">
                                <requiredInput href="#dayType_id" />
                                </informationRequirement>
                                <knowledgeRequirement id="KnowledgeRequirement_0n56cqb">
                                <requiredKnowledge href="#elMenu" />
                                </knowledgeRequirement>
                                <decisionTable id="guestCountDecisionTable">
                                <input id="typeOfDayInput" label="Type of day">
                                <inputExpression id="typeOfDayInputExpression" typeRef="string">
                                <text>dayType</text>
                                </inputExpression>
                                </input>
                                <output id="guestCountOutput" label="Guest count" name="guestCount" typeRef="integer" />
                                <rule id="row-495762709-8">
                                <inputEntry id="UnaryTests_0l72u8n">
                                <text>"Weekday"</text>
                                </inputEntry>
                                <outputEntry id="LiteralExpression_0wuwqaz">
                                <text>4</text>
                                </outputEntry>
                                </rule>
                                <rule id="row-495762709-9">
                                <inputEntry id="UnaryTests_03a73o9">
                                <text>"Holiday"</text>
                                </inputEntry>
                                <outputEntry id="LiteralExpression_1whn119">
                                <text>10</text>
                                </outputEntry>
                                </rule>
                                <rule id="row-495762709-10">
                                <inputEntry id="UnaryTests_12tygwt">
                                <text>"Weekend"</text>
                                </inputEntry>
                                <outputEntry id="LiteralExpression_1b5k9t8">
                                <text>15</text>
                                </outputEntry>
                                </rule>
                                </decisionTable>
                                </decision>
                                <textAnnotation id="TextAnnotation_1">
                                <text>Week day or week end</text>
                                </textAnnotation>
                                <association id="Association_18hoj4i">
                                <sourceRef href="#dayType_id" />
                                <targetRef href="#TextAnnotation_1" />
                                </association>
                                <dmndi:DMNDI>
                                <dmndi:DMNDiagram id="DMNDiagram_05sfxgt">
                                <dmndi:DMNShape id="DMNShape_1nkrqp5" dmnElementRef="dayType_id">
                                <dc:Bounds height="45" width="125" x="417" y="377" />
                                </dmndi:DMNShape>
                                <dmndi:DMNShape id="DMNShape_0wgwr3t" dmnElementRef="temperature_id">
                                <dc:Bounds height="45" width="125" x="188" y="377" />
                                </dmndi:DMNShape>
                                <dmndi:DMNShape id="DMNShape_17n98pm" dmnElementRef="host_ks">
                                <dc:Bounds height="63" width="100" x="646" y="48" />
                                </dmndi:DMNShape>
                                <dmndi:DMNShape id="DMNShape_1i9incu" dmnElementRef="guest_ks">
                                <dc:Bounds height="63" width="100" x="660" y="198" />
                                </dmndi:DMNShape>
                                <dmndi:DMNEdge id="DMNEdge_0tdfvdg" dmnElementRef="AuthorityRequirement_0vkhray">
                                <di:waypoint x="570" y="245" />
                                <di:waypoint x="660" y="235" />
                                </dmndi:DMNEdge>
                                <dmndi:DMNShape id="DMNShape_1uo50vq" dmnElementRef="elMenu">
                                <dc:Bounds height="46" width="135" x="642" y="307" />
                                </dmndi:DMNShape>
                                <dmndi:DMNShape id="DMNShape_0s7a8pk" dmnElementRef="dish-decision">
                                <dc:Bounds height="80" width="180" x="301" y="48" />
                                </dmndi:DMNShape>
                                <dmndi:DMNEdge id="DMNEdge_1cvfntf" dmnElementRef="InformationRequirement_1d56kg6">
                                <di:waypoint x="480" y="210" />
                                <di:waypoint x="421" y="148" />
                                <di:waypoint x="421" y="128" />
                                </dmndi:DMNEdge>
                                <dmndi:DMNEdge id="DMNEdge_0djoiii" dmnElementRef="InformationRequirement_11onl5b">
                                <di:waypoint x="251" y="210" />
                                <di:waypoint x="361" y="148" />
                                <di:waypoint x="361" y="128" />
                                </dmndi:DMNEdge>
                                <dmndi:DMNEdge id="DMNEdge_0qqxexx" dmnElementRef="AuthorityRequirement_142y75e">
                                <di:waypoint x="646" y="81" />
                                <di:waypoint x="481" y="86" />
                                </dmndi:DMNEdge>
                                <dmndi:DMNShape id="DMNShape_06z5z89" dmnElementRef="season">
                                <dc:Bounds height="80" width="180" x="161" y="210" />
                                </dmndi:DMNShape>
                                <dmndi:DMNEdge id="DMNEdge_1383eyj" dmnElementRef="InformationRequirement_1sdwefx">
                                <di:waypoint x="251" y="377" />
                                <di:waypoint x="251" y="310" />
                                <di:waypoint x="251" y="290" />
                                </dmndi:DMNEdge>
                                <dmndi:DMNShape id="DMNShape_0qbhe8q" dmnElementRef="guestCount">
                                <dc:Bounds height="80" width="180" x="390" y="210" />
                                </dmndi:DMNShape>
                                <dmndi:DMNEdge id="DMNEdge_131oa1j" dmnElementRef="KnowledgeRequirement_0n56cqb">
                                <di:waypoint x="691" y="307" />
                                <di:waypoint x="570" y="262" />
                                </dmndi:DMNEdge>
                                <dmndi:DMNEdge id="DMNEdge_1avtdb1" dmnElementRef="InformationRequirement_0j60f3j">
                                <di:waypoint x="480" y="377" />
                                <di:waypoint x="480" y="310" />
                                <di:waypoint x="480" y="290" />
                                </dmndi:DMNEdge>
                                <dmndi:DMNShape id="DMNShape_0bblyhb" dmnElementRef="TextAnnotation_1">
                                <dc:Bounds height="45" width="125" x="328" y="477" />
                                </dmndi:DMNShape>
                                <dmndi:DMNEdge id="DMNEdge_0aqnkob" dmnElementRef="Association_18hoj4i">
                                <di:waypoint x="480" y="422" />
                                <di:waypoint x="391" y="477" />
                                </dmndi:DMNEdge>
                                </dmndi:DMNDiagram>
                                </dmndi:DMNDI>
                                </definitions>',
        1, N'بيانات المتقدم', N'Applicant Data', N'2021-04-13 13:38:43', null);
GO
