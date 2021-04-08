package com.elm.shj.admin.portal.automation.pages;

import com.elm.qa.framework.core.ActionX;
import com.elm.qa.framework.core.DataManager;
import com.elm.qa.framework.core.Global;
import com.elm.qa.framework.utilities.Common;
import com.elm.qa.framework.utilities.ReporterX;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Hashtable;
import java.util.List;
import java.util.Locale;

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

    @FindBy(xpath = "//app-applicant-list//a[@routerlink='/roles/create']")
    WebElement btnAddNewRole;


    public RoleManagement() {
        try {
            PageFactory.initElements(Global.Test.Browser, this);
        } catch (Exception ex) {
            ReporterX.error(ex);
        }
    }


    public void addNewRole(Hashtable<String,String> dataRow) throws Exception{

        Home home = new Home();

        if (ActionX.Exists(btnAddNewRole, 3)) {
            btnAddNewRole.click();
        }

        if(Exists(txtRoleArbName,30)) {

            // preparing test data randomization

//            UtilitiesUI.prepareTestData(dataRow);

            //filling  Data

            SetValue(txtRoleArbName, dataRow.get("RoleArbName".toUpperCase()));

            SetValue(txtRoleEngName, dataRow.get("RoleEngName".toUpperCase()));

            if (dataRow.get("UserRoleActive".toUpperCase()).equalsIgnoreCase("yes")) {
                btnRoleActive.click();
            } else {
                btnRoleNotActive.click();
            }

            // Selecting list of Authorities
            List<String> lstRows = Common.GetIterations(dataRow.get("RoleAuthorities".toUpperCase()),",");
            Hashtable<String,String> lstAuthorities = DataManager.GetExcelDictionary("Select RowID,AuthorityName FROM RoleAuthorities");
            for (String row: lstRows ) {
                ReporterX.info("Select Authority : "+lstAuthorities.get(row));
               try {
                   Global.Test.Browser.
                           findElements(By.xpath("//app-add-update-role//form//label[contains(text(),'" + lstAuthorities.get(row) + "') and contains(@class,'btn-outline-info')]")).get(0).click();
               }catch (Exception e){}

            }
            ActionX.ScrollToElement(btnSave);
            btnSave.click();

            //validate add item
            if(Exists(home.divSaveMsgContent,30)){
                if(home.divSaveMsgContent.getText().toLowerCase().contains("success")){
                    ReporterX.info("Add User Role System Message : "+home.divSaveMsgContent.getText());
                    ReporterX.pass("User Role ["+dataRow.get("RoleEngName".toUpperCase())+"] was added.!");
                }else {
                    ReporterX.info("Add User Role System Message : "+home.divSaveMsgContent.getText());
                    ReporterX.fail("Failed to Add User Role ["+dataRow.get("RoleEngName".toUpperCase())+"].!");
                }
            }else {
                ReporterX.fail("Failed to Add User Role ["+dataRow.get("RoleEngName".toUpperCase())+"].!");
            }


        }else {
            ReporterX.fail("Add New User Role Page not Loaded.!!");
        }

    }




}
