package com.lemonfish;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Chao Zhang
 * @version V1.0 安全版
 * @Package com.lemonfish.disease
 * @date 2020/3/26 16:34
 */
public class NewDiseaseCard {
    // 个人信息
    public static final String USERNAME = "your username";
    public static final String PASSWORD = "your password";
    public static final String QUERY_URL = "https://www.wjx.cn/jq/61862730.aspx";
    public static final String NAME = "your name";
    public static final String WEBDRIVER_CHROME_DRIVER = "webdriver.chrome.driver";

    public static void main(String[] args) throws InterruptedException {
//        Scanner in = new Scanner(System.in);
//        System.out.println("请输入你的体温(36.X)的X，即一位");
//        String temperature = in.next();

        autoCompleteForm(NAME, "0", USERNAME, PASSWORD, QUERY_URL);

    }

    private static void autoCompleteForm(String name, String temperature, String username, String password, String url) throws InterruptedException {
        // 设置webdriver路径
        System.setProperty(WEBDRIVER_CHROME_DRIVER, NewDiseaseCard.class.getClassLoader().getResource("chromedriver.exe").getPath());

        WebDriver webDriver = new ChromeDriver();
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        webDriver.get(url);

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
//         停止8-12S，模拟自己填写的情况
        Random rand = new Random();
        long doneTime = rand.nextInt(4000)+8000;
        Thread.sleep(doneTime);
        webDriver.findElement(By.className("submitbutton")).click();

        Thread.sleep(1000);


        // 师生健康信息上报平台
        webDriver.get("https://ehall.neu.edu.cn/db_portal/guide?id=D06BDA87-2E6E-4324-A14D-3BFAD839F2B9");

        webDriver.findElement(By.className("btn-blue")).click();

        // 跳转新Tab
        String originWindow = webDriver.getWindowHandle();
        for(String winHandle : webDriver.getWindowHandles()) {
            if (winHandle.equals(originWindow)) {
                continue;
            }
            webDriver.switchTo().window(winHandle);
            break;
        }

        webDriver.findElement(By.id("un")).sendKeys(USERNAME);
        webDriver.findElement(By.id("pd")).sendKeys(PASSWORD);
        webDriver.findElement(By.id("index_login_btn")).click();

        webDriver.findElement(By.xpath("//*[@id=\"app\"]/main/div/form/div[1]/table/tbody/tr/td[1]/div/div/div/label[1]/span[1]/span")).click();
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/main/div/form/div[3]/div[2]/table/tbody/tr[1]/td/div/div/div/label[1]/span[1]/span")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/main/div/form/div[4]/div[2]/table/tbody/tr[1]/td/div/div/div/label[1]/span[1]/span")).click();
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/main/div/form/div[5]/div[2]/div/div/div/textarea")).sendKeys("大家辛苦啦 !!!");
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"app\"]/main/div/form/div[6]/button")).click();
    }
}
