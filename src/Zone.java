import java.util.Date;

class Zone {
	public Zone persist() 
	{
		Registry.add("Zone", this);
		return this;
	}
	
	public static Zone get (String name) 
	{
		return (Zone) Registry.get("Zone", name);
	}
	
	public Date summerEnd() 
	{
		return _summerEnd;
	}
	
	public Date summerStart() 
	{
		return _summerStart;
	}
	
	public double winterRate() 
	{
		return _winterRate;
	}
	
	public double summerRate() 
	{
		return _summerRate;
	}
	
	Zone (String name, double summerRate, double winterRate, Date summerStart, Date summerEnd) 
	{
		_name = name;
		_summerRate = summerRate;
		_winterRate = winterRate;
		_summerStart = summerStart;
		_summerEnd = summerEnd;
	};
	
	private Date _summerEnd;
	private Date _summerStart;
	private double _winterRate;
	private double _summerRate;
	private String _name;
}