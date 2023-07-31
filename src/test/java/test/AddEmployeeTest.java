package test;

import org.testng.annotations.Test;
import utils.CommonMethods;
import utils.ConfigReader;

public class AddEmployeeTest extends CommonMethods {
    //read the configuration file for username and password
    //and add an employee

    @Test
    public void addEmployee() {

        loginPage.loginMethod(ConfigReader.getPropertyValue("username"),ConfigReader.getPropertyValue("password"));

    }

}
