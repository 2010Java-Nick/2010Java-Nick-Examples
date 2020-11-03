package MoneyAppServices;

import MoneyAppPojos.Bank;
import MoneyAppPojos.Credit;

public interface MoneyTransferService {
	
	public boolean SendMoney(Credit fromUserCredit, Credit toUserCredit,double amount);
	
	public boolean AddFunds(Bank fromBankObj, Credit toCardObj,double amount);
	
	public boolean Removefunds(Credit fromUserCard, Bank toUserBank,double amount);
	
}
