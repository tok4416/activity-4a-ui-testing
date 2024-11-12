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

  private ytHomePage homePage;

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
    // guard condition
    Assumptions.assumeTrue(homePage != null, "No homepage link found");
    
    homePage.youtubeSearch("RIT Baja");

    SimplePage page = assertNewPage(SimplePage::new);

    //waituntilgone was throwing weird errors so fixed it with this
    while (page.getURL() == "https://www.youtube.com/" || page.getURL() == "https://www.youtube.com/?themeRefresh=1/" ) {
      page = assertNewPage(SimplePage::new);
    }
    
    assertEquals("https://www.youtube.com/results?search_query=RIT+Baja", page.getURL());
  }
}