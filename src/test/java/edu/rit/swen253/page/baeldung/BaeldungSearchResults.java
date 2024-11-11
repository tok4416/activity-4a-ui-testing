package edu.rit.swen253.page.baeldung;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

public class BaeldungSearchResults extends AbstractPage {
    
    private static final By SEARCH_RESULT_CONTAINER_FINDER = By.className("archive-columns");
    private static final By RESULT_HEADER_FINDER = By.className("post-title"); // finds the h3 tag inside each search result that contains information about the title & the url the result links to

    private DomElement searchResultContainer;
    private List<DomElement> searchResults;

    public BaeldungSearchResults() {
        super();
        try {
            searchResultContainer = findOnPage(SEARCH_RESULT_CONTAINER_FINDER);
        } catch (TimeoutException e) {
            fail("Could not find search result container element");
        }   
    }

    /**
     * This method finds the container on the page that contains all of the search results after entering a search term 
     * and returns the title and URL of all search results in the container
     * 
     * @return A map of titles of search results to the URL that the search result links to
     */
    public Map<String, String> getSearchResults() {
        try {
            HashMap<String, String> titleToURLMap = new HashMap<>();
            searchResults = searchResultContainer.findChildrenBy(By.tagName("article")); // this gets all <article> elements that are children of the search result container
            
            for (DomElement element : searchResults) {
                // the data we need is contained in an <h3> tag that then contains an anchor tag
                DomElement innerTitle = element.findChildBy(RESULT_HEADER_FINDER);
                DomElement innerAnchor = innerTitle.findChildBy(By.tagName("a"));

                titleToURLMap.put(innerAnchor.getAttribute("title"), innerAnchor.getAttribute("href"));
            }
            return titleToURLMap;
        } catch (TimeoutException e) {
            fail("Search result container element not found");
            return new HashMap<String, String>();
        }
    }

    /**
     * This method clicks the first link that appears in the search results page and returns the URL that link references
     */
    public String clickFirstLink() {
        DomElement innerHeading = searchResults.getFirst().findChildBy(RESULT_HEADER_FINDER);
        DomElement innerAnchor = innerHeading.findChildBy(By.tagName("a"));
        final String link = innerAnchor.getAttribute("href");

        innerHeading.click(); // clicking the link

        return link;
    }

}
