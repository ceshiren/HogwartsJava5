package mock;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.common.FileSource;
import com.github.tomakehurst.wiremock.extension.Parameters;
import com.github.tomakehurst.wiremock.extension.ResponseDefinitionTransformer;
import com.github.tomakehurst.wiremock.extension.ResponseTransformer;
import com.github.tomakehurst.wiremock.extension.requestfilter.FieldTransformer;
import com.github.tomakehurst.wiremock.extension.requestfilter.RequestFilterAction;
import com.github.tomakehurst.wiremock.extension.requestfilter.RequestWrapper;
import com.github.tomakehurst.wiremock.extension.requestfilter.StubRequestFilter;
import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.github.tomakehurst.wiremock.http.*;
import com.github.tomakehurst.wiremock.matching.ContainsPattern;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;

public class WireMockProxyTest {
    @Test
    void proxy() throws IOException {
        //WireMock server启动
        WireMockServer wireMockServer = new WireMockServer(
                wireMockConfig()
                        .port(8089)
                        .extensions(
                                new ResponseTemplateTransformer(true),
/*                                new ResponseDefinitionTransformer() {
                                    @Override
                                    public ResponseDefinition transform(Request request, ResponseDefinition responseDefinition, FileSource files, Parameters parameters) {
                                        System.out.println(request);
                                        System.out.println(responseDefinition);
                                        System.out.println(files);
                                        System.out.println(parameters);
                                        return new ResponseDefinitionBuilder()
                                                .withHeader("MyHeader", "Transformed")
                                                .withStatus(200)
                                                .withBody("Transformed body")
                                                .build();
                                    }

                                    @Override
                                    public String getName() {
                                        return "ResponseDefinitionExample";
                                    }
                                },*/
                                new StubRequestFilter() {
                                    @Override
                                    public RequestFilterAction filter(Request request) {
                                        //修改header，避免服务器返回压缩格式的文本导致无法进行字符串替换
                                        Request wrappedRequest = RequestWrapper.create()
                                                .transformHeader("Accept-Encoding", new FieldTransformer<List<String>>() {
                                                    @Override
                                                    public List<String> transform(List<String> source) {
                                                        return Arrays.asList("identity");
                                                    }
                                                })
                                                .wrap(request);
                                        return RequestFilterAction.continueWith(wrappedRequest);

                                    }

                                    @Override
                                    public String getName() {
                                        return "StubRequestFilterDemo";
                                    }
                                },
                                new ResponseTransformer() {
                                    @Override
                                    public Response transform(Request request, Response response, FileSource files, Parameters parameters) {
                                        //把测试人改名为测式人
                                        return Response.Builder.like(response)
                                                .body(response.getBodyAsString().replace("测试人", "测式人"))
                                                .build();
                                    }

                                    @Override
                                    public String getName() {
                                        return "ResponseTransformerDemo";
                                    }
                                })
        ); //No-args constructor will start on port 8080, no HTTPS
        wireMockServer.start();

        //client配置
        configureFor(8089);

        //stub设置
        stubFor(get(urlMatching(".*")).willReturn(aResponse().proxiedFrom("https://ceshiren.com")));

        System.in.read();
        wireMockServer.stop();
    }
}
