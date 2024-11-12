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
public class ytSearchResultsTest extends AbstractWebTest {

  private ytSearchResultsPage searchResultsPage;

  //
  // Test sequence
  //

  /**
   * First, navigate to the search results page
   */
  @Test
  @Order(1)
  public void navigateToSearchResults() {
    searchResultsPage = navigateToPage("https://www.youtube.com/results?search_query=RIT+Baja", ytSearchResultsPage::new);
  }

  /**
   * Second, Confirm first result
   */
  @Test
  @Order(2)
  public void inspectSearchResults() {
    // guard condition
    Assumptions.assumeTrue(searchResultsPage != null, "No link found");

    searchResultsPage.getSearchResultsList();

    assertEquals("RIT Baja 2023 Recap", searchResultsPage.selectIndex(0));
  }
}
