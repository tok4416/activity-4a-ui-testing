package edu.rit.swen253.test.youtube;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.youtube.*;
import edu.rit.swen253.test.AbstractWebTest;
import org.junit.jupiter.api.*;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * A simple test that explores RIT's area of study; 'Computing' in particular.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ytSearchTest extends AbstractWebTest {
  private static final Logger logger = Logger.getLogger(ytSearchTest.class.getName());

  private ytHomePage homePage;
  private ytSearchResultsPage searchResultsPage;

  //
  // Test sequence
  //

  /**
   * First, navigate to the Youtube Home page.
   */
  @Test
  @Order(1)
  public void navigateToHomePage() {
    homePage = navigateToPage("https://www.youtube.com/", ytHomePage::new);
  }

  /**
   * Second, find, click, enter, and submit a search.
   */
  @Test
  @Order(2)
  public void useSearchBar() {
    
    homePage.youtubeSearch("RIT Baja");

    homePage.waitUntilGone();

    final SimplePage page = assertNewPage(SimplePage::new);
    
    assertEquals("https://www.youtube.com/results?search_query=RIT+Baja", page.getURL());
  }

  /**
   * Third, navigate to the search results page
   */
  @Test
  @Order(3)
  public void navigateToSearchResults() {
    searchResultsPage = navigateToPage("https://www.youtube.com/results?search_query=RIT+Baja", ytSearchResultsPage::new);
  }

  /**
   * Fourth, Confirm first result
   */
  @Test
  @Order(4)
  public void inspectSearchResults() {

    searchResultsPage.getSearchResultsList();

    assertEquals("RIT Baja 2023 Recap", searchResultsPage.selectIndex(0));
  }
}
