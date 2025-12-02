import com.swaglabs.Listeners.TestNGListeners;
import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.BrowserActions;
import com.swaglabs.utils.JsonUtils;
import com.swaglabs.utils.PropertiesUtils;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
@Listeners(TestNGListeners.class)
public class UserFlowTC {
    //variables
    private GUIDriver driver;
    JsonUtils testData;

    @Test
    public void userFlow() {
        new LoginPage(driver)
                .enterUserName(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password"))
                .clickLoginBtn()
                .assertSuccessfulLoginSoft()
                .addSpecificProductToCart(testData.getJsonData("product-names.item1.name"))
                .assertProductAddedToCart(testData.getJsonData("product-names.item1.name"))
                .clickCartIcon()
                .assertProductDetails(testData.getJsonData("product-names.item1.name"),
                        (testData.getJsonData("product-names.item1.price")))
                .clickCheckoutButton()
                .fillInformationForm(testData.getJsonData("information-form.first-name"),
                        testData.getJsonData("information-form.last-name"),
                        testData.getJsonData("information-form.postal-code"))
                .assertInformationPage(testData.getJsonData("information-form.first-name"),
                        testData.getJsonData("information-form.last-name"),
                        testData.getJsonData("information-form.postal-code"))
                .clickContinueButton()
                .clickFinishButton()
                .assertConfirmationMessage(testData.getJsonData("confirmationMessage"));

    }

    //Configurations
    @BeforeClass
    public void beforeClass(){
        testData = new JsonUtils("test-data");

    }
    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        String browserName = PropertiesUtils.getPropertyValue("browserType");
        driver = new GUIDriver(browserName);
        new LoginPage(driver).navigateToLoginPage();
    }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
//        DriverManager.getDriver().quit();
//        CustomSoftAssert.customAssertAll();
        driver.browserActions().closeBrowser();


    }
}
