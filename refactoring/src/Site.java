public class Site {
	protected Zone _zone;
	protected Reading[] _readings = new Reading[1000];

	public Site(Zone zone) {
		this._zone = zone;
	}

	public int lastReadingIndex() {
		int i = 0;
		for (i = 0; _readings[i] != null; i++)
			;
		return i;
	}
}
