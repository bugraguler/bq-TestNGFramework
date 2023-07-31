package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.CommonMethods;

public class LoginTest extends CommonMethods {

    @Test
    public void logoIsPresent() {
        //assert that logo is present on the login page
        boolean isLogoDisplayed = loginPage.logo.isDisplayed();
        Assert.assertTrue(isLogoDisplayed);
    }

    @DataProvider(name = "Credentials")
    public Object[][] data() {
        Object[][] login = {
                {"Admin", "abc", "Invalid credentials"},
                {"Adm", "Hum@nhrm123", "Invalid credentials"},
                {"Admin", "", "Password cannot be empty"},
                {"", "Hum@nhrm123", "Username cannot be empty"}};
        return login;
    }

    @Test(dataProvider = "Credentials")
    public void invalidCredentials(String username, String password, String errorMessage) {
        loginPage.userNameBox.sendKeys(username);
        loginPage.passwordBox.sendKeys(password);
        loginPage.loginBtn.click();
        String errorMessageText = loginPage.errorMessage.getText();
        Assert.assertEquals(errorMessageText, errorMessage);
    }

}
