package ru.ibs;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.ibs.pages.ProductsPage;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс с проверками страницы "Товары"
 */
public class ProductsTest extends BaseTest {
    /**
     * Проверка отображения элементов на странице "Товары"
     */
    @DisplayName("Проверка отображения элементов на странице \"Товары\"")
    @ParameterizedTest
    @CsvSource({"Наименование,Тип,Экзотический"})
    public void test1(String name, String type, String ex) {
        String[] split = {name, type, ex};
        //Перейти на страницу "Товары";
        chromeDriver.get("http://localhost:8080/food");
        ProductsPage productsPage = new ProductsPage(chromeDriver);

        //Открыта страница "Товары".Проверить отображение названия таблицы;
        Assertions.assertEquals("Список товаров",
                productsPage.getTableName().getText(),
                "Таблица не названа 'Список товаров'");

        List<String> stringList = Arrays.asList(split);
        //Проверить отображение заголовков таблицы.В таблице есть заголовки "Наименование", "Тип","Экзотический"
        Assertions.assertTrue(productsPage.getTableHeads()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList())
                .containsAll(stringList),
                "В таблице нет заголовков(есть,но не все) из параметров");

        //Проверить отображение строк таблицы(достаточно проверить, что идентификаторы не null)
        Assertions.assertTrue(
                productsPage.getIdentifiers()
                        .stream().
                        noneMatch(s -> s.getText() == null),
                "Идентификатор строки null");

        //Проверить кнопку "Добавить".Кнопка кликабельна,имеет синий цвет,имеет надпись "Добавить"
        Assertions.assertTrue(
                productsPage.getAddButton().isDisplayed()
                        && productsPage.getAddButton().getText().equals("Добавить")
                        && Color.fromString(productsPage.getAddButton().getCssValue("background-color"))
                        .asHex().equals("#007bff"),
                "Одна или более проверок кнопки 'Сохранить' не прошла");
    }

    @DisplayName("Проверка добавления товара с валидными данными")
    @ParameterizedTest
    @CsvSource({"Ананас,Фрукт,true"})
    public void test2(String name, String type, String exotic) {

        //Перейти на страницу "Товары";
        chromeDriver.get("http://localhost:8080/food");
        ProductsPage productsPage = new ProductsPage(chromeDriver);

        //Открыта страница "Товары".Проверить отображение названия таблицы;
        Assertions.assertTrue(productsPage
                .getTableName()
                .isDisplayed(),
                "Заголовок страницы не отображен");

        //Нажать кнопку "Добавить"
        productsPage.getAddButton().click();
        wait.until(ExpectedConditions.
                elementToBeClickable(productsPage.getSaveButton()));

        // Открыто всплывающее окно "Добавление товара"
        Assertions.assertTrue(
                productsPage.getAddProductHeader()
                        .isDisplayed(),
                "Заголовок формы добавления не отображен");
        //Окно содержит поле "Наименование".  ;
        Assertions.assertTrue(
                productsPage.getAddProductName()
                        .isDisplayed(),
                "Поле ввода 'Наименование' не отображен");
        // Окно содержит поле "Тип".
        Assertions.assertTrue(
                productsPage.getAddType()
                        .isDisplayed(),
                "Выпадающий список 'Тип' не отображен");
        // В поле "Тип" на выбор 2 варианта "Фрукт", "Овощ".
        Assertions.assertTrue(productsPage.getAllAddTypes().size() == 2
                && productsPage.getAllAddTypes()
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList())
                .containsAll(Arrays.asList("Овощ", "Фрукт")),
                "Проверки на количество и/или содержимое поля 'Тип' не прошли");
        //Окно содержит чек-бокс "Экзотический".
        Assertions.assertTrue(
                productsPage.getAddExoticCheckBox()
                        .isDisplayed(),
                "Чек-бокс не отображен");
        //Окно содержит кнопку "Сохранить"
        Assertions.assertTrue(
                productsPage.getSaveButton()
                        .isDisplayed(),
                "Кнопка 'Сохранить' не отображена");

        //В поле "Наименование" ввести значение переменной имени
        productsPage.getAddProductName().sendKeys(name);
        //Введено значение переменной "имя" в поле "Наименование";
        Assertions.assertEquals(
                name, productsPage.getAddProductName()
                        .getAttribute("value"),
                "Имя в поле Наименование не соответствует введенному");

        //В поле "Тип" выбрать значение переменной "тип";
        productsPage.setAddType(type);
        //Выбрано значение переменной "тип" в поле "Тип";
        Assertions.assertTrue(productsPage
                .getAddType().getDomProperty("textContent")
                .contains(type),
                "Тип в поле Тип не соответствует введенному");

        // Активировать чек-бокс "Экзотический";
        productsPage.setAddExoticCheckBox(exotic);
        //Чек-бокс "Экзотический" активирован;
        Assertions.assertTrue(
                productsPage.getAddExoticCheckBox()
                        .isSelected(),
                "Чек-бокс не активирован");

        //Нажать кнопку "Сохранить".
        productsPage.getSaveButton().click();
        wait.until(
                ExpectedConditions.invisibilityOf(
                        productsPage.getAddProductHeader()));
        //Проверить, что закрыто всплывающее окно "Добавление товара"
        Assertions.assertFalse(
                productsPage.getAddProductHeader()
                        .isDisplayed(),
                "Форма добавления не закрыта");
        //Проверить, что в таблице есть строка со всеми данными-параметрами
        List<String> list = Arrays.asList(name, type, exotic);
        Assertions.assertTrue(productsPage
                .getRowsData()
                .stream()
                .map(WebElement::getText)
                .anyMatch(s -> {
                    for (String l :
                            list) {
                        if (!s.contains(l)) return false;
                    }
                    return true;
                }),"Данные в таблице не соответствуют добавляемым параметрам");
    }
}
