package com.toll.calulator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toll.calulator.utility.ApplicationUtilty;
import com.toll.calulator.vo.InterChangeMap;
import com.toll.calulator.vo.Route;

@SpringBootTest
class CalulatorApplicationTests {

	@Test
	void contextLoads() {
	
		ObjectMapper mapper = new ObjectMapper();
		File from = new File(".\\src\\main\\resources\\templates\\interchanges.json");

		try {
			Map<String, Object> data = mapper.readValue(from, new TypeReference<Map<String, Object>>() {
			});
			JSONObject jObject = new JSONObject(data); // json
			JSONObject mapData = jObject.getJSONObject("locations"); // get data object
			//List<InterChangeMap> listMap = new ArrayList<>();
			Map<String,InterChangeMap> listMap = new HashMap<>();
			Iterator<String> keysItr = mapData.keys();
			
			while (keysItr.hasNext()) {
				String key = keysItr.next();
				JSONObject value = (JSONObject) mapData.get(key);
				
				InterChangeMap objMap = mapper.readValue(value.toString(), InterChangeMap.class);
				objMap.setRouteId(key);
				listMap.put(objMap.getName(), objMap);
				
			}
			
			String entryName = "Dundas Street";
			String exitName="Appleby Line";
			
			InterChangeMap im1 = listMap.get(entryName);
			InterChangeMap im2 = listMap.get(exitName);
			
			String exitRouteId = im2.getRouteId();
			//Double distance = 0.0;
			List<Route> entryRouteIdList = im1.getRoutes();
			
			Double distance = entryRouteIdList.stream().filter(rId-> 
			rId.getToId() == Integer.parseInt(exitRouteId)).mapToDouble(r -> r.getDistance()).findFirst().getAsDouble();
	
			System.out.println(distance);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}