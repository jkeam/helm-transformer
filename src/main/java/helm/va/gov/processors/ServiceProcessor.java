package helm.va.gov.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Map;

public class ServiceProcessor implements Processor {
    public void process(Exchange exchange) throws Exception {
        String payload = exchange.getMessage().getBody(String.class);
        Map<String, String> map = new ObjectMapper().readValue(payload, Map.class);
        System.out.println(map);
    }
}
