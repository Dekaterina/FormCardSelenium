package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FormCardSeleniumTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void BeforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void AfterEach() {
        driver.quit();
        driver = null;
    }

    @Test
    public void shouldValidateInputs() {
        driver.get("http://localhost:9999");
        driver.findElement(By.cssSelector("[data-test-id=name]input")).sendKeys("Николай-Николаевич Николаев");
        driver.findElement(By.cssSelector("[data-test-id=phone]input")).sendKeys("+12345678999");
        driver.findElement(By.cssSelector("[data-test-id=agreement]input")).click();
        driver.findElement(By.cssSelector("[type=button]")).click();
        var actualText = driver.findElement(By.cssSelector("[data-test-id=order-success]")).getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actualText.trim());

    }
}


