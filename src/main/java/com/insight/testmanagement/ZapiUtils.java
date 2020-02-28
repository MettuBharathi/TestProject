package com.insight.testmanagement;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

public class ZapiUtils {
	public static String jiraBaseUrl,ZAPI_CREATE_CYCLE,ZAPI_SEARCH_ISSUES,ZAPI_ADD_TESTS_TO_CYCLE,ZAPI_GET_EXECUTIONS,ZAPI_UPDATE_EXECUTION;
	public static String projectId, versionId,authentication;	
	public static void main(String[] args) throws URISyntaxException, JSONException, InterruptedException, IOException {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MMM-dd-HH_mm");
		Date date = new Date();
		LoadJiraProperties();
		String cycleId=createCycle("AutomationCycle_"+dateFormat.format(date));
		System.out.println("New Cycle created, Cycle id -"+cycleId);
		ArrayList<String> issueKeys=new ArrayList<String>();
		issueKeys.add("QA-681");
		issueKeys.add("QA-682");
		issueKeys.add("QA-683");
		issueKeys.add("QA-530");
		issueKeys.add("QA-409");
		addTestsToCycle(cycleId,issueKeys);
		Thread.sleep(10000);
		Map<String, List<String>> executionsByCycle= getExecutionsByCycleId(cycleId);
		System.out.println(executionsByCycle);
		List<String> testDetails=getTestDetailsFromCycle(executionsByCycle,"QA-681");
		System.out.println(testDetails);
		updateExecutions(testDetails.get(0),testDetails.get(1),"PASS", cycleId);
		System.out.println("Done....!");
	}

