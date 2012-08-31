package cdiddy.fantasyfootballapp;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.YahooApi;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        OAuthService service = new ServiceBuilder()
                           .provider(YahooApi.class)
                           .apiKey("dj0yJmk9MWNNeHFyMVZneFdFJmQ9WVdrOVNqVm9hSGQ2TXpZbWNHbzlNVEU0TURVM09UYzJNZy0tJnM9Y29uc3VtZXJzZWNyZXQmeD0wYQ--")
                           .apiSecret("9e1bb2700b79696770c9c931b182bf12260eb4e6")
                           .build();
        
    Scanner in = new Scanner(System.in);    
    System.out.println("=== Yahoo's OAuth Workflow ===");
    System.out.println();

    // Obtain the Request Token
    System.out.println("Fetching the Request Token...");
    Token requestToken = service.getRequestToken();
    System.out.println("Got the Request Token!");
    System.out.println();

    System.out.println("Now go and authorize Scribe here:");
    System.out.println(service.getAuthorizationUrl(requestToken));
    System.out.println("And paste the verifier here");
    System.out.print(">>");
    Verifier verifier = new Verifier(in.nextLine());
    System.out.println();

    // Trade the Request Token and Verfier for the Access Token
    System.out.println("Trading the Request Token for an Access Token...");
    Token accessToken = service.getAccessToken(requestToken, verifier);
    ///Token accessToken = new Token("A=gWqDWjLroC3iEwO7w4f6Lj2p05Rlpr4N7pIO5sHJbhOHN0Q1EOwEN1AXzftVFb.wn9huvp.LrRz0V3fRViJD1ryIbx_Hpo52PfawZgpTZfzMl31hV7R6kuwbjggIMh6KZVuq.WE2YgPUh.FtBkssIh48VeY_MEvkTFhOmL7quzZ71Hb2oygAH62lgL6ftI6rgTWeMtGqB8rZPfrsdexhx1nD8aPLnketINxeJRcuz_RdOTwwZkzqm0L0YHS7WEzXJzKv86PW0T8VF705QejYJjxGSn4AIp_CgR.a1bQW4zY3iyUMggQCjzp8nGUJwD0iTPJqGpvdKNbLgLikQUbvIOZnf_rcHi3A6bmRwXGYCvbqUFBD5wEp_sl87y5JjuJS8_ckEuU5BOb2EeSVsJo_Bo4HJ2YTH1WBD3.fRSccUp0a0Rxssouz_Tvjs6QASH8f.7Pr6VKsmfaUiRx6KJQ4UvEkdJ2jNY.qeE5VJWBerHGNl5cMZqyIqS7JiDdy2vVhn.4ECVDxhWOSKuxYXUdtj4ajb8uOq5c35Tc.kYfCXirNoiuj48EHxlgiWJJRLg.EM27pBAGRNp.lbDQjiHBgbELt3BybInRa3Q3nxuXTiKQjkEf7cG3RHkJgJAApP3.ra4OYMZyeEokPDZQCG0fo10BC5.9mFMY_a3loPtIzwX5Q5NNXQgsPXEIZz7xbvpyRwGKMxCWzt8toYXdkBTgINHy1Y0cCFndYDdqjrWGyPGwlpwktikRsnmlfJzeCCPIMyB4DeZjVhb00Kki9vWHlf0GTG4OUCBtOe1CiAzZPPXWhp9wrbxT8sw1.53K9_0GIXg--" , "575ca086722406ba5a75ecd5c93a3b73ca029d6c");
   // (if your curious it looks like this: Token[A=gWqDWjLroC3iEwO7w4f6Lj2p05Rlpr4N7pIO5sHJbhOHN0Q1EOwEN1AXzftVFb.wn9huvp.LrRz0V3fRViJD1ryIbx_Hpo52PfawZgpTZfzMl31hV7R6kuwbjggIMh6KZVuq.WE2YgPUh.FtBkssIh48VeY_MEvkTFhOmL7quzZ71Hb2oygAH62lgL6ftI6rgTWeMtGqB8rZPfrsdexhx1nD8aPLnketINxeJRcuz_RdOTwwZkzqm0L0YHS7WEzXJzKv86PW0T8VF705QejYJjxGSn4AIp_CgR.a1bQW4zY3iyUMggQCjzp8nGUJwD0iTPJqGpvdKNbLgLikQUbvIOZnf_rcHi3A6bmRwXGYCvbqUFBD5wEp_sl87y5JjuJS8_ckEuU5BOb2EeSVsJo_Bo4HJ2YTH1WBD3.fRSccUp0a0Rxssouz_Tvjs6QASH8f.7Pr6VKsmfaUiRx6KJQ4UvEkdJ2jNY.qeE5VJWBerHGNl5cMZqyIqS7JiDdy2vVhn.4ECVDxhWOSKuxYXUdtj4ajb8uOq5c35Tc.kYfCXirNoiuj48EHxlgiWJJRLg.EM27pBAGRNp.lbDQjiHBgbELt3BybInRa3Q3nxuXTiKQjkEf7cG3RHkJgJAApP3.ra4OYMZyeEokPDZQCG0fo10BC5.9mFMY_a3loPtIzwX5Q5NNXQgsPXEIZz7xbvpyRwGKMxCWzt8toYXdkBTgINHy1Y0cCFndYDdqjrWGyPGwlpwktikRsnmlfJzeCCPIMyB4DeZjVhb00Kki9vWHlf0GTG4OUCBtOe1CiAzZPPXWhp9wrbxT8sw1.53K9_0GIXg-- , 575ca086722406ba5a75ecd5c93a3b73ca029d6c] )
    System.out.println("Got the Access Token!");
    System.out.println("(if your curious it looks like this: " + accessToken + " )");
    System.out.println();

    // Now let's go and ask for a protected resource!
    System.out.println("Now we're going to access a protected resource...");
        
    OAuthRequest request = new OAuthRequest(Verb.GET, "http://fantasysports.yahooapis.com/fantasy/v2/users;use_login=1/games;game_keys=nfl/leagues?format=json");
    service.signRequest(accessToken, request); // the access token from step 4
    Response response = (Response) request.send();
    
    OAuthRequest request2 = new OAuthRequest(Verb.GET, "http://fantasysports.yahooapis.com/fantasy/v2/league/273.l.8899/players;status=A;count=100?format=json");
    service.signRequest(accessToken, request2); // the access token from step 4
    Response response2 = (Response) request2.send();
    ObjectMapper mapper = new ObjectMapper();
    Map<String,Object> userData;
     Map<String,Object> params;
     ArrayList league;
        try {
            userData = mapper.readValue(response2.getBody(), Map.class);
            params = (Map<String, Object>)userData.get("fantasy_content");
             league = (ArrayList)params.get("league");
             if(league != null)
             {
                league.get(1);
             }
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } 

  
    
    
    System.out.println(response.getBody());
    System.out.println(response2.getBody());
        
    System.out.println();
    System.out.println("Thats it man! Go and build something awesome with Scribe! :)");
         
    }
}
