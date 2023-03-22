package com.toll.calulator.utility;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.toll.calulator.vo.InterChangeMap;
import com.toll.calulator.vo.Route;
import com.toll.calulator.vo.TollCalculatorRequest;
import com.toll.calulator.vo.TollCalculatorResponse;

public class ApplicationUtilty {

	private static HashMap<String, InterChangeMap> interChangesMap = new HashMap<>();

	private static final Double costPerKm = 0.2567;

	List<InterChangeMap> listMap = new ArrayList<>();

	static {
		if (interChangesMap.isEmpty()) {
			loadInterChangeMap();
		}

	}

	public static Double calculateTollCost(String entryLoc, String exitLoc) {
		InterChangeMap entryRouteDetails = interChangesMap.get(entryLoc);
		InterChangeMap exitRouteDetails = interChangesMap.get(exitLoc);
		String exitRouteId = null != exitRouteDetails ? exitRouteDetails.getRouteId() : null;
		List<Route> entryRouteIdList = null != entryRouteDetails ? entryRouteDetails.getRoutes() : null;
		Double distance = entryRouteIdList.stream().filter(rId -> rId.getToId() == Integer.parseInt(exitRouteId))
				.mapToDouble(r -> r.getDistance()).findFirst().getAsDouble();

		return distance * costPerKm;

	}

	private static void loadInterChangeMap() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			File from = new File(".\\src\\main\\resources\\templates\\interchanges.json");
			Map<String, Object> data = mapper.readValue(from, new TypeReference<Map<String, Object>>() {
			});
			JSONObject jObject = new JSONObject(data); // json
			JSONObject mapData = jObject.getJSONObject("locations"); // get data object
			Iterator<String> keysItr = mapData.keys();
			while (keysItr.hasNext()) {
				String key = keysItr.next();
				JSONObject value = (JSONObject) mapData.get(key);
				InterChangeMap objMap = mapper.readValue(value.toString(), InterChangeMap.class);
				objMap.setRouteId(key);
				interChangesMap.put(objMap.getName(), objMap);
			}
		} catch (IOException e) {
			interChangesMap = new HashMap<>();
		}

	}

	public static boolean validateRequest(String entryLoc, String exitLoc) {
		if (interChangesMap.isEmpty()) {
			loadInterChangeMap();
		}
		return !interChangesMap.isEmpty() && interChangesMap.containsKey(entryLoc)
				&& interChangesMap.containsKey(exitLoc);

	}

	public static TollCalculatorResponse createResponse(TollCalculatorRequest request, Double cost, String resDesc) {
		TollCalculatorResponse res = new TollCalculatorResponse();

		res.setEntryPostiton(request.getEntryPostiton());
		res.setExitPosition(request.getExitPosition());
		res.setCost(cost);
		res.setDescription("Total Cost calucalated successfully");
		if (resDesc != null) {
			res.setDescription("Error occured while calculating : " + resDesc + " for this data");
		}

		return res;

	}
}
