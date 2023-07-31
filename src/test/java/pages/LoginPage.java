package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class LoginPage extends CommonMethods {

    //this is object repository
    @FindBy(id = "txtUsername")
    public WebElement userNameBox;

    @FindBy(id="txtPassword")
    public WebElement passwordBox;

    @FindBy(id = "btnLogin")
    public WebElement loginBtn;

    @FindBy(id = "spanMessage")
    public WebElement errorMessage;

    @FindBy(xpath = "//*[@id=\"divLogo\"]/img")
    public WebElement logo;

    public LoginPage() {    //page factory interfaceini kullanmadan yukarıdaki locatorları diğer yerlerde kullanamayız!!!!!!!
        PageFactory.initElements(driver,this);
    }

    public void loginMethod(String username,String password){
        sendText(userNameBox,username);
        sendText(passwordBox,password);
        click(loginBtn);

    }
}
