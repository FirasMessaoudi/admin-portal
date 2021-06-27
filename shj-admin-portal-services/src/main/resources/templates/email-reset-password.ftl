<!DOCTYPE html>
<html lang="ar">

<head>
    <title>Reset User Password إعادة تعيين كلمة السر</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <style type="text/css">
        /* CLIENT-SPECIFIC STYLES */

        body,
        table,
        td,
        a {
            -webkit-text-size-adjust: 100%;
            -ms-text-size-adjust: 100%;
        }

        table,
        td {
            mso-table-lspace: 0pt;
            mso-table-rspace: 0pt;
        }

        img {
            -ms-interpolation-mode: bicubic;
        }

        /* RESET STYLES */

        img {
            border: 0;
            height: auto;
            line-height: 100%;
            outline: none;
            text-decoration: none;
        }

        table {
            border-collapse: collapse !important;
        }

        body {
            height: 100% !important;
            /* height: 100% 100vh !important*/
            margin: 0 !important;
            padding: 0 !important;
            width: 100% !important;
            background-repeat: no-repeat;
            background-image: url("./../images/dots-pattern.png");
            background-size: 200px 200px;
            background-position-y: bottom;
            background-position-x: right;
            background-color: #fff;
        }

        /* iOS BLUE LINKS */

        a[x-apple-data-detectors] {
            color: inherit !important;
            text-decoration: none !important;
            font-size: inherit !important;
            font-family: inherit !important;
            font-weight: inherit !important;
            line-height: inherit !important;
        }

        /* GMAIL BLUE LINKS */

        u + #body a {
            color: inherit;
            text-decoration: none;
            font-size: inherit;
            font-family: inherit;
            font-weight: inherit;
            line-height: inherit;
        }

        /* SAMSUNG MAIL BLUE LINKS */

        #MessageViewBody a {
            color: inherit;
            text-decoration: none;
            font-size: inherit;
            font-family: inherit;
            font-weight: inherit;
            line-height: inherit;
        }

        /* Universal styles for links and stuff */

        .email-header {
            background-repeat: no-repeat;
            background-image: url("./../images/header.png");
            background-size: contain;
            background-position: top;
        }

        a {
            color: #3b7937;
            font-weight: bold;
        }

        a.button:hover {
            background-color: #20411e !important;
        }

        /* Responsive styles */
        /* @media screen and (max-width: 600px) {
				.mobile { width: 100% !important; }
				.block { display: block !important; width: 100% !important; }
				h1 { font-size: 24px !important; }
			} */
    </style>
</head>

<body id="body" style="margin: 0 !important; padding: 0 !important">
<!-- Preview text for the inbox -->
<!-- <div style="display: none; max-height: 0; overflow: hidden">
    This will be displayed underneath the subject line. Use it wisely.
</div> -->

<table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%">
    <tr class="email-header">
        <td align="center">
            <table border="0" class="mobile" cellpadding="0" cellspacing="0" role="presentation" width="600" style="
              color: #000000;
              font-family: sans-serif;
              font-size: 18px;
              line-height: 36px;
              margin: 120px;
              padding: 0;
            ">
                <!-- Logo image -->
                <tr>
                    <td align="center" style="padding: 20px 20px 40px 20px">
                        <!--  base64 images will not work with outlook
                            Suggested solution :
                            - CID attachments, or embedding an image using MIME object

                            - hosted images in external server and attached to the HTML (as below)
                            -->
                        <img src="../images/logo.png" alt="" width="120" height="120" border="0"
                             style="display: block; margin: 0 0 0 auto"/>
                    </td>
                </tr>

                <!-- Two Columns -->
                <tr>
                    <td style="padding-bottom: 40px">
                        <table border="0" cellpadding="0" cellspacing="0" role="presentation" width="100%">
                            <tr>
                                <td>
                                    <!-- Left column -->
                                    <table align="left" border="0" cellpadding="0" cellspacing="0" class="block"
                                           role="presentation" width="48%">
                                        <tr>
                                            <td style="padding: 20px; line-height: 24px">
                                                <h2 style="
                                font-size: 16px;
                                margin: 0 0 20px 0;
                                color: #707070;
                              ">
                                                    Dear ${user.firstName},
                                                </h2>
                                                <p style="font-size: 14px; margin: 0; color: #707070">
                                                    You requested a reset password in Shaaer platform. You can login to
                                                    the system using the following
                                                    username <#setting number_format="0" />${user.nin}<#setting number_format="" />
                                                    and the temporary password sent to your mobile.
                                                </p>
                                            </td>
                                        </tr>
                                    </table>
                                    <!-- Right column -->
                                    <table align="right" border="0" cellpadding="0" cellspacing="0" class="block"
                                           role="presentation" width="48%">
                                        <tr>
                                            <td style="
                              padding: 20px;
                              line-height: 24px;
                              text-align: right;
                            ">
                                                <h2 style="
                                font-size: 16px;
                                margin: 0 0 20px 0;
                                color: #707070;
                              ">،عزيزي ${user.firstName}</h2>
                                                <p style="font-size: 14px; margin: 0; color: #707070"> يمكنك الدخول على المنصة باستخدام اسم المستخدم <#setting number_format="0" />${user.nin}<#setting number_format="" /> وكلمة المرور المؤقتة المرسلة على جوالك.</p>
                                            </td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </td>
    </tr>
</table>
</body>

</html>