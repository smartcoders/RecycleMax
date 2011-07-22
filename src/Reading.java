import java.util.Date;

class Reading 
{
	public Date date() 
	{
		return _date;
	}
	public int amount() 
	{
		return _amount;
	}
	
	public Reading(int amount, Date date) 
	{
		_amount = amount;
		_date = date;
	}
	
	private Date _date;
	private int _amount;
}