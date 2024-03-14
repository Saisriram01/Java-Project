package main.multiServerThread;
import java.util.*;
import java.net.*;
import java.io.*;
import main.cowsAndBulls.CowsAndBulls;
import main.dictionary.Dictionary;
import main.translation.Translation;
import main.MultiServer;

public class MultiServerThread extends Thread {
    private Socket socket = null;
    public MultiServerThread(Socket socket) {
	super("MultiServerThread");
	this.socket = socket;
    }

    public void run() {
	MultiServer.addMe();
	System.out.println("Now connected to: "+socket.getInetAddress());
	try {
	    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
	    BufferedReader in = new BufferedReader(
					   new InputStreamReader(socket.getInputStream()));

	    String inputLine, outputLine="--";
	    String output = "";
		output += "Hello, now connected to Server...\n";
		output+="Hey Client...\nHere are the following services I can provide today:\n";
		output +="\t\t- 1)Translation\n\t\t- 2)Dictionary\n\t\t- 3)CowsAndBulls(game)\n\t\tbye- to end the connection\n";
		output+="\nend";
		out.println(output);
		
		while ((inputLine = in.readLine()) != null) {
			processInput(inputLine,in,out);
			output="";
			output+="Hey Client...\nHere are the following services I provide:\n";
			output+="\t\t- 1)Translation\n\t\t- 2)Dictionary\n\t\t- 3)CowsAndBulls(game)\n\t\tbye- to end the connection\n";
			output+="\nend";
			out.println(output.toString());
			//out.println("end");
			inputLine.toLowerCase();
			
			if (inputLine.equals("bye")){
			
				out.flush();
			    out.close();
			    in.close();
			    socket.close();
			    MultiServer.removeMe();
			    break;
			}		
	}
	} catch (IOException e) {
	    e.printStackTrace();
	}finally{
		try{
			//out.flush();
		    socket.close();
		}catch(Exception e){System.out.println("Problem while closing socket..."+e);}
	}
  }
  private void processInput(String inputLine,BufferedReader in, PrintWriter out){
	  //String input = inputLine.toLowerCase().trim();
	 // StringBuffer output = new StringBuffer();
  		inputLine.toLowerCase().stripIndent();
		if(inputLine.contains("translation")||inputLine.equals("1")){
			translationService(in,out);
		}else if(inputLine.startsWith("dictionary")||inputLine.equals("2")){
			dictionaryService(in,out);
		}
		else if(inputLine.startsWith("cowsandbulls")||inputLine.equals("3")||inputLine.startsWith("game")){
			gameService(in,out);
		}
		else if(inputLine.startsWith("bye")){
			out.println("Bye and Have a good day ");
		}else {
			out.println( "Sorry my dear client, I did not understand your request...Try again...");
		}
  }
  private void translationService(BufferedReader in, PrintWriter out){
  	out.println("inside Translation service");
    Translation t= new Translation();
    t.mainTranslation(in,out);
  }
  private void dictionaryService(BufferedReader in, PrintWriter out){
  		out.println("inside Dictionary service");
  		Dictionary d= new Dictionary();
  		d.mainDictionary(in,out);
  }

   private void gameService(BufferedReader in, PrintWriter out){
   	String ch="y";
   	try{
   	while(ch.equals("y")){
   	out.println("inside cowsandbulls service");
   	CowsAndBulls c= new CowsAndBulls();
   	c.mainGame(in,out);
   	out.println("\n\nContinue Playing(y/n): \nend");
	ch=in.readLine();
   }
}catch(IOException e){e.printStackTrace();}
}
   
}

