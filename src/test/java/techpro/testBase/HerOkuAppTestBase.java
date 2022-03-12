package techpro.testBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class HerOkuAppTestBase {
    protected RequestSpecification spec02;

    @Before
    public void setup(){
        spec02= new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com").build();
    }
}
