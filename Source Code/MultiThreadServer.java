import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MultiThreadServer implements Runnable {
   Socket csocket;
   static int portno;
   static String result;
   MultiThreadServer(Socket csocket)
   {
      this.csocket = csocket;
   }

   public static void main(String args[]) 
   throws Exception 
   {
	  portno=Integer.parseInt(args[0]); 
      ServerSocket srvsock = new ServerSocket(portno);
      System.out.println("Listening");
      while (true) {
         Socket sock = srvsock.accept();
         System.out.println("Connected");
         new Thread(new MultiThreadServer(sock)).start();
      }
   }
   public void run()
   { 
	   String[] inputs=new String[3];
		FileInputStream fis=null;
		String file=null,status=null,temp=null;
		try
		{
				InputStreamReader ir=new InputStreamReader(csocket.getInputStream());
			BufferedReader br= new BufferedReader(ir);
			PrintStream os = new PrintStream(csocket.getOutputStream());
			String message=br.readLine();
			inputs=message.split(" ");
			if(inputs[0].equals("GET"))
				{
				try{
				file=inputs[1];
				file="."+file;
				File fname=new File(file);
				if (!fname.exists())
				{throw new Exception();}
				Scanner fscan=new Scanner(fname);
				
				
					fis=new FileInputStream(file);
					status="200 OK \n";
					os.println(status);
					 	while(fscan.hasNextLine())
					{
						status=fscan.nextLine();
					os.println(status);
					  //os.flush();
					}
					os.flush();					  
				fis.close();	
				
				}
				catch(Exception ex)
				{
					status="404 File not found";
					os.println(status);
					
				}
				csocket.close();
				}
			else if(inputs[0].equals("PUT"))
			{	
				File fos = new File("C:\\\\Lectures\\\\"+inputs[1]);				        
		        
		        BufferedWriter bos = new BufferedWriter(new FileWriter(fos));
		        if(br.readLine().equals("error"))
		        	csocket.close();
		        while(br.readLine().equals("pending"))
		        {	
		        	temp=br.readLine();        	
		        	bos.write(temp);
		        	bos.newLine();
		        }
		        
		        bos.close();
		      
		        
		        status="200 OK \n";
				os.println(status);
	           }
	          
	           csocket.close();

			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	   
   }
}