import java.util.HashMap;
import java.util.Map;

class Registry {

	private static Map<String, Zone> registry = new HashMap<String, Zone>();

	public static void add(Zone zone) {
		registry.put(zone.name(), zone);
	}

	public static Zone get(String name) {
		return registry.get(name);
	}

}
