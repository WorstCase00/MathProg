package com.mst.mp.kmst.experiment;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OptimalSolutions {

	private static LinkedHashMap<String, Map<Integer, Double>> INSTANCE_K_OPT = Maps.newLinkedHashMap();

	static {
		INSTANCE_K_OPT.put("g01", ImmutableMap.of(2, 46d, 5, 477d));
		INSTANCE_K_OPT.put("g02", ImmutableMap.of(4, 373d, 10, 1390d));
		INSTANCE_K_OPT.put("g03", ImmutableMap.of(10, 725d, 25, 3074d));
		INSTANCE_K_OPT.put("g04", ImmutableMap.of(14, 909d, 35, 3292d));
		INSTANCE_K_OPT.put("g05", ImmutableMap.of(20, 1235d, 50, 4898d));
		INSTANCE_K_OPT.put("g06", ImmutableMap.of(40, 2068d, 100, 6705d));
		INSTANCE_K_OPT.put("g07", ImmutableMap.of(60, 1335d, 150, 4534d));
		INSTANCE_K_OPT.put("g08", ImmutableMap.of(80, 1620d, 200, 5787d));

	}

	public static double getValue(String instance, int k) {
		return INSTANCE_K_OPT.get(instance).get(k);
	}

	public static int getLoK(String instance) {
		return INSTANCE_K_OPT.get(instance).keySet().stream().min(Integer::compareTo).get();
	}

	public static int getHiK(String instance) {
		return INSTANCE_K_OPT.get(instance).keySet().stream().max(Integer::compareTo).get();
	}

	public static List<String> getOrderedInstanceKeys() {
		return Lists.newArrayList(INSTANCE_K_OPT.keySet());
	}
}
