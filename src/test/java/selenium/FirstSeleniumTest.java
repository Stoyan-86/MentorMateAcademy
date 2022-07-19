package selenium;

import com.fasterxml.jackson.databind.DatabindContext;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.checkerframework.checker.units.qual.A;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class FirstSeleniumTest {
    public WebDriver driver;
    public WebDriverWait wait;
    public JavascriptExecutor executor;
    public Actions actions;

    @BeforeMethod
    public void setUp() {
//      Using Chrome driver from download
//      System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chrome/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
//      options.addArguments("--headless");
        options.addArguments("--window-size=1920x1080");

        WebDriverManager.chromedriver()./*.browserVersion("103")*/setup();
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        //Implicit wait
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
//      driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        //Explicit wait
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        executor = (JavascriptExecutor) driver;
        Actions action = new Actions(driver);

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @Test
    public void testLandingPage() throws InterruptedException {
        //open home page
        driver.get("http://training.skillo-bg.com");
        //finding login link
        By loginLinkBy = By.id("nav-link-login");
        WebElement loginLink = driver.findElement(loginLinkBy);
        Assert.assertTrue(loginLink.isDisplayed());
        //finding list of 3 posts
        List<WebElement> listPosts = driver.findElements(By.xpath("//div[@class='row post-list-container']//app-post-detail"));
        Assert.assertEquals(listPosts.size(), 3);
        //redirect to login page
        loginLink.click();
        //check that we are on the login page
        WebElement signInText = driver.findElement(By.xpath("//p [text() = 'Sign in']"));
        Assert.assertTrue(signInText.isDisplayed());
        Thread.sleep(1000);
    }

    @Test
    public void testLoginPage() throws InterruptedException {
        //open login page
        driver.get("http://training.skillo-bg.com/users/login");
        //finding elements: username, password, singIn
        WebElement usernameField = driver.findElement(By.cssSelector("#defaultLoginFormUsername"));
        Assert.assertTrue(usernameField.isDisplayed());
        WebElement passwordField = driver.findElement(By.cssSelector("#defaultLoginFormPassword"));
        Assert.assertTrue(passwordField.isDisplayed());
        WebElement signInButton = driver.findElement(By.cssSelector("#sign-in-button"));
        Assert.assertTrue(signInButton.isDisplayed());
        wait.until(ExpectedConditions.visibilityOf(usernameField));
        //input credentials
        usernameField.click();
        usernameField.clear();
        usernameField.sendKeys("stoyan86");
        passwordField.click();
        passwordField.clear();
        passwordField.sendKeys("pass1234");
        signInButton.click();

        WebElement newPost = driver.findElement(By.cssSelector("#nav-link-new-post"));
        WebElement profile = driver.findElement(By.cssSelector("#nav-link-profile"));
        Assert.assertTrue(newPost.isDisplayed());
        Assert.assertTrue(profile.isDisplayed());

        Thread.sleep(2000);
    }

    @Test /*(invocationCount = 5) - number of test */
    public void testMobileBg() {
        String carMarka = "Mini";
        String carModel = "Cooper";

        driver.get("https://www.mobile.bg/pcgi/mobile.cgi");

        By marka = By.xpath("//select[@name='marka']");
        By model = By.xpath("//select[@name='model']");
        //      By search = By.xpath("//input[@id='button2']");
        By atSite = By.xpath("//p[text()='Към сайта']");

        driver.findElement(atSite).click();

        Select markaDropDown = new Select(driver.findElement(marka));
        markaDropDown.selectByVisibleText("Mini");

        Select modelDropDown = new Select(driver.findElement(model));
        modelDropDown.selectByVisibleText("Cooper");

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("button2"))));
        driver.findElement(By.id("button2")).click();
        executor.executeScript("window.scrollBy(0,250)");
        List<WebElement> listAdd = driver.findElements(By.xpath("//form[@mane='search']//a[@class='mmm']"));

        listAdd.forEach(add -> {
            Assert.assertTrue(add.getText().contains(carMarka + " " + carModel));
            //            Assert.assertTrue(add.getText().contains(carModel));
        });

    }

    @Test
    public void testFloatingMenu() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/floating_menu");

        By menu = By.xpath("//div[@id='menu']");
        Assert.assertTrue(driver.findElement(menu).isDisplayed());

        executor.executeScript("window.scrollBy(0,3000)");

        Assert.assertTrue(driver.findElement(menu).isDisplayed());

        Thread.sleep(2000);
    }

    @Test
    public void testLinkRedirect() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/redirector");

        WebElement redirectLink = driver.findElement(By.cssSelector("#redirect"));

        String originalURL = driver.getCurrentUrl();
        Assert.assertEquals(originalURL, "https://the-internet.herokuapp.com/redirector");

        redirectLink.click();

        String redirectedURL = driver.getCurrentUrl();
        Assert.assertNotEquals(redirectedURL, originalURL);
        Assert.assertEquals(redirectedURL, "https://the-internet.herokuapp.com/status_codes");

        Thread.sleep(2000);
    }

    @Test
    public void testAddRemoveElements() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/add_remove_elements/");

        By listElements = By.xpath("//div[@id='elements']/button");
        List<WebElement> listDeletes = driver.findElements(listElements);
        Assert.assertEquals(listDeletes.size(), 0);

        WebElement addElement = driver.findElement(By.xpath("//button[text()='Add Element']"));
        addElement.click();
        addElement.click();

        listDeletes = driver.findElements(listElements);
        Assert.assertEquals(listDeletes.size(), 2);

        Thread.sleep(1500);

        listDeletes.get(0).click();

        listDeletes = driver.findElements(listElements);
        Assert.assertEquals(listDeletes.size(), 1);
    }

    @Test
    public void testBasicAuth() throws InterruptedException {
        driver.get("https://admin:admin@the-internet.herokuapp.com/basic_auth");

        WebElement confirmation = driver.findElement(By.xpath("//p"));
        Assert.assertTrue(confirmation.isDisplayed());

        Thread.sleep(2000);
    }

    @Test
    public void testCheckboxex() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        By checkboxes = By.cssSelector("#checkboxes input");
        WebElement checkboxOne = driver.findElements(checkboxes).get(0);
        WebElement checkboxTwo = driver.findElements(checkboxes).get(1);

        Assert.assertFalse(checkboxOne.isSelected());
        Assert.assertTrue(checkboxTwo.isSelected());

        Thread.sleep(2000);

        checkboxOne.click();
        checkboxTwo.click();

        Assert.assertTrue(checkboxOne.isSelected());
        Assert.assertFalse(checkboxTwo.isSelected());

        Thread.sleep(2000);
    }

    @Test
    public void testContextMenu() {
        driver.get("https://the-internet.herokuapp.com/context_menu");
        WebElement divHotSpot = driver.findElement(By.cssSelector("#hot-spot"));

        actions.contextClick(divHotSpot).perform();

        Alert alert = driver.switchTo().alert();

        Assert.assertEquals(alert.getText(), "You selected a context menu");
        alert.accept();

        driver.switchTo().defaultContent();

    }

    @Test
    public void dragNdrop() {
        driver.get("https://jqueryui.com/droppable/");

        WebElement iFrame = driver.findElement(By.xpath("//iframe"));

        driver.switchTo().frame(iFrame);

        WebElement elementA = driver.findElement(By.xpath("//div[@id='draggable']"));
        WebElement elementB = driver.findElement(By.xpath("//div[@id='droppable']"));

        WebElement beforeDropText = driver.findElement(By.xpath("//div[@id='droppable']/p"));

        Assert.assertEquals(beforeDropText.getText(), "Drop here");

        actions.dragAndDrop(elementA, elementB).perform();

        Assert.assertEquals(beforeDropText.getText(), "Dropped!");

        driver.switchTo().parentFrame();
    }

