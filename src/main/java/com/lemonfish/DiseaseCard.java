package com.lemonfish;
import	java.util.Scanner;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * @author Chao Zhang
 * @version V1.0 安全版
 * @Package com.lemonfish.disease
 * @date 2020/3/26 16:34
 */
public class DiseaseCard {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        // 需要填写的信息
        System.out.println("请输入你的名字");
        String name = in.next();
        System.out.println("请输入你的体温(36.X)的X，即一位");
        String temperature = in.next();
        System.out.println("请输入你云成长的帐号");
        String username = in.next();
        System.out.println("请输入你云成长的密码");
        String password = in.next();


        // 设置webdriver路径
        System.setProperty("webdriver.chrome.driver", DiseaseCard.class.getClassLoader().getResource("chromedriver.exe").getPath());
        WebDriver webDriver = new ChromeDriver();
        webDriver.get("https://www.wjx.cn/jq/61862730.aspx");


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
        webDriver.findElement(By.className("submitbutton")).click();
        Thread.sleep(2000);


        // 云成长
        webDriver.get("http://stuinfo.neu.edu.cn/#/");
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[3]/div[2]/div[1]/input")).sendKeys(username);
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/div/div[1]/div[3]/div[2]/div[2]/div/input")).sendKeys(password);
        webDriver.findElement(By.className("loginButton")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/section/aside/div/ul/li[2]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/section/section/section/section/main/div[1]/div[1]/div/div[2]/div/div/div")).click();
        Thread.sleep(2000);
        String originWindow = webDriver.getWindowHandle();
        for(String winHandle : webDriver.getWindowHandles()) {
            if (winHandle.equals(originWindow)) {
                continue;
            }
            webDriver.switchTo().window(winHandle);
            break;
        }
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
        Thread.sleep(20000);
        webDriver.findElement(By.xpath("/html/body/div/div/div/button")).click();
    }
}
