import java.net.*;
import java.io.*;
public class TcpClient {

	/**
	 * @param args
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int port=Integer.parseInt(args[1]);
		Socket client=null;
		InputStreamReader in=null;
		BufferedReader br=null;
		PrintStream ps=null;
		double starttime,time[] =new double[1000],max;
		String server=args[0],message="cccccccccc";
		try
		{
		 
		 client=new Socket(server,port);
		  in=new InputStreamReader(client.getInputStream());
		  br = new BufferedReader(in);
		  ps=new PrintStream(client.getOutputStream());
		  for(int i=0;i<1000;i++)
			 {
			  starttime=System.currentTimeMillis();
			  ps.println((i+1)+message);
		  
		 System.out.print( br.readLine());
		 time[i]= (float)(System.currentTimeMillis()-starttime)/2 ;	
		 System.out.print("  Time taken is "+time[i]);
		 System.out.println();
			 }
		client.close();
		starttime=0;
		max=time[0];
		 for(int j=0;j<1000;j++)
		 {
			if (max<time[j])
				max=time[j];
			starttime=starttime+time[j];
		 }
		 System.out.println("Maximum RTT is "+max);
		 System.out.println("Average Rtt is "+(double)(starttime/1000));
		 
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
		}
	}

}
