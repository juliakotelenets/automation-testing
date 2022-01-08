package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.*;
import pages.HomePage;
import pages.ProductPage;
import pages.SearchResultsPage;
import utils.CapabilityFactory;

import java.net.MalformedURLException;
import java.net.URL;


public class BaseTest {

    protected static ThreadLocal<RemoteWebDriver> driver = new ThreadLocal<RemoteWebDriver>();
    private CapabilityFactory capabilityFactory = new CapabilityFactory();

    private static final String MAKEUP_URL = "https://makeup.com.ua/";

    @BeforeMethod
    @Parameters(value = {"browser"})
    public void setUp(@Optional("chrome") String browser) throws MalformedURLException{
        driver.set(new RemoteWebDriver(new URL("http://192.168.0.105:4444/wd/hub"), capabilityFactory.getCapabilities(browser)));
        getDriver().manage().window().maximize();
        getDriver().get(MAKEUP_URL);
    }

    @AfterMethod
    public void tearDown(){
        getDriver().close();
    }

    @AfterClass
    void terminate(){
        driver.remove();
    }


    public WebDriver getDriver(){
        return driver.get();
    }

    public HomePage getHomePage(){
        return new HomePage(getDriver());
    }

    public SearchResultsPage getSearchResultsPage(){
        return new SearchResultsPage(getDriver());
    }

    public ProductPage getProductPage(){
        return new ProductPage(getDriver());
    }

}
