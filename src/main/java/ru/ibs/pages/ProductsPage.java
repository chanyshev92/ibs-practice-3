package ru.ibs.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

/**
 * Страница вкладки "Товары"
 */
public class ProductsPage {

    protected WebDriver chromeDriver;
    /**
     * Название таблицы
     */
    @FindBy(xpath = "//div[@class='container-fluid']/h5")
    private WebElement tableName;
    /**
     * Заголовки таблицы(1ая строка)
     */
    @FindBy(xpath = "//div/h5/../table/thead//th")
    private List<WebElement> tableHeads;
    /**
     * Строки с данными
     */
    @FindBy(xpath = "//div/h5/../table/tbody//tr")
    private List<WebElement> tableRows;

    /**
     * Кнопка "Добавить"
     */
    @FindBy(xpath = "//button[@data-toggle='modal']")
    private WebElement addButton;
    /**
     * Заголовок формы добавления
     */
    @FindBy(xpath = "//div[@id='editModal']//div[@class='modal-header']/h5")
    private WebElement addProductHeader;
    /**
     * Поле ввода "Название"
     */
    @FindBy(xpath = "//div[@id='editModal']//div[@class='modal-body']//div/input[@id='name']")
    private WebElement addProductName;
    /**
     * Выпадающий список "Тип"
     */
    @FindBy(xpath = "//div[@id='editModal']//div[@class='modal-body']//div/select[@id='type']")
    private WebElement addType;
    /**
     * Чек-бокс "Экзотический"
     */
    @FindBy(xpath = "//div[@id='editModal']//div[@class='modal-body']//div/input[@id='exotic']")
    private WebElement addExoticCheckBox;
    /**
     * Кнопка "Сохранить"
     */
    @FindBy(xpath = "//div[@id='editModal']//div[@class='modal-footer']/button[@id='save']")
    private WebElement saveButton;

    public ProductsPage(WebDriver chromeDriver) {
        PageFactory.initElements(chromeDriver, this);
        this.chromeDriver = chromeDriver;
    }

    public WebElement getTableName() {
        return tableName;
    }

    public List<WebElement> getTableHeads() {
        return tableHeads;
    }

    public List<WebElement> getTableRows() {
        return tableRows;
    }

    public WebElement getAddButton() {
        return addButton;
    }

    public WebElement getAddProductHeader() {
        return addProductHeader;
    }

    public WebElement getAddProductName() {
        return addProductName;
    }

    public WebElement getAddType() {
        return addType;
    }

    public WebElement getAddExoticCheckBox() {
        return addExoticCheckBox;
    }

    public WebElement getSaveButton() {
        return saveButton;
    }

    public void setAddType(String string) {
        getAddType().findElement(By.xpath("//option[text()='"+string+"']")).click();

    }
}
