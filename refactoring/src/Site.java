import java.util.Date;

public class Site {
	protected Zone _zone;
	protected Reading[] _readings = new Reading[1000];
	protected static final double TAX_RATE = 0.05;

	public Site(Zone zone) {
		this._zone = zone;
	}
	
	public Site() {
	}

	public int lastReadingIndex() {
		int i = 0;
		for (i = 0; _readings[i] != null; i++);
		return i;
	}
	
	int dayOfYear(Date arg) {
		int result;
		// TODO fazer o calendar aqui!! 
		switch (arg.getMonth()) {
		case 0:
			result = 0;
			break;
		case 1:
			result = 31;
			break;
		case 2:
			result = 59;
			break;
		case 3:
			result = 90;
			break;
		case 4:
			result = 120;
			break;
		case 5:
			result = 151;
			break;
		case 6:
			result = 181;
			break;
		case 7:
			result = 212;
			break;
		case 8:
			result = 243;
			break;
		case 9:
			result = 273;
			break;
		case 10:
			result = 304;
			break;
		case 11:
			result = 334;
			break;
		default:
			throw new IllegalArgumentException();
		}
		;

		result += arg.getDate();
		// check leap year
		if ((arg.getYear() % 4 == 0)
				&& ((arg.getYear() % 100 != 0) || ((arg.getYear() + 1900) % 400 == 0))) {
			result++;
		}
		;
		return result;
	}

}
