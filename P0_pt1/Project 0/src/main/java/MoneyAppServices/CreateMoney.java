package MoneyAppServices;

import MoneyAppPojos.Bank;
import MoneyAppPojos.Credit;

public interface CreateMoney {
	public Bank createBank(String bankName, double currentBalance, String accountNumber, String routingNumber);
	
	public Credit createCredit(String cardNum, String cardType, int expirationDate, int cVV, double balance);
}
