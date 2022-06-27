package framework.advance;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;

public class YAML2TestCase {
    public void render(String dataPath, String templatePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TypeReference<HashMap<String, List<HashMap<String, Object>>>> typeDef = new TypeReference<>() {
        };
        HashMap<String, List<HashMap<String, Object>>> data = mapper.readValue(new File(dataPath), typeDef);

        MustacheFactory mf = new DefaultMustacheFactory();
        Mustache mustache = mf.compile(templatePath);
        mustache.execute(new PrintWriter(System.out), data).flush();
    }

    public static void main(String[] args) throws IOException {
        YAML2TestCase yaml2TestCase = new YAML2TestCase();
        yaml2TestCase.render(
                "src/test/java/framework/advance/record.yaml",
                "src/test/java/framework/advance/java.mustache"
        );
    }

}


