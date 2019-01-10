package com.cg.app.account.service;

import java.sql.SQLException;
import java.util.List;

import com.cg.app.account.SavingsAccount;
import com.cg.app.exception.AccountNotFoundException;
import com.cg.app.exception.InvalidInputException;

public interface SavingsAccountService {

	SavingsAccount createNewAccount(String accountHolderName, double accountBalance, boolean salary) throws ClassNotFoundException, SQLException;

	SavingsAccount updateAccount(SavingsAccount account) throws ClassNotFoundException, SQLException;

	SavingsAccount getAccountById(int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException;

	SavingsAccount deleteAccount(int accountNumber) throws ClassNotFoundException, SQLException, InvalidInputException;
	
	List<SavingsAccount> getAllSavingsAccount() throws ClassNotFoundException, SQLException;

	void fundTransfer(SavingsAccount sender, SavingsAccount receiver, double amount) throws ClassNotFoundException, SQLException;
	void deposit(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException;
	void withdraw(SavingsAccount account, double amount) throws ClassNotFoundException, SQLException;

	double checkAccountBalance(int accountNumber) throws ClassNotFoundException, SQLException, InvalidInputException;
	
	List<SavingsAccount> getAllSavingsAccountsSortedByNames() throws ClassNotFoundException, SQLException;

	List<SavingsAccount> getAllSavingsAccountsSortedByRange(double minimum,
			double maximum) throws ClassNotFoundException, SQLException;

	SavingsAccount getAccountHolderName(String accountToSearch) throws ClassNotFoundException, SQLException, AccountNotFoundException;

	List<SavingsAccount> getAccountByBalanceRange(double minimumBalance, double highestBalance) throws ClassNotFoundException, SQLException, AccountNotFoundException;

	List<SavingsAccount> sort(int choice) throws ClassNotFoundException, SQLException;


}










