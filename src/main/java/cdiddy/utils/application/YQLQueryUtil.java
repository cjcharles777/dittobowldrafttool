/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.application;

import cdiddy.utils.system.OAuthConnection;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.httpclient.URIException;
import org.apache.commons.httpclient.util.URIUtil;
import org.scribe.model.Verb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author cedric
 */
@Repository("yqlQueryUtil")
public class YQLQueryUtil 
{
    @Autowired
    private static OAuthConnection conn;
    
    public static String queryYQL (String query) 
    {
        try 
        {
            String encoded = URIUtil.encodeQuery(query);
            String actualQuery = "http://query.yahooapis.com/v1/yql?q=" + encoded +"&format=json";
            return conn.requestData(actualQuery, Verb.GET);
        }
        catch (URIException ex) 
        {
            Logger.getLogger(YQLQueryUtil.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
}
