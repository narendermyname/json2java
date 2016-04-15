/**
 * 
 */

package com.naren.json2java;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.jamcracker.blockvolume.response.impl.DefaultAttachBlockVolumeResponse;

/**
 * @author nstanwar
 *
 */
public class JSON2Java {

	private static final Logger LOG=Logger.getLogger(JSON2Java.class);
	/**
	 * 
	 */
	public JSON2Java() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * @throws JSONException 
	 */
	public static void main(String[] args) throws JsonGenerationException, JsonMappingException, IOException, JSONException {
		getUsageJson();
		String json="{\"DefaultCreateBlockVolumeResponse\":{\"volume\": {\"status\": \"available\", \"display_name\": \"jnjhxn\", \"attachments\": [], \"availability_zone\": \"nova\", \"bootable\": "+
				"\"false\", \"encrypted\": false, \"created_at\": \"2014-11-03T10:09:26.000000\", \"os-vol-tenant-attr:tenant_id\": \"67c873b46e45452194d3b368b3f4ba3f\", "+
				"\"display_description\": null, \"os-vol-host-attr:host\": \"IceHouse\", \"volume_type\": \"None\", \"snapshot_id\": null, \"source_volid\": null, "+
				"\"os-vol-mig-status-attr:name_id\": null, \"metadata\": {}, \"id\": \"c75f1f3e-6aab-4129-b523-00f81548a020\", \"os-vol-mig-status-attr:migstat\": null, "+
				"\"size\": 1}},\"DefaultAttachBlockVolumeResponse\":{\"volumeId\":\"dsdsd23dd232d324r43r\",\"device\":\"/device\",\"serverId\":\"fdfdffdf32324sdf23eew\",\"id\":\"iaas\"}, \"Properties\" : {\"devicename\" : \"/dev/mtn\"}}";
		Map<String,Object> dataMap=convertJsonToMapObject(json);
		LinkedHashMap<?, ?> lhm=(LinkedHashMap<?, ?>) dataMap.get("Properties");
		LOG.debug("Device : "+lhm.get("devicename"));
	}
	/**
	 * 
	 * @return
	 */
	public static DefaultAttachBlockVolumeResponse getUsageJson() {
		/*DefaultCreateBlockVolumeRequest op=new DefaultCreateBlockVolumeRequest();
		op.setAvailabilityZone("op");
		op.setDescription("Oppop");
		op.setDisplayName("Voolliuimer1");
		op.setVolumeStatus(BlockVolumeISVStatus.ATTACHED);*/
		DefaultAttachBlockVolumeResponse dl=new DefaultAttachBlockVolumeResponse();
		dl.setDevice("/device");
		dl.setServerId("fdfdffdf32324sdf23eew");
		//dl.setTenantId("iaas");
		dl.setVolumeId("dsdsd23dd232d324r43r");
		ObjectMapper mapper = new ObjectMapper();
		Writer strWriter = new StringWriter();
		try {
			mapper.writeValue(strWriter, dl);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		String userDataJSON = strWriter.toString();
		System.out.println("JSON ::::\n \t"+userDataJSON);
		//String userDataJSON = "{\"userId\":\"100\",\"userName\":{\"firstname\":\"K\",\"middlename\":\"Siva\",\"lastname\":\"Prasad\"},\"dob\":1300878089906}";
		DefaultAttachBlockVolumeResponse userFromJSON=null;
		try {
			userFromJSON = mapper.readValue(userDataJSON, DefaultAttachBlockVolumeResponse.class);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Object ::::\n\t "+userFromJSON);
		return dl;
	}
	/**
	 * convertObjectToJSON
	 * @param obj
	 * @return
	 */
	protected String convertObjectToJSON(Object obj) {
		LOG.debug("#####convertObjectToJSON Started ####### ");
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(obj);
			if(json != null && json != ""){
				json = json.replaceAll("\\\\", "");
				json = json.replace("\"{", "{");
				json = json.replace("}\"", "}");
				json = json.replace("\"[{", "[{");
				json = json.replaceAll("}]\"", "}]");
			}
		} catch (Exception e) {
			LOG.error("#####convertJsonToMapObject Error ####### "+e,e);
		}
		LOG.debug("#####convertObjectToJSON Completed#######:"+json);
		return json;
	}
	/**
	 * convertJsonToMapObject
	 * @param jsonInput
	 * @return
	 * @throws JSONException 
	 */
	@SuppressWarnings("unchecked")
	protected static Map<String,Object> convertJsonToMapObject(String jsonInput) throws JSONException {
		LOG.debug("#####convertJsonToMapObject Started ####### JSON : "+jsonInput);
		ObjectMapper mapper = new ObjectMapper();
		Map<String,Object> jsonObject = null;
		try {
			jsonObject = mapper.readValue(jsonInput, Map.class);
		} catch (Exception e) {
			LOG.error("#####convertJsonToMapObject Error ####### "+e,e);
		}
		JSONObject jObject  = new JSONObject(jsonInput);
		jObject.getJSONObject("DefaultAttachBlockVolumeResponse");
		LOG.debug("#####convertJsonToMapObject Completed ####### ");
		return jsonObject; 
	}
}
