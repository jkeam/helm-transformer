package helm.va.gov;

import helm.va.gov.processors.MapperProcessor;
import helm.va.gov.processors.ServiceProcessor;
import helm.va.gov.tokenizers.RecordTokenizerExpression;
import jakarta.enterprise.context.ApplicationScoped;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.language.TokenizerExpression;
import org.eclipse.microprofile.config.inject.ConfigProperty;

@ApplicationScoped
public class SftpRoute extends RouteBuilder {
    @ConfigProperty(name = "ingestdir")
    public String ingestDir;

    @ConfigProperty(name = "sftpendpoint")
    public String sftpEndpoint;

    @Override
    public void configure() throws Exception {
//        uploadInputFile();
        processFiles();
    }

    /**
     * Polls sftp for files to process.
     */
    private void processFiles() {
        TokenizerExpression tokenizerExpression = new TokenizerExpression();
        String token = "CTL\\^\\^\\^";
        tokenizerExpression.setToken(token);
        tokenizerExpression.setRegex("true");

        from(sftpEndpoint)
                .split(RecordTokenizerExpression.createExpression())
                .process(new MapperProcessor())
                .process(new ServiceProcessor())
                .log("Processing...");
    }

    /**
     *  Upload input file, do this before app starts.
     */
    private void uploadInputFile() {
        from(ingestDir)
                .to(sftpEndpoint)
                .log("uploading to sftp");
    }
}