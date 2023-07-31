package test;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;
import utils.CommonMethods;
import utils.ConfigReader;
import utils.Constants;
import utils.ExcelReader;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class AddEmployeeTest extends CommonMethods {
    //read the configuration file for username and password
    //and add an employee

    @Test
    public void addEmployee() {

        loginPage.loginMethod(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));
        click(dashboardPage.pimOption);
        click(dashboardPage.addEmployeeButton);

        sendText(addEmployeePage.firstNameField, "alibekto");
        sendText(addEmployeePage.middleNameField, "mn");
        sendText(addEmployeePage.lastNameField, "desidero");
        //get the empID
        String empID = addEmployeePage.emplDdLocator.getAttribute("value");
        System.out.println(empID);
        click(addEmployeePage.saveButton);
    }

    @Test
    public void addMultipleEmployee() throws InterruptedException {
        loginPage.loginMethod(ConfigReader.getPropertyValue("username"), ConfigReader.getPropertyValue("password"));
        click(dashboardPage.pimOption);
        click(dashboardPage.addEmployeeButton);
        //read the employee data from excel file
        //assert that u have successfully added the employee
        List<Map<String, String>> newEmployees = ExcelReader.excelIntoMap(Constants.TESTDATA_FILEPATH, ConfigReader.getPropertyValue("sheetName"));
        Iterator<Map<String, String>> iterator = newEmployees.iterator();
        while (iterator.hasNext()) {
            //it returns the key and value for employees
            Map<String, String> mapNewEmp = iterator.next();
            System.out.println(mapNewEmp.get("FirstName"));
            System.out.println(mapNewEmp.get("MiddleName"));
            System.out.println(mapNewEmp.get("LastName"));
            //filling all the fields from the data coming from excel file
            sendText(addEmployeePage.firstNameField, mapNewEmp.get("FirstName"));
            sendText(addEmployeePage.middleNameField, mapNewEmp.get("MiddleName"));
            sendText(addEmployeePage.lastNameField, mapNewEmp.get("LastName"));
            //it will fetch the employee id from attribute
            String empIdValue = addEmployeePage.emplDdLocator.getAttribute("value");
            //to upload the photograph
            sendText(addEmployeePage.choosePhotoLocator, mapNewEmp.get("Photograph"));
            if (!addEmployeePage.checkBox.isSelected()) {
                click(addEmployeePage.checkBox);
            }
            sendText(addEmployeePage.createUsername, mapNewEmp.get("Username"));
            sendText(addEmployeePage.createPassword, mapNewEmp.get("Password"));
            sendText(addEmployeePage.confirmPassword, mapNewEmp.get("Password"));
            click(addEmployeePage.saveButton);

            Thread.sleep(3000);
            //to verify the employee we will navigate to employee list option
            click(employeeSearchPage.empListOption);

            sendText(employeeSearchPage.IdField, empIdValue);
            click(employeeSearchPage.searchButton);
            //it is returning the data from the row in results
            List<WebElement> rowData = driver.findElements(By.xpath("//*[@id ='resultTable']/tbody/tr"));
            for (int i = 0; i < rowData.size(); i++) {
                String rowText = rowData.get(i).getText();
                System.out.println(rowText);
                String expectedData = empIdValue + " " + mapNewEmp.get("FirstName") + " " + mapNewEmp.get("MiddleName") + " " +
                        mapNewEmp.get("LastName");

                Assert.assertEquals(expectedData, rowText);
            }

            click(employeeSearchPage.addEmployeeOption);
            Thread.sleep(2000);



        }

    }
}