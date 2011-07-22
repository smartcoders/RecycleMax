import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ 
				BusinessTester.class, 
				DisabilityTester.class,
				DollarsTest.class, 
				LifelineTester.class, 
				ResidentialTester.class 
			 })
public class AllTests {
}