	//####################################Activities in JIRA###############################################
	/**
	 * @Description - This method is used to create a Test Cycle in JIRA
	 * @param name - name of the Cycle
	 * @return String - CycleId
	 * @throws URISyntaxException
	 * @throws JSONExcep
	 */
	public static String createCycle(String name) throws URISyntaxException, JSONException {
		JSONObject createCycleObj = new JSONObject();
		createCycleObj.put("name", name);
		createCycleObj.put("description", "automation");
		createCycleObj.put("projectId", projectId);
		createCycleObj.put("versionId", versionId);
		StringEntity cycleJSON = null;
		try {
			cycleJSON = new StringEntity(createCycleObj.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		HttpResponse response = POST(ZAPI_CREATE_CYCLE, cycleJSON);
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		String cycleId = "-1";
		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity = response.getEntity();
			try {
				JSONObject cycleObj = new JSONObject(EntityUtils.toString(entity));
				cycleId = cycleObj.getString("id");
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			try {
				throw new ClientProtocolException("Unexpected response status: " + statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
		return cycleId;
	}

	/**
	 * @Description - Adding tests to Cycle
	 * @param cycleId
	 * @param issueKeys
	 * @throws URISyntaxException
	 * @throws JSONException
	 * @throws InterruptedException 
	 */

	public static void addTestsToCycle(String cycleId,ArrayList<String> issueKeys) throws URISyntaxException, JSONException, InterruptedException {

		final String addTestsUri = ZAPI_ADD_TESTS_TO_CYCLE;

		//** Create JSON object by providing input values *//*
		JSONObject addTestsObj = new JSONObject();
		addTestsObj.put("cycleId", cycleId);
		addTestsObj.put("projectId", projectId);
		addTestsObj.put("versionId", versionId);
		addTestsObj.put("method", "1");
		addTestsObj.put("issues", issueKeys);
		StringEntity addTestsJSON = null;
		try {
			addTestsJSON = new StringEntity(addTestsObj.toString());
			addTestsJSON.setContentType("application/json");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpResponse response = POST(addTestsUri, addTestsJSON);
		Thread.sleep(10000);
		int statusCode = response.getStatusLine().getStatusCode();
		System.out.println(statusCode);
		String temp = null;
		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity = response.getEntity();
			try {
				temp = EntityUtils.toString(entity);
				System.out.println(temp);
				JSONObject cycleObj = new JSONObject(temp);
				System.out.println(cycleObj.toString());
				System.out.println("Tests added Successfully");
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			try {
				throw new ClientProtocolException("Unexpected response status: " + statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @Description - Get all the executions for a cycle 
	 * @param cycleId
	 * @return - Map of executions for the cycle
	 * @throws URISyntaxException
	 * @throws JSONException
	 */
	public static Map<String, List<String>> getExecutionsByCycleId(String cycleId) throws URISyntaxException, JSONException {
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		final String getExecutionsUri = ZAPI_GET_EXECUTIONS+"project=" + projectId +"&cycle="+cycleId;
		HttpResponse response = GET(getExecutionsUri);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode >= 200 && statusCode < 300) {
			HttpEntity entity = response.getEntity();
			String obj = null;
			try {
				obj = EntityUtils.toString(entity);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			JSONObject allIssues = new JSONObject(obj);
			JSONArray IssuesArray = allIssues.getJSONArray("executions");
			if (IssuesArray.length() == 0) {
				return map;
			}
			for (int j = 0; j <= IssuesArray.length() - 1; j++) {
				JSONObject execution = IssuesArray.getJSONObject(j);
				String currentCycleId = execution.getString("cycleId");
				if (currentCycleId.equals(cycleId)) {
					String id = execution.getString("id");
					long issueId = execution.getLong("issueId");
					String issueKey = execution.getString("issueKey");
					List<String> valSet = new ArrayList<String>();
					valSet.add(id);
					valSet.add(String.valueOf(issueId));
					map.put(issueKey, valSet);
					System.out.println("Execution id : " + currentCycleId + " - " + id + " - " + issueKey);
				}
			}
		}
		return map;
	}
	
	/**
	 * 
	 * @param executionsByCycle
	 * @param jiraTestCaseId
	 * @return
	 */
	public static List<String> getTestDetailsFromCycle(Map<String, List<String>> executionsByCycle, String jiraTestCaseId) {
		return executionsByCycle.get(jiraTestCaseId);
	}
	
	/**
	 * 
	 */
	public static String updateExecutions(String id,String issueId,String issueStatus, String cycleId) throws URISyntaxException, JSONException, IOException {
		final String updateExecutionUri =  ZAPI_UPDATE_EXECUTION + id+"/execute";
		String testResult;
		switch(issueStatus.toLowerCase()){
		case "pass":
			testResult="1";
			break;
		case "fail":
			testResult="2";
			break;
		default:
			testResult="-1";
			break;
		}
		JSONObject executeTestsObj = new JSONObject();
		executeTestsObj.put("status", testResult);
		executeTestsObj.put("cycleId", cycleId);
		executeTestsObj.put("projectId", projectId);
		executeTestsObj.put("versionId", versionId);
		executeTestsObj.put("comment", "Executed by ZAPI Cloud");
		executeTestsObj.put("issueId", issueId);
		StringEntity executeTestsJSON = null;
		try {
			executeTestsJSON = new StringEntity(executeTestsObj.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpResponse response = PUT(updateExecutionUri, executeTestsJSON);
		int statusCode = response.getStatusLine().getStatusCode();
		String executionStatus = "No Test Executed";
		HttpEntity entity = response.getEntity();
		if (statusCode >= 200 && statusCode < 300) {
			String string = null;
			try {
				string = EntityUtils.toString(entity);
				JSONObject executionResponseObj = new JSONObject(string);
				// JSONObject descriptionResponseObj = executionResponseObj.getJSONObject("execution");
				JSONObject statusResponseObj = executionResponseObj.getJSONObject("status");
				executionStatus = executionResponseObj.getString("description");
				System.out.println(executionResponseObj.get("issueKey") + "--" + executionStatus);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			try {
				String string = null;
				string = EntityUtils.toString(entity);
				JSONObject executionResponseObj = new JSONObject(string);
				cycleId = executionResponseObj.getString("clientMessage");
				// System.out.println(executionResponseObj.toString());
				throw new ClientProtocolException("Unexpected response status: " + statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
		return executionStatus;
	}
	
	/**
	 * 
	 */
	public static String updateExecutions(String id,String issueId,String issueStatus, String cycleId,String comments) throws URISyntaxException, JSONException, IOException {
		final String updateExecutionUri =  ZAPI_UPDATE_EXECUTION + id+"/execute";
		String testResult;
		switch(issueStatus.toLowerCase()){
		case "wip":
			testResult="3";
			break;
		case "pass":
			testResult="1";
			break;
		case "fail":
			testResult="2";
			break;
		default:
			testResult="-1";
			break;
		}
		JSONObject executeTestsObj = new JSONObject();
		executeTestsObj.put("status", testResult);
		executeTestsObj.put("cycleId", cycleId);
		executeTestsObj.put("projectId", projectId);
		executeTestsObj.put("versionId", versionId);
		executeTestsObj.put("comment", comments);
		executeTestsObj.put("issueId", issueId);
		StringEntity executeTestsJSON = null;
		try {
			executeTestsJSON = new StringEntity(executeTestsObj.toString());
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		HttpResponse response = PUT(updateExecutionUri, executeTestsJSON);
		int statusCode = response.getStatusLine().getStatusCode();
		String statusResponseObj,executionStatus = "No Test Executed";
		HttpEntity entity = response.getEntity();
		if (statusCode >= 200 && statusCode < 300) {
			String string = null;
			try {
				string = EntityUtils.toString(entity);
				JSONObject executionResponseObj = new JSONObject(string);
				executionStatus = executionResponseObj.getString("executionStatus");
				System.out.println(executionResponseObj.getString("issueKey") + "--" + executionStatus);
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		} else {
			try {
				String string = null;
				string = EntityUtils.toString(entity);
				JSONObject executionResponseObj = new JSONObject(string);
				cycleId = executionResponseObj.getString("clientMessage");
				// System.out.println(executionResponseObj.toString());
				throw new ClientProtocolException("Unexpected response status: " + statusCode);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			}
		}
		return executionStatus;
	}
	//#################################### Requests to JIRA ##############################################
	/**
	 * @Description - Get Response
	 * @param url
	 * @return HttpResponse
	 * @throws URISyntaxException
	 */
	public static HttpResponse GET(String url) throws URISyntaxException {
		URI uri = new URI(url);
		System.out.println("FINAL API : " +uri.toString());
		HttpResponse response = null;
		HttpClient restClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(uri);
		getRequest.addHeader("authorization", "Basic "+authentication);
		getRequest.addHeader("Content-Type", "application/json");
		try {
			response = restClient.execute(getRequest);
		} catch (ClientProtocolException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return response;
	}

	/**
	 * @Description - Put Response
	 * @param uriStr
	 * @param payload
	 * @return
	 * @throws URISyntaxException
	 */
	public static HttpResponse PUT(String uriStr, StringEntity payload) throws URISyntaxException {
		URI uri = new URI(uriStr);
		System.out.println(uri.toString());
		HttpResponse response = null;
		HttpClient restClient = new DefaultHttpClient();
		HttpPut putRequest = new HttpPut(uri);
		putRequest.addHeader("authorization", "Basic "+authentication);
		putRequest.addHeader("Content-Type", "application/json");
		putRequest.setEntity(payload);
		try {
			response = restClient.execute(putRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * @Description - Post Response
	 * @param uriStr
	 * @param payload
	 * @return
	 * @throws URISyntaxException
	 */
	public static HttpResponse POST(String uriStr, StringEntity entityJSON) throws URISyntaxException {
		URI uri = new URI(uriStr);
		System.out.println(uri.toString());
		HttpResponse response = null;
		HttpClient restClient = new DefaultHttpClient();
		HttpPost postRequest = new HttpPost(uri);
		postRequest.addHeader("Content-Type", "application/json");
		postRequest.addHeader( "Authorization","Basic "+authentication);
		postRequest.setEntity(entityJSON);
		try {
			response = restClient.execute(postRequest);
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * @Description - Post Response
	 * @param uriStr
	 * @param payload
	 * @return
	 * @throws URISyntaxException
	 */
	public static void LoadJiraProperties(){
		String fileName = "resources/jira.properties";
		Properties properties = new Properties();
		Connection connection = null;
		InputStream is = null;
		try {
			is = new FileInputStream(fileName);
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		jiraBaseUrl = properties.getProperty("jiraUrl");
		projectId=properties.getProperty("projectId");
		versionId=properties.getProperty("versionId");
		authentication = properties.getProperty("authentication");
		ZAPI_CREATE_CYCLE = jiraBaseUrl+"/rest/zapi/latest/cycle?";
		ZAPI_ADD_TESTS_TO_CYCLE = jiraBaseUrl+"/rest/zapi/latest/execution/addTestsToCycle/";
		ZAPI_SEARCH_ISSUES = jiraBaseUrl+"/rest/api/2/search";
		ZAPI_GET_EXECUTIONS = jiraBaseUrl+"/rest/zapi/latest/zql/executeSearch?zqlQuery=";
		ZAPI_UPDATE_EXECUTION = jiraBaseUrl+"/rest/zapi/1.0/execution/";
	}


}
