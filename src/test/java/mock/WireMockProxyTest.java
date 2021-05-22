package mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.matching.ContainsPattern;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WireMockProxyTest {
    @Test
    void mock() throws IOException {
        //WireMock server启动
        WireMockServer wireMockServer = new WireMockServer(
                wireMockConfig()
                        .port(8089)
                        .extensions(new ResponseTemplateTransformer(true))
        ); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();

// Do some stuff

        //client配置
        configureFor(8089);

        //stub设置
        // Low priority catch-all proxies to otherhost.com by default
        stubFor(get(urlMatching(".*")).atPriority(1)
                .willReturn(aResponse().proxiedFrom("https://ceshiren.com")));

// High priority stub will send a Service Unavailable response
// if the specified URL is requested
//        stubFor(get(urlEqualTo("/api/override/123")).atPriority(10)
//                .willReturn(aResponse().proxiedFrom("https://ceshiren.com").withTransformer()));


        System.in.read();


        WireMock.reset();

// Finish doing stuff

        wireMockServer.stop();
    }
}
