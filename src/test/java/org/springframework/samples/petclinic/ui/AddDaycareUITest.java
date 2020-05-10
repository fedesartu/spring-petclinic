package org.springframework.samples.petclinic.ui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.samples.petclinic.model.Daycare;
import org.springframework.samples.petclinic.model.Pet;
import org.springframework.samples.petclinic.service.PetService;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AddDaycareUITest {

	@LocalServerPort
	private int port;

	@Autowired
	PetService petService;

	private String user;
	private Daycare daycare;

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
	public void testAddDaycareUI() throws Exception {
		driver.get("http://localhost:" + port);
		as("george", "george").whenIamLoggedIntheSystem().openMyDaycares().addDaycare().openMyDaycares()
				.thenDaycareIsInUserDaycaresTable();
	}

	private AddDaycareUITest thenDaycareIsInUserDaycaresTable() {
		try {
			assertEquals(this.daycare.getDate().toString(),
					driver.findElement(By.xpath("//table[@id='daycaresTable']/tbody/tr[3]/td[1]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(this.daycare.getDescription(),
					driver.findElement(By.xpath("//table[@id='daycaresTable']/tbody/tr[3]/td[2]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(this.daycare.getCapacity().toString(),
					driver.findElement(By.xpath("//table[@id='daycaresTable']/tbody/tr[3]/td[3]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}
		try {
			assertEquals(this.daycare.getPet().getName(),
					driver.findElement(By.xpath("//table[@id='daycaresTable']/tbody/tr[3]/td[4]")).getText());
		} catch (Error e) {
			verificationErrors.append(e.toString());
		}

		return this;

	}

	private AddDaycareUITest whenIamLoggedIntheSystem() {
		return this;
	}

	private AddDaycareUITest addDaycare() {
		this.initTraining();
		
		// Coge el ultimo dia del mes
		Calendar calendar = Calendar.getInstance();
		int year = LocalDate.now().getYear();
		int month = LocalDate.now().getMonthValue();
		int date = 1;
		calendar.set(year, month, date);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		String ndays= Integer.toString(days);

		driver.findElement(By.linkText("Add daycare")).click();
		driver.findElement(By.id("date")).click();
		driver.findElement(By.linkText(ndays)).click();
		driver.findElement(By.id("description")).click();
		driver.findElement(By.id("description")).clear();
		driver.findElement(By.id("description")).sendKeys("Daycare description");
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		return this;
	}

	private void initTraining() {
		List<Pet> pets = this.petService.findPetsByOwner(this.user);
		this.daycare = new Daycare();
		
		// Coge el ultimo dia del mes em el que estamos
		Calendar calendar = Calendar.getInstance();
		int year = LocalDate.now().getYear();
		int month = LocalDate.now().getMonthValue();
		int date = 1;
		calendar.set(year, month, date);
		int days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		this.daycare.setDate(LocalDate.of(year, month, days));
		this.daycare.setDescription("Daycare description");
		this.daycare.setCapacity(10);
		this.daycare.setPet(pets.get(0));

	}

	private AddDaycareUITest openMyDaycares() {
		driver.findElement(By.xpath("//div[@id='main-navbar']/ul/li[3]/a")).click();
	    driver.findElement(By.xpath("//a[contains(@href, '/daycares/owner')]")).click();
		return this;
	}

	private AddDaycareUITest as(String user, String password) {
		this.user = user;

		driver.findElement(By.xpath("//div[@id='main-navbar']/ul/li[5]/a")).click();
		driver.findElement(By.xpath("//a[contains(text(),'Login')]")).click();
	    driver.findElement(By.id("username")).clear();
	    driver.findElement(By.id("username")).sendKeys("george");
	    driver.findElement(By.id("password")).click();
	    driver.findElement(By.id("password")).clear();
	    driver.findElement(By.id("password")).sendKeys("george");
	    driver.findElement(By.xpath("//button[@type='submit']")).click();

		return this;
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
