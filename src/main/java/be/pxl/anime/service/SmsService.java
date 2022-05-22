package be.pxl.anime.service;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
@PropertySource(value={"classpath:application.properties"})
public class SmsService {

    private static final Logger LOGGER = LogManager.getLogger(SmsService.class);
    @Value("${textbelt.api.key}")
    private String textbeltApiKey;

    public void sendSms(String message, String phoneNumber) {
        LOGGER.info("SENDING " + message + " TO " + phoneNumber);
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("phone", phoneNumber));
        params.add(new BasicNameValuePair("message", message));
        params.add(new BasicNameValuePair("key", textbeltApiKey));
        CloseableHttpClient httpClient = HttpClients.createMinimal();
        HttpPost httpPost = new HttpPost("https://textbelt.com/text");
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(params));
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            String responseString = EntityUtils.toString(httpResponse.getEntity());
            LOGGER.info("Response: " + responseString);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
