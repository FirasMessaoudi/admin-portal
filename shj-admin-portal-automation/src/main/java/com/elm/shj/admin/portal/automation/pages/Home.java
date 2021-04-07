package com.elm.shj.admin.portal.automation.pages;

import com.elm.qa.framework.core.Global;
import com.elm.qa.framework.utilities.ReporterX;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Home {

    @FindBy(xpath = "//div[@id='spinner']")
    WebElement divLoading;


    public Home() {
        try {
            PageFactory.initElements(Global.Test.Browser, this);
        } catch (Exception ex) {
            ReporterX.error(ex);
        }
    }
}
