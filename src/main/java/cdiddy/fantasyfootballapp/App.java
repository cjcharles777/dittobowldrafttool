package cdiddy.fantasyfootballapp;

import org.scribe.builder.ServiceBuilder;
import org.scribe.builder.api.YahooApi;
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
                           .apiSecret("Consumer")
                           .build();
         
    }
}
