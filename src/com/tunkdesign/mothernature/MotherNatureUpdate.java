package com.TunkDesign.MotherNature;

//Java import
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public class MotherNatureUpdate
{

  public MotherNatureUpdate()
  {
  }

  @SuppressWarnings("deprecation")
	public static void runVersionCheck()
  {
      URL uri = null;
      InputStream is = null;
      String source = "";
      try
      {
          uri = new URL("https://raw.github.com/TunkDesign/MotherNature/master/version");
      }
      catch(MalformedURLException e)
      {
          e.printStackTrace();
      }
      try
      {
          is = uri.openStream();
      }
      catch(IOException e)
      {
          e.printStackTrace();
      }
      DataInputStream dis = new DataInputStream(new BufferedInputStream(is));
      String s;
      try
      {
          while((s = dis.readLine()) != null) 
              source = (new StringBuilder(String.valueOf(source))).append(s).toString();
      }
      catch(IOException e)
      {
          e.printStackTrace();
      }
      try
      {
          is.close();
      }
      catch(IOException ioexception) { }
      latestVersionID = Double.parseDouble(formatSource(source));
      if(latestVersionID != currentVersionID)
      {
          log.severe((new StringBuilder(String.valueOf(Prefix))).append("You are not running the latest version of MotherNature!").toString());
          log.severe((new StringBuilder(String.valueOf(Prefix))).append("Running Version : ").append(currentVersionID).append(" | Latest Version : ").append(latestVersionID).toString());
      } else
      {
          log.info((new StringBuilder(String.valueOf(Prefix))).append(" v").append(currentVersionID).append(" is enabled. MotherNature is running the latest version").toString());
      }
  }

  public static String formatSource(String source)
  {
      String formattedSource[] = source.split("\\@");
      return formattedSource[1];
  }

  public static String Prefix = "[MotherNature] ";
  public static double latestVersionID = 0.0D;
  public static double currentVersionID = 1.1000000000000001D;
  public static Logger log = Logger.getLogger("Minecraft");

}
	