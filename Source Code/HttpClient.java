import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
public class HttpClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String url=args[0]+":"+args[1]+"/"+args[3],file=null;
//String url="http://en.wikipedia.org/wiki/Rurouni_Kenshin";
try
{
	int port=Integer.parseInt(args[1]);
	Socket client=new Socket(args[0],port);
	int flag=0;
	BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	PrintStream ps=new PrintStream(client.getOutputStream());
	if(args[2].equals("GET"))
	{
	System.out.println("\nSending 'GET' request to URL : " + url);
	url=args[2]+" /"+args[3]+" HTTP/1.1";
	ps.println(url);
	String response = in.readLine();
	System.out.println(response);
	if(response.equalsIgnoreCase("200 OK "))
		{flag=1;}
	while((response=in.readLine())!=null && flag==1)
	{
		System.out.println(response);
	}
	
	client.close();
	in.close();
	}
	else if(args[2].equals("PUT"))
	{
		try
		{String status=null,filestatus="pending";
		System.out.println("Sending 'PUT' request to URL : " + url);
		file="./"+args[3];
		File fname=new File(file);
		
		Scanner fscan=new Scanner(fname);
		url=args[2]+" /"+args[3]+" HTTP/1.1";
		ps.println(url);
		while(fscan.hasNextLine())
		{ps.println(filestatus);
			status=fscan.nextLine();
		ps.println(status);	
		
		if(fscan.hasNextLine()==false)
		{
			filestatus="completed";
			ps.println(filestatus);
		}
		
		}	
		status = in.readLine();
		System.out.println(status);
		  client.close();
		  in.close();
		}
		catch(Exception e)
		{System.out.println(e.toString());
		ps.println("error");
		 }
		  
		 		
	}
	
	}
catch(Exception e)
	{System.out.print(e.toString());}
//print result

	}

}
