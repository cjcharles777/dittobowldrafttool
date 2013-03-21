package cdiddy.fantasyfootballapp.fantasyfootballconversion;

import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.Game;
import cdiddy.utils.system.JacksonPojoMapper;
import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try {
            System.out.println( "Hello World!" );
            ObjectMapper mapper = new ObjectMapper();
            InputStream input = App.class.getResourceAsStream("/2009081350.json");
           
            String name = "2009081350.json";
            int pos = name.lastIndexOf(".");
            if (pos > 0) {
                name = name.substring(0, pos);
            }
            Map<String, Object> testme = mapper.readValue(input, Map.class);
            Object gameObj = testme.get(name);
            Game game = mapper.readValue(JacksonPojoMapper.toJson(testme.get(name), false) , Game.class);
            System.out.println( "JSON converted into POJO" );
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
}
