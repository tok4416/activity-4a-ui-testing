package edu.rit.swen253.page.youtube;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.HtmlUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.fail;


/**
 * A Page Object for the RIT Home page.
 *
 * @author <a href='mailto:mot7506@g.rit.edu'>Miles Tallia</a>
 */
public class ytHomePage extends AbstractPage {

  /**
   * A finder to extract the main body content.
   * WARN: this is fragile code
   */
  private static final By SEARCH_CONTAINER_FINDER =  By.id("search");

  private DomElement searchBox;

  public ytHomePage() {
    super();
    // validate basic page structure
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
