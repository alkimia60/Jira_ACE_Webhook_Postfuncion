package sample.connect.spring.basic;

import com.atlassian.connect.spring.AtlassianHostRestClients;
//import com.atlassian.connect.spring.AtlassianHostUser;
import com.atlassian.connect.spring.IgnoreJwt;



import java.util.HashMap;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.json.JSONObject;
import org.json.simple.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class BasicController {
	
	@Autowired
	private AtlassianHostRestClients atlassianHostRestClients;	
	private static final Logger logger = LoggerFactory.getLogger(BasicController.class);

    @IgnoreJwt
    @RequestMapping(value = "/basic", method = RequestMethod.GET)
    public Object getPrincipal(@AuthenticationPrincipal Object principal) {
        return principal;
    }
    @IgnoreJwt
	  @RequestMapping("/installed")
	  String installed() 
	  {
		  logger.info("Log tipo debug en /intalled");
	        return "200";
	  } 
    @IgnoreJwt
	  @RequestMapping("/uninstalled")
	  String uninstalled() 
	  {
		  logger.info("Log tipo debug en /uninstalled");
	        return "200";
	  } 
	@IgnoreJwt
	@RequestMapping("/create")
	  String create() 
	  {
		  logger.info("Log tipo debug en create");
	        return "200";
	  }
	@IgnoreJwt  
	@RequestMapping("/view")
	  String view() 
	  {
		  logger.info("Log tipo debug en view");
	        return "200";
	  }
	@IgnoreJwt@RequestMapping("/edit")
	  String edit() 
	  {
		  logger.info("Log tipo debug en edit");
	        return "200";
	  }
	//@IgnoreJwt  
	//@RequestMapping(value = "/triggered", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@RequestMapping("/triggered")  
	@ResponseBody
	  public String updateIssue(HttpEntity<String> httpEntity) throws Throwable 
	  {  
		 //JSONObject obj = new JSONObject(httpEntity.getHeaders());
		//String url ="/rest/api/2/issue/TEST-24";
		//String comments = "/rest/api/2/issue/TEST-24/comment";
		//String worklog = "/rest/api/2/issue/TEST-24/worklog/10003/properties/";
		String worklog = "/rest/api/2/issue/TEST-24/worklog/10002/properties/CustomWorklogProperty";
		//String pathAddon = "/rest/atlassian-connect/1/addons/atlassian-connect-spring-boot-sample-basic/";
		//String pathAddonVariable = "/rest/atlassian-connect/1/addons/{addonKey}/properties/{propertykey}";
		//String pathAddonVariable = "/rest/atlassian-connect/1/addons/atlassian-connect-spring-boot-sample-basic/properties/CustomWorklogProperty";
		Map <String, String> ParamsValue = new HashMap <String, String>(); 
		Map <String, String> ParamsValueAddon = new HashMap <String, String>();
			
		
		//ParamsValueAddon.put("addonKey", "atlassian-connect-spring-boot-sample-basic");
		ParamsValueAddon.put("CustomWorklogProperty", "Hola mundo");
		ParamsValue.put("body", "Hola Mundo");
	
		//logger.info(atlassianHostRestClients.authenticatedAsHostActor().getForObject(comments, String.class, ParamsValue));
		//logger.info(atlassianHostRestClients.authenticatedAsHostActor().postForObject(comments, ParamsValue, String.class));
		atlassianHostRestClients.authenticatedAsAddon().put(worklog, ParamsValueAddon);
		
	    logger.info("Log tipo debug en updateIssue" );
		  //logger.info("RESULTADO   " + res);
		  //logger.info("RESULTADO   " + restype);
		  return "200!";
	        
	  }
	@RequestMapping("/worklogupdate")  
	@ResponseBody
	  public String worklogupdate(HttpEntity<String> httpEntity) throws Throwable 
	  {
		JSONObject obj = new JSONObject(httpEntity.getBody());
		JSONObject objWorklogid = new JSONObject(httpEntity.getBody());
		 
		obj= obj.getJSONObject("issue");
		String issueKey = obj.getString("key");
		obj = obj.getJSONObject("fields");
		obj = obj.getJSONObject("customfield_10038");
		String complejidad = obj.getString("value");
		String worklogId ="";
		
		objWorklogid = objWorklogid.getJSONObject("changelog");
		org.json.JSONArray item = objWorklogid.getJSONArray("items");
		for( int i = 0; i < item.length(); i++)
		{
			objWorklogid =item.getJSONObject(i);
			
			if (objWorklogid.get("field").equals("WorklogId"))
			{
				worklogId = objWorklogid.getString("toString");
			}
		}
		
		String worklog = "/rest/api/2/issue/"+issueKey+"/worklog/"+worklogId+"/properties/CustomWorklogProperty";
		Map <String, String> ParamsValueAddon = new HashMap <String, String>();
		ParamsValueAddon.put("CustomWorklogProperty", complejidad);
		
		atlassianHostRestClients.authenticatedAsAddon().put(worklog, ParamsValueAddon);		
		
		
		logger.info("Complejidad:" + complejidad + "worklogId: " + worklogId );
		logger.info("Webhook correcto");
		
		return "200";
	  }
	  
}
