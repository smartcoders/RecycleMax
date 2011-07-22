class Dollars {
	private Double amount;

	public Dollars(Double amount) {
		this.amount = amount;
	}

	public Dollars(Dollars dollars) {
		this.amount = dollars.amount;
	}

	public Dollars(int amount) {
		this((double) amount);
	}

	public Dollars plus(Dollars dollars) {
		return new Dollars(amount + dollars.amount);
	}

	public Dollars plus(int amount) {
		return plus(new Dollars(amount));
	}

	public Dollars minus(Dollars dollars) {
		return new Dollars(amount - dollars.amount);
	}

	public Dollars times(double multiplier) {
		return new Dollars(amount * multiplier);
	}

	public boolean isGreaterThan(Dollars dollars) {
		return amount > dollars.amount;
	}

	public Dollars min(Dollars dollars) {
		return new Dollars(Math.min(amount, dollars.amount));
	}

	public Dollars max(Dollars dollars) {
		return new Dollars(Math.max(amount, dollars.amount));
	}
	
	@Override
	public String toString() {
		return String.format("$ %.2f", amount);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((amount == null) ? 0 : amount.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dollars other = (Dollars) obj;
		if (amount == null) {
			if (other.amount != null)
				return false;
		} else if (amount - other.amount > 0.1D)
			return false;
		return true;
	}
	
	
	
	
}
