package com.douzone.jblog.dto;

public class JsonResult {

	private String result;	/** "success" or "fail" */
	private Object data;	/** if success, data Set */
	private String message;	/** if fail, message set */
	
	private JsonResult() {

	}
	
	private JsonResult(Object data) {
		result = "success";
		this.data = data; 
		
	}
	
	private JsonResult(String message) {
		result = "fail";
		this.message = message; 
		
	}
	
	public static JsonResult success(Object data) {
		return new JsonResult(data);
	}
	
	public static JsonResult fail(String message) {
		return new JsonResult(message);
	}
	
	// json은 getter 꼭 필요!!
	// setter 는 api.UserController에서 맘대로 할 수 있기 때문에 안씀
	public String getResult() {
		return result;
	}
	public Object getData() {
		return data;
	}
	public String getMessage() {
		return message;
	}
	
}
