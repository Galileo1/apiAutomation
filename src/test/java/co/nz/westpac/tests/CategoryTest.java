package co.nz.westpac.tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.get;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import java.util.List;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;

public class CategoryTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://api.trademe.co.nz/v1/";
    }

    @Test
    public void getCategoryTest() {
        when().request("GET", "Categories/0001.json").then().statusCode(200);
    }

    @Test(dataProvider = "category")
    public void getCategoryTest2(String category, String categoryId) {
        when().
            get("Categories/{id}.json",categoryId).
        then().
            statusCode(200).
        assertThat()
            .body("Name", equalTo(category) );
    }

    @Test(dataProvider = "category")
    public void getCategoryTest3(String category, String categoryId) {
        String resp = get("Categories/{id}.json",categoryId).getBody().asString();
        ReadContext ctx = JsonPath.parse(resp);
        List<String> subCategory = ctx.read("$.Subcategories..Path");
        assertEquals(subCategory.get(1), "/Trade-Me-Motors/Cars/Alfa-Romeo");
    }

    @DataProvider(name = "category")
    public String[][] createCircuitTestData() {    
        return new String[][] {
                {"Trade Me Motors","0001"},
        };
    }

}