import java.util.HashMap;
import java.util.Map;

/**
 * 
 */

/**
 * @author narender
 *
 */
public class Util {

	/**
	 * 
	 */
	public Util() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * This method is use to split message to userName and user message
	 * @param message
	 * @return
	 */
	public synchronized static Map<?,?> getUserMap(String message){
		Map<String,String> userMap=new HashMap<String,String>();
		String userData []=message.split(":::");
		userMap.put(userData[0], userData[1]);		
		return userMap;
	}

}
