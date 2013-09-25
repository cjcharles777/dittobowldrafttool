/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.system;

import java.util.Collection;

/**
 *
 * @author cedric
 */
public class ArrayConversionUtil 
{
    
    public static <T> void fromArrayToCollection(Object[] a, Collection<T> c) 
    {
        for (Object o : a) 
        {
            c.add((T)o); // Correct
        }
    }

    
}
