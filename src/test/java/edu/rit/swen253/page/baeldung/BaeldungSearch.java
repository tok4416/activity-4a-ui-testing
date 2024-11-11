package edu.rit.swen253.page.baeldung;

import org.openqa.selenium.TimeoutException;

import static org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.By;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

public class BaeldungSearch extends AbstractPage {
    
    // finders to extract various elements from key pages
    private static final By SEARCH_NAVIGATION_FINDER = By.id("menu-item-40489");
    private static final By POPUP_CLOSE_FINDER = By.className("qc-usp-close-icon");
    private static final By SEARCH_TEXT_FIELD_FINDER = By.id("search");
    private static final By SEARCH_ENTRY_BUTTON_FINDER = By.className("btn-search-icon");

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

    /**
     * This method clicks the search icon on the baeldung page to open the page that allows for the entry of search terms and submission of those terms 
     */
    public void goToSearchPage() {
        searchIcon.click(); // clicking the icon opens the search view (it does not change the current URL)
    }

    /**
     * This method allows for the entry of text into the page's search bar
     * 
     * @param text The text that will be entered into the search bar
     */
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

    /**
     * This method submits the search terms that have been entered. This should be invoked AFTER enterSearchText is called, otherwise a search with no text will be performed
     */
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
