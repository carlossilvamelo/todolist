package com.api.todolist.util;

public class SpinalCaseConverter {

	public static String toSpinalCase(String str) {
		return str.trim().replaceAll("([a-z])([A-Z])", "$1-$2").replaceAll("_", "-").replaceAll(" ", "-").toLowerCase();
	}
}
