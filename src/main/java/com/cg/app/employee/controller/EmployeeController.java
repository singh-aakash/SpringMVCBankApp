package com.cg.app.employee.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cg.app.account.SavingsAccount;
import com.cg.app.account.service.SavingsAccountService;
import com.cg.app.exception.AccountNotFoundException;

@Controller
public class EmployeeController {
	@Autowired
	private SavingsAccountService savingsAccountService;

	@RequestMapping("/")
	public String home() {
		return "home";
	}

	@RequestMapping(value = "/createAccount")
	public String createAccount(Map model) {
		return "createSavingAccount";
	}

	@RequestMapping(value = "/SavingAccountcreated")
	public String createSavingAccount(@RequestParam("accountHolderName") String accountHolderName,
			@RequestParam("accountBalance") double  accountBalance,
			@RequestParam("salary") boolean salary) throws ClassNotFoundException, SQLException
	{
		savingsAccountService.createNewAccount(accountHolderName, accountBalance, salary);
		return "redirect:getAll";

	}
	/*
	 * @RequestMapping("/createSavingAccount") public String
	 * createSavingAccount(HttpServletRequest request, Model model) throws
	 * ClassNotFoundException, SQLException {
	 * 
	 * String name = request.getParameter("accountHolderName"); String typeOfAccount
	 * = request.getParameter("accountType"); double initialBalance =
	 * Double.parseDouble(request.getParameter("accountBalance")); boolean salaried
	 * = request.getParameter("salaried").equals("no") ? false : true;
	 * SavingsAccount account = savingsAccountService.createNewAccount(name,
	 * initialBalance,salaried); return "redirect:getAll"; }
	 */

	@RequestMapping("/getAll")
	public String getAllAccount(Model model) throws ClassNotFoundException, SQLException {
		List<SavingsAccount> accounts = savingsAccountService.getAllSavingsAccount();
		model.addAttribute("accounts", accounts);
		return "accountDetails";
	}
	
	@RequestMapping(value= "/update")
	public String updateform()
	{
		return "updateInt";
	}
	
	
	@RequestMapping("/updateForm")
	public String updateAccountDetails(Model model, @RequestParam("accountNumber") int accountNumber)
			throws ClassNotFoundException, SQLException, AccountNotFoundException
	{
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		model.addAttribute("account", account);
		return "accountDetails";
	}
	
	@RequestMapping("/updateAccount")
	public String updateDetails(Model model, @RequestParam("accountNumber") int accountNumber,
			@RequestParam("accounthn") String accountHolderName,
			@RequestParam("salary") boolean salary) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount accounts = savingsAccountService.getAccountById(accountNumber);
		accounts.getBankAccount().setAccountHolderName(accountHolderName);
		accounts.setSalary(salary);
		savingsAccountService.updateAccount(accounts);
		return "redirect:getAll";
		
	}
	
	@RequestMapping("/currentBalance")
	public String getBalance()
	{
		return "currentBalanace";
	}
	
	
	@RequestMapping("/closeAccount")
	public String closeAccounts()
	{
		return "closeAccount";
	}
	
	@RequestMapping("/closedAccount")
	public String showBalance(Model model, @RequestParam("accountNumber") int accountNumber) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		model.addAttribute("account", account);
		savingsAccountService.deleteAccount(accountNumber);
		return "redirect:getAll";
	}
	
	@RequestMapping("/withdrawAmount")
	public String withdrawForm()
	{
		return "withdraw";
	}
	
	
	@RequestMapping("/amountwithdraw")
	public String withdrawOperation(Model model, @RequestParam("accountNumber") int accountNumber,
			@RequestParam("amount") double amount) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		//model.addAttribute("account", account);
		savingsAccountService.withdraw(account, amount);
		return "redirect:getAll";
	}
	
	
	@RequestMapping("/depositAmount")
	public String depositForm()
	{
		return "deposit";
	}
	
	@RequestMapping("/amountdeposit")
	public String depositOperation(Model model, @RequestParam("accountNumber") int accountNumber,
			@RequestParam("amount") double amount) throws ClassNotFoundException, SQLException, AccountNotFoundException {
		SavingsAccount account = savingsAccountService.getAccountById(accountNumber);
		//model.addAttribute("account", account);
		savingsAccountService.deposit(account, amount);;
		return "redirect:getAll";
	}
	
	@RequestMapping("/transferFund")
	public String fundtransferOperation()
	{
		return "fundTransfer";
	}
	
	@RequestMapping("/fundTransferform")
	public String fundTransferForms(Model model, @RequestParam("senderAccountNumber") int senderaccountNumber,
			@RequestParam("recieverAccountNumber") int recieverAccountNumber,
			@RequestParam("amount") double amount) throws ClassNotFoundException, SQLException, AccountNotFoundException {

		SavingsAccount senderSavingsAccount = savingsAccountService.getAccountById(senderaccountNumber);
		SavingsAccount receiverSavingsAccount = savingsAccountService.getAccountById(recieverAccountNumber);
		savingsAccountService.fundTransfer(senderSavingsAccount, receiverSavingsAccount, amount);
		//model.addAttribute("account", account);
		return "redirect:getAll";
	}
}