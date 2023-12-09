package helm.va.gov.tokenizers;

import org.apache.camel.model.language.TokenizerExpression;

public class RecordTokenizerExpression {

    public static TokenizerExpression createExpression() {
        TokenizerExpression tokenizerExpression = new TokenizerExpression();
        String token = "CTL\\^\\^\\^";
        tokenizerExpression.setToken(token);
        tokenizerExpression.setRegex("true");
        return tokenizerExpression;
    }
}
