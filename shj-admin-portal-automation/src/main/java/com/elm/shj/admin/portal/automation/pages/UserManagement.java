package com.elm.shj.admin.portal.automation.pages;

import com.elm.qa.framework.core.ActionX;
import com.elm.qa.framework.core.Global;
import com.elm.qa.framework.utilities.ReporterX;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Hashtable;

import static com.elm.qa.framework.core.ActionX.Exists;
import static com.elm.qa.framework.core.ActionX.SetValue;

public class UserManagement {
    // declaring elements
    @FindBy(xpath = "//app-user-add-update//input[@formcontrolname='firstName']")
    WebElement txtUserFirstName;

    @FindBy(xpath = "//app-user-add-update//input[@formcontrolname='fatherName']")
    WebElement txtUserFatherName;

    @FindBy(xpath = "//app-user-add-update//input[@formcontrolname='grandFatherName']")
    WebElement txtUserGrandFatherName;

    @FindBy(xpath = "//app-user-add-update//input[@formcontrolname='familyName']")
    WebElement txtUserFamilyName;

    @FindBy(xpath = "//app-user-add-update//input[@formcontrolname='nin']")
    WebElement txtUserIdNumber;

    @FindBy(xpath = "//app-user-add-update//input[@ng-reflect-name='dateOfBirthGregorian']")
    WebElement txtUserDOB;

    @FindBy(xpath = "//app-user-add-update//button[contains(text(),'Gregorian')]")
    WebElement btnClndrTypeGregorian;

    @FindBy(xpath = "//app-user-add-update//button[contains(text(),'Hijri')]")
    WebElement btnClndrTypeHijri;

    @FindBy(xpath = "//app-user-add-update//input[@formcontrolname='gender' and @value='M']")
    WebElement btnUserGenderMale;

    @FindBy(xpath = "//app-user-add-update//input[@formcontrolname='gender' and @value='F']")
    WebElement btnUserGenderFemale;

    @FindBy(xpath = "//app-user-add-update//input[@formcontrolname='email']")
    WebElement txtUserEmail;

    @FindBy(xpath = "//app-user-add-update//input[@formcontrolname='mobileNumber']")
    WebElement txtUserMobileNumber;

    @FindBy(xpath = "//app-user-add-update//div[@formcontrolname='activated']//label[contains(text(),'Active')]")
    WebElement btnUserStatusActive;

    @FindBy(xpath = "//app-user-add-update//div[@formcontrolname='activated']//label[contains(text(),'Not Activated')]")
    WebElement btnUserStatusInActive;

    @FindBy(xpath = "//app-user-add-update//select[@formcontrolname='role']")
    WebElement lstUserMainRole;

    @FindBy(xpath = "//app-user-add-update//ng-multiselect-dropdown[@formcontrolname='additionalRoles']")
    WebElement lstUserAdditionalRole;

    @FindBy(xpath = "(//app-user-add-update//ng-multiselect-dropdown[@formcontrolname='additionalRoles']//ul)[2]")
    WebElement ulUserAdditionalRole;

    @FindBy(xpath = "//app-user-add-update//div[contains(@class,'footer__action')]//button[contains(text(),'Save')]")
    WebElement btnSaveUser;

    @FindBy(xpath = "//app-user-add-update//div[contains(@class,'footer__action')]//button[contains(text(),'Cancel')]")
    WebElement btnCancelSaveUSer;




    @FindBy(xpath = "//div[@id='swal2-content']")
    WebElement divSaveMsgContent;

    @FindBy(xpath = "//h2[@id='swal2-title']")
    WebElement divSaveMsgTitle;

    @FindBy(xpath = "//div[@class='swal2-actions']//button[contains(text(),'موافقة')]")
    WebElement btnSaveMSGConfirmYes;

    @FindBy(xpath = "//div[@id='spinner']")
    WebElement divLoading;

    @FindBy(xpath = "//isp-user-list//button[contains(text(),'إضافة مستخدم جديد')]")
    WebElement btnAddNewUser;


    public UserManagement() {
        try {
            PageFactory.initElements(Global.Test.Browser, this);
        } catch (Exception ex) {
            ReporterX.error(ex);
        }
    }


    public void addNewUSer(Hashtable<String,String> dataRow) throws Exception{

        if (ActionX.Exists(btnAddNewUser, 3)) {
            btnAddNewUser.click();
            UtilitiesUI.waitForLoaderHidden(5);
        }

        if(Exists(txtUserFirstName,30)){

            // preparing test data randomization

            UtilitiesUI.prepareTestData(dataRow);

            //filling  Data


            SetValue(txtUserIdNumber,dataRow.get("UserIdNumber".toUpperCase()));

            SetValue(txtUserFirstName,dataRow.get("UserFullName".toUpperCase()));


            SetValue(txtUserEmail,dataRow.get("UserEmail".toUpperCase()));

            SetValue(txtUserDOB,dataRow.get("UserDOB".toUpperCase()));




            btnSaveUser.click();
            UtilitiesUI.waitForLoaderHidden(20);

            //validate add item
            if(Exists(divSaveMsgTitle,30)){
                if(divSaveMsgTitle.getText().contains("نجاح")){
                    ReporterX.info("System Message : "+divSaveMsgContent.getText());
                    btnSaveMSGConfirmYes.click();
                    UtilitiesUI.waitForLoaderHidden(10);
                    ReporterX.pass("User [["+dataRow.get("UserFullName".toUpperCase())+"] was added.!");
                }else {
                    ReporterX.info("System Message : "+divSaveMsgContent.getText());
                    btnSaveMSGConfirmYes.click();
                    UtilitiesUI.waitForLoaderHidden(10);
                    ReporterX.fail("Failed to Add User ["+dataRow.get("UserFullName".toUpperCase())+"].!");
                }
            }else {
                ReporterX.fail("Failed to Add User ["+dataRow.get("UserFullName".toUpperCase())+"].!");
            }


        }else {
            ReporterX.fail("Add New User Page not Loaded.!!");
        }

    }




}
