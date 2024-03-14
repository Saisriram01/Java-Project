package main.dictionary;
import  javax.net.ssl.HttpsURLConnection;
import  java.io.*;
import  java.net.URL;
import  java.lang.Thread; 
public class Dictionary { 
     public static void mainDictionary(BufferedReader input, PrintWriter output){  
        try{
        output.println("\n\n\t\t\t\t\t\t\t---!!!---DICTIONARY---!!!---");  
        final String language = "en-gb";     
        String word ="";
        String ch="y";
        while(ch.equals("y")){
        output.println("\n\nEnter the word: \nend");
        word=input.readLine();
        word=word.stripIndent();
        final String link = "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word.toLowerCase();
        final String app_id = "5a91d45f";     
        final String app_key = "4abc63f8cef3d75b8f5f1f6a4c3a0125";     
        
        //Establishing connections....
         try {        
               URL url = new URL(link);
               HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();     
               urlConnection.setRequestProperty("Accept", "text/plain");    
               urlConnection.setRequestProperty("app_id", app_id);      
               urlConnection.setRequestProperty("app_key", app_key);

         // reading the output from the server      
            BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  
           mainDisplay(reader,output);
            }catch(FileNotFoundException e){output.println("Invalid word!");}
            catch (IOException e) { e.printStackTrace();  }
        output.println("\n\nWant to Search again?(y/n): \nend");
        ch=input.readLine();
        ch.toLowerCase().stripIndent();    
    }
  }     
  catch (IOException e) { e.printStackTrace();  }

}

private static void mainDisplay(BufferedReader reader,PrintWriter output){
    try{
    String line="";
    String meaning="";
    String synon="\n\n\tSynonyms: ";
    int i=0,j=0;
    
     while ((line = reader.readLine()) != null){
      if(line.contains("definitions")&&(j<4)){
        line=reader.readLine();
        //Thread.sleep(500);
        output.println("\t"+(++j)+")"+line.strip());
      }
      if(line.contains("synonyms")){
        while(!(line=reader.readLine()).contains("]")){
          if(line.contains("text")&&(++i<=5)){
            line=(line.strip()).substring(8);

            synon+= i+")"+line + "\t";
            
          }
        }
      }
    }
   
    if(synon.length()>15)
    output.println(synon+"\n\n");
  }catch(FileNotFoundException e){output.println("Invalid word!");}
   catch (IOException e) { e.printStackTrace();  }    

  }
}











           
           /* String line = null;  
            String l="";       
             while ((line = reader.readLine()) != null) { 
              l+=line;
              l+="\n";
              //stringBuilder.append(line + "\n");        
             }       
            // System.out.println(l); 
             /*FileWriter outputStream = null;
          outputStream = new FileWriter("Meaning.txt");
          outputStream.write(l);
          outputStream.close(); 
           /* System.out.println(stringBuilder.toString());      
            JSONParser jsonParser= new JSONParser();
            Object obj= jsonParser.parse(l);
            JSONObject jsonObject = (JSONObject) obj;                     //some nonsense i have written.....should correct....
            String def="";
            while(!def.contains("null"))
              { def+= (String) jsonObject.get("definitions");
                System.out.println(def);}
            System.out.println(def);
*/
               