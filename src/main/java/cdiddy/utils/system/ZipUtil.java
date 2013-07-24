/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cdiddy.utils.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 *
 * @author Cedric
 */
public class ZipUtil 
{
     public static void unzip(File zipfile, File directory) throws IOException 
     {
        ZipFile zfile = new ZipFile(zipfile);
        Enumeration<? extends ZipEntry> entries = zfile.entries();
        while (entries.hasMoreElements()) 
        {
          ZipEntry entry = entries.nextElement();
          File file = new File(directory, entry.getName());
          if (entry.isDirectory()) {
            file.mkdirs();
          } else {
            file.getParentFile().mkdirs();
            InputStream in = zfile.getInputStream(entry);
            try {
              copy(in, file);
            } finally {
              in.close();
            }
          }
        }
    }
    private static void copy(InputStream in, OutputStream out) throws IOException 
    {
        byte[] buffer = new byte[1024];
        while (true) 
        {
          int readCount = in.read(buffer);
          if (readCount < 0) {
            break;
          }
          out.write(buffer, 0, readCount);
        }
      }

    private static void copy(File file, OutputStream out) throws IOException 
    {
      InputStream in = new FileInputStream(file);
      try 
      {
        copy(in, out);
      } 
      finally 
      {
        in.close();
      }
    }

    private static void copy(InputStream in, File file) throws IOException 
    {
      OutputStream out = new FileOutputStream(file);
      try {
        copy(in, out);
      } finally {
        out.close();
      }
    }
    
}
