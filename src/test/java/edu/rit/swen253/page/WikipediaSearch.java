package edu.rit.swen253.page;
import edu.rit.swen253.utils.DomElement;

import edu.rit.swen253.utils.SeleniumUtils;
import edu.rit.swen253.utils.TimingUtils;



import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;


public class WikipediaSearch extends AbstractPage {
    
    private DomElement textBar;
    private DomElement searchButton;

    private static final By SEARCH_BOX = By.id("searchInput");
    private static final By SEARCH_BUTTON = By.id("searchButton");
    private static final By SEARCH_RESULTS = By.cssSelector(".mw-search-result-heading a");
    
    private static final Logger logger = Logger.getLogger(WikipediaSearch.class.getName());


    /**
     * Initalizing WikipediaSearch Constructor
    */
    public WikipediaSearch(){ 
        super();
    }

    /**
     * This function closes the Donation Popup that Wikipedia sometimes puts when loading into their homepage
    */
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
    /**
     * This function gets the driver and goes the to the wikipedia homepage
    */
    public void goToHomePage(){
        SeleniumUtils.getDriver().get("https://wikipedia.org");
    }

    /**
     * This function clicks on the search textbox and enters text for search to happen 
     *  when text is in the textbox shows a list of suggestions 
     */
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

    /**
     * This function does search submission of the search text in the search text box field
     * If there are no search results showing when there is search text in the text box field than the search button will be clicked
     * and lead to an empty page
    */
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

    /**
     * This function returns String[] List that returns Search results of based on the search text results
     * 
    */
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
        return results; 
    }
        return results;
    }
     /**
     * This function clicks the First Result on the list of search Results when you have put the search text in
     * Other wise it triggers exceptions
     */
    public void clickFirstResult() {
        try {
            
            TimingUtils.sleep(3); // Wait for things to load
    
            WebElement suggestionHighlight = SeleniumUtils.getDriver().findElement(By.cssSelector(".suggestion-highlight"));
    
            if (suggestionHighlight != null && suggestionHighlight.isDisplayed()) {
                suggestionHighlight.click();
                logger.info("Clicked on the suggestion: Antoine");
            } else {
                logger.severe("Suggestion highlight has not been loaded yet");
            }


        } catch (TimeoutException e) {
            System.out.println("Timeout Exception has occurred");
        } catch (Exception e) {
            System.out.println("Exception has occurred cannot click on result");
        }
    }

}
