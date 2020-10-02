import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class seleTranslator {

    public void seleTranslate(String word) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://translate.google.com/#view=home&op=translate&sl=en&tl=vi");
        driver.manage().deleteAllCookies();
        driver.findElement(By.ById.id("source")).sendKeys(word);
    }

}
