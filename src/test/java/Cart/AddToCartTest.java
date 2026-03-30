package Cart;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.time.Duration;

public class AddToCartTest {

    WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.periplus.com/");
    }

    @AfterClass
    public void tearDown() {
        //driver.quit();
    }

    @Test
    public void addToCart() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Click Sign In
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sign In"))).click();

        // Input email
        WebElement email = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.name("email"))
        );
        email.sendKeys("muhgiyas@gmail.com");

        // Input password
        WebElement password = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("ps"))
        );
        password.sendKeys("M91y@5wr");

        // Click login
        wait.until(ExpectedConditions.elementToBeClickable(By.id("button-login"))).click();

        // Wait search box and search product
        WebElement searchBox = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("filter_name_desktop"))
        );
        searchBox.sendKeys("Elphie");
        searchBox.submit();

        // Wait until preloader disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("preloader")
        ));

        // Then click product
        wait.until(ExpectedConditions.elementToBeClickable(
                By.linkText("Elphie")
        )).click();

        // Click Add to Cart
        wait.until(ExpectedConditions.elementToBeClickable(
                By.className("btn-add-to-cart")
        )).click();

        // Close modal (if appears)
        wait.until(ExpectedConditions.elementToBeClickable(
                By.className("btn-modal-close")
        )).click();

        // Wait until preloader disappears
        wait.until(ExpectedConditions.invisibilityOfElementLocated(
                By.className("preloader")
        ));
        // Open cart
        wait.until(ExpectedConditions.elementToBeClickable(By.id("show-your-cart"))).click();
    }
}
