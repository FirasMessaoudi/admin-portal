<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Reset User Password إعادة تعيين كلمة السر</title>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>

    <link href='http://fonts.googleapis.com/css?family=Roboto' rel='stylesheet' type='text/css'>

    <!-- use the font -->
    <style>
        body {
            font-family: 'Roboto', sans-serif;
            font-size: 48px;
        }
    </style>
</head>
<body style="margin: 0; padding: 0;">

<table align="center" border="0" cellpadding="0" cellspacing="0" width="600" style="border-collapse: collapse;">
    <tr>
        <td align="center" bgcolor="#78ab46" style="padding: 40px 0 30px 0;">
            <a href="http://10.33.20.210:8080/shj-admin/">نظام هوية ضيف الرحمن<br/>Smart ID Platform</a>
        </td>
    </tr>
    <tr>
        <td bgcolor="#eaeaea" style="padding: 40px 30px 40px 30px;">
            <p dir="rtl"> عزيزي ${user.firstName}،</p>
            <p dir="rtl"> تلقينا طلبا لإعادة تعيين كلمة المرور الخاصة بك يمكنك الدخول على المنصة باستخدام اسم
                المستخدم ${user.nin} وكلمة المرور المؤقتة المرسلة على جوالك. </p>
            </br>
            <p>Dear ${user.firstName},</p>
            <p>You requested a reset password in Smart ID platform and you can login to the system using the following
                username ${user.nin} and the temporary password sent to your mobile.</p>
        </td>
    </tr>
    <tr>
        <td bgcolor="#777777" style="padding: 30px 30px 30px 30px;">
            <p>نظام هوية ضيف الرحمن<br/>Smart ID Platform</p>
        </td>
    </tr>
</table>

</body>
</html>
