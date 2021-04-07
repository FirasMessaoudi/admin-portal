package com.elm.shj.admin.portal.automation.scenarios;


import com.elm.qa.framework.runner.Executer;
import com.elm.qa.framework.utilities.ReporterX;
import com.elm.shj.admin.portal.automation.apis.FieldSupporter;
import com.elm.shj.admin.portal.automation.apis.ShjApiManagement;
import com.elm.shj.admin.portal.automation.pages.DatabaseQueryManagement;
import com.elm.shj.admin.portal.automation.pages.SystemLogin;
import com.elm.shj.admin.portal.automation.pages.SystemNavigators;
import com.elm.shj.admin.portal.automation.pages.UserManagement;

import org.testng.annotations.Test;


public class TestScenarios {

    //Identifying objects:
    SystemLogin systemLogin = new SystemLogin();
    DatabaseQueryManagement dbMgmt = new DatabaseQueryManagement();
    SystemNavigators systemNavigators = new SystemNavigators();
    UserManagement addUser = new UserManagement();
    ShjApiManagement insProcMgmt = new ShjApiManagement();
    FieldSupporter fieldSupporter = new FieldSupporter();



    //region Before Test

//    @BeforeClass
//    public void Initialization() {
//        try {
//
//            ReporterX.info("Build Number: " + System.getenv("BUILD_NUMBER"));
//            ReporterX.info("Job Name: " + System.getenv("JOB_NAME"));
//
//            //Filling Full Database Table names from Excel to fullTableName hashtable
//            UtilitiesUI.fullTableName = new Hashtable<String,String>();
//            Hashtable<Integer, Hashtable<String, String>> htDBTableData = DataManager.GetExcelDataTable("Select Key,FinalTableName from DBTables");
//            for (int i = 1; i <= htDBTableData.size(); i++) {
//                UtilitiesUI.fullTableName.put(htDBTableData.get(i).get("Key".toUpperCase()), htDBTableData.get(i).get("FinalTableName".toUpperCase()));
//            }
//            //Updating DBServerIP in Config.properties file as per Excel value
//             if (!Global.Test.EnviromentVars.get("DBServerIP").trim().equalsIgnoreCase(DataManager.getPropertyFile("DBServerIP"))) {
//                Path path = Paths.get("Configuration/config.properties");
//                String content = new String(Files.readAllBytes(path), StandardCharsets.UTF_8);
//                content = content.replaceAll(DataManager.getPropertyFile("DBServerIP"), Global.Test.EnviromentVars.get("DBServerIP"));
//                Files.write(path, content.getBytes(StandardCharsets.UTF_8));
//            }
//
//           //Cleaning DB
//            if(Global.Test.EnviromentVars.get("CleanDB").equalsIgnoreCase("yes")) {
//                ReporterX.info("<----- Cleaning DB Started ----->");
//
//                dbMgmt.deleteSurveyTasks();
//                dbMgmt.deleteInspectionItemIDs();
//                dbMgmt.deleteViolations();
//                dbMgmt.deleteFacilityFullTable();
//                dbMgmt.deleteInsProcessFullTable();
//                dbMgmt.deleteTenantInboxTasks();
//
//                ReporterX.info("<----- Cleaning DB Completed ----->");
//
//            }
//            // dbMgmt.deleteInspectionCentre();
//            UtilitiesUI.resetVariables();
//
//            ReporterX.info("@BeforeClass executed Successfully!");
//
//        } catch (Exception e) {
//            ReporterX.error(e);
//        }
//    }

    //endregion

    //region Admin Scope

    @Test
    public void ValidateSystemLogin() {
        try {

            ReporterX.info("Validate System Login");

            systemLogin.SignIn(Executer.TestDataRow);

        } catch (Exception ex) {
            ReporterX.error(ex);
        }
    }
    @Test
    public void validateAddNewUser() {
        try {
            systemLogin.SignIn(Executer.TestDataRow.get("LoginRow".toUpperCase()));
            systemNavigators.goToUserManagement();
            addUser.addNewUSer(Executer.TestDataRow);

        } catch (Exception ex) {
            ReporterX.error(ex);
        }
    }

    //endregion // End Of Admin Scope



}
















