import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DollarsTest {
	
	private Dollars zero;
	private Dollars fraction;
	private Dollars one;
	
	@Before
	public void setUp() throws Exception {
		zero = new Dollars(0);
		one = new Dollars(1);
		fraction = new Dollars(1.75);
	}

	@Test
	public void itShouldCalculateSumWithDollars() {
		Dollars sum = fraction.plus(one);
		Assert.assertEquals(new Dollars(2.75), sum);
	}
	
	@Test
	public void itShouldCalculateSumWithInts() {
		Dollars sum = zero.plus(1);
		Assert.assertEquals(one, sum);
	}
	
	public void itShouldReturnItSelfWhenIAddZero(){
		Dollars sum = one.plus(zero);
		Assert.assertEquals(one, sum);
	}

	@Test
	public void itShouldCalculateDiferences() {
		Assert.assertEquals(one, fraction.minus(new Dollars(0.75)));
	}

	@Test
	public void itShouldReturnZeroWhenIMultiplyByZero() {
		Assert.assertEquals(zero, one.times(0));
	}
	
	@Test
	public void itShouldReturnItSelfWhenIMultiplyByOne(){
		Assert.assertEquals(fraction, fraction.times(1));
	}

	@Test
	public void itShouldRecognizeWheAnotherDollarIsGreaterThenHim() {
		Assert.assertTrue(one.isGreaterThan(zero));
	}

	@Test
	public void itShouldReturnTheMinimun() {
		Assert.assertEquals(zero, zero.min(fraction));
	}

	@Test
	public void testMax() {
		Assert.assertEquals(one, zero.max(one));
	}
	
	@Test
	public void itShouldPrintItsValue(){
		Assert.assertEquals("$ 0,00", zero.toString());
	}
	
}
