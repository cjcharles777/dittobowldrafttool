/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.system;

import java.util.Scanner;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.YahooApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 *
 * @author cedric
 */
public class OAuthConnection 
{
    private OAuthService service;
    Token requestToken ;
    Verifier verifier;
    Token accessToken;
    
    public OAuthConnection()
    {
        service = new ServiceBuilder()
                           .provider(YahooApi.class)
                           .apiKey("dj0yJmk9MWNNeHFyMVZneFdFJmQ9WVdrOVNqVm9hSGQ2TXpZbWNHbzlNVEU0TURVM09UYzJNZy0tJnM9Y29uc3VtZXJzZWNyZXQmeD0wYQ--")
                           .apiSecret("9e1bb2700b79696770c9c931b182bf12260eb4e6")
                           .build();
    }
    public void connect()
    {
           
    
    System.out.println("=== Yahoo's OAuth Workflow ===");
    System.out.println();

    // Obtain the Request Token
    System.out.println("Fetching the Request Token...");
    requestToken = service.getRequestToken();
    System.out.println("Got the Request Token!");
    System.out.println();
    System.out.println(service.getAuthorizationUrl(requestToken));
    System.out.println("And paste the verifier here");
    System.out.print(">>");
    }
    
    public void retrieveAccessToken(String token)
    {
        verifier = new Verifier(token);
            // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        Token accessToken = service.getAccessToken(requestToken, verifier);
    }
    
    public String requestData(String url, Verb v)
    {
        OAuthRequest request = new OAuthRequest(v, url);
        service.signRequest(accessToken, request); // the access token from step 4
        Response response = (Response) request.send();      
        return response.getBody();
    }
    
      
}
