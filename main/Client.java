package main;
import java.net.*;
import java.io.*;
public class Client{
	public static void main(String[] args) throws Exception{
		Socket socket=null;
		PrintWriter out= null;
		BufferedReader in= null;
		try{
			socket= new Socket(InetAddress.getByName("localhost"),2222);
			out= new PrintWriter(socket.getOutputStream(),true);
			in= new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
		}catch(UnknownHostException e){e.printStackTrace();}

		BufferedReader stdIn= new BufferedReader(new InputStreamReader(System.in));
		String fromServer=" ";
		String fromUser="";
		try{
			while(true){
			System.out.println("\n\nServer:");
			while(!(fromServer=in.readLine()).equals("end")){

				System.out.println(fromServer);
				if(fromServer.contains("good day")){
				out.close();
				in.close();
				stdIn.close();
				socket.close();
				System.exit(0);
				
				}

			}
			fromUser = stdIn.readLine();
			if(fromUser !=null){
				out.println(fromUser);
			}
			
			
		}

		}catch (IOException ex)
	    	    {
	    	    System.err.println(ex);
	    	    }
}

}
