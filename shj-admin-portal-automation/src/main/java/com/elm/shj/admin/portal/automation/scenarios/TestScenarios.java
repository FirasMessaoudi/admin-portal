package com.elm.shj.admin.portal.automation.scenarios;


import com.elm.qa.framework.core.ActionX;
import com.elm.qa.framework.runner.Executer;
import com.elm.qa.framework.utilities.ReporterX;
import com.elm.shj.admin.portal.automation.pages.Home;
import com.elm.shj.admin.portal.automation.pages.RoleManagement;
import com.elm.shj.admin.portal.automation.pages.SystemLogin;
import com.elm.shj.admin.portal.automation.pages.Navigators;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


public class TestScenarios {

    //Identifying objects:
    SystemLogin systemLogin = new SystemLogin();
    Navigators navigators = new Navigators();
    RoleManagement roleManagement = new RoleManagement();
    Home homePage = new Home();




    //region Before and After Execution

    @AfterClass
    public void doLogout(){
        try{

            systemLogin.LogOut();
        }catch (Exception e){
            ReporterX.error(e);
        }
    }

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
    public void ValidateAddUserRole() {
        try {
            systemLogin.SignIn(Executer.TestDataRow.get("LoginRow".toUpperCase()));
            navigators.goToRoleManagement();
            roleManagement.addNewRole(Executer.TestDataRow);

        } catch (Exception ex) {
            ReporterX.error(ex);
        }
    }

    //endregion // End Of Admin Scope



}
















