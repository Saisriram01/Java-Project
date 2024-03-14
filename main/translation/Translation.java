package main.translation;
import java.io.*;
//import java.lang.Thread;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import main.translation.org.json.*;
import java.util.*;

///in between spaces for language entries....and caps

public class Translation{

	public static String checkingPresence(String code,HashMap map,BufferedReader input, PrintWriter output){
			String c=code;
			try{
			
			c=c.stripIndent();
			c=c.replaceAll("\\s","");
			while(!(map.containsKey(c))){
				output.println("!!!Wrong Entry!!!\nEnter Again:\nend");
				c=input.readLine();
				c=c.stripIndent();
			    c=c.replace(" ","");
			}
		//return c.toLowerCase();
		}catch(Exception e){}
		return c.toLowerCase();
			
	}
	
	public static void mainTranslation(BufferedReader input, PrintWriter output){
			try{
			String ch="y";
			while(ch.equals("y")){
			output.println("\n\n\n\t\t\t\t\t\t\t---!!!---TRANSLATOR---!!!---");
			//Thread.sleep(1000);
			String langList="ar:Arabic			en:English			el:Greek			ja:Japanese			pl:Polish\n"+
				 			 "bn:Bengali			fi:Finnish			gu:Gujarati			ko:Korean			ru:Russian\n"+	
				 			 "cs:Czech			fr:French			hi:Hindi			mr:Marathi			es:Spanish\n"+	
				 			 "nl:Dutch			de:German			it:Italian			or:Odia (Oriya)			ta:Tamil\n";
			output.println("\n\n"+langList+"\n");
			
			HashMap<String,String> map=new HashMap<String,String>();
			File inputFile= new File("/home/dmacs/Desktop/java/Assignment-2/main/translation/langList.txt");
			FileReader in= new FileReader(inputFile);
			BufferedReader reader= new BufferedReader(in);
			String line="";

			while ((line = reader.readLine()) != null){
				String[] split= line.split(":");
				map.put(split[0],split[1]);	
			}

			//Thread.sleep(2000);
			//Taking inputs	
			output.println("\n\n---Enter the code infront of the language---\n");
			//Thread.sleep(1000);
			String fromLang="";
			output.println("Enter the Input language:  \nend");
			fromLang= input.readLine();
			fromLang= checkingPresence(fromLang,map,input,output);


			String toLang="";
			output.println("Enter the Output language:  \nend");
			toLang= input.readLine();
			toLang= checkingPresence(toLang,map,input,output);

			String sentence="";
			output.println("\n\nEnter Text to translate:\nend");
			sentence=input.readLine();

			//formatting the text entered to match requirements...
			sentence= sentence.stripIndent();
			sentence= sentence.replace(" ","%20");
						
			final String link = "https://translate.googleapis.com/translate_a/single?client=gtx&sl="+fromLang+"&tl="+toLang+"&dt=t&dt=bd&dj=1&q="+sentence;
			
			URL url = new URL(link);
            HttpsURLConnection urlConnection = (HttpsURLConnection) url.openConnection();     
            BufferedReader readerResp = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));  

            String line1 = null;  
            String l="";       
             while ((line1 = readerResp.readLine()) != null) { 
              l+=line1;
             }      
      		 
             JSONObject jsonO= new JSONObject(l);
             JSONArray jsonarray= jsonO.getJSONArray("sentences");
             JSONObject jsonO2= (JSONObject) jsonarray.get(0);
             output.println("\n\nTranslated Text:");
             output.println("  "+jsonO2.getString("trans")+"\n\n");
             output.println("\n\nWant to translate more?(y/n): \nend");
			ch=input.readLine();
			ch.toLowerCase().stripIndent();
         }
        }catch (IOException e) { e.printStackTrace();  }    
         catch (JSONException e) { e.printStackTrace();  }
  }
}







//JSONParser jsonParser = new JSONParser();
//System.out.println(jsonO.get("sentences"));
             
/*Object obj= jsonParser.parse(l);
        	 JSONObject jsonObject= new obj
             System.out.println(obj.get("trans"));
             //JSONArray array = (JSONArray) obj;
             //System.out.println(array);
            // JSONObject obj2 = (JSONObject) array.get(0);
           //  System.out.println(obj2.get("trans"));
            // System.out.println("\n\n\n"+ l); */
