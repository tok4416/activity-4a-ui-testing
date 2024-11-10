package edu.rit.swen253.page.baeldung;

import org.openqa.selenium.TimeoutException;

import static org.junit.jupiter.api.Assertions.fail;

import org.openqa.selenium.By;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

public class BaeldungSearch extends AbstractPage {
    
    // private static final By SEARCH_NAVIGATION_FINDER = By.className("menu-search");
    private static final By SEARCH_NAVIGATION_FINDER = By.id("menu-item-40489");
    private static final By POPUP_CLOSE_FINDER = By.className("qc-usp-close-icon");
    private static final By SEARCH_TEXT_FIELD_FINDER = By.id("search");
    private static final By SEARCH_ENTRY_BUTTON_FINDER = By.className("btn-search-icon");

    private DomElement searchIcon;
    private DomElement searchTextBar;
    private DomElement searchSubmissionButton;

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
        searchIcon.click();
    }

    public void enterSearchText(String text) {
        try {
            searchTextBar = findOnPage(SEARCH_TEXT_FIELD_FINDER);
            searchTextBar.click();
            searchTextBar.enterText(text);
        } catch (TimeoutException e) {
            fail("Search textbar element not found");
        }
    }

    public void submitSearch() {
        try {
            searchSubmissionButton = findOnPage(SEARCH_ENTRY_BUTTON_FINDER);
            searchSubmissionButton.click();
        } catch (TimeoutException e) {
            fail("Search submission button element not found");
        }
    }

}
