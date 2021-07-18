import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProvider;
import com.amazonaws.services.cognitoidp.AWSCognitoIdentityProviderClientBuilder;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthRequest;
import com.amazonaws.services.cognitoidp.model.AdminInitiateAuthResult;
import com.amazonaws.services.cognitoidp.model.AuthFlowType;
import com.api.setup.RequestBuilder;
import com.core.Config;
import org.json.simple.parser.ParseException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import static com.amazonaws.SDKGlobalConfiguration.ACCESS_KEY_SYSTEM_PROPERTY;
import static com.amazonaws.SDKGlobalConfiguration.SECRET_KEY_SYSTEM_PROPERTY;
public class AWSCognitoAccess {
public static String getIdToken(String username, String password) throws IOException, ParseException {
Regions regions = Regions.EU_WEST_1;
String accessKey = "<Replace your accessKey>";
String secretKey = "<Replace your secretKey>";
String clientId = "<Replace your clientId>";
String poolId = "<Replace your poolId>";
System.setProperty(ACCESS_KEY_SYSTEM_PROPERTY, accessKey); System.setProperty(SECRET_KEY_SYSTEM_PROPERTY, secretKey);
AWSCognitoIdentityProvider provider = AWSCognitoIdentityProviderClientBuilder.standard()
.withRegion(regions).withCredentials(new SystemPropertiesCredentialsProvider()).build();
Map<String, String> authParams = new HashMap<>();
authParams.put("USERNAME", username);
authParams.put("PASSWORD", password);
AdminInitiateAuthRequest adminInitiateAuthRequest = new AdminInitiateAuthRequest().withClientId(clientId)
.withUserPoolId(poolId).withAuthFlow(AuthFlowType.ADMIN_NO_SRP_AUTH).withAuthParameters(authParams);
AdminInitiateAuthResult result = provider.adminInitiateAuth(adminInitiateAuthRequest);
return result.getAuthenticationResult().getIdToken();
}
}