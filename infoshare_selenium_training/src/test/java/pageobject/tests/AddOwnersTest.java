package pageobject.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobject.pages.AddOwnerPage;
import pageobject.pages.FindOwnersPage;
import pageobject.pages.HomePage;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.runner.RunWith;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobject.pages.OwnerInformationPage;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(DataProviderRunner.class)
public class AddOwnersTest {
    private static final String PAGE_URL = "http://localhost:8080/";
    private WebDriver driver;
    private HomePage homePage;
    private FindOwnersPage findOwnersPage;
    private AddOwnerPage addOwnerPage;
    private OwnerInformationPage ownerInformationPage;
    @DataProvider
    public static Object[][] testForAddingOwners()
    {        return new String[][]{
            new String[]{"Adam.", "Kowalski", "Grunwaldzka 1", "Warszawa", "604567891"},
            new String[]{"Krystyna", "Wafel", "Tuwima 7", "Krakow", "503345671"},
            new String[]{"Marcin", "Kozlowski", "Teatralna 14/2", "Tczew", "587643457"},
            new String[]{"Wojtek", "Adamski", "Szucha 12/1", "Torun", "225678902"},
            new String[]{"Jerzy", "Col", "Cicha 24", "Opole", "567853120"},
    };
    }
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/home/piotrdomzalski/bazaprojektowa/infoshare_selenium_training/src/test/resources/drivers/chrome/chromedriver_Linux64");
        driver = new ChromeDriver();
        homePage = PageFactory.initElements(driver, HomePage.class);
        findOwnersPage = PageFactory.initElements(driver, FindOwnersPage.class);
        addOwnerPage = PageFactory.initElements(driver, AddOwnerPage.class);
        ownerInformationPage = PageFactory.initElements(driver, OwnerInformationPage.class);
        driver.get(PAGE_URL);
    }
    @Test
    @UseDataProvider("testForAddingOwners")
    public void addNewOwner(String firstName, String lastName, String address, String city, String telephone) {
        homePage.clickFindOwner();
        findOwnersPage.chooseLabelAddOwner();
        addOwnerPage.inputOwnerDetails(firstName, lastName, address, city, telephone);
        ownerInformationPage.getTextFromOwnerInformationPage();
        assertThat(ownerInformationPage.getTextFromOwnerInformationPage().contains("Owner Information"));
    }
    @After
    public void tearDown() {
        driver.close();
    }
}