import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
class Server
{
	ServerSocket serverSocket;
	Socket socket;
	public Server()
	{
		try
		{
			System.out.println("Server Started : ");
			serverSocket=new ServerSocket(9018);
			while(true) {
				socket=serverSocket.accept();
				System.out.println(socket);
				System.out.println("Client Connected");
				Thread thread=new Thread(new ClientHandler(socket));
				thread.start();
			}
		}catch(Exception e)
		{
			System.out.println(e);
		}

	}

	public static void main(String...a)
	{
		new Server();
	}
}
/**
 * Client handler class that will read data comming from client and revert back data  to client
 * @author narender
 *
 */
class ClientHandler implements Runnable{

	Socket client;
	public ClientHandler(Socket client) {
		this.client=client;
	}
	public void run() {
		try {
			serverChat();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Reading and writting data to client
	 * @throws IOException
	 */
	@SuppressWarnings("static-access")
	public void serverChat()throws IOException
	{
		DataInputStream in;
		DataOutputStream out;
		String str=null;
		in=new DataInputStream(client.getInputStream());
		out=new DataOutputStream(client.getOutputStream());
		do{
			str=in.readUTF(in);
			System.out.println(str);
			out.writeUTF(str);
			out.flush();
		}while(!str.equals("stop"));
	}

}