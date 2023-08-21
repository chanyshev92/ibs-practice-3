package ru.ibs.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {

    protected WebDriver chromeDriver;

    @FindBy(xpath = "//div[@class='container-fluid']/h5")
    private WebElement tableName;

    @FindBy(xpath = "//div/h5/../table/thead//th")
    private List<WebElement> tableHeads;

    @FindBy(xpath = "//div/h5/../table/tbody//tr")
    private List<WebElement> tableRows;

    @FindBy(xpath = "//button[@data-toggle='modal']")
    private WebElement addButton;

    @FindBy(xpath = "//div[@id='editModal']//div[@class='modal-header']/h5")
    private WebElement addProductHeader;

    @FindBy(xpath ="//div[@id='editModal']//div[@class='modal-body']//div/input[@id='name']" )
    private WebElement addProductName;

    @FindBy(xpath = "//div[@id='editModal']//div[@class='modal-body']//div/select[@id='type']")
    private WebElement addType;

    @FindBy(xpath = "//div[@id='editModal']//div[@class='modal-body']//div/input[@id='exotic']")
    private WebElement addExoticCheckBox;

    @FindBy(xpath = "//div[@id='editModal']//div[@class='modal-footer']/button[@id='save']")
    private WebElement saveButton;

    protected WebDriverWait wait;

    public ProductsPage(WebDriver chromeDriver) {
        PageFactory.initElements(chromeDriver,this);
        this.chromeDriver = chromeDriver;
        this.wait=new WebDriverWait(chromeDriver, 10);
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

    public void setAddProductName(String string){
        addProductName.sendKeys(string);
    }
}
