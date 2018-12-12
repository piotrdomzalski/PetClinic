package pageobject.tests;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.FindOwnersPage;
import pageobject.pages.HomePage;
import pageobject.pages.OwnerInformationPage;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@RunWith(DataProviderRunner.class)
public class FindOwnerTest {
    private static final String PAGE_URL = "http://localhost:8080/";
    private WebDriver driver;
    private HomePage homePage;
    private FindOwnersPage findOwnersPage;
    private OwnerInformationPage ownerInformationPage;
    @DataProvider
    public static Object[][] testForFindingOwners()
    {        return new String[][]{
            new String[]{"Kowalski"},
            new String[]{"Wafel"},
            new String[]{"Adamski"},
            new String[]{"Col"},
            new String[]{"Kozlowski"},
    };
    }
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/piotrdomzalski/bazaprojektowa/infoshare_selenium_training/src/test/resources/drivers/chrome/chromedriver_Linux64");
        driver = new ChromeDriver();
        homePage = PageFactory.initElements(driver, HomePage.class);
        findOwnersPage = PageFactory.initElements(driver, FindOwnersPage.class);
        ownerInformationPage = PageFactory.initElements(driver, OwnerInformationPage.class);
        driver.get(PAGE_URL);
    }
    @Test
    @UseDataProvider("testForFindingOwners")
    public void findOwner( String lastName) {
        homePage.clickFindOwner();
        findOwnersPage.typeOwnerLastName(lastName);
        findOwnersPage.chooseButtonFindOwner();
        ownerInformationPage.getTextFromOwnerInformationPage();
        assertThat(ownerInformationPage.getTextFromOwnerInformationPage().contains("Owner Information"));
    }
    @After
    public void tearDown() {
        driver.close();
    }
}



