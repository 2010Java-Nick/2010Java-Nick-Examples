package MoneyAppPojos;

public class Credit {
	
	private String cardNum;
	
	private String cardType;
	
	private int expirationDate;
	
	private int CVV;
	
	private double balance;

	/**
	 * Creation of credit obj
	 * @param cardNum
	 * @param cardType
	 * @param expirationDate
	 * @param cVV
	 * @param balance
	 */
	public Credit(String cardNum, String cardType, int expirationDate, int cVV, double balance) {
		super();
		this.cardNum = cardNum;
		this.cardType = cardType;
		this.expirationDate = expirationDate;
		CVV = cVV;
		this.balance = balance;
	}



	public Credit() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public String getCardType() {
		return cardType;
	}

	public void setCardType(String cardType) {
		this.cardType = cardType;
	}

	public int getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(int expirationDate) {
		this.expirationDate = expirationDate;
	}

	public int getCVV() {
		return CVV;
	}

	public void setCVV(int cVV) {
		CVV = cVV;
	}
	
	public double getBalance() {
		return balance;
	}



	public void setBalance(double balance) {
		this.balance = balance;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + CVV;
		long temp;
		temp = Double.doubleToLongBits(balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((cardNum == null) ? 0 : cardNum.hashCode());
		result = prime * result + ((cardType == null) ? 0 : cardType.hashCode());
		result = prime * result + expirationDate;
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
		Credit other = (Credit) obj;
		if (CVV != other.CVV)
			return false;
		if (Double.doubleToLongBits(balance) != Double.doubleToLongBits(other.balance))
			return false;
		if (cardNum == null) {
			if (other.cardNum != null)
				return false;
		} else if (!cardNum.equals(other.cardNum))
			return false;
		if (cardType == null) {
			if (other.cardType != null)
				return false;
		} else if (!cardType.equals(other.cardType))
			return false;
		if (expirationDate != other.expirationDate)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Credit [cardNum=" + cardNum + ", cardType=" + cardType + ", expirationDate=" + expirationDate + ", CVV="
				+ CVV + ", balance=" + balance + "]";
	}
	
	
}
