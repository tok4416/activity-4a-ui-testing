package edu.rit.swen253.page;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.HtmlUtils;
import edu.rit.swen253.utils.SeleniumUtils;
import edu.rit.swen253.utils.TimingUtils;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


public class WikipediaSearch extends AbstractPage {
    
    // private DomElement searchIcon;
    private DomElement textBar;
    private DomElement searchButton;

    private static final By RESULT_LINKS = By.cssSelector("a[href^='/wiki/']");  // Adjust CSS selector as needed
    private static final By SEARCH_BOX = By.id("searchInput");
    private static final By SEARCH_BUTTON = By.id("searchButton");
    private static final By SEARCH_RESULTS = By.cssSelector(".mw-search-result-heading a");
    
    public WikipediaSearch(){ 
        super();
    }

    public void closeDonatePopup() {
        try {
            Thread.sleep(2000);  

            WebElement minimizeButton = SeleniumUtils.getDriver().findElement(By.className("frb-header-minimize"));
            minimizeButton.click();
    
            WebElement closeButton = SeleniumUtils.getDriver().findElement(By.className("frb-conversation-close"));
            closeButton.click();

        } catch (NoSuchElementException e) {
            System.out.println("NoSuchElement Exception has occurred");
        } catch (InterruptedException e) {
            System.out.println("Interrupted Exception has occured");
        } catch (Exception e) {
            System.out.println("Exception has occurred");
        }
    }
    
    public void goToHomePage(){
        SeleniumUtils.getDriver().get("https://wikipedia.org");
    }

    public void searchTextInBar(String text){

        try {
            textBar = findOnPage(SEARCH_BOX);
            if (textBar == null) {
                System.out.println("Search bar not found.");
                return;
            }
            textBar.click();
            textBar.enterText(text);
        } catch (TimeoutException e) {
            System.out.println("Search Bar could not be found");
        }
    }

    public void searchSubmission(){
        try {
            searchButton = findOnPage(SEARCH_BUTTON);
            if (searchButton == null) {
                return;                         
            }
            searchButton.click();
        } catch (TimeoutException e) {
            System.out.println("Could not find Search button");
        }
    }

    public List<String[]> listSearchResults() {
        
        List<String[]> results = new ArrayList<>();
    try {
        TimingUtils.sleep(10); 

        List<DomElement> searchResults = findAllOnPage(SEARCH_RESULTS);

        for (DomElement element : searchResults) {
            try {
                String title = element.getText();
                String url = element.getAttribute("href");
                results.add(new String[] { title, url });

            } catch (StaleElementReferenceException e) {
                System.out.println("Stale element reference encountered");
            }
        }
    } catch (StaleElementReferenceException e) {
        System.out.println("StaleElementReference Exception has been encountered");
        return listSearchResults(); 
    }
        return results;
    }

    public void clickFirstResult() {
        try {
            List<WebElement> searchResults = SeleniumUtils.getDriver().findElements(RESULT_LINKS);
            
            if (searchResults.isEmpty()) {
                return;
            }
            WebElement firstResult = searchResults.get(0);
            System.out.println("Clickedfirst search result: " + firstResult.getText());
            firstResult.click();

        } catch (TimeoutException e) {
            System.out.println("Timeout Exception has occurred");
        } catch (Exception e) {
            System.out.println("Exception has occurred cannot click on result");
        }
    }

}
