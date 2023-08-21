package ru.ibs;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * Класс для организации доступа к вебрайверу
 */
public class WebDriverProvider {
    /**
     * Экземпляр Chrome Driver
     */
    protected WebDriver chromeDriver;

    /**
     * Выполняется перед каждым тестом
     */
    @BeforeEach
    public void before(){

        System.setProperty("webdriver.chrome.driver", System.getenv("CHROME_DRIVER"));
        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    /**
     * Выполняется после каждого теста
     */
    @AfterEach
    public void after(){

        chromeDriver.quit();

    }
}
