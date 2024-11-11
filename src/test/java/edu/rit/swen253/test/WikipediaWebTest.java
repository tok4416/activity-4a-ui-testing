package edu.rit.swen253.test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import edu.rit.swen253.utils.SeleniumUtils;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.logging.Logger;

import edu.rit.swen253.page.WikipediaSearch;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class WikipediaWebTest extends AbstractWebTest{
    
    // private static final Logger logger = (Logger) LoggerFactory.getLogger(WikipediaWebTest.class);
    private static final Logger logger = Logger.getLogger(WikipediaSearch.class.getName());
    private static final String wikipedia_url = "https://wikipedia.org";

    private WikipediaSearch homepage;

    // public void setUp() {
    //     super.setUpSeleniumDriver();
    // }

    @Test
    @Order(1)
    public void goToHomePage(){
        homepage = navigateToPage(wikipedia_url, WikipediaSearch::new);
        homepage.goToHomePage();
    }

    @Test
    @Order(2)
    public void searchTest(){
        homepage.searchTextInBar("Antoine");
        homepage.searchSubmission();
    }

}
