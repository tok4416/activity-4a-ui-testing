package edu.rit.swen253.test.baeldung;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import edu.rit.swen253.page.baeldung.BaeldungSearch;
import edu.rit.swen253.test.AbstractWebTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BaeldungSearchTest extends AbstractWebTest {
    private BaeldungSearch home;


    @Test
    @Order(1)
    public void goToSearchPage() {
        home = navigateToPage("https://baeldung.com", BaeldungSearch::new);
        home.goToSearchPage();
    }

    @Test
    @Order(2)
    public void enterSearch() {
        home.enterSearchText("java");
    }
}