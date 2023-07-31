package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.CommonMethods;

public class EmployeeSearchPage extends CommonMethods {

    //this is object repository
    @FindBy(id = "menu_pim_viewPimModule")
    public WebElement pimOption;

    @FindBy(id = "menu_pim_addEmployee")
    public WebElement addEmployeeOption;

    @FindBy(id = "menu_pim_viewEmployeeList")
    public WebElement empListOption;

    @FindBy(id = "empsearch_employee_name_empName")
    public WebElement NameField;

    @FindBy(id = "empsearch_id")
    public WebElement IdField;

    @FindBy(id = "searchBtn")
    public WebElement searchButton;

    public EmployeeSearchPage(){            //we created constructor because constructors has been automatically called when we create an object of a class
        PageFactory.initElements(driver,this);
    }
}
