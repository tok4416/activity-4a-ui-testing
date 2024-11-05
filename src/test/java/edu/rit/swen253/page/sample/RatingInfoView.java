package edu.rit.swen253.page.sample;

import edu.rit.swen253.utils.BrowserType;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;
import org.openqa.selenium.By;


/**
 * A View Object that contains rating information.
 *
 * <p>
 * Example:
 * <pre>
 *   <div class="card statistic statistic-6499 position-relative h-100 text-align-center paragraph paragraph--type--statistic paragraph--view-mode--default" style="height: 262px;">
 *     <p class="card-header position-relative pb-0  font-weight-normal">
 *       5<sup>th</sup>
 *     </p>
 *     <div class="card-body position-relative px-3  pt-0 pt-sm-2 pt-md-3">
 *       <p class="card-title font-weight-bold">
 *         Among Top Schools for Co-op or Internship Programs
 *       </p>
 *       <div class="card-text text-gray-800">
 *         <p>...</p>
 *       </div>
 *     </div>
 *   </div>
 * </pre>
 */
public class RatingInfoView {

  private final DomElement viewContainer;

  public RatingInfoView(final DomElement viewContainer) {
    this.viewContainer = viewContainer;
  }

  public String getRating() {
    scrollIntoView();
    return viewContainer.findChildBy(By.cssSelector("p.card-header")).getText();
  }

  public String getTitle() {
    scrollIntoView();
    return viewContainer.findChildBy(By.cssSelector("p.card-title")).getText();
  }

  //
  // Private
  //

  private void scrollIntoView() {
    // FIXME: this command doesn't work in Safari nor Firefox
    viewContainer.scrollIntoView();
  }

  /* NOTE: possible fix.  Why is this solution unreliable?
  private void scrollIntoView() {
    if (!scrolledAlready) {
      switch (BrowserType.discover()) {
        case CHROME, EDGE -> viewContainer.scrollIntoView();
        case FIREFOX, SAFARI -> SeleniumUtils.makeAction().scrollByAmount(0, 2000).perform();
      }
      scrolledAlready = true;
    }
  }
  private static boolean scrolledAlready = false;
  */
}
