package sample.connect.spring.basic;

import java.nio.charset.Charset;

public class OAuthTokenGetter {
	
	public static final String AUTHORIZATION_SERVER_URL = "https://auth.atlassian.io";
    public static final long EXPIRY_SECONDS = 10;
    public static final String GRANT_TYPE = "urn:ietf:params:oauth:grant-type:jwt-bearer";
    public static final String SCOPES = "READ ACT_AS_USER"; // case-sensitive space-delimited as per the specification
    
    public static final String USAGE_MESSAGE =
            "Usage: java OAuthTokenGetter {oauth-client-id} {instance-base-url} {userkey-to-act-as} {secret}"
                    + "\n\nPLEASE NOTE: userkey is not the same thing as username (the former never changes for a given user)."
                    + "\nTo get the key of user `alex`, use the REST endpoint /rest/api/2/user?username=alex in JIRA or "
                    + "/rest/api/user?username=alex in Confluence.";


    public static final Charset UTF8 = Charset.forName("UTF-8");

}
