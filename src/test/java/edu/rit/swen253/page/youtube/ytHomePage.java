package edu.rit.swen253.page.youtube;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * A Page Object for the Youtube Home page.
 *
 * @author <a href='mailto:mot7506@g.rit.edu'>Miles Tallia</a>
 */
public class ytHomePage extends AbstractPage {
  
  private static final By SEARCH_CONTAINER_FINDER =  By.id("search");

  private DomElement searchBox;

  public ytHomePage() {
    super();
    try {
      searchBox = findOnPage(SEARCH_CONTAINER_FINDER);
    } catch (TimeoutException e) {
      fail("Search box element not found");
    }
  }

  public void youtubeSearch(String input) {
    
    try {
      DomElement searchField = searchBox.findChildBy(By.id("search"));
      searchField.click();
      searchField.enterText(input);
    } catch (TimeoutException e) {
      fail("Search text entry element not found");
    }

    try {
      DomElement searchButton = searchBox.findChildBy(By.id("search-icon-legacy"));
      searchButton.click();
    } catch (TimeoutException e) {
      fail("Search button element not found");
    }
  }
}
