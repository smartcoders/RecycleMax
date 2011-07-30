import java.util.Date;

public abstract class Site {
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
		if (isLeapYear(arg)) {
			result++;
		}
		;
		return result;
	}

	private boolean isLeapYear(Date arg) {
		return (arg.getYear() % 4 == 0)
				&& ((arg.getYear() % 100 != 0) || ((arg.getYear() + 1900) % 400 == 0));
	}

	protected double getSummerFraction(Date start, Date end){
		double summerFraction;
		
		if (start.after(_zone.summerEnd()) || end.before(_zone.summerStart())) {
			summerFraction = 0;
		} else if (!start.before(_zone.summerStart()) && !start.after(_zone.summerEnd()) && !end.before(_zone.summerStart()) && !end.after(_zone.summerEnd())) {
			summerFraction = 1;
		} else {
			double summerDays;
			
			if (start.before(_zone.summerStart()) || start.after(_zone.summerEnd())) { // end is in the summer
				summerDays = dayOfYear(end) - dayOfYear(_zone.summerStart()) + 1;
			} else { // start is in summer
				summerDays = dayOfYear(_zone.summerEnd()) - dayOfYear(start) + 1;
			}
			
			return summerFraction = summerDays / (dayOfYear(end) - dayOfYear(start) + 1);
			
		}

		return summerFraction;
	}
	
	public abstract Dollars charge ();
	
	public abstract void addReading(Reading newReading);
	
}
