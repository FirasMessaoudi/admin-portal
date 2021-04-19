package com.elm.shj.admin.portal.automation.pages;

import com.elm.qa.framework.core.ActionX;
import com.elm.qa.framework.core.Global;
import com.elm.qa.framework.utilities.ReporterX;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Hashtable;

import static com.elm.qa.framework.core.ActionX.*;

public class UserManagement {
    // declaring elements
    @FindBy(xpath = "//app-user-list//button[@routerlink='/users/create']")
    WebElement btnAddNewUser;

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

    @FindBy(xpath = "//app-user-add-update//div[@ng-reflect-selected='true']")
    WebElement btnUserDobDay;

    @FindBy(xpath = "//app-user-add-update//button[contains(text(),'Gregorian') or contains(text(),'ميلاد')]")
    WebElement btnClndrTypeGregorian;

    @FindBy(xpath = "//app-user-add-update//button[contains(text(),'Hijri') or contains(text(),'هجر')]")
    WebElement btnClndrTypeHijri;

    @FindBy(xpath = "//app-user-add-update//input[@formcontrolname='gender' and @value='M']//parent::label")
    WebElement btnUserGenderMale;

    @FindBy(xpath = "//app-user-add-update//input[@formcontrolname='gender' and @value='F']//parent::label")
    WebElement btnUserGenderFemale;

    @FindBy(xpath = "//app-user-add-update//input[@formcontrolname='email']")
    WebElement txtUserEmail;

    @FindBy(xpath = "//app-user-add-update//input[@formcontrolname='mobileNumber']")
    WebElement txtUserMobileNumber;

    @FindBy(xpath = "//app-user-add-update//input[@name='radioActivated' and @ng-reflect-value='true']//parent::label")
    WebElement btnUserStatusActive;

    @FindBy(xpath = "//app-user-add-update//input[@name='radioActivated' and @ng-reflect-value='false']//parent::label")
    WebElement btnUserStatusInActive;

    @FindBy(xpath = "//app-user-add-update//select[@formcontrolname='role']")
    WebElement lstUserMainRole;

    @FindBy(xpath = "//app-user-add-update//ng-multiselect-dropdown[@formcontrolname='additionalRoles']")
    WebElement lstUserAdditionalRole;

    @FindBy(xpath = "(//app-user-add-update//ng-multiselect-dropdown[@formcontrolname='additionalRoles']//ul)[2]")
    WebElement ulUserAdditionalRole;

    @FindBy(xpath = "//app-user-add-update//div[contains(@class,'footer__action')]//button[contains(@class,'btn-outline-dcc-primary')]")
    WebElement btnSaveUser;

    @FindBy(xpath = "//app-user-add-update//div[contains(@class,'footer__action')]//button[contains(@class,'btn-outline-secondary')]")
    WebElement btnCancelSaveUSer;




    @FindBy(xpath = "//div[@id='swal2-content']")
    WebElement divSaveMsgContent;

    @FindBy(xpath = "//h2[@id='swal2-title']")
    WebElement divSaveMsgTitle;

    @FindBy(xpath = "//div[@class='swal2-actions']//button[contains(text(),'موافقة')]")
    WebElement btnSaveMSGConfirmYes;

    @FindBy(xpath = "//div[@id='spinner']")
    WebElement divLoading;



    public UserManagement() {
        try {
            PageFactory.initElements(Global.Test.Browser, this);
        } catch (Exception ex) {
            ReporterX.error(ex);
        }
    }

    private void validateSuccess(Hashtable<String, String> dataRow) {
        Home home = new Home();
        if(Exists(home.divSaveMsgContent,30)){
            String msg = home.divSaveMsgContent.getText().toLowerCase();
            if(msg.contains("success") || msg.contains("نجاح") ){
                ReporterX.info("User Manage, System Message : "+msg);
                ReporterX.pass("User ["+dataRow.get("UserIdNumber".toUpperCase())+"] Success.!");
            }else {
                ReporterX.info("User Manage,System Message : "+msg);
                ReporterX.fail("User ["+dataRow.get("UserIdNumber".toUpperCase())+"] Failed.!");
            }
        }else {
            ReporterX.fail("User ["+dataRow.get("UserIdNumber".toUpperCase())+"] Failed.!");
        }
    }


    public void addNewUser(Hashtable<String,String> dataRow) throws Exception{

        if (ActionX.Exists(btnAddNewUser, 3)) {
            btnAddNewUser.click();
        }

        if(Exists(txtUserFirstName,30)){

            // preparing test data randomization

//            UtilitiesUI.prepareTestData(dataRow);

            //filling  Data

            SetValue(txtUserFirstName,dataRow.get("FirstName".toUpperCase()));
            SetValue(txtUserFatherName,dataRow.get("FatherName".toUpperCase()));
            SetValue(txtUserGrandFatherName,dataRow.get("GrandFatherName".toUpperCase()));
            SetValue(txtUserFamilyName,dataRow.get("FamilyName".toUpperCase()));
            SetValue(txtUserIdNumber,dataRow.get("UserIdNumber".toUpperCase()));
            RemoveAttribute(txtUserDOB,"readonly");
            SetValue(txtUserDOB,dataRow.get("UserDOB".toUpperCase()));
            txtUserDOB.click();
            Thread.sleep(1000);
            btnUserDobDay.click();
            if (dataRow.get("UserGender".toUpperCase()).equalsIgnoreCase("male")) {
                btnUserGenderMale.click();
            } else {
                btnUserGenderFemale.click();
            }
            if (dataRow.get("UserStatus".toUpperCase()).equalsIgnoreCase("active")) {
                btnUserStatusActive.click();
            } else {
                btnUserStatusInActive.click();
            }
            SetValue(txtUserMobileNumber,dataRow.get("MobileNumber".toUpperCase()));
            Select(lstUserMainRole,dataRow.get("MainRole".toUpperCase()));
            try{
                lstUserAdditionalRole.click();
                Thread.sleep(1000);
                String xpath = "(//app-user-add-update//ng-multiselect-dropdown[@formcontrolname='additionalRoles']//ul)[2]//div[contains(text(),'"+dataRow.get("AdditionalRole".toUpperCase())+"')]";
                Global.Test.Browser.findElements(By.xpath(xpath)).get(0).click();
            }catch (Exception e){

            }
            SetValue(txtUserEmail,dataRow.get("UserEmail".toUpperCase()));

            ActionX.ScrollToElement(btnSaveUser);
            btnSaveUser.click();

            //validate add item
            validateSuccess(dataRow);


        }else {
            ReporterX.fail("Add New User Page not Loaded.!!");
        }

    }




}
