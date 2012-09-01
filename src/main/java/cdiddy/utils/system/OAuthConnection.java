/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.system;

import cdiddy.objects.OAuthToken;
import cdiddy.objects.dao.OAuthDAO;
import cdiddy.objects.dao.OAuthDAOImpl;
import java.util.List;
import java.util.Scanner;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.YahooApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cedric
 */
@Repository("oauthConnection")
public class OAuthConnection 
{
    private OAuthService service;
    Token requestToken ;
    Verifier verifier;
    Token accessToken;
    
    @Autowired
    private OAuthDAO oauthDAOImpl;
    
    
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
           
        List<OAuthToken> prevList = oauthDAOImpl.getAllOAuth();
        if(prevList == null || prevList.size() == 0)
        {
            System.out.println("=== Yahoo's OAuth Workflow ===");
            System.out.println();
            Scanner in = new Scanner(System.in);
            // Obtain the Request Token
            System.out.println("Fetching the Request Token...");
            requestToken = service.getRequestToken();
            System.out.println("Got the Request Token!");
            System.out.println();
            System.out.println(service.getAuthorizationUrl(requestToken));
            System.out.println("And paste the verifier here");
            System.out.print(">>");
            retrieveAccessToken(in.nextLine());
        }
        else
        {
            accessToken = new Token (prevList.get(0).getToken(),prevList.get(0).getSecret());
            verifier = new Verifier(prevList.get(0).getVerifier());
            
            
        }
    }
    
    public void retrieveAccessToken(String token)
    {
        verifier = new Verifier(token);
            // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
         accessToken = service.getAccessToken(requestToken, verifier);
         OAuthToken temp = new OAuthToken();
         temp.setVerifier(verifier.getValue());
         temp.setToken(accessToken.getToken());
         temp.setSecret(accessToken.getSecret());
         oauthDAOImpl.savePlayer(temp);
    }
    
    public String requestData(String url, Verb v)
    {
        OAuthRequest request = new OAuthRequest(v, url);
        service.signRequest(accessToken, request); // the access token from step 4
        Response response = (Response) request.send();  
        if(!response.isSuccessful())
        {
            refreshToken();
            response = (Response) request.send();
        }
        return response.getBody();
    }
    
    public void refreshToken()
    {
        accessToken = service.getAccessToken(accessToken, verifier);
         OAuthToken temp = new OAuthToken();
         temp.setVerifier(verifier.getValue());
         temp.setToken(accessToken.getToken());
         temp.setSecret(accessToken.getSecret());
         oauthDAOImpl.savePlayer(temp);
    }
      
}
