package com.lemonfish;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * @author Chao Zhang
 * @version V1.0 安全版
 * @Package com.lemonfish.disease
 * @date 2020/3/26 16:34
 */
public class OldDiseaseCard {
    // 个人信息
    public static final String USERNAME = "your username";
    public static final String PASSWORD = "your password";
    public static final String QUERY_URL = "https://www.wjx.cn/jq/61862730.aspx";
    public static final String NAME = "your name";
    public static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";

    public static void main(String[] args) throws InterruptedException {
//209304
        Scanner in = new Scanner(System.in);

        // 需要填写的信息
//        System.out.println("请输入你的名字");
//        String name = in.next();
        System.out.println("请输入你的体温(36.X)的X，即一位");
        String temperature = in.next();
//        System.out.println("请输入你云成长的帐号");
//        String username = in.next();
//        System.out.println("请输入你云成长的密码");
//        String password = in.next();
//        System.out.println("因为每个班级的问卷调查网址不同，请输入问卷调查完整网址（建议复制）:\n\r(软件1809班同学输入 9 即可)");
//        String url = in.next();
//        if ("9".equals(url)) {
//            url = ;
//        }
        autoCompleteForm(NAME, temperature, USERNAME, PASSWORD, QUERY_URL);

    }

    private static void autoCompleteForm(String name, String temperature, String username, String password, String url) throws InterruptedException {
        // 设置webdriver路径
        System.setProperty(WEBDRIVER_CHROME_DRIVER, OldDiseaseCard.class.getClassLoader().getResource("chromedriver.exe").getPath());

        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get(url);
        HashMap<String, String> map = new HashMap<>();

        // 问卷调查
        webDriver.findElement(By.xpath("//*[@id=\"divquestion1\"]/ul/li[1]/a")).click();
        webDriver.findElement(By.id("q2")).click();
        webDriver.switchTo().frame("PDF_i_chezchenz");
        webDriver.findElement(By.className("search-txt")).sendKeys(name);
        webDriver.findElement(By.className("item2")).click();
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.className("underline")).sendKeys(temperature);
        webDriver.findElement(By.id("q4")).click();
        webDriver.switchTo().frame("__calendarIframe");
        webDriver.findElement(By.id("selectTodayButton")).click();
        webDriver.switchTo().defaultContent();
        webDriver.findElement(By.xpath("//*[@id=\"divquestion5\"]/ul/li[1]/a")).click();
        webDriver.findElement(By.xpath("//*[@id=\"divquestion7\"]/ul/li[5]/a\n")).click();
//         停止15-20S，模拟自己填写的情况
        Random rand = new Random();
        long doneTime = rand.nextInt(2000)+10000;
        Thread.sleep(doneTime);
        webDriver.findElement(By.className("submitbutton")).click();

        Thread.sleep(1000);


        // 云成长
        /*webDriver.get("http://stuinfo.neu.edu.cn/#/");
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[3]/div[2]/div[1]/input")).sendKeys(username);
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[3]/div[2]/div[2]/div/input")).sendKeys(password);
        webDriver.findElement(By.className("loginButton")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/section/aside/div/ul/li[2]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/section/section/section/section/main/div[1]/div[1]/div/div[2]/div/div/div")).click();
        Thread.sleep(2000);
        String originWindow = webDriver.getWindowHandle();
        for(String winHandle : webDriver.getWindowHandles()) {
            System.out.println(winHandle);
            if (winHandle.equals(originWindow)) {
                continue;
            }
            webDriver.switchTo().window(winHandle);
            break;
        }
        Thread.sleep(3000);
        WebElement select = webDriver.findElement(By.id("sfgcyiqz"));
        Select select1 = new Select(select);
        select1.selectByValue("否");
        webDriver.findElement(By.name("hjnznl")).sendKeys("呆在家里");
        Thread.sleep(200);
        webDriver.findElement(By.name("qgnl")).sendKeys("没有去过任何地方");
        Thread.sleep(200);
        String[] fou = {"sfqtdqlxs", "sfjcgbr", "sfjcglxsry", "sfjcgysqzbr", "sfjtcyjjfbqk", "sfqgfrmz","sfygfr","sfyghxdbsy","sfygxhdbsy"};
        for (int i = 0; i < 9; i++) {
            Select select2 = new Select(webDriver.findElement(By.id(fou[i])));
            if (i < 6) {
                select2.selectByValue("否");
            }else {
                select2.selectByValue("无");
            }
        }
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("/html/body/div/div/div/button")).click();*/
    }
}
