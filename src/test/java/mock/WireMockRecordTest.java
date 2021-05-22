package mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.matching.ContainsPattern;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WireMockRecordTest {
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
        stubFor(get(urlEqualTo("/some/thing"))
                .willReturn(
                        aResponse()
                                .withHeader("Content-Type", "text/plain")
                                .withBody("Hello world!")));
        stubFor(
                get(
                        anyUrl()
                ).withQueryParam("site", new ContainsPattern("ceshiren.com")).willReturn(
                        aResponse()
                                .withBody("site={{request.query.site}}")
                )
        );

        stubFor(get(urlPathEqualTo("/a/b/c")).withQueryParam("id", equalTo("1")).willReturn(
                aResponse().withBody("files/{{request.query.id}}.png")
        ));

        stubFor(get(urlMatching("/ceshiren/.*"))
                .willReturn(
                        aResponse().proxiedFrom("https://ceshiren.com/")));

        stubFor(get(urlMatching("/s\\?.*"))
                .willReturn(
                        aResponse().proxiedFrom("https://www.baidu.com/")));

        System.in.read();


        WireMock.reset();

// Finish doing stuff

        wireMockServer.stop();
    }
}
