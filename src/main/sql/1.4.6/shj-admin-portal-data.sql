USE shc_portal
GO

SET IDENTITY_INSERT shc_portal.shc_chatbot_item ON;
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (115, 'HAJJ_RITUAL', 'fr','A propos du Hajj');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (116, 'LOCATIONS', 'fr','Lieux importants ');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (117, 'CONTACTS', 'fr','Numéros de contact importants');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (118, 'SMART_CARD', 'fr','Carte à puce');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (119, 'SPECIAL_NEEDS', 'fr','Besoins spécifiques');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (120, 'SLAUGHTER_PROCESS','fr','Quand l''abattage peut-il être confirmé ? Comme lorsque la personne requérant le rituel est un des pèlerins, de sorte que l''accomplissement de son rituel est lié à sa sortie de l''état d''Ihraam au Hajj ou le sacrifiant qui n''est pas un pèlerin ?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (121, 'SLAUGHTER_PROCESS_RESPONSE','fr', 'Après l''achat et dès que le montant est déduit du compte du client et que sa réception sur le compte du Projet est confirmée, ou que le client obtient le coupon ou le bon de rituel, le client est donc considéré comme ayant confié au Projet l''accomplissement du rituel en son nom ; et ainsi, il est déchargé de l''accomplissement du rituel, en notant que dans le cas où l''achat et le paiement ont été effectués par le site web, un message sera envoyé pour informer le client de l''exécution de sa demande (l''achèvement de l''abattage).','SLAUGHTER_PROCESS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (122, 'ELECTRONIC_PATH', 'fr', 'La voie électronique permet-elle d''acheter des Hady et des Adahi (animaux sacrifiés) ?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (123, 'ELECTRONIC_PATH_RESPONSE', 'fr','Oui, ce service est disponible.','ELECTRONIC_PATH');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (124, 'HAJJ_APPLICATION', 'fr','Quand débute le dépôt des demandes pour la saison du Hajj 1443 AH ?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (125, 'HAJJ_APPLICATION_RESPONSE', 'fr','L''annonce de la date d''ouverture des inscriptions pour le Hadj 1443 AH a commencé sur le site web du ministère du Hadj et de la Omra et sur ses canaux officiels le vendredi 4/11/1443 AH (Le 3 Juin 2022)', 'HAJJ_APPLICATION');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (126, 'HAJJ_AGE', 'fr','Quel est l''âge autorisé pour accomplir le Hajj ?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (127, 'HAJJ_AGE_RESPONSE', 'fr','Il n''y a aucune contre-indication à accompagner des enfants pendant le Hajj de 1443, à condition qu''ils soient vaccinés.', 'HAJJ_AGE');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (128, 'INTERNAL_HAJJ', 'fr','Quels sont les dates de début et de fin de la demande pour les campagnes internes du Hajj ?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (129, 'INTERNAL_HAJJ_RESPONSE', 'fr','Les inscriptions commencent le 4 de Dhu Al-Qaedah (le 3 Juin) et se terminent le 13 de Dhu Al-Qaedah (le 12 Juin).', 'INTERNAL_HAJJ');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (130, 'HAJJ_INQUIRY', 'fr','Comment se renseigner sur l''éligibilité au Hajj ?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (131, 'HAJJ_INQUIRY_RESPONSE', 'fr','Pour se renseigner sur l''éligibilité au Hajj, il suffit de se rendre sur la plate-forme Absher, de sélectionner " e-inquiries ", de se renseigner sur l''éligibilité au Hajj, puis de saisir le numéro d''identification et le code visuel, et l''éligibilité apparaîtra.', 'HAJJ_INQUIRY');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (132, 'GULF_NATIONALS', 'fr','Citoyens du CCG qui se trouvent dans le Royaume mais ne sont pas résidents et souhaitent accomplir le Hajj national', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (133, 'GULF_NATIONALS_RESPONSE', 'fr','Un citoyen du CCG qui réside dans le Royaume peut s''inscrire dans la voie électronique en utilisant le numéro de saisie qu''il peut obtenir auprès du département des passeports du CCG ""*. Un citoyen du CCG qui ne réside pas dans le Royaume ne peut pas s''enregistrer dans la voie électronique destinée aux pèlerins nationaux. Il doit s''enregistrer dans son pays conformément à la réglementation de celui-ci. ', 'GULF_NATIONALS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (134, 'MADINAH_CARE_CENTERS', 'fr','Centres de soins de Médine','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (135, 'MADINAH_CARE_CENTERS_RESPONSE', 'fr','Centre de l''est de la mosquée du Prophète Adresse : King Faisal Road, 1st Ring Road, Al-Haram Horaires d''ouverture : 7h-21h Aéroport international Prince Mohammad bin Abdulaziz Adresse : Aéroport international Prince Mohammad bin Abdulaziz Horaires d''ouverture : 8h - 22h','MADINAH_CARE_CENTERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (136, 'JEDDAH_CARE_CENTERS', 'fr','Centres de soins de Djeddah','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (137, 'JEDDAH_CARE_CENTERS_RESPONSE', 'fr','Aéroport international Roi Abdulaziz à Djeddah Adresse : King Abdulaziz International Airport Terminal No. 1','JEDDAH_CARE_CENTERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (138, 'MAKKAH_CARE_CENTERS', 'fr','Centres de soins de La Mecque','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (139, 'MAKKAH_CARE_CENTERS_RESPONSE', 'fr','Succursale d''Al-Hujun : Madinah Road, Al Hujun, Makkah. Horaires d''ouverture : 8h - 22h Lien de localisation sur Google Maps : https://maps.app.goo.gl/NE1G4t6S6WvNkDVb8 Succursale de Misfalah : Adresse : Ibrahim Al Khalil Street, Misfalah, Makkah. Horaires d''ouverture : 8h - 22h Lien de localisation sur Google Maps : https://maps.app.goo.gl/NGCnEg6hCp9JTT238 Succursale de Dar Al-Tawhid Hotel / Makkah Lien de localisation sur Google Maps : https://maps.app.goo.gl/3c9iDmVRL2Zqk3n57','MAKKAH_CARE_CENTERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (140, 'HARAM', 'fr','Al-Haram','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (141, 'HARAM_RESPONSE', 'fr', 'https://goo.gl/maps/P9L6JX9VCNgcuxR3A','HARAM');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (142, 'MERCY_MOUNT', 'fr','Montagne Rahma','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (143, 'MERCY_MOUNT_RESPONSE', 'fr', 'https://goo.gl/maps/TjMBJqj754QzJFg88','MERCY_MOUNT');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (144, 'ARAFAT_MOUNT', 'fr','Montagne Arafat','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (145, 'ARAFAT_MOUNT_RESPONSE', 'fr', 'https://goo.gl/maps/hGcK5D7uY2jcr6gP7','ARAFAT_MOUNT');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (146, 'MASJID_NABAWI', 'fr','La mosquée du Prophète','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (147, 'MASJID_NABAWI_RESPONSE', 'fr', 'https://goo.gl/maps/SseRYc5NoZXXHxsh8','MASJID_NABAWI');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (148, 'FATWA_NUMBERS', 'fr','Numéros de fatwa (opinion religieux)','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (149, 'FATWA_NUMBERS_RESPONSE', 'fr','Numéro saoudien de fatwa 24 heures sur 24 ou numéro gratuit de fatwa Numéro gratuit 24 heures sur 24, de fatwa : 8002451000 Numéro de fatwa unifié, Makkah : 8001222100 Numéro de la Fatwa de Makkah : 8001222400 Numéro de Fatwa de la radio du Saint Coran : 0114052999 Numéro de Dar Al-Iftaa saoudien : 0114595555 Numéro de fatwa du MDHO : 8002451000 Numéro de fatwa WhatsApp (1) : 0501785536','FATWA_NUMBERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (150, 'PRESIDENCY_MOSQUES_NUMBERS', 'fr','Présidence générale de Haramain','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (151, 'PRESIDENCY_MOSQUES_NUMBERS_RESPONSE', 'fr','Numéro gratuit de contact 1966','PRESIDENCY_MOSQUES_NUMBERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (152, 'HAJJ_MINISTRY', 'fr','Ministère du Hajj et de la Omra','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (153, 'HAJJ_MINISTRY_RESPONSE', 'fr','Pour contacter le ministère du Hadj et de la Omra, vous pouvez joindre le centre de service clientèle du MDHO au 920002814 ou à care@haj.gov.sa, ou par le biais du compte officiel Twitter @MOHU_Care.','HAJJ_MINISTRY');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (154, 'EMERGENCY_NUMBER', 'fr','Pour les urgences et les services de sécurité','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (155, 'EMERGENCY_NUMBER_RESPONSE', 'fr', '911','EMERGENCY_NUMBER');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (156, 'MEDICAL_ADVICES', 'fr','Pour les consultations et les demandes de renseignements médicaux','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (157, 'MEDICAL_ADVICES_RESPONSE', 'fr', '937','MEDICAL_ADVICES');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (158, 'EVALUATION', 'fr', 'Évaluation', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (159, 'EVALUATION_RESPONSE', 'fr','Un compte-rendu quotidien et une évaluation à la fin du voyage du pèlerin peuvent être sélectionnés dans la liste des services, veuillez donc prêter attention à la remplir pour nous aider à améliorer les services. ','EVALUATION');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (160, 'COMPLAINTS', 'fr', 'Plaintes', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (161, 'COMPLAINTS_RESPONSE', 'fr','La plainte est déposée auprès de l''organisateur, et après une période sans résolution, la plainte est évoquée comme un rapport. ','COMPLAINTS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (162, 'INCIDENTS', 'fr', 'Rapports', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (163, 'INCIDENTS_RESPONSE', 'fr','Le rapport est envoyé à l''organisateur et soumis aux autorités officielles pour qu''elles prennent les mesures nécessaires. ','INCIDENTS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (164, 'CARD_COLORS', 'fr', 'Couleurs de la carte', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (165, 'CARD_COLORS_RESPONSE', 'fr','Les couleurs de la carte sont basées sur l''établissement organisateur du pèlerinage auquel le pèlerin est affilié, et il existe une couleur distinctive pour les pèlerins nationaux. ','CARD_COLORS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (166, 'WHEELCHAIRS_GATES', 'fr', 'Quelles sont les portes prévues pour les personnes en fauteuil roulant dans le hall d''Al-Haram ?', 'SPECIAL_NEEDS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (167, 'WHEELCHAIRS_GATES_RESPONSE', 'fr','Vous pouvez contacter la Présidence générale de Haramain à 1966 à ce sujet.','WHEELCHAIRS_GATES');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (168, 'HAJJ_CARTS', 'fr', 'Des véhicules sont-ils disponibles à l''intérieur d''Al-Haram ?', 'SPECIAL_NEEDS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (169, 'HAJJ_CARTS_RESPONSE', 'fr','La Présidence générale de Haramain met à disposition des véhicules électriques, qui peuvent être loués via l''application Tanaqol, pour effectuer la circumambulation ou la déambulation à des étages spécifiques d''Al-Haram. Veuillez contacter la Présidence de Haramain pour tout renseignement complémentaire.','HAJJ_CARTS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (170, 'SEPCIAL_NEEDS_SERVICES', 'fr', 'Quels sont les services disponibles pour les personnes ayant des besoins particuliers ?', 'SPECIAL_NEEDS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (171, 'SEPCIAL_NEEDS_SERVICES_RESPONSE', 'fr','Vous pouvez contacter la Présidence générale de Haramain à ce sujet.','SEPCIAL_NEEDS_SERVICES');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (172, 'HAJJ_RITUAL', 'ms','Mengenai Haji');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (173, 'LOCATIONS', 'ms','Lokasi Penting');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (174, 'CONTACTS', 'ms','Nombor Perhubungan Penting');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (175, 'SMART_CARD', 'ms','Kad Pintar');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label) VALUES (176, 'SPECIAL_NEEDS', 'ms','Keperluan Khas');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (177, 'SLAUGHTER_PROCESS', 'ms', 'Bilakah penyembelihan disahkan? Jika orang yang memohon ibadah tersebut merupakan jemaah haji, adakah pelaksanaan ibadahnya itu dikaitkan dengan beliau keluar dari keadaan Ihram dalam Haji atau orang yang melakukan korban itu bukan jemaah haji?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (178, 'SLAUGHTER_PROCESS_RESPONSE','ms', 'Selepas pembelian dan sebaik sahaja jumlah ditolak daripada akaun pelanggan dan resit dalam nama akaun Projek disahkan, atau pelanggan memperoleh kupon atau bon ibadah, pelanggan itu dianggap telah mengamanahkan Projek untuk mengerjakan ibadah tersebut bagi pihaknya. Maka beliau dilepaskan daripada mengerjakan ibadah berkenaan, mengambil maklum bahawa jika pembelian dan pembayaran telah dibuat melalui laman web, mesej akan dihantar bagi memaklumkan pelanggan mengenai pelaksanaan permintaannya (selesai penyembelihan). ','SLAUGHTER_PROCESS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (179, 'ELECTRONIC_PATH', 'ms', 'Adakah E-track menyediakan pembelian Hady dan Adahi [dam] (haiwan korban)?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (180, 'ELECTRONIC_PATH_RESPONSE', 'ms','Ya, perkhidmatan ini disediakan.','ELECTRONIC_PATH');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (181, 'HAJJ_APPLICATION', 'ms','Bilakah permohonan Musim Haji 1443 Hijrah bermula?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (182, 'HAJJ_APPLICATION_RESPONSE', 'ms','Pengumuman mengenai tarikh pembukaan permohonan bagi Haji 1443 telah bermula melalui laman web Kementerian Haji dan Umrah serta saluran rasminya pada hari Jumaat 11/4/1443 Hijrah.', 'HAJJ_APPLICATION');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (183, 'HAJJ_AGE', 'ms','Berapakah umur yang dibenarkan untuk menunaikan Haji?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (184, 'HAJJ_AGE_RESPONSE', 'ms','Tiada halangan untuk kanak-kanak pengiring semasa Haji 1443, dengan syarat mereka sudah divaksin.', 'HAJJ_AGE');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (185, 'INTERNAL_HAJJ', 'ms','Bilakah permohonan kempen Haji dalaman bermula dan berakhir?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (186, 'INTERNAL_HAJJ_RESPONSE', 'ms','Permohonan bermula pada 4hb Zulkaedah dan tamat pada 13 Zulkaedah.', 'INTERNAL_HAJJ');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (187, 'HAJJ_INQUIRY', 'ms','Apakah mekanisme untuk bertanya mengenai kelayakan Haji?', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (188, 'HAJJ_INQUIRY_RESPONSE', 'ms','Untuk bertanya mengenai kelayakan Haji, masuk ke Platform Absher, pilih e-pertanyaan, tanya mengenai kelayakan Haji, kemudian masukkan Nombor ID dan kod visual, dan kelayakan akan dipaparkan.', 'HAJJ_INQUIRY');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (189, 'GULF_NATIONALS', 'ms','Warganegara GCC yang berada di Kerajaan Arab Saudi tetapi bukan pemastautin dan ingin menunaikan Haji domestik', 'HAJJ_RITUAL');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (190, 'GULF_NATIONALS_RESPONSE', 'ms','Warganegara GCC yang merupakan pemastautin di Kerajaan Arab Saudi boleh mendaftar di E-track menggunakan nombor kemasukan yang boleh diperolehi daripada Jabatan Pasport GCC ""*. Warganegara GCC yang bukan pemastautin di Kerajaan Arab Saudi tidak boleh mendaftar untuk jemaah haji domestik, dan beliau mesti mendaftar di negaranya menurut peraturan berkenaan.', 'GULF_NATIONALS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (191, 'MADINAH_CARE_CENTERS', 'ms','Pusat Khidmat Madinah','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (192, 'MADINAH_CARE_CENTERS_RESPONSE', 'ms','Timur Pusat Masjid An-Nabawi Alamat: King Faisal Road, 1st Ring Road, Al-Haram Waktu bekerja: 7 am - 9 pm Lapangan Terbang Antarabangsa Putera Mohammad bin Abdulaziz Address: Lapangan Terbang Antarabangsa Putera Mohammad bin Abdulaziz Waktu bekerja: 8 am - 10 pm','MADINAH_CARE_CENTERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (193, 'JEDDAH_CARE_CENTERS', 'ms','Pusat Khidmat Jeddah','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (194, 'JEDDAH_CARE_CENTERS_RESPONSE', 'ms','Lapangan Terbang Antarabangsa Raja Abdulaziz di Jeddah Alamat: Lapangan Terbang Antarabangsa Raja Abdulaziz Terminal No. 1','JEDDAH_CARE_CENTERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (195, 'MAKKAH_CARE_CENTERS', 'ms','Pusat Khidmat Makkah','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (196, 'MAKKAH_CARE_CENTERS_RESPONSE', 'ms','Cawangan Al-Hujun: Alamat: Madinah Road, Al Hujun, Makkah. Waktu bekerja: 8 am - 10 pm Pautan lokasi pada Google Maps: https://maps.app.goo.gl/NE1G4t6S6WvNkDVb8 Cawangan Misfalah: Alamat: Ibrahim Al Khalil Street, Misfalah, Makkah. Waktu bekerja: 8 am - 10 pm Pautan lokasi pada Google Maps: https://maps.app.goo.gl/NGCnEg6hCp9JTT238   Cawangan Hotel Dar Al-Tawhid / Makkah Pautan lokasi pada Google Maps: https://maps.app.goo.gl/3c9iDmVRL2Zqk3n57','MAKKAH_CARE_CENTERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (197, 'HARAM', 'ms','Al-Haram','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (198, 'HARAM_RESPONSE', 'ms', 'https://goo.gl/maps/P9L6JX9VCNgcuxR3A','HARAM');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (199, 'MERCY_MOUNT', 'ms','Gunung Rahma','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (200, 'MERCY_MOUNT_RESPONSE', 'ms', 'https://goo.gl/maps/TjMBJqj754QzJFg88','MERCY_MOUNT');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (201, 'ARAFAT_MOUNT', 'ms','Gunung Arafah','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (202, 'ARAFAT_MOUNT_RESPONSE', 'ms', 'https://goo.gl/maps/hGcK5D7uY2jcr6gP7','ARAFAT_MOUNT');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (203, 'MASJID_NABAWI', 'ms','Masjid An-Nabawi','LOCATIONS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (204, 'MASJID_NABAWI_RESPONSE', 'ms', 'https://goo.gl/maps/SseRYc5NoZXXHxsh8','MASJID_NABAWI');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (205, 'FATWA_NUMBERS', 'ms','Nombor Bahagian Fatwa','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (206, 'FATWA_NUMBERS_RESPONSE', 'ms','Talian 24 jam Bahagian Fatwa Saudi atau Talian bebas tol Fatwa Talian 24 jam bebas tol Bahagian Fatwa : 8002451000 Nombor Bahagian Fatwa Bersatu, Makkah: 8001222100   Nombor Bahagian Fatwa Makkah: 8001222400 Nombor Bahagian Fatwa Holy Quran Radio: 0114052999 Nombor Dar Al-Iftaa Saudi: 0114595555 Nombor Bahagian Fatwa MOHU: 8002451000 Nombor WhatsApp Bahagian Fatwa (1): 0501785536','FATWA_NUMBERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (207, 'PRESIDENCY_MOSQUES_NUMBERS', 'ms','Presiden Umum Haramain','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (208, 'PRESIDENCY_MOSQUES_NUMBERS_RESPONSE', 'ms','Hubungi nombor bebas tol 1966','PRESIDENCY_MOSQUES_NUMBERS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (209, 'HAJJ_MINISTRY', 'ms','Kementerian Haji dan Umrah','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (210, 'HAJJ_MINISTRY_RESPONSE', 'ms','Untuk menghubungi Kementerian Haji dan Umrah, anda boleh menghubungi Pusat Perkhidmatan Pelanggan MOHU di  920002814 atau care@haj.gov.sa, atau melalui akaun rasmi Twitter @MOHU_Care','HAJJ_MINISTRY');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (211, 'EMERGENCY_NUMBER', 'ms','Untuk kecemasan dan perkhidmatan keselamatan','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (212, 'EMERGENCY_NUMBER_RESPONSE', 'ms', '911','EMERGENCY_NUMBER');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (213, 'MEDICAL_ADVICES', 'ms','Untuk perundingan perubatan dan pertanyaan','CONTACTS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (214, 'MEDICAL_ADVICES_RESPONSE', 'ms', '937','MEDICAL_ADVICES');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (215, 'EVALUATION', 'ms', 'Penilaian', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (216, 'EVALUATION_RESPONSE', 'ms','Ulasan harian dan penilaian di akhir perjalanan jemaah haji boleh dibuat dengan memiih daripada senarai Perkhidmatan. Oleh itu, sila berikan perhatian dengan mengisinya agar kami dapat menambah baik perkhidmatan kami.','EVALUATION');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (217, 'COMPLAINTS', 'ms', 'Aduan', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (218, 'COMPLAINTS_RESPONSE', 'ms','Aduan akan didaftarkan untuk penganjur dan selepas tempoh tamat tanpa sebarang penyelesaian, aduan tersebut akan dibangkitkan sebagai laporan.','COMPLAINTS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (219, 'INCIDENTS', 'ms', 'Laporan', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (220, 'INCIDENTS_RESPONSE', 'ms','Laporan akan didaftarkan untuk penganjur dan dikemukakan sebagai laporan kepada pihak berkuasa rasmi untuk tindakan yang sewajarnya.','INCIDENTS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (221, 'CARD_COLORS', 'ms', 'Warna kad', 'SMART_CARD');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (222, 'CARD_COLORS_RESPONSE', 'ms','Warna kad adalah berdasarkan Tawafa Establishment; badan yang bergabung dalam mengurus Haji, dan terdapat warna tersendiri untuk jemaah haji domestik.','CARD_COLORS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (223, 'WHEELCHAIRS_GATES', 'ms', 'Apakah pintu pagar yang ditetapkan untuk pengguna kerusi roda dalam Al-Haram?', 'SPECIAL_NEEDS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (224, 'WHEELCHAIRS_GATES_RESPONSE', 'ms','Anda boleh menghubungi Presiden Umum Haramain di 1966 dalam hal ini.','WHEELCHAIRS_GATES');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (225, 'HAJJ_CARTS', 'ms', 'Adakah kenderaan tersedia di dalam Al-Haram?', 'SPECIAL_NEEDS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (226, 'HAJJ_CARTS_RESPONSE', 'ms','Presiden Umum Haramain menyediakan kenderaan elektrik yang boleh disewa melalui Aplikasi Tanaqol untuk mengerjakan Tawaf atau Saie di tingkat khusus di dalam Al-Haram. Sila hubungi Presiden Umum Haramain untuk sebarang perkembangan.','HAJJ_CARTS');

INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (227, 'SEPCIAL_NEEDS_SERVICES', 'ms', 'Apakah perkhidmatan yang tersedia untuk orang yang mempunyai keperluan khas?', 'SPECIAL_NEEDS');
INSERT INTO shc_portal.shc_chatbot_item (id, code, lang, label, parent_code) VALUES (228, 'SEPCIAL_NEEDS_SERVICES_RESPONSE', 'ms','Anda boleh menghubungi Presiden Umum Haramain dalam hal ini.','SEPCIAL_NEEDS_SERVICES');

SET IDENTITY_INSERT shc_portal.shc_chatbot_item OFF;

GO

SET IDENTITY_INSERT shc_portal.shc_language_lk ON;
INSERT INTO shc_portal.shc_language_lk(id, code, lang , label) values(37, 'MS', 'ms', 'Malay')
SET IDENTITY_INSERT shc_portal.shc_language_lk OFF;
GO


DELETE FROM shc_portal.shc_survey_question_lk WHERE id > 0;
GO
SET IDENTITY_INSERT shc_portal.shc_survey_question_lk ON;
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (1, 'WELCOME_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن الترحيب والاستقبال', 1);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (2, 'WELCOME_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with the welcoming and greeting?', 1);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (3, 'TRANS_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن الحافلات', 2);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (4, 'TRANS_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with the buses?', 2);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (5, 'TRANS_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن الالتزام بمواعيد الحافلات', 3);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (6, 'TRANS_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with the bus timings?', 3);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (7, 'FOOD_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن جودة الوجبات المقدمة', 4);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (8, 'FOOD_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with the quality of the meals served?', 4);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (9, 'FOOD_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن الالتزام بمواعيد تقديم الوجبات', 5);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (10, 'FOOD_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'en', N'How satisfied are you with serving meals on time?', 5);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (11, 'HOUSING_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن السكن', 6);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (12, 'HOUSING_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with housing?', 6);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (13, 'UTIL_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن المرافق العامة', 7);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (14, 'UTIL_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with public utilities?', 7);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (15, 'GUIDE_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن خدمات الارشاد والتوجيه', 8);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (16, 'GUIDE_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with the counseling and guidance services?', 8);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (17, 'WORKERS_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'رضاك عن تعامل العاملين في تقديم الخدمات', 9);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (18, 'WORKERS_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with the quality of workers services?', 9);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (19, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'DAILY', 'ar', N'مدى رضاك عن مستوى النظافة', 10);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (20, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'DAILY', 'en', 'How satisfied are you with the level of cleanliness?', 10);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (21, 'WELCOME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ar', N'مدى رضاك عن الترحيب والاستقبال', 1);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (22, 'WELCOME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'en', 'How satisfied are you with the welcoming and greeting?', 1);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (23, 'TRANS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ar', N'مدى رضاك عن الحافلات', 2);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (24, 'TRANS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'en', 'How satisfied are you with the buses?', 2);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (25, 'TRANS_TIME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ar', N'مدى رضاك عن الالتزام بمواعيد الحافلات', 3);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (26, 'TRANS_TIME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'en', 'How satisfied are you with the bus timings?', 3);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (27, 'FOOD_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ar', N'مدى رضاك عن جودة الوجبات المقدمة', 4);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (28, 'FOOD_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'en', 'How satisfied are you with the quality of the meals served?', 4);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (29, 'FOOD_TIME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ar', N'مدى رضاك عن الالتزام بمواعيد تقديم الوجبات', 5);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (30, 'FOOD_TIME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'en', N'How satisfied are you with serving meals on time?', 5);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (31, 'HOUSING_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ar', N'مدى رضاك عن السكن', 6);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (32, 'HOUSING_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'en', 'How satisfied are you with housing?', 6);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (33, 'UTIL_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ar', N'مدى رضاك عن المرافق العامة', 7);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (34, 'UTIL_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'en', 'How satisfied are you with public utilities?', 7);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (35, 'GUIDE_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ar', N'مدى رضاك عن خدمات الارشاد والتوجيه', 8);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (36, 'GUIDE_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'en', 'How satisfied are you with the counseling and guidance services?', 8);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (37, 'WORKERS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ar', N'رضاك عن تعامل العاملين في تقديم الخدمات', 9);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (38, 'WORKERS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'en', 'How satisfied are you with the quality of workers services?', 9);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (39, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ar', N'مدى رضاك عن مستوى النظافة', 10);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (40, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'en', 'How satisfied are you with the level of cleanliness?', 10);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (41, 'WELCOME_SATISFACTION_Q_DAILY', 'DAILY', 'ms', 'Sejauh manakah anda berpuas hati dengan sambutan dan sapaan?', 1);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (42, 'TRANS_SATISFACTION_Q_DAILY', 'DAILY', 'ms', 'Sejauh manakah anda berpuas hati dengan bas?', 2);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (43, 'TRANS_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'ms', 'Sejauh manakah anda berpuas hati dengan masa bas?', 3);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (44, 'FOOD_SATISFACTION_Q_DAILY', 'DAILY', 'ms', 'Sejauh manakah anda berpuas hati dengan kualiti makanan yang dihidangkan?', 4);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (45, 'FOOD_TIME_SATISFACTION_Q_DAILY', 'DAILY', 'ms', N'Sejauh manakah anda berpuas hati dengan kualiti makanan yang dihidangkan?', 5);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (46, 'HOUSING_SATISFACTION_Q_DAILY', 'DAILY', 'ms', 'Sejauh manakah anda berpuas hati dengan kediaman?', 6);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (47, 'UTIL_SATISFACTION_Q_DAILY', 'DAILY', 'ms', 'Sejauh manakah anda berpuas hati dengan kemudahan awam?', 7);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (48, 'GUIDE_SATISFACTION_Q_DAILY', 'DAILY', 'ms', 'Sejauh manakah anda berpuas hati dengan perkhidmatan kaunseling dan bimbingan?', 8);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (49, 'WORKERS_SATISFACTION_Q_DAILY', 'DAILY', 'ms', 'Kepuasan anda terhadap layanan pekerja dalam menyediakan perkhidmatan?', 9);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (50, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'DAILY', 'ms', 'Sejauh manakah anda berpuas hati dengan tahap kebersihan?', 10);

INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (51, 'WELCOME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ms', 'Sejauh manakah anda berpuas hati dengan sambutan dan sapaan?', 1);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (52, 'TRANS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ms', 'Sejauh manakah anda berpuas hati dengan bas?', 2);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (53, 'TRANS_TIME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ms', 'Sejauh manakah anda berpuas hati dengan masa bas?', 3);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (54, 'FOOD_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ms', 'Sejauh manakah anda berpuas hati dengan kualiti makanan yang dihidangkan?', 4);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (55, 'FOOD_TIME_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ms', N'Sejauh manakah anda berpuas hati dengan kualiti makanan yang dihidangkan?', 5);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (56, 'HOUSING_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ms', 'Sejauh manakah anda berpuas hati dengan kediaman?', 6);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (57, 'UTIL_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ms', 'Sejauh manakah anda berpuas hati dengan kemudahan awam?', 7);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (58, 'GUIDE_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ms', 'Sejauh manakah anda berpuas hati dengan perkhidmatan kaunseling dan bimbingan?', 8);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (59, 'WORKERS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ms', 'Kepuasan anda terhadap layanan pekerja dalam menyediakan perkhidmatan?', 9);
INSERT INTO shc_portal.shc_survey_question_lk (id, code, survey_type_code, lang, label, question_index) VALUES (60, 'CLEANLINESS_SATISFACTION_Q_DAILY', 'END_OF_RITUAL', 'ms', 'Sejauh manakah anda berpuas hati dengan tahap kebersihan?', 10);
SET IDENTITY_INSERT shc_portal.shc_survey_question_lk OFF;
GO
