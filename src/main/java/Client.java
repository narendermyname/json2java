import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Map;
class Client
{
	private Socket socket;
	private String userName;
	private BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
	public Client()
	{
		try
		{
			socket=new Socket("localhost",9018);
			//s=new Socket("192.168.1.35",10);
			System.out.println(socket);
			System.out.println("Enter Name :");
			userName=br.readLine();
			clientChat();
		}catch(Exception e){System.out.println(e);}
		
	}
	@SuppressWarnings("static-access")
	public void clientChat()throws IOException
	{
		DataInputStream in=new DataInputStream(socket.getInputStream());
		DataOutputStream out=new DataOutputStream(socket.getOutputStream());
		System.out.println("Enter Message to send and stop to logout :");
		String str=null;
		do
		{
			str=br.readLine();
			if(str.equals(""))
			{
				continue;
			}
			out.writeUTF(userName+":::"+str);
			out.flush();
			for (Map.Entry<?, ?> entry : Util.getUserMap(in.readUTF(in)).entrySet()) {
				System.out.println(entry.getKey() + " : "+ entry.getValue());
			}
		}while(!str.equals("stop"));
	}
	public static void main(String...a)
	{
		new Client();
	}
}
