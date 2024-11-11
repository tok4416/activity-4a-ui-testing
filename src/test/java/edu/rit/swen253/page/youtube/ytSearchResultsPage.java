package edu.rit.swen253.page.youtube;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;

/**
 * A Page Object for the Youtube Home page.
 *
 * @author <a href='mailto:mot7506@g.rit.edu'>Miles Tallia</a>
 */
public class ytSearchResultsPage extends AbstractPage {
  
  private static final By SEARCH_CONTAINER_FINDER =  By.id("contents");

  private DomElement searchResultsContainer;
  private List<DomElement> searchResults;

  public ytSearchResultsPage() {
    super();
    try {
      searchResultsContainer = findOnPage(SEARCH_CONTAINER_FINDER);
    } catch (TimeoutException e) {
      fail("Search box element not found");
    }
  }

  public void getSearchResultsList() {
    searchResults = searchResultsContainer.findChildrenBy(By.cssSelector("ytd-video-renderer"));
  }

  public String selectIndex(int i) {
    DomElement indexResult;
    try {
      indexResult = searchResults.get(i);
      
      DomElement indexResultLink;
      try {

        indexResultLink = indexResult.findChildBy(By.cssSelector("a"));
        final String link = indexResultLink.getAttribute("href");
        indexResultLink.click();
        return link;

      } catch (TimeoutException e) {
        fail("Link for indexed result not found");
      }

    } catch (TimeoutException e) {
      fail("Indexed result element not found");
    }
    return "Link not found";
  }
}