//    @Test
//    public void testDynamicContent() {
//        driver.get("https://the-internet.herokuapp.com/dynamic_content");
//
//        By rowsTexts = By.cssSelector(".large-10.columns:not(.large-centered)");
//        By rowsImages = By.cssSelector(".large-2.columns:not(.large-centered)>img");
//
//        List<WebElement> listTextElements = driver.findElements(rowsTexts);
//        List<WebElement> listImagesElements = driver.findElements(rowsImages);
//
//        List<String> listImages = new ArrayList<>();
//        for (WebElement element : listTextElements
//        ) {
//            listImages.add(element.getAttribute("src"));
//        }
//
//   //     Assert.assertEquals(listImages.);
//    }

    @Test
    public void testDynamicControls() throws InterruptedException {
        driver.get("https://the-internet.herokuapp.com/dynamic_controls");
        //first part
        WebElement firstCheckbox = driver.findElement(By.xpath("//*[@id=\"checkbox\"]/input"));
        WebElement removeButton = driver.findElement(By.xpath("//button[text()='Remove']"));

        Thread.sleep(1500);
        firstCheckbox.click();
        Assert.assertTrue(firstCheckbox.isSelected());
        Thread.sleep(1500);
        removeButton.click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//button[text()='Add']"))));

        WebElement addButton = driver.findElement(By.xpath("//button[text()='Add']"));
        Assert.assertEquals(addButton.getText(), "Add");

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//p[text()=\"It's gone!\"]"))));

        Thread.sleep(1500);

        addButton.click();


        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//input[@id='checkbox']"))));
        WebElement itsBack = driver.findElement(By.xpath("//p[text()=\"It's back!\"]"));
        WebElement secondCehckbox = driver.findElement(By.xpath("//*[@id=\"checkbox\"]"));

        Thread.sleep(1500);

        secondCehckbox.click();

        Assert.assertEquals(itsBack.getText(), "It's back!");
        Assert.assertTrue(secondCehckbox.isSelected());

        //second part
        WebElement textBox = driver.findElement(By.xpath("//*[@id=\"input-example\"]/input"));
        WebElement enableButton = driver.findElement(By.xpath("//*[@id=\"input-example\"]/button"));

        Assert.assertFalse(textBox.isEnabled());

        Thread.sleep(1500);

        enableButton.click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"message\"]"))));

        Assert.assertTrue(textBox.isEnabled());

        Thread.sleep(1500);

        textBox.sendKeys("Stoyan Stoyanov");

        Thread.sleep(3000);

    }
}