// package edu.rit.swen253.test.sample;

// import edu.rit.swen253.page.SimplePage;
// import edu.rit.swen253.page.sample.RitAreaOfStudyLink;
// import edu.rit.swen253.page.sample.RitHomePage;
// import edu.rit.swen253.test.AbstractWebTest;
// import org.junit.jupiter.api.*;

// import java.util.List;
// import java.util.Optional;
// import java.util.logging.Logger;

// import static org.junit.jupiter.api.Assertions.assertAll;
// import static org.junit.jupiter.api.Assertions.assertEquals;


// /**
//  * A simple test that explores RIT's area of study; 'Computing' in particular.
//  *
//  * @author <a href='mailto:bdbvse@rit.edu'>Bryan Basham</a>
//  */
// @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
// public class RitExploreStudiesInComputingTest extends AbstractWebTest {
//   private static final Logger logger = Logger.getLogger(RitExploreStudiesInComputingTest.class.getName());

//   private RitHomePage homePage;
//   private RitAreaOfStudyLink linkToComputingStudies;

//   //
//   // Test sequence
//   //

//   /**
//    * First, navigate to the RIT Home page.
//    */
//   @Test
//   @Order(1)
//   public void navigateToHomePage() {
//     homePage = navigateToPage("https://rit.edu", RitHomePage::new);
//   }

//   /**
//    * Second, find out how many 'Area of Study' links are visible.
//    */
//   @Test
//   @Order(2)
//   public void exploreAreasOfStudy() {
//     final List<RitAreaOfStudyLink> studyLinks = homePage.getStudyLinks();
//     assertEquals(12, studyLinks.size(), "Number of links should be 12");

//     // prepare for next test
//     Optional<RitAreaOfStudyLink> hasLink = studyLinks.stream()
//       .filter(link -> link.getTitle().equals("Computing and Information Sciences"))
//       .findFirst();
//     hasLink.ifPresent(link -> linkToComputingStudies = link);
//   }

//   /**
//    * Third, inspect the content of the 'Computing and Information Sciences' link.
//    */
//   @Test
//   @Order(3)
//   public void inspectComputingLink() {
//     // guard condition
//     Assumptions.assumeTrue(linkToComputingStudies != null,
//       "No 'Computing and Information Sciences' link found");

//     // validate page content
//     assertAll("group assertions"
//       , () -> assertEquals("phone-laptop", linkToComputingStudies.getFirstIconName())
//       , () -> assertEquals("code", linkToComputingStudies.getSecondIconName())
//     );
//   }

//   /**
//    * Finally, navigate to the Computing area of study.
//    */
//   @Test
//   @Order(4)
//   public void navigateToComputing() {
//     // guard condition
//     Assumptions.assumeTrue(linkToComputingStudies != null,
//       "No 'Computing and Information Sciences' link found");

//     // attempt to navigate to the Computing area of study page
//     linkToComputingStudies.clickLink();

//     // expect the Home page to go away
//     homePage.waitUntilGone();

//     // expect navigation to the area of study page
//     final SimplePage page = assertNewPage(SimplePage::new);
//     // validate simple page content
//     assertAll("group assertions"
//       , () -> assertEquals("Computing and Information Sciences Degrees | RIT", page.getTitle())
//       , () -> assertEquals("https://www.rit.edu/study/computing-and-information-sciences", page.getURL())
//     );
//   }
// }
