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
  private static final Logger logger = Logger.getLogger(ytHomePage.class.getName());

  /**
   * A finder to extract the main body content.
   * WARN: this is fragile code
   */
  private static final By MAIN_CONTENT_FINDER = By.cssSelector("ytd--app > div.content");

  private DomElement mainContentPanel;

  public ytHomePage() {
    super();
    // validate basic page structure
    try {
      mainContentPanel = findOnPage(MAIN_CONTENT_FINDER);
    } catch (TimeoutException e) {
      fail("Main content panel not found");
    }
  }

  public List<RitAreaOfStudyLink> getStudyLinks() {
    // the Ratings panel is in the fifth <div> of the main content
    // WARN: this is fragile code
    return mainContentPanel.findChildBy(By.xpath("div[7]"))
      // the statistics columns are organized in an unordered list embedded in a layout div
      .findChildBy(By.cssSelector("div.row ul"))
      // extract each statistics columns
      .findChildrenBy(By.cssSelector("li > div.card"))
      .stream()
      // build a Rating Info view object for each of these divs
      .map(RitAreaOfStudyLink::new)
      .toList();
  }
}
