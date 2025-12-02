import com.swaglabs.Listeners.TestNGListeners;
import com.swaglabs.drivers.GUIDriver;
import com.swaglabs.pages.CartPage;
import com.swaglabs.pages.HomePage;
import com.swaglabs.pages.InformationPage;
import com.swaglabs.pages.LoginPage;
import com.swaglabs.utils.PropertiesUtils;
import com.swaglabs.utils.*;
import org.testng.annotations.*;

import static com.swaglabs.utils.TimeStampUtils.getTimeStamp;

@Listeners(TestNGListeners.class)
public class E2E {
    //Variables
    private GUIDriver driver;
    JsonUtils testData;
    String FIRST_NAME;
    String SECOND_NAME;


    //Test
    @Test
    public void successfulLogin() {
        new LoginPage(
                driver).enterUserName(testData.getJsonData("login-credentials.username"))
                .enterPassword(testData.getJsonData("login-credentials.password")).clickLoginBtn().assertSuccessfulLoginSoft();;
    }

    @Test(dependsOnMethods = "successfulLogin")
    public void addingProductToCart() {
        new HomePage(driver).addSpecificProductToCart(testData.getJsonData("product-names.item1.name"))
                .assertProductAddedToCart(testData.getJsonData("product-names.item1.name"));
    }

    @Test(dependsOnMethods = "addingProductToCart")
    public void checkoutProduct() {
        new HomePage(driver)
                .clickCartIcon()
                .assertProductDetails(testData.getJsonData("product-names.item1.name"), (testData.getJsonData("product-names.item1.price")));
    }

    @Test(dependsOnMethods = "checkoutProduct")
    public void fillInformationForm() {
        new CartPage(driver)
                .clickCheckoutButton()
                .fillInformationForm(FIRST_NAME, SECOND_NAME,
                        testData.getJsonData("information-form.postal-code"))
                .assertInformationPage(FIRST_NAME, SECOND_NAME,
                        testData.getJsonData("information-form.postal-code"));
    }

    @Test(dependsOnMethods = "fillInformationForm")
    public void finishedCheckout() {
        new InformationPage(driver)
                .clickContinueButton()
                .clickFinishButton()
                .assertConfirmationMessage(testData.getJsonData("confirmationMessage"));
    }

    //Configurations
    @BeforeClass
    public void beforeClass() {
        testData = new JsonUtils("test-data");
        FIRST_NAME = testData.getJsonData("information-form.first-name") + getTimeStamp();
        SECOND_NAME = testData.getJsonData("information-form.last-name") + getTimeStamp();
        String browserName = PropertiesUtils.getPropertyValue("browserType");
        driver = new GUIDriver(browserName);
        new LoginPage(driver).navigateToLoginPage();
    }

    @AfterClass
    public void tearDown() {
//        DriverManager.getDriver().quit();
//        CustomSoftAssert.customAssertAll();
        driver.browserActions().closeBrowser();

    }

//    @AfterClass
//    public void afterClass() {
//
//    }
}
