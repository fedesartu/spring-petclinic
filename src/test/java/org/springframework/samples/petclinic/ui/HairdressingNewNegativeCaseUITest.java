package org.springframework.samples.petclinic.ui;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HairdressingNewNegativeCaseUITest {

	@LocalServerPort
	private int port;

	private WebDriver driver;
	private String baseUrl;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@BeforeEach
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://localhost:" + port;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	  public void testNewHairdressingNegativeCase() throws Exception {
	    driver.get(baseUrl);
		driver.findElement(By.linkText("USER")).click();
		driver.findElement(By.linkText("LOGIN")).click();
	    driver.findElement(By.id("username")).click();
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("george");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("george");
	    driver.findElement(By.id("password")).sendKeys(Keys.ENTER);
	    driver.findElement(By.linkText("RESERVATIONS")).click();
		driver.findElement(By.linkText("HAIRDRESSINGS")).click();
	    driver.findElement(By.linkText("Add hairdressing")).click();
	    driver.findElement(By.xpath("//form[@id='add-hairdressing-form']/div")).click();
	    driver.findElement(By.id("date")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.linkText("30")).click();
	    driver.findElement(By.id("description")).click();
	    driver.findElement(By.id("description")).clear();
	    driver.findElement(By.id("description")).sendKeys("Test");
	    new Select(driver.findElement(By.id("time"))).selectByVisibleText("7:00");
	    driver.findElement(By.xpath("//option[@value='7:00']")).click();
	    new Select(driver.findElement(By.id("cuidado"))).selectByVisibleText("ESTETICA");
	    driver.findElement(By.xpath("//option[@value='ESTETICA']")).click();
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    driver.findElement(By.linkText("Add hairdressing")).click();
	    driver.findElement(By.id("date")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.xpath("//div[@id='ui-datepicker-div']/div/a[2]/span")).click();
	    driver.findElement(By.linkText("30")).click();
	    driver.findElement(By.id("description")).click();
	    driver.findElement(By.id("description")).clear();
	    driver.findElement(By.id("description")).sendKeys("Test");
	    new Select(driver.findElement(By.id("time"))).selectByVisibleText("7:00");
	    driver.findElement(By.xpath("//option[@value='7:00']")).click();
	    new Select(driver.findElement(By.id("cuidado"))).selectByVisibleText("ESTETICA");
	    driver.findElement(By.xpath("//option[@value='ESTETICA']")).click();
	    driver.findElement(By.xpath("//button[@type='submit']")).click();
	    assertEquals("This time has already been taken. Please, select another time", driver.findElement(By.xpath("//form[@id='add-hairdressing-form']/div/div[3]/div/div/span[2]")).getText());
	  }

	  @AfterEach
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	}
