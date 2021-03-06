package mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.matching.ContainsPattern;
import com.github.tomakehurst.wiremock.matching.MatchResult;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WireMockTest {
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

        System.in.read();


        WireMock.reset();

// Finish doing stuff

        wireMockServer.stop();
    }
}
