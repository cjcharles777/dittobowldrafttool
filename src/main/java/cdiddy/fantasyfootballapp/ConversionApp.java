/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.fantasyfootballapp;

import cdiddy.fantasyfootballapp.conversion.util.ResourceUtil;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.Game;
import cdiddy.fantasyfootballapp.fantasyfootballconversion.objects.Player;
import cdiddy.utils.system.JacksonPojoMapper;
import java.io.InputStream;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.codehaus.jackson.map.ObjectMapper;

/**
 *
 * @author DMDD
 */
public class ConversionApp {
        public static void main( String[] args )
    {
        try {
            System.out.println( "Hello World!" );
            CodeSource src = App.class.getProtectionDomain().getCodeSource();
            List<String> list = new ArrayList<String>();

            String[] spam = ResourceUtil.getResourceListing(App.class, "nfldata/");
             list = Arrays.asList(spam);
            ObjectMapper mapper = new ObjectMapper();
         
            for(String fileName : list)
            {
                InputStream input = App.class.getResourceAsStream("/nfldata/"+fileName);

                String name = fileName;
                int pos = name.lastIndexOf(".");
                if (pos > 0) {
                    name = name.substring(0, pos);
                }
                Map<String, Object> testme = mapper.readValue(input, Map.class);
                Object gameObj = testme.get(name);
                Game game = mapper.readValue(JacksonPojoMapper.toJson(testme.get(name), false) , Game.class);
                System.out.println( "JSON converted into POJO" );
            }
           
                InputStream input = App.class.getResourceAsStream("/players/players.json");


                Map<String, Object> testme = mapper.readValue(input, Map.class);
                Map<String, Player> playerMap = new HashMap<String, Player>();
                Iterator it = testme.entrySet().iterator();
                while (it.hasNext()) 
                {
                    Map.Entry pairs = (Map.Entry)it.next();
                    Player player = mapper.readValue(JacksonPojoMapper.toJson(pairs.getValue(), false) , Player.class);
                    playerMap.put((String)pairs.getKey(), player);
                    it.remove(); // avoids a ConcurrentModificationException
                }
                //
                Player me = playerMap.get("00-0026221");
                System.out.println(me.getProfile_url());
                System.out.println( "JSON converted into POJO" );
            
        } 
        catch (Exception ex) 
        {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } 

    }
    
}
