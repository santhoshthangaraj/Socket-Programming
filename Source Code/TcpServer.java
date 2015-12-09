import java.net.*;
import java.io.*;
public class TcpServer  {

	/**
	 * @param args
	 * @return 
	 */
	private static ServerSocket server=null;
	
	public static void server() throws IOException
	{
		String message=null;
		
		System.out.print(" Waiting for client on port " );
		System.out.print(server.getLocalPort());
		Socket serversocket= server.accept();
		System.out.println("  Connected to "+serversocket.getRemoteSocketAddress());
		
		while(true)
		{
		try{
				  InputStreamReader in = new InputStreamReader(serversocket.getInputStream());
				  BufferedReader br=new BufferedReader(in);
				  PrintStream ps=new PrintStream(serversocket.getOutputStream());
				  
					  message= br.readLine();
				      ps.println(message);				  					         
		   }
		
			catch(Exception e)
			{System.out.println(e.toString());
			break;}
		}
					
	}
		
		
	
	
	public static void main(String[] args) throws IOException {
		int port=Integer.parseInt(args[0]);
		server = new ServerSocket(port);
		TcpServer.server();
		}
		
		
	}


