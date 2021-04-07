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
    @FindBy(xpath = "//input[@name='data[fullName]']")
    WebElement txtUserFullName;

    @FindBy(xpath = "//select[@name='data[idType]']//parent::div")
    WebElement divUserIdType;

    @FindBy(xpath = "//select[@name='data[idType]']//parent::div//following-sibling::div[contains(@class,'list--dropdown')]")
    WebElement lstUserIdType;

    @FindBy(xpath = "//select[@name='data[idType]']//parent::div//following-sibling::div[contains(@class,'list--dropdown')]//input")
    WebElement txtUserIdType;

    @FindBy(xpath = "//input[@name='data[idNumber]']")
    WebElement txtUserIdNumber;

    @FindBy(xpath = "//select[@name='data[gender]']//parent::div")
    WebElement divUserGender;

    @FindBy(xpath = "//select[@name='data[gender]']//parent::div//following-sibling::div[contains(@class,'list--dropdown')]")
     WebElement lstUserGender;

    @FindBy(xpath = "//select[@name='data[gender]']//parent::div//following-sibling::div[contains(@class,'list--dropdown')]//input")
    WebElement txtUserGender;

    @FindBy(xpath = "//input[@name='data[email]']")
    WebElement txtUserEmail;

    @FindBy(xpath = "//input[@name='data[birthDate]']//following-sibling::input")
    WebElement txtUserDOB;

    @FindBy(xpath = "//input[@name='data[jobTitle]']")
    WebElement txtUserJobTitle;

    @FindBy(xpath = "//input[@name='data[mobileNumber]']")
    WebElement txtUserMobileNumber;

    @FindBy(xpath = "//input[@name='data[inspectorCapacity]']")
    WebElement txtDailyInspectionCapacity;

    @FindBy(xpath = "//select[@name='data[status]']//parent::div")
    WebElement divUserStatus;

    @FindBy(xpath = "//select[@name='data[status]']//parent::div//following-sibling::div[contains(@class,'list--dropdown')]")
    WebElement lstUserStatus;

    @FindBy(xpath = "//select[@name='data[status]']//parent::div//following-sibling::div[contains(@class,'list--dropdown')]//input")
    WebElement txtUserStatus;

    @FindBy(xpath = "//select[@name='data[roleLevel]']//parent::div")
    WebElement divUserRoleLevel;

    @FindBy(xpath = "//select[@name='data[roleLevel]']//parent::div//following-sibling::div[contains(@class,'list--dropdown')]")
    WebElement lstUserRoleLevel;

    @FindBy(xpath = "//select[@name='data[roleLevel]']//parent::div//following-sibling::div[contains(@class,'list--dropdown')]//input")
    WebElement txtUserRoleLevel;

    @FindBy(xpath = "//select[@name='data[inspectionCenterId]']//parent::div")
    WebElement divInspectionCenter;

    @FindBy(xpath = "//select[@name='data[inspectionCenterId]']//parent::div//following-sibling::div[contains(@class,'list--dropdown')]")
    WebElement lstInspectionCenter;

    @FindBy(xpath = "//select[@name='data[inspectionCenterId]']//parent::div//following-sibling::div[contains(@class,'list--dropdown')]//input")
    WebElement txtInspectionCenter;

    @FindBy(xpath = "//select[@name='data[inspectionCenterIds]']//parent::div")
    WebElement divInspectionCenters;

    @FindBy(xpath = "//select[@name='data[inspectionCenterIds]']//parent::div//following-sibling::div[contains(@class,'list--dropdown')]")
    WebElement lstInspectionCenters;

    @FindBy(xpath = "//select[@name='data[inspectionCenterIds]']//following-sibling::input")
    WebElement txtInspectionCenters;

    @FindBy(xpath = "//select[@name='data[userSpecialization]']//parent::div")
    WebElement divUserSpecialization;

    @FindBy(xpath = "//select[@name='data[userSpecialization]']//parent::div//following-sibling::div[contains(@class,'list--dropdown')]")
    WebElement lstUserSpecialization;

    @FindBy(xpath = "//select[@name='data[userSpecialization]']//following-sibling::input")
    WebElement txtUserSpecialization;

    @FindBy(xpath = "//select[@name='data[userRoles]']//parent::div")
    WebElement divUserRol;

    @FindBy(xpath = "//select[@name='data[userRoles]']//parent::div//following-sibling::div[contains(@class,'list--dropdown')]")
    WebElement lstUserRol;

    @FindBy(xpath = "//select[@name='data[userRoles]']//following-sibling::input")
    WebElement txtUserRol;

    @FindBy(xpath = "//button[@name='data[submit]']")
    WebElement btnSaveItem;

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

        if(Exists(txtUserFullName,30)){

            // preparing test data randomization

            UtilitiesUI.prepareTestData(dataRow);

            //filling  Data

            UtilitiesUI.selectWithInputText(divUserIdType,txtUserIdType,dataRow.get("UserIdType".toUpperCase()));

            SetValue(txtUserIdNumber,dataRow.get("UserIdNumber".toUpperCase()));

            SetValue(txtUserFullName,dataRow.get("UserFullName".toUpperCase()));

            UtilitiesUI.selectWithInputText(divUserGender,txtUserGender,dataRow.get("UserGender".toUpperCase()));

            SetValue(txtUserEmail,dataRow.get("UserEmail".toUpperCase()));

            SetValue(txtUserDOB,dataRow.get("UserDOB".toUpperCase()));

            UtilitiesUI.selectWithInputText(divUserRoleLevel,txtUserRoleLevel,dataRow.get("UserRoleLevel".toUpperCase()));

            UtilitiesUI.selectWithInputText(divUserStatus,txtUserStatus,dataRow.get("UserStatus".toUpperCase()));

            UtilitiesUI.selectWithInputText(divUserRol,txtUserRol,dataRow.get("UserRole".toUpperCase()));

            if(dataRow.get("UserRoleLevel".toUpperCase()).contains("مركز التفتيش")){
                UtilitiesUI.selectWithInputText(divInspectionCenter,txtInspectionCenter,dataRow.get("InspectionCenter".toUpperCase()));

            } else if(dataRow.get("UserRoleLevel".toUpperCase()).contains("إدارة مراكز التفتيش")){
                UtilitiesUI.selectWithInputText(divInspectionCenters,txtInspectionCenters,dataRow.get("InspectionCenter".toUpperCase()));

            }

            SetValue(txtUserJobTitle,dataRow.get("UserJobTitle".toUpperCase()));

            SetValue(txtUserMobileNumber,dataRow.get("UserMobileNumber".toUpperCase()));

            SetValue(txtDailyInspectionCapacity,dataRow.get("DailyInspectionCapacity".toUpperCase()));

            if(dataRow.get("UserRole".toUpperCase()).contains("مراقب")){

                UtilitiesUI.selectWithInputText(divUserSpecialization,txtUserSpecialization,dataRow.get("UserSpecialization".toUpperCase()));

            }

            btnSaveItem.click();
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
