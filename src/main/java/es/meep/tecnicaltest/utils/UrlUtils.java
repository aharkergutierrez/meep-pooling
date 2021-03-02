package es.meep.tecnicaltest.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;

public class UrlUtils {

	public static String replaceValues(String urlReturnValue, String json, List<String> variablesInUrl) {
		for (String variable : variablesInUrl) {
			String variableToReplace = readVariable(json, variable);
			urlReturnValue = urlReturnValue.replace("{" + variable + "}", variableToReplace);
		}
		return urlReturnValue;
	}

	public static String readVariable(String json, String variable) {
		DocumentContext jsonContext = JsonPath.parse(json);

		Object jsonValue = jsonContext.read(variable);

		if (Objects.isNull(jsonValue)) {
			return "";
		}

		if (jsonValue instanceof Collection) {
			return (String) ((Collection) jsonValue).stream().map(n -> n.toString()).collect(Collectors.joining(","));
		} else {
			return jsonValue.toString();
		}
	}

	public static List<String> findUrlVariables(String urlReturnValue) {
		List<String> variables = new ArrayList<>();
		Map<Integer, Integer> openAndCloseIndexes = findIndexesPairs(urlReturnValue);
		openAndCloseIndexes.keySet().forEach(indexOpen -> {
			variables.add(urlReturnValue.substring(indexOpen + 1, openAndCloseIndexes.get(indexOpen)));
		});
		return variables;
	}

	public static Map<Integer, Integer> findIndexesPairs(String urlReturnValue) {
		List<Integer> openBrakets = findIndexes(urlReturnValue, "{");
		List<Integer> closeBrakets = findIndexes(urlReturnValue, "}");
		Map<Integer, Integer> openAndCloseIndexes = new HashMap<>();
		for (int i = 0; i < openBrakets.size(); ++i) {
			openAndCloseIndexes.put(openBrakets.get(i), closeBrakets.get(i));
		}
		return openAndCloseIndexes;
	}

	public static List<Integer> findIndexes(String urlReturnValue, String bracket) {
		List<Integer> indexes = new ArrayList<>();
		int index = urlReturnValue.indexOf(bracket);
		while (index >= 0) {
			indexes.add(index);
			index = urlReturnValue.indexOf(bracket, index + 1);
		}
		return indexes;
	}

	public static Double measure(Double lat1, Double lon1, Double lat2, Double lon2){  // generally used geo measurement function
	    Double R = 6378.0; // Radius of earth in KM
	    Double dLat = lat2 * Math.PI / 180 - lat1 * Math.PI / 180;
	    Double dLon = lon2 * Math.PI / 180 - lon1 * Math.PI / 180;
	    Double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
	    Math.cos(lat1 * Math.PI / 180) * Math.cos(lat2 * Math.PI / 180) *
	    Math.sin(dLon/2) * Math.sin(dLon/2);
	    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
	    Double d = R * c;
	    return d * 1000; // meters
	}
	
	
}
