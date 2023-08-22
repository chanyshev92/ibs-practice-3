package ru.ibs;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Класс для организации доступа к вебрайверу
 */
public class BaseTest {
    /**
     * Экземпляр Chrome Driver
     */
    protected static WebDriver chromeDriver;

    /**
     * Explicitly wait
     */
    protected static WebDriverWait wait;

    /**
     * Выполняется перед всеми тестами
     */
    @BeforeAll
    public static void beforeAll() {
        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
    }

    /**
     * Выполняется перед каждым тестом
     */
    @BeforeEach
    public void before() {
        chromeDriver = new ChromeDriver();
        wait=new WebDriverWait(chromeDriver,Duration.ofSeconds(10));
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }

    /**
     * Выполняется после каждого теста
     */
    @AfterEach
    public void after() {
        chromeDriver.close();
    }

    /**
     * Выполняется после всех тестов
     */
    @AfterAll
    public static void afterAll() {
        chromeDriver.quit();
    }
}
