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

public class RoleManagement {
    // declaring elements
    @FindBy(xpath = "//app-add-update-role//input[@formcontrolname='nameArabic']")
    WebElement txtRoleArbName;

    @FindBy(xpath = "//app-add-update-role//input[@formcontrolname='nameEnglish']")
    WebElement txtRoleEngName;

    @FindBy(xpath = "//app-add-update-role//div[@formcontrolname='activated']//label[contains(text(),'Not Activated')]")
    WebElement btnRoleNotActive;

    @FindBy(xpath = "//app-add-update-role//div[@formcontrolname='activated']//label[contains(text(),'Active')]")
    WebElement btnRoleActive;

    @FindBy(xpath = "//app-add-update-role//form")
    WebElement frmRoleAuthorities;

    @FindBy(xpath = "//app-add-update-role//form//label[contains(text(),'Edit Role')]")
    WebElement lblEditRole;

    @FindBy(xpath = "//app-add-update-role//button[contains(text(),'Save')]")
    WebElement btnSave;

    @FindBy(xpath = "//app-toasts//div[@class='toast-body']")
    WebElement divSaveMsgContent;

    @FindBy(xpath = "//app-toasts//div[contains(@class,'toast-header')]")
    WebElement divSaveMsgTitle;

    @FindBy(xpath = "//app-confirm-dialog//button[contains(@class,'btn-danger')]")
    WebElement btnActionMsgConfirmNo;

    @FindBy(xpath = "//app-confirm-dialog//button[contains(@class,'btn-primary')]")
    WebElement btnActionMsgConfirmYes;

    @FindBy(xpath = "//app-applicant-list//button[@routerlink='/roles/create']")
    WebElement btnAddNewRole;


    public RoleManagement() {
        try {
            PageFactory.initElements(Global.Test.Browser, this);
        } catch (Exception ex) {
            ReporterX.error(ex);
        }
    }


    public void addNewRole(Hashtable<String,String> dataRow) throws Exception{

        if (ActionX.Exists(btnAddNewRole, 3)) {
            btnAddNewRole.click();
        }

        if(Exists(txtRoleArbName,30)){

            // preparing test data randomization

            UtilitiesUI.prepareTestData(dataRow);

            //filling  Data

            SetValue(txtRoleArbName,dataRow.get("RoleArbName".toUpperCase()));

            SetValue(txtRoleEngName,dataRow.get("RoleEngName".toUpperCase()));

           if(dataRow.get("UserRoleActive".toUpperCase()).equalsIgnoreCase("yes")){
                btnRoleActive.click();
            } else {
               btnRoleNotActive.click();
            }

            // Selecting list of Authorities








//            btnSave.click();
//            UtilitiesUI.waitForLoaderHidden(20);
//
//            //validate add item
//            if(Exists(divSaveMsgTitle,30)){
//                if(divSaveMsgTitle.getText().contains("نجاح")){
//                    ReporterX.info("System Message : "+divSaveMsgContent.getText());
//                    btnSaveMSGConfirmYes.click();
//                    UtilitiesUI.waitForLoaderHidden(10);
//                    ReporterX.pass("User [["+dataRow.get("UserFullName".toUpperCase())+"] was added.!");
//                }else {
//                    ReporterX.info("System Message : "+divSaveMsgContent.getText());
//                    btnSaveMSGConfirmYes.click();
//                    UtilitiesUI.waitForLoaderHidden(10);
//                    ReporterX.fail("Failed to Add User ["+dataRow.get("UserFullName".toUpperCase())+"].!");
//                }
//            }else {
//                ReporterX.fail("Failed to Add User ["+dataRow.get("UserFullName".toUpperCase())+"].!");
//            }


        }else {
            ReporterX.fail("Add New User Page not Loaded.!!");
        }

    }




}
