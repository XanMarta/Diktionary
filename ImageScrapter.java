import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;


public class ImageScrapter {

    public WebDriver driver = null;
    public boolean isReady = false;

    public void initScrapter() {
        new Thread() {
            public void run() {
                DesiredCapabilities capabilities = DesiredCapabilities.chrome();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--incognito");
                options.addArguments("--headless");
                capabilities.setCapability(ChromeOptions.CAPABILITY, options);
                System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
                driver = new ChromeDriver(capabilities);
                driver.get("https://www.google.com/search?hl=EN&tbm=isch&source=hp&biw=1536&bih=722&ei=qeOGX9SJN8vdz7sP-fSNqAs&q=a&oq=a&gs_lcp=CgNpbWcQAzIFCAAQsQMyAggAMgUIABCxAzIFCAAQsQMyAggAMgUIABCxAzIICAAQsQMQgwEyAggAMgUIABCxAzICCABQogxYogxgzxNoAHAAeACAAf8DiAH_A5IBAzUtMZgBAKABAaoBC2d3cy13aXotaW1nsAEA&sclient=img&ved=0ahUKEwiUgv36_7PsAhXL7nMBHXl6A7UQ4dUDCAY&uact=5");
                isReady = true;
            }
        }.start();
    }

    public BufferedImage[] getImage(String word, int number) {
        WebElement searchBar = driver.findElement(By.ByName.name("q"));
        searchBar.clear();
        searchBar.sendKeys(word);
        searchBar.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.ByXPath.xpath("/html/body/div[2]/c-wiz/div[3]/div[1]/div/div/div/div/div[1]/div[1]/div[1]/a[1]/div[1]/img")));

        BufferedImage[] result = new BufferedImage[number + 1];
        for (int i = 1; i <= number ; i++) {
            try {
                String code = driver.findElement(By.ByXPath.xpath("/html/body/div[2]/c-wiz/div[3]/div[1]/div/div/div/div/div[1]/div[1]/div[" + i + "]/a[1]/div[1]/img"))
                        .getAttribute("src");
                result[i] = decodeImage(code);
            } catch (Exception e) { }
        }

        return result;
    }


    public BufferedImage decodeImage(String code) {
        try {
            if (code.contains("data:image/jpeg;base64,")) {
                code = code.replaceFirst("data:image/jpeg;base64,", "");
                byte[] imageByte = Base64.getMimeDecoder().decode(code);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                BufferedImage image = ImageIO.read(bis);
                bis.close();
                return image;
            } else {
                return ImageIO.read(new URL(code));
            }
        } catch (IOException e) {
            return null;
        }
    }

}
