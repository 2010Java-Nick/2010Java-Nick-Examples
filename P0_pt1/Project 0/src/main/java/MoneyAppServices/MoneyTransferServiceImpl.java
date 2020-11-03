package MoneyAppServices;

import MoneyAppPojos.Bank;
import MoneyAppPojos.Credit;

public class MoneyTransferServiceImpl implements MoneyTransferService{
	
	/**
	 * Transfers money between credit objs
	 * Credit fromUserCredit, Credit toUserCredit, double amount
	 * @return boolean
	 */
	@Override
	public boolean SendMoney(Credit fromUserCredit, Credit toUserCredit, double amount) {
		if (fromUserCredit.getBalance()>amount) {
			fromUserCredit.setBalance(fromUserCredit.getBalance()-amount);
			toUserCredit.setBalance(toUserCredit.getBalance()+amount);
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Transfers money from bank to credit obj
	 * Bank fromBankObj, Credit toCardObj, double amount
	 * @return boolean
	 */
	@Override
	public boolean AddFunds(Bank fromBankObj, Credit toCardObj, double amount) {
		if (fromBankObj.getCurrentBalance()>amount) {
			fromBankObj.setCurrentBalance(fromBankObj.getCurrentBalance()-amount);
			toCardObj.setBalance(toCardObj.getBalance()+amount);
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Transfers money from credit to bank obj
	 * Credit fromUserCard, Bank toUserBank, double amount
	 * @return boolean
	 */
	@Override
	public boolean Removefunds(Credit fromUserCard, Bank toUserBank, double amount) {
		if (fromUserCard.getBalance()>amount) {
			toUserBank.setCurrentBalance(toUserBank.getCurrentBalance()+amount);
			fromUserCard.setBalance(fromUserCard.getBalance()-amount);
			return true;
		}
		else {
			return false;
		}
	}


	
}
