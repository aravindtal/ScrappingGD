package com.scrapper.gd;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by aravindp on 17/3/16.
 */
public class GdScrapper {

    public static void main(String args[]) throws Exception {
        System.setProperty("webdriver.chrome.driver", "/home/aravindp/Softwares/chromedriver");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.glassdoor.co.in/Job/pune-jobs-SRCH_IL.0,4_IC2856202.htm");
        Thread.sleep(5000);  // Let the user actually see something!

        WebElement searchBox = driver.findElement(By.id("sc.location"));
        searchBox.clear();
        searchBox.sendKeys("Houston, TX (US)");
        searchBox.sendKeys(Keys.RETURN);
        Thread.sleep(5000);  // Let the user actually see something!

        String currentUrl = driver.getCurrentUrl();
        WebElement Pages = driver.findElement(By.xpath("//*[@id='ResultsFooter']/div[2]/span"));
        String noPages = Pages.getText().split(" ")[2].replaceAll(",","");

        List<JobObject> jobListings = new LinkedList<>();
        int noOfPages = Integer.parseInt(noPages);
        for(int i=0;i< noOfPages;i++) {
            List<WebElement> jlList = driver.findElements(By.className("jobListing"));
            for (WebElement jl : jlList) {
                String jobId = jl.getAttribute("data-id");
                JobObject job = new JobObject();
                job.setId(jobId);
                try {
                    WebElement jobname = driver.findElement(By.xpath("//*[@data-id='" + jobId + "']/div[2]/div/div/span"));
                    job.setName(jobname.getText());
                } catch (Exception e) {

                }

                try {
                    WebElement compname = driver.findElement(By.xpath("//*[@data-id='" + jobId + "']/div[2]/div/div[2]/div[1]/span"));
                    job.setCompany(compname.getText());
                } catch (Exception e) {

                }

                try {
                    WebElement compstars = driver.findElement(By.xpath("//*[@data-id='" + jobId + "']/div[2]/div/div[2]/div[2]/span"));
                    job.setCompanyRating(compstars.getText());
                } catch (Exception e) {

                }

                try {
                    WebElement compreviews = driver.findElement(By.xpath("//*[@data-id='" + jobId + "']/div[2]/div/div[2]/div[2]/span[2]"));
                    job.setCompanyReview(compreviews.getText());
                } catch (Exception e) {

                }

                jobListings.add(job);
                System.out.println("Index : "+(i+1));
                //System.out.println(jobId + " " + job.getName() + " " + job.getCompany() + " " + job.getCompanyRating() + " " + job.getCompanyReview());
            }
            Thread.sleep(5000);  // Let the user actually see something!
            currentUrl = currentUrl.substring(0,currentUrl.lastIndexOf("."))+"_IP"+(i+1)+".htm";
            driver.get(currentUrl);
            Thread.sleep(10000);
        }


        Thread.sleep(5000);  // Let the user actually see something!
        driver.quit();
    }

}
