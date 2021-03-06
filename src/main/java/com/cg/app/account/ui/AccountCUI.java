package com.cg.app.account.ui;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cg.app.account.SavingsAccount;
import com.cg.app.account.service.SavingsAccountService;
import com.cg.app.account.service.SavingsAccountServiceImpl;
import com.cg.app.exception.AccountNotFoundException;
import com.cg.app.exception.InvalidInputException;

@Component
public class AccountCUI {
	private Scanner scanner = new Scanner(System.in);
	@Autowired
	private SavingsAccountService savingsAccountService = new SavingsAccountServiceImpl();

	public void start() {

		do {
			System.out.println("****** Welcome to Money Money Bank********");
			System.out.println("1. Open New Savings Account");
			System.out.println("2. Update Account");
			System.out.println("3. Close Account");
			System.out.println("4. Search Account");
			System.out.println("5. Withdraw");
			System.out.println("6. Deposit");
			System.out.println("7. FundTransfer");
			System.out.println("8. Check Current Balance");
			System.out.println("9. Get All Savings Account Details");
			System.out.println("10. Sort Accounts");
			System.out.println("11. Exit");
			System.out.println();
			System.out.println("Make your choice: ");

			int choice = scanner.nextInt();

			performOperation(choice);

		} while (true);
	}

	private void performOperation(int choice) {
		switch (choice) {
		case 1:
			acceptInput("SA");
			break;
		case 2:
			updateAccount();
			break;
		case 3:
			deleteAccount();
			break;
		case 4:
			searchAccount();
			break;
		case 9:
			showAllAccounts();
			break;
		case 5:
			withdraw();
			break;
		case 6:
			deposit();
			break;
		case 7:
			fundTransfer();
			break;
		case 8:
			currentBalance();
			break;
		case 10:
			sortMenu();
			break;
		case 11:
			System.exit(0);
			break;
		default:
			System.err.println("Invalid Choice!");
			break;
		}

	}

	private void searchAccount() {
		System.out.println(
				"Choose how you want to search : \n 1.Using Account Number \n 2.Using Accountholder Name \n 3.Using Account Balance Range");
		int choosedValue = scanner.nextInt();

		switch (choosedValue) {
		case 1:
			System.out.println("Enter account number to search: ");
			int accountNumber = scanner.nextInt();

			SavingsAccount account;
			try {
				account = savingsAccountService.getAccountById(accountNumber);
				System.out.println(account);
			} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
				e.printStackTrace();
			}
			break;

		case 2:
			System.out.println("Enter accountHolderName to search: ");
			String accountToSearch = scanner.nextLine();
			accountToSearch = scanner.nextLine();

			try {
				account = savingsAccountService.getAccountHolderName(accountToSearch); // account
																						// reference
																						// type is
																						// already
																						// created in
				System.out.println(account);
			} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
				e.printStackTrace();
			}
			break;

		case 3:
			System.out.println("Enter minimun balance: ");
			double minimumBalance = scanner.nextInt();

			System.out.println("Enter highest balance: ");
			double highestBalance = scanner.nextInt();

