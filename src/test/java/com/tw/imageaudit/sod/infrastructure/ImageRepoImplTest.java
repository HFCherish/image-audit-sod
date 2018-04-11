package com.tw.imageaudit.sod.infrastructure;

import com.tw.imageaudit.sod.App;
import com.tw.imageaudit.sod.domain.Image;
import com.tw.imageaudit.sod.domain.ImageRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import static com.tw.imageaudit.sod.conf.SorConf.sorUrl;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.text.MatchesPattern.matchesPattern;
import static org.junit.Assert.assertThat;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class, webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ImageRepoImplTest {
    @Autowired
    RestTemplate restTemplate;

    @Test
    public void should_able_to_access_sor() {
        ResponseEntity<String> forEntity = restTemplate.getForEntity(sorUrl("/echo"), String.class);

        assertThat(forEntity.getStatusCode(), is(HttpStatus.OK));
        assertThat(forEntity.getBody(), is("hello world"));
    }

    @Autowired
    ImageRepo imageRepo;

    @Test
    public void should_able_to_post_image_to_sor() {
        String save = imageRepo.save(new Image("testdata", "testname"));
        assertThat(save, matchesPattern("^/images/.*$"));
    }


}