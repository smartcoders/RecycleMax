import java.util.Date;

class DisabilitySite extends Site {
	private static final Dollars FUEL_TAX_CAP = new Dollars(0.10);
	private static final double TAX_RATE = 0.05;
	private static final int CAP = 200;

	public DisabilitySite(Zone zone) {
		super(zone);
	}

	public void addReading(Reading newReading) {
		_readings[lastReadingIndex()] = newReading;
	}

	public Dollars charge() {

		int i = lastReadingIndex();

		if (i < 2)
			throw new NullPointerException();

		Reading lastReading = _readings[i - 1];
		Reading previousReading = _readings[i - 2];

		Date end = lastReading.date();
		Date start = previousReading.date();

		start.setDate(start.getDate() + 1); // set to begining of period

		return charge(lastUsage(lastReading, previousReading), start, end);
	}

	private int lastUsage(Reading lastReading, Reading previousReading) {
		return lastReading.amount() - previousReading.amount();
	}

	private Dollars charge(int fullUsage, Date start, Date end) {
		Dollars result;
		double summerFraction;
		int usage = Math.min(fullUsage, CAP);
		if (start.after(_zone.summerEnd()) || end.before(_zone.summerStart()))
			summerFraction = 0;
		else if (!start.before(_zone.summerStart())
				&& !start.after(_zone.summerEnd())
				&& !end.before(_zone.summerStart())
				&& !end.after(_zone.summerEnd()))
			summerFraction = 1;
		else {
			double summerDays;
			if (start.before(_zone.summerStart()) || start.after(_zone.summerEnd())) { // end is in the summer
				summerDays = dayOfYear(end) - dayOfYear(_zone.summerStart()) + 1;
			} else { // start is in summer
				summerDays = dayOfYear(_zone.summerEnd()) - dayOfYear(start) + 1;
			}
			summerFraction = summerDays	/ (dayOfYear(end) - dayOfYear(start) + 1);
		}

		result = new Dollars((usage * _zone.summerRate() * summerFraction)
				+ (usage * _zone.winterRate() * (1 - summerFraction)));
		result = result
				.plus(new Dollars(Math.max(fullUsage - usage, 0) * 0.062));
		result = result.plus(new Dollars(result.times(TAX_RATE)));
		Dollars fuel = new Dollars(fullUsage * 0.0175);
		result = result.plus(fuel);
		result = new Dollars(
				result.plus(fuel.times(TAX_RATE).min(FUEL_TAX_CAP)));
		return result;
	}

	int dayOfYear(Date arg) {
		int result;
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
