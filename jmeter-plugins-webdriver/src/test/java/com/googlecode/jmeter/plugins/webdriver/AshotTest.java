package com.googlecode.jmeter.plugins.webdriver;

import com.googlecode.jmeter.plugins.webdriver.config.RemoteBrowser;
import com.googlecode.jmeter.plugins.webdriver.config.RemoteDriverConfig;
import com.googlecode.jmeter.plugins.webdriver.ui.DocumentSettleCondition;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import ru.yandex.qatools.ashot.shooting.SimpleShootingStrategy;

import javax.imageio.ImageIO;
import java.io.File;
import java.time.Duration;

public class AshotTest extends RemoteDriverConfig {


    @Test
    public void testTakeFullScreenshot() throws Exception {
        setSeleniumGridUrl("http://172.16.200.15:4444");
        setSelectedBrowser(RemoteBrowser.CHROME);

        RemoteWebDriver driver = createBrowser();
        //navigate to url
        driver.get("https://www.ruanyifeng.com/blog/2024/03/weekly-issue-291.html");
        driver.manage().window().setSize(new Dimension(1920, 1080));
        // capture screenshot and store the image
        Screenshot s = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(100)).takeScreenshot(driver);
        ImageIO.write(s.getImage(), "PNG", new File("/tmp/fullPageScreenshot.png"));

        //closing the webdriver
        driver.close();
    }

    @Test
    public void testTakeSimpleScreenshot() throws Exception {
        setSeleniumGridUrl("http://172.16.200.15:4444");
        setSelectedBrowser(RemoteBrowser.CHROME);

        RemoteWebDriver driver = createBrowser();
        // 隐式等待
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        //navigate to url
//        driver.get("https://www.ruanyifeng.com/blog/2024/03/weekly-issue-291.html");
        driver.get("http://101.43.186.75:8081/#/bug-management/index?organizationId=100001&projectId=1025088434552832");
        driver.manage().window().setSize(new Dimension(1920, 1080));
        // 等待浏览器加载完成
        DocumentSettleCondition settleCondition = new DocumentSettleCondition(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
        new WebDriverWait(driver, Duration.ofSeconds(300))
                .until(settleCondition);


        // capture screenshot and store the image
        Screenshot s = new AShot()
                .shootingStrategy(new SimpleShootingStrategy())
                .takeScreenshot(driver);

        ImageIO.write(s.getImage(), "PNG", new File("/tmp/visiblePageScreenshot.png"));

        //closing the webdriver
        driver.close();
    }

    @Test
    public void testTakePartScreenshot() throws Exception {
        setSeleniumGridUrl("http://172.16.200.15:4444");
        setSelectedBrowser(RemoteBrowser.CHROME);

        RemoteWebDriver driver = createBrowser();
        //navigate to url
//        driver.get("https://www.ruanyifeng.com/blog/2024/03/weekly-issue-291.html");
        driver.manage().window().setSize(new Dimension(1920, 1080));
        driver.get("http://101.43.186.75:8081/#/bug-management/index?organizationId=100001&projectId=1025088434552832");

        // 等待浏览器加载完成
        DocumentSettleCondition settleCondition = new DocumentSettleCondition(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("body")));
        new WebDriverWait(driver, Duration.ofSeconds(300))
                .until(settleCondition);

        // capture screenshot and store the image
        WebElement element = driver.findElement(By.className("arco-form"));

        Screenshot s = new AShot()
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(driver, element);

        ImageIO.write(s.getImage(), "PNG", new File("/tmp/elementScreenshot.png"));

        //closing the webdriver
        driver.close();
    }
}
