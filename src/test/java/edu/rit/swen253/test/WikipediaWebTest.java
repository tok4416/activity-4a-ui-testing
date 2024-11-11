package edu.rit.swen253.test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import edu.rit.swen253.utils.SeleniumUtils;
import edu.rit.swen253.utils.TimingUtils;


import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;

import java.util.List;
import java.util.logging.Logger;

import edu.rit.swen253.page.WikipediaSearch;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WikipediaWebTest extends AbstractWebTest{

    private static final Logger logger = Logger.getLogger(WikipediaSearch.class.getName());
    private static final String wikipedia_url = "https://wikipedia.org";

    private WikipediaSearch wikipediaSearch;


     /**
     * This tests going to wikipediaHomePage to search
     */
    @Test
    @Order(1)
    public void goToHomePage(){
        wikipediaSearch = navigateToPage(wikipedia_url, WikipediaSearch::new);
        wikipediaSearch.goToHomePage();
        wikipediaSearch.closeDonatePopup();
    }

     /**
     * This tests searches in the search bar. In this case we are testing search is for Antoine
     * Also shows results of the search of Antoine (Can be any other search)
     */
    @Test
    @Order(2)
    public void searchTest(){

        wikipediaSearch = navigateToPage(wikipedia_url, WikipediaSearch::new);
 
        try {
            TimingUtils.sleep(3);
            wikipediaSearch.searchTextInBar("Antoine");
            TimingUtils.sleep(5);

            wikipediaSearch.searchSubmission();
            wikipediaSearch.clickFirstResult();
        } catch (TimeoutException e) {
            logger.severe("Failed to locate search field or submit search.");
            return;
        }

        TimingUtils.sleep(3); // Wait to for results to load

        List<String[]> searchResults = wikipediaSearch.listSearchResults();
        if (searchResults.isEmpty()) {
            logger.warning("No search results found.");
            return;
        }

        logger.info("Search Results:");
        for (String[] result : searchResults) {
            String title = result[0];
            String url = result[1];
            logger.info("Title: " + title + ", URL: " + url);
        }

        try {
            wikipediaSearch.clickFirstResult();

            TimingUtils.sleep(3); // wait to navigate to click first result

            WebDriver driver = SeleniumUtils.getDriver();
            String currentUrl = driver.getCurrentUrl();
            String expectedUrl = searchResults.get(0)[1];

            if (currentUrl.equals(expectedUrl)) {
                logger.info("Navigation to first result successful. URL: " + currentUrl);
            } else {
                logger.warning("Navigation failed. Expected: " + expectedUrl + " but got: " + currentUrl);
            }
        } catch (Exception e) {
            logger.severe("Failed to click on the first search result or validate the URL.");
        }
    }

    

     /**
     * This test tests log all search results on the searched page.
     */
    @Test
    @Order(3)
    public void logSearchResults() {
        List<String[]> results = wikipediaSearch.listSearchResults();
        for (String[] result : results) {
            String title = result[0];
            String url = result[1];
            logger.info("\nTitle: " + title + " URL: " + url + "\n");
        }
    }

}
