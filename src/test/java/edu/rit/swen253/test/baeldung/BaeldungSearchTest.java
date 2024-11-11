package edu.rit.swen253.test.baeldung;

import java.util.Map;
import java.util.logging.Logger;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.bidi.log.LogLevel;

import edu.rit.swen253.page.baeldung.BaeldungSearch;
import edu.rit.swen253.page.baeldung.BaeldungSearchResults;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.test.sample.RitExploreStudiesInComputingTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaeldungSearchTest extends AbstractWebTest {
    private static final Logger logger = Logger.getLogger(RitExploreStudiesInComputingTest.class.getName());
    private BaeldungSearch homePage;
    private BaeldungSearchResults resultsPage;

    /*
     * First, navigate to the search page from the home page
     */
    @Test
    @Order(1)
    public void goToSearchPage() {
        homePage = navigateToPage("https://baeldung.com", BaeldungSearch::new);
        homePage.goToSearchPage();
    }

    /*
     * Second, enter a search term and submit, allowing the site to fetch results
     */
    @Test
    @Order(2)
    public void enterSearch() {
        homePage.enterSearchText("page object model");
        homePage.submitSearch();
        // constructing the page to view search results
        resultsPage = navigateToPage("https://baeldung.com/?s=page+object+model", BaeldungSearchResults::new);
    }

    /*
     * Third, log all the search results on the page
     */
    @Test
    @Order(3)
    public void logSearchResults() {
        Map<String, String> resultTitleToURL = resultsPage.getSearchResults(); // apparently this is stale? yes, actually bc we navigated off the page - need to do smth about this
        for (String title : resultTitleToURL.keySet()) {
            logger.info("\nTitle: " + title + " URL: " + resultTitleToURL.get(title) + "\n");
        }
    }
}