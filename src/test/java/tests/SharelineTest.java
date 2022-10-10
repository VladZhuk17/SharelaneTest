package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class SharelineTest {
    /*
    1) Регистрация на сайте с вводом валидных значений;
    2) Регистрация на сайте с вводом невалидной электронной почты;
    3) Регистрация на сайте с заполнением цифр в поля "First Name" и "Last Name";
     */
    WebDriver driver;

    @Test
    public void verifyRegistrationAllFields() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/");
        driver.findElement(By.xpath("//b[text()='ENTER']")).click();
        driver.findElement(By.xpath("//a[text()='Sign up']")).click();
        driver.findElement(By.xpath("//input[@name='zip_code']")).sendKeys("11111");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("Vlad");
        driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys("Alexandrov");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("vladtut93.by@gmail.com");
        driver.findElement(By.xpath("//input[@name='password1']")).sendKeys("0123456789");
        driver.findElement(By.xpath("//input[@name='password2']")).sendKeys("0123456789");
        driver.findElement(By.xpath("//input[@value='Register']")).click();
        String actualMassage = driver.findElement(By.xpath("//span[text()='Account is created!']")).getText();
        Assert.assertEquals(actualMassage, "Account is created!");
    }

    @Test
    public void verifyRegistrationInvalidFirstNameAndSecondName() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.xpath("//input[@name='zip_code']")).sendKeys("22222");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("Misha");
        driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys("Volkov");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("qwesdasd1234dasd@asdad");
        driver.findElement(By.xpath("//input[@name='password1']")).sendKeys("0123456789");
        driver.findElement(By.xpath("//input[@name='password2']")).sendKeys("0123456789");
        driver.findElement(By.xpath("//input[@value='Register']")).click();
        String actualMassage = driver.findElement(By.xpath("/html/body/center/table/tbody/tr[4]/td/span")).getText();
        Assert.assertEquals(actualMassage, "Oops, error on page. Some of your fields have " +
                "invalid data or email was previously used");
    }

    @Test
    public void verifyRegistration() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.xpath("//input[@name='zip_code']")).sendKeys("11111");
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        driver.findElement(By.xpath("//input[@name='first_name']")).sendKeys("32312312");
        driver.findElement(By.xpath("//input[@name='last_name']")).sendKeys("234234");
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys("vladtut93.by@gmail.com");
        driver.findElement(By.xpath("//input[@name='password1']")).sendKeys("0123456789");
        driver.findElement(By.xpath("//input[@name='password2']")).sendKeys("0123456789");
        driver.findElement(By.xpath("//input[@value='Register']")).click();
        String actualMassage = driver.findElement(By.xpath("//span[@class='error_message']")).getText();
        Assert.assertEquals(actualMassage, "Oops, error on page. Some of your fields have" +
                " invalid data or email was previously used");
    }
    @AfterClass
    public void closeTest() {
        driver.quit();
            }
}
