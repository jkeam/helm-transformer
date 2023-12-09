package helm.va.gov.processors;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import java.util.Arrays;
import java.util.Map;

public class ServiceProcessor implements Processor {
    private String[] ids;

    public ServiceProcessor(String [] ids) {
        super();
        this.ids = ids;
    }

    public void process(Exchange exchange) throws Exception {
        String payload = exchange.getMessage().getBody(String.class);
        Map<String, String> map = new ObjectMapper().readValue(payload, Map.class);
        String id = map.get("id");
        if (Arrays.stream(ids).filter(cur -> id.equals(cur)).findAny().isPresent()) {
            System.out.println(map);
        }
    }
}
