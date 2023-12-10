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

    @ConfigProperty(name = "ids", defaultValue = "")
    public String ids;

    @Override
    public void configure() throws Exception {
//        uploadInputFile();
        processFiles();
    }

    /**
     * Polls sftp for files to process.
     */
    private void processFiles() {
        from(sftpEndpoint)
                .split(RecordTokenizerExpression.createExpression())
                .process(new MapperProcessor())
                .process(new ServiceProcessor(ids.split(",")))
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
