import java.util.Calendar;
import java.util.Date;

class DisabilitySite extends Site {
	private static final Dollars FUEL_TAX_CAP = new Dollars(0.10);
	private static final int CAP = 200;

	public DisabilitySite(Zone zone) {
		super(zone);
	}

	public void addReading(Reading newReading) {
		_readings[lastReadingIndex()] = newReading;
	}

	public Dollars charge() {

		int i = lastReadingIndex();

		if (i < 2) {
			throw new NullPointerException();
		}

		Reading lastReading = _readings[i - 1];
		Reading previousReading = _readings[i - 2];

		Date end = lastReading.date();
		Date start = previousReading.date();

		Calendar cal = Calendar.getInstance();
		cal.setTime(start);
		
		cal.add(Calendar.DATE, 1);
		start = cal.getTime();
		
		//start.setDate(start.getDate() + 1); // set to begining of period

		return charge(lastUsage(lastReading, previousReading), start, end);
	}

	private int lastUsage(Reading lastReading, Reading previousReading) {
		return lastReading.amount() - previousReading.amount();
	}

	private Dollars charge(int fullUsage, Date start, Date end) {
		Dollars result;
		int usage = Math.min(fullUsage, CAP);

		double summerFraction = getSummerFraction(start, end); 

		result = new Dollars((usage * _zone.summerRate() * summerFraction) + (usage * _zone.winterRate() * (1 - summerFraction)));
		result = result.plus(new Dollars(Math.max(fullUsage - usage, 0) * 0.062));
		result = result.plus(new Dollars(result.times(TAX_RATE)));
		
		Dollars fuel = new Dollars(fullUsage * 0.0175);
		
		result = result.plus(fuel);
		result = new Dollars(result.plus(fuel.times(TAX_RATE).min(FUEL_TAX_CAP)));

		return result;
	}
	


}
