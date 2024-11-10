package edu.rit.swen253.page.youtube;

import edu.rit.swen253.utils.BrowserType;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.HtmlUtils;
import edu.rit.swen253.utils.SeleniumUtils;
import org.openqa.selenium.By;

import java.util.List;


/**
 * A View Object that contains a link in the 'Search' section of the Home page.
**/

public class RitAreaOfStudyLink {

  private final DomElement link;
  private String firstIconName;
  private String secondIconName;

  /**
   * The View container is the DIV that holds the link: {@code <li><div> ...LINK... </div></li>}.
   */
  public RitAreaOfStudyLink(final DomElement viewContainer) {
    this.link = viewContainer.findChildBy(HtmlUtils.ANCHOR_FINDER);
  }

  /**
   * Navigate to this 'Area of Study' by clicking the link.
   */
  public void clickLink() {
    scrollIntoView();
    // still not 'in view' due to the 'Cookies Setting' panel at the bottom of the screen
    // so scroll up a little more...
    SeleniumUtils.makeAction().scrollByAmount(0, 200).perform();
    // now the click action will work
    link.click();
  }

  /**
   * Extract the FontAwesome icon name from the first span.
   * <p>This method removes the 'fa-' prefix.</p>
   */
  public String getFirstIconName() {
    if (firstIconName == null) {
      scrollIntoView();
      firstIconName = extractFontName(By.xpath("span[1]"));
    }
    return firstIconName;
  }

  /**
   * Extract the FontAwesome icon name from the second span.
   * <p>This method removes the 'fa-' prefix.</p>
   */
  public String getSecondIconName() {
    if (secondIconName == null) {
      scrollIntoView();
      secondIconName = extractFontName(By.xpath("span[2]"));
    }
    return secondIconName;
  }

  /**
   * Extract the title of the 'Area of Study' link.
   */
  public String getTitle() {
    scrollIntoView();
    return link.findChildBy(By.cssSelector("div.field--name-name")).getText();
  }

  //
  // Private
  //

  private void scrollIntoView() {
    // FIXME: this command doesn't work in Safari nor Firefox
    link.scrollIntoView();
  }

  /* NOTE: possible fix.  Why is this solution unreliable?
  private void scrollIntoView() {
    if (!scrolledAlready) {
      switch (BrowserType.discover()) {
        case CHROME, EDGE -> link.scrollIntoView();
        case FIREFOX, SAFARI -> SeleniumUtils.makeAction().scrollByAmount(0, 2700).perform();
      }
      scrolledAlready = true;
    }
  }
  private static boolean scrolledAlready = false;
  */

  private String extractFontName(final By fontSpanFinder) {
    final DomElement firstFontAwesomeSpan = link.findChildBy(fontSpanFinder);
    final List<String> spanClasses = firstFontAwesomeSpan.getClasses();
    return spanClasses.stream()
      // find the first class that matches fa-ICONNAME
      .filter(className -> className.startsWith("fa-"))
      .findFirst().get()
      // remove the fa- prefix
      .substring(3);
  }
}