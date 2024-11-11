package edu.rit.swen253.page.baeldung;

import org.openqa.selenium.TimeoutException;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

public class BaeldungSearch extends AbstractPage {
    
    // finders to extract various elements from key pages
    private static final By SEARCH_NAVIGATION_FINDER = By.id("menu-item-40489");
    private static final By POPUP_CLOSE_FINDER = By.className("qc-usp-close-icon");
    private static final By SEARCH_TEXT_FIELD_FINDER = By.id("search");
    private static final By SEARCH_ENTRY_BUTTON_FINDER = By.className("btn-search-icon");
    private static final By SEARCH_RESULT_CONTAINER_FINDER = By.className("archive-columns");

    // storing the search icon that will be initialized in the constructor to allow other methods to use it for navigation
    private DomElement searchIcon;

    public BaeldungSearch() {
        super();
        try {
            DomElement popupCloseButton = findOnPage(POPUP_CLOSE_FINDER); // using this to try to catch the popup that appears on chromium browsers, if something is found then there is a popup that can be closed
            popupCloseButton.click(); // close the popup!
        } catch (Exception e) {
            // do nothing, this just means no popup was present
        }
        try {
            searchIcon = findOnPage(SEARCH_NAVIGATION_FINDER);
        } catch (TimeoutException e) {
            fail("Search navigation element not found");
        }
    }

    public void goToSearchPage() {
        searchIcon.click(); // clicking the icon opens the search view (it does not change the current URL)
    }

    public void enterSearchText(String text) {
        try {
            DomElement searchTextBar = findOnPage(SEARCH_TEXT_FIELD_FINDER);
            // we click the textbar and THEN begin entering text since Firefox does not allow text entry when the element isn't selected yet
            searchTextBar.click(); 
            searchTextBar.enterText(text);
        } catch (TimeoutException e) {
            fail("Search textbar element not found");
        }
    }

    public void submitSearch() {
        try {
            // finding the search button & clicking it to submit our search terms
            DomElement searchSubmissionButton = findOnPage(SEARCH_ENTRY_BUTTON_FINDER);
            searchSubmissionButton.click();
        } catch (TimeoutException e) {
            fail("Search submission button element not found");
        }
    }

}
