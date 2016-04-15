import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;


/**
 * 
 */

/**
 * @author narender
 *
 */
public class SSHClient {

	/**
	 * 
	 */
	public SSHClient() {
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*SshDriver driver = new SshDriver("localhost" , "narender", "Neeru$098","p");
		driver.setSkipVT100Filter(true); // disable filter here. we'll get the raw data
		Shell shell = driver.open();
		System.out.println(shell.execute("date").getCommandResult());
		shell.send("ls");
		shell.expect("p" );
		System.out.println(shell.execute("ls").getCommandResult());*/
		try {
			InputStream in=Runtime.getRuntime().exec("mkdir /d01/pl").getInputStream();
			DataInputStream din=new DataInputStream(in);
			String str=null;
			while((str=din.readLine())!=null)
			System.out.println(str);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
