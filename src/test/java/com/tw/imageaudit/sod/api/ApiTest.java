package com.tw.imageaudit.sod.api;

import com.tw.imageaudit.sod.App;
import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author hf_cherish
 * @date 4/9/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public abstract class ApiTest {
    @BeforeClass
    public static void beforeClass() {
        RestAssured.port = 8082;
    }
}
