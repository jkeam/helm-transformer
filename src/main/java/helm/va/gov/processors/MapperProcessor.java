package helm.va.gov.processors;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Arrays;

public class MapperProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String payload = exchange.getMessage().getBody(String.class);
        String[] theSplit = payload.split("\n");
        String id = theSplit[0].replace("^", "");
        String body = String.join("\n", Arrays.copyOfRange(theSplit, 1, theSplit.length));
        JsonObject object = Json.createObjectBuilder().add("id", id).add("body", body).build();
        exchange.getMessage().setBody(object.toString());
    }
}
