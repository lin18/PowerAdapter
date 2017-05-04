package com.lin.poweradapter.example.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class JSONUtils {

	/**
	 * 获取JsonObject
	 * @param json
	 * @return
	 */
	public static JsonObject parseJson(String json){
		JsonParser parser = new JsonParser();
		JsonObject jsonObj = parser.parse(json).getAsJsonObject();
		return jsonObj;
	}

	/**
	 * 根据json字符串返回Map对象
	 * @param json
	 * @return
	 */
	public static Map<String, Object> toMap(String json){
		return toMap(parseJson(json));
	}

	/**
	 * 将JSONObjec对象转换成Map-List集合
	 * @param json
	 * @return
	 */
	public static Map<String, Object> toMap(JsonObject json){
		Map<String, Object> map = new HashMap<String, Object>();
		Set<Map.Entry<String, JsonElement>> entrySet = json.entrySet();
		for (Iterator<Map.Entry<String, JsonElement>> iter = entrySet.iterator(); iter.hasNext(); ){
			Map.Entry<String, JsonElement> entry = iter.next();
			String key = entry.getKey();
			Object value = entry.getValue();
			if(value instanceof JsonArray)
				map.put((String) key, toList((JsonArray) value));
			else if(value instanceof JsonObject)
				map.put((String) key, toMap((JsonObject) value));
			else
				map.put((String) key, value);
		}
		return map;
	}

	/**
	 * 将JSONArray对象转换成List集合
	 * @param json
	 * @return
	 */
	public static List<Object> toList(JsonArray json){
		List<Object> list = new ArrayList<Object>();
		for (int i=0; i<json.size(); i++){
			Object value = json.get(i);
			if(value instanceof JsonArray){
				list.add(toList((JsonArray) value));
			}
			else if(value instanceof JsonObject){
				list.add(toMap((JsonObject) value));
			}
			else{
				list.add(value);
			}
		}
		return list;
	}

	public static final <T> T toObject(String json, TypeToken<T> typeToken) {
		try {
			return new Gson().fromJson(json, typeToken.getType());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static final <T> T toObject(String json, Class<T> clazz){
		try {
			return new Gson().fromJson(json, clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static final <T> T toObject(JSONObject json, Class<T> clazz) {
		try {
			return new Gson().fromJson(json.toString(), clazz);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析某个json节点对应的内容为对象
	 * @param json
	 * @param tag
	 * @param clazz
	 * @return
	 */
	public static final <T>T toObject(JSONObject json, String tag, Class<T> clazz) {
		try {
			if(json.has(tag) && !json.isNull(tag)){
				return new Gson().fromJson(json.getJSONObject(tag).toString(), clazz);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解析jsonArray节点的数据到集合中
	 * @param json
	 * @param tag 节点名称
	 * @param typeToken ,例如： new TypeToken<ArrayList<User>>(){}
	 * @return
	 */
	@SuppressWarnings("rawtypes") 
	public static <T> ArrayList<T> toList(JSONObject json, String tag, TypeToken typeToken) {
		try {
			if(json.has(tag) && !json.isNull(tag)){
				return new Gson().fromJson(json.getJSONArray(tag).toString(), typeToken.getType());
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}


	@SuppressWarnings("rawtypes")
	public static <T> ArrayList<T> toList(String json, TypeToken typeToken) {
		return new Gson().fromJson(json, typeToken.getType());
	}

	/**
	 * 转换成json
	 * @param list
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static String toJson(ArrayList list) {
		return new Gson().toJson(list);
	}
}
