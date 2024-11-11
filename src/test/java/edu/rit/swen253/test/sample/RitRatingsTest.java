package edu.rit.swen253.test.sample;

import edu.rit.swen253.page.sample.RatingInfoView;
import edu.rit.swen253.page.sample.RitHomePage;
import edu.rit.swen253.test.AbstractWebTest;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * A simple test that explores RIT's ratings on their home page.
 *
 * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
 */

@Disabled
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RitRatingsTest extends AbstractWebTest {

  private RitHomePage homePage;
  private RatingInfoView firstRatingInfo;

  //
  // Test sequence
  //

  /**
   * First, navigate to the RIT Home page.
   */
  @Test
  @Order(1)
  public void navigateToHomePage() {
    homePage = navigateToPage("https://rit.edu", RitHomePage::new);
  }

  /**
   * Second, find out how many rating info views are visible.
   */
  @Test
  @Order(2)
  public void exploreRatings() {
    final List<RatingInfoView> ratingInfoViews = homePage.getRatingViews();
    assertEquals(6, ratingInfoViews.size(), "Number of views should be 6");
    // prepare for next test
    firstRatingInfo = ratingInfoViews.getFirst();
  }

  /**
   * Third, inspect the content of the first rating info panel.
   */
  @Test
  @Order(3)
  public void inspectFirstRatingInfo() {
    assertAll("group assertions"
      , () -> assertEquals("5th", firstRatingInfo.getRating())
      , () -> assertEquals("Among Top Schools for Co-op or Internship Programs", firstRatingInfo.getTitle())
    );
  }
}
