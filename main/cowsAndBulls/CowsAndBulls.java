package main.cowsAndBulls;
import java.io.*;
import java.util.*;
import java.io.*;
//import java.lang.Thread;
public class CowsAndBulls{
	private static String checkWord(String dictLine,BufferedReader input, PrintWriter output){
		String entWord="";
		try{
		byte f=1;
		output.println("Enter your word: \nend");
		entWord= input.readLine();
		entWord=entWord.toLowerCase();
		entWord= entWord.stripIndent();
		while(!dictLine.contains(entWord)){


			entWord=entWord.toLowerCase();
			entWord= entWord.stripIndent();


			if(entWord.equals("resign"))
			{
				break;
			}
			if(f==1)
			{output.println("Sorry, you are way ahead.... this word is yet to be added to our Dictionary...\n\nEnter again:\nend");
			 entWord= input.readLine();
			f++;
			}
			else{
				output.println("Wrong Entry, Enter Again:\nend ");
				entWord= input.readLine();
			}
		}
	}catch(IOException e){e.printStackTrace();}
		return entWord;
	}

	
	private static String getWord() {
		String word="";
		try{
		Random num= new Random();
		int i= num.nextInt(505)+1;
		
		File inputFile= new File("/home/dmacs/Desktop/java/Assignment-2/main/cowsAndBulls/finalList.txt");
		FileReader in= new FileReader(inputFile);
		BufferedReader reader= new BufferedReader(in);

		while((i--)!=1)
		word= reader.readLine();
		
		word= reader.readLine();
		
	}catch(IOException e){e.printStackTrace();}
	return word;
}


	public static void mainGame(BufferedReader input, PrintWriter output){
		try{
		
		File inputFile1= new File("/home/dmacs/Desktop/java/Assignment-2/main/cowsAndBulls/4letterDictionary.txt");
		FileReader inp= new FileReader(inputFile1);
		BufferedReader readerDict= new BufferedReader(inp);
		
		
		String dictLine= readerDict.readLine();
		String status="";
		int noofTrials=0;
		String ch="y";

		output.println("\n\n\t\t\t\t\t\t\t---!!!COWS AND BULLS!!!---\n\nINSTRUCTIONS:");
		output.println("--Its a guessing game...\n--4 letter words...\n--Guessed words must be real...\n--TYPE 'resign' anytime to quit...");
		output.println("\n\n--Cow means a letter in the wrong position\n--Bull means a letter in the right position...");
		output.println("\n--GAME FINISHES WHEN GUESSED CORRECTLY\n");

		//Thread.sleep(2000);
		output.println("Start Guessing...I have locked on a word...");	
		String word= getWord();
		//System.out.println(word);
		char[] wordLetters= word.toCharArray();
		noofTrials=0;
		while(!status.equals("Won")){
		
		output.println("\n-------------------\nTrial:"+(++noofTrials));
		String entWord= checkWord(dictLine,input,output);
		status= entWord;
		if(status.equals("resign"))
			{break;}
		
		
		char[] entWordLett= entWord.toCharArray();
		
		byte cows=0;
		byte bulls=0;
		byte j=0;
		int i=0;
		
		while(i<4){
			j=0;
			while(j<4){
				if(wordLetters[i]==entWordLett[j])
				{
					if(i==j)
						{bulls++;}
					else {cows++;}
				}
				j++;
			}
			i++;
		}

		output.println("Cows= "+cows+"\t  "+"Bulls= "+bulls);
		
		if(bulls==4)
			{
				output.println("\n\nTotal number of Trials taken: "+ noofTrials);
				output.println("!!!You Have Won My Brother!!!");
				status="Won";
			}

	}
	
	output.println("The word is: "+word);
	//output.println("\n\nContinue Playing(y/n): \nend");
	//ch=input.readLine();
	//ch.toLowerCase().stripIndent();
	//}
	 }catch(IOException e){e.printStackTrace();}
	  catch(IllegalArgumentException e){e.printStackTrace();}
	  //catch(InterruptedException e){e.printStackTrace();}
	 //output.println("\n\nBack to Server\n");
	}
}