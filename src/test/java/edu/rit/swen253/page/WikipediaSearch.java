package edu.rit.swen253.page;

import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

public class WikipediaSearch extends AbstractPage {
    
    private DomElement textBar;
    private DomElement searchButton;

    private static final By SEARCH_BOX = By.id("searchInput");
    private static final By SEARCH_BUTTON = By.id("searchButton");
    private static final By SEARCH_RESULTS = By.cssSelector(".mw-search-result-heading a");
    
    public WikipediaSearch(){ 
        super();
    }

    public void goToHomePage(){
        SeleniumUtils.getDriver().get("https://wikipedia.org");
    }

    public void searchTextInBar(String text){
        try {
            textBar = findOnPage(SEARCH_BOX);
            // textBar = findOnPage(SEARCH_BOX);
            textBar.click();
            textBar.enterText(text);
        } catch (TimeoutException e) {
            fail("Search bar could not be found");
        }
    }

    public void searchSubmission(){
        try{
            searchButton = findOnPage(SEARCH_BUTTON);
            searchButton.click();
        } catch (TimeoutException e) {
            fail("Search bar could not be found");
        }
    }

    public List<String[]> listSearchResults(){
        List<String[]> results = new ArrayList<>();
        List<DomElement> searchResults = findAllOnPage(SEARCH_RESULTS);

            for (DomElement element : searchResults) {
                String title = element.getText();
                String url = element.getAttribute("href");
                results.add(new String[] { title, url });
            }
        return results;

    }
}
