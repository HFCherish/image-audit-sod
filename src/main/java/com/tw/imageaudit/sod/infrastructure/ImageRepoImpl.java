package com.tw.imageaudit.sod.infrastructure;

import com.tw.imageaudit.sod.conf.SorConf;
import com.tw.imageaudit.sod.domain.Image;
import com.tw.imageaudit.sod.domain.ImageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hf_cherish
 * @date 4/11/18
 */
@Component
public class ImageRepoImpl implements ImageRepo {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public String save(Image image) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        headers.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        URI uri = restTemplate.postForLocation(SorConf.sorUrl("/images"),
                new HttpEntity<>(image, headers));

        String location = uri.toString();
        Pattern pattern = Pattern.compile("^/images/(.*)$");
        Matcher matcher = pattern.matcher(location);
        while (matcher.find()) {
            return matcher.group(1);
        }
        throw new Exception("upload fail");
    }
}
