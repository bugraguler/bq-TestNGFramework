package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.CommonMethods;
import utils.ConfigReader;

import java.sql.SQLOutput;
import java.util.List;

public class EmployeeSearchTest extends CommonMethods {
    //search the employee with ID

    @Test
    public void employeeSearchByID() {
        loginPage.loginMethod(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));
        click(employeeSearchPage.pimOption);
        click(employeeSearchPage.empListOption);
        sendText(employeeSearchPage.IdField, ConfigReader.getPropertyValue("employeeID"));
        click(employeeSearchPage.searchButton);
        List<WebElement>rowData =employeeSearchPage.rowData;
        for (WebElement data:rowData
             ) {
            Assert.assertEquals(ConfigReader.getPropertyValue("employeeID"),data.getText());
        }
    }
}
