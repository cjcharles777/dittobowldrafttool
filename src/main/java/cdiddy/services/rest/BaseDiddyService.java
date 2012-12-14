/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.services.rest;

/**
 *
 * @author cedric
 */
public class BaseDiddyService 
{
    public String getRestCall ()
    {
        URLConnection urlConnection =  new URL(fetchUrl).openConnection();
        urlConnection.connect();
        JsonReader reader = new JsonReader(
             new InputStreamReader(urlConnection.getInputStream()));
        JsonParser parser = new JsonParser();
        JsonElement rootElement = parser.parse(reader);
        JsonArray tweetsJson = rootElement.getAsJsonArray();
    
    }

    
}