			try {
				List<SavingsAccount> savingAccount = savingsAccountService.getAccountByBalanceRange(minimumBalance,
						highestBalance);
				System.out.println(savingAccount);
			} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	private void updateAccount() {
		System.out.println("Enter account number to update");
		int accountNumber = scanner.nextInt();
		SavingsAccount savingsAccount;
		try {
			savingsAccount = savingsAccountService.getAccountById(accountNumber);
			
			System.out.println(
					"enter 1. update Account holderName \n 2. Update SalaryType \n 3. Update both HolderName and SalaryType ");
			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				System.out.println("Enter name to change account holder name");
				String accountHolderName = scanner.nextLine();
				accountHolderName = scanner.nextLine();
				try {
					savingsAccount = savingsAccountService.getAccountById(accountNumber);
					savingsAccount.getBankAccount().setAccountHolderName(accountHolderName);
					savingsAccountService.updateAccount(savingsAccount);
				} catch (ClassNotFoundException | SQLException | AccountNotFoundException e1) {
					e1.printStackTrace();
				}

				break;
			case 2:
				System.out.println("Enter salarytype to change account salarytype");
				boolean salary = scanner.nextBoolean();
				try {
					savingsAccount = savingsAccountService.getAccountById(accountNumber);
					savingsAccount.setSalary(salary);
					savingsAccountService.updateAccount(savingsAccount);
				} catch (ClassNotFoundException | SQLException | AccountNotFoundException e1) {
					e1.printStackTrace();
				}

				break;
			case 3:
				System.out.println("enter the accountHolderName");
				accountHolderName = scanner.nextLine();
				accountHolderName = scanner.nextLine();
				System.out
				.println("Enter salarytype to change account salarytype");
				salary = scanner.nextBoolean();
				try {
					savingsAccount = savingsAccountService.getAccountById(accountNumber);
					savingsAccount.setSalary(salary);
					savingsAccount.getBankAccount().setAccountHolderName(accountHolderName);
					savingsAccountService.updateAccount(savingsAccount);
				} catch (ClassNotFoundException | SQLException | AccountNotFoundException e1) {

					e1.printStackTrace();
				}
				break;
			default:
				System.err.println("Invalid Choice!");
				break;

			}
		} catch (ClassNotFoundException | SQLException | AccountNotFoundException e1) {
			e1.printStackTrace();
		}
	}

	private void currentBalance() {

		System.out.println("Enter account number to search");
		int accountNumber = scanner.nextInt();
		try {
			savingsAccountService.checkAccountBalance(accountNumber);
			System.out.println("current balance" + savingsAccountService.checkAccountBalance(accountNumber));
		} catch (ClassNotFoundException | SQLException | InvalidInputException e) {
			e.printStackTrace();
		}

	}

	private void deleteAccount() {
		System.out.println("Enter account number to delete");
		int deleteaccountNumber = scanner.nextInt();
		try {
			savingsAccountService.deleteAccount(deleteaccountNumber);
		} catch (ClassNotFoundException | SQLException | InvalidInputException e) {
			e.printStackTrace();
		}

	}

	private void fundTransfer() {
		System.out.println("Enter Account Sender's Number: ");
		int senderAccountNumber = scanner.nextInt();
		System.out.println("Enter Account Receiver's Number: ");
		int receiverAccountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		try {
			SavingsAccount senderSavingsAccount = savingsAccountService.getAccountById(senderAccountNumber);
			SavingsAccount receiverSavingsAccount = savingsAccountService.getAccountById(receiverAccountNumber);
			savingsAccountService.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amount);
		} catch (ClassNotFoundException | SQLException | InvalidInputException | AccountNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void deposit() {
		System.out.println("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		SavingsAccount savingsAccount = null;
		try {
			savingsAccount = savingsAccountService.getAccountById(accountNumber);
			savingsAccountService.deposit(savingsAccount, amount);
		} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void withdraw() {
		System.out.println("Enter Account Number: ");
		int accountNumber = scanner.nextInt();
		System.out.println("Enter Amount: ");
		double amount = scanner.nextDouble();
		SavingsAccount savingsAccount = null;
		try {
			savingsAccount = savingsAccountService.getAccountById(accountNumber);
			savingsAccountService.withdraw(savingsAccount, amount);
		} catch (ClassNotFoundException | SQLException | AccountNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void sortMenu() {
		do {
			System.out.println("+++++Ways of Sorting+++++++");
			System.out.println("1. Account Number");
			System.out.println("2. Account Holder Name");
			System.out.println("3. Account Balance");
			System.out.println("4. Exit from Sorting");

			int choice = scanner.nextInt();
			switch (choice) {
			case 1:
				try {
					List<SavingsAccount> sortedAccounts = savingsAccountService.sort(choice);
					System.out.println(sortedAccounts);
				} catch (ClassNotFoundException | SQLException e) {
					e.printStackTrace();
				}
				break;
			case 2:
				List<SavingsAccount> sortedAccounts;
				try {
					sortedAccounts = savingsAccountService.getAllSavingsAccountsSortedByNames();
					System.out.println(sortedAccounts);
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				break;
			case 4:
				System.exit(0);
				break;
			default:
				System.out.println("invalid choice");
			}
		} while (true);

	}

	private void showAllAccounts() {
		List<SavingsAccount> savingsAccounts;
		try {
			savingsAccounts = savingsAccountService.getAllSavingsAccount();
			for (SavingsAccount savingsAccount : savingsAccounts) {
				System.out.println(savingsAccount);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

	private void acceptInput(String type) {
		if (type.equalsIgnoreCase("SA")) {
			System.out.println("Enter your Full Name: ");
			String accountHolderName = scanner.nextLine();
			accountHolderName = scanner.nextLine();
			System.out.println("Enter Initial Balance(type na for Zero Balance): ");
			String accountBalanceStr = scanner.next();
			double accountBalance = 0.0;
			if (!accountBalanceStr.equalsIgnoreCase("na")) {
				accountBalance = Double.parseDouble(accountBalanceStr);
			}
			System.out.println("Salaried?(y/n): ");
			boolean salary = scanner.next().equalsIgnoreCase("n") ? false : true;
			createSavingsAccount(accountHolderName, accountBalance, salary);
		}
	}

	private void createSavingsAccount(String accountHolderName, double accountBalance, boolean salary) {
		try {
			savingsAccountService.createNewAccount(accountHolderName, accountBalance, salary);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
