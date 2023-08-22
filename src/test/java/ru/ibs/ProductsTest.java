package ru.ibs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.pages.ProductsPage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс с проверками
 */
public class ProductsTest extends BaseTest {
    /**
     * Проверка отображения элементов на странице "Товары"
     */
    @DisplayName("Проверка отображения элементов на странице \"Товары\"")
    @Test
    public void test1() {

        //Перейти на страницу "Товары";
        chromeDriver.get("http://localhost:8080/food");
        ProductsPage productsPage = new ProductsPage(chromeDriver);

        //Проверить отображение таблицы;
        Assertions.assertEquals("Список товаров", productsPage.getTableName().getText());

        List<String> strings = Arrays.asList("Наименование", "Тип", "Экзотический");

        //Проверить отображение заголовков таблицы;
        Assertions.assertTrue(productsPage.getTableHeads()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList())
                .containsAll(strings));

        //Проверить кнопку "Добавить".
        Assertions.assertTrue(productsPage.getAddButton().isDisplayed()
                && productsPage.getAddButton().getText().equals("Добавить")
                && Color.fromString(productsPage.getAddButton().getCssValue("background-color")).asHex().equals("#007bff"));
    }

    @DisplayName("Проверка добавления товара с валидными данными")
    @Test
    public void test2() {

        //Перейти на страницу "Товары";
        chromeDriver.get("http://localhost:8080/food");
        ProductsPage productsPage = new ProductsPage(chromeDriver);

        //Нажать кнопку "Добавить"
        productsPage.getAddButton().click();
        wait.until(
                ExpectedConditions.elementToBeClickable(productsPage.getSaveButton()));

        // Открыто всплывающее окно "Добавление товара"
        Assertions.assertTrue(productsPage.getAddProductHeader().isDisplayed());
        //Окно содержит поле "Наименование".  ;
        Assertions.assertTrue(productsPage.getAddProductName().isDisplayed());
        // Окно содержит поле "Тип". Поле "Тип" является выпадающим списком.
        Assertions.assertTrue(productsPage.getAddType().isDisplayed());
        //Окно содержит чек-бокс "Экзотический".
        Assertions.assertTrue(productsPage.getAddExoticCheckBox().isDisplayed());
        //Окно содержит кнопку "Сохранить"
        Assertions.assertTrue(productsPage.getSaveButton().isDisplayed());

        //В поле "Наименование" ввести значение "Ананас"
        productsPage.getAddProductName().sendKeys("Ананас");
        //Введено значение "Ананас" в поле "Наименование";
        Assertions.assertEquals("Ананас", productsPage.getAddProductName().getAttribute("value"));

        //В поле "Тип" выбрать значение "Фрукт";
        productsPage.setAddType("Фрукт");
        //Выбрано значение "Фрукт" в поле "Тип";
        Assertions.assertEquals(productsPage.getAddType().getAttribute("value"), "FRUIT");

        // Активировать чек-бокс "Экзотический";
        productsPage.getAddExoticCheckBox().click();
        //Чек-бокс "Экзотический" активирован;
        Assertions.assertTrue(productsPage.getAddExoticCheckBox().isSelected());

        //Нажать кнопку "Сохранить".
        productsPage.getSaveButton().click();
        wait.until(
                ExpectedConditions.stalenessOf(productsPage.getSaveButton()));
        //Закрыто всплывающее окно "Добавление товара"
        Assertions.assertFalse(productsPage.getAddProductHeader().isDisplayed());
    }
}
