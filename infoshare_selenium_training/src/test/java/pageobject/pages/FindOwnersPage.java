package pageobject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.WebDriver;

public class FindOwnersPage {
    private WebDriver driver;
    @FindBy(id = "lastName")
    private WebElement fieldOwnerLastName;
    @FindBy(xpath = "//button[@type='submit']")
    private WebElement buttonFindOwner;
    @FindBy(xpath = "//a[@class='btn btn-default']")
    private WebElement labelAddOwner;

    public FindOwnersPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void typeOwnerLastName(String lastName) {
        fieldOwnerLastName.sendKeys(lastName);
    }

    public void chooseButtonFindOwner() {
        buttonFindOwner.click();
    }

    public void chooseLabelAddOwner() {
        labelAddOwner.click();
    }
}
