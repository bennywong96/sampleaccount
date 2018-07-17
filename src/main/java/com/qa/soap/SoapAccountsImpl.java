package com.qa.soap;

import javax.inject.Inject;
import javax.jws.WebService;

import org.apache.log4j.Logger;

import com.qa.business.service.AccountService;
import com.qa.persistence.repository.AccountRepository;

@WebService(serviceName = "SoapAccountsImpl", portName = "SoapAccount", name = "SoapAccount", endpointInterface = "com.qa.business.service.SoapAccounts",
targetNamespace = "http://localhost:8080/sampleaccount/")
public class SoapAccountsImpl implements SoapAccounts {

	private static final Logger LOGGER = Logger.getLogger(AccountService.class);

	@Inject
	private AccountRepository repo;

	public String getAllAccounts() {
		LOGGER.info("In AccountServiceImpl getAllAccounts ");
		return repo.getAllAccounts();
	}

	@Override
	public String addAccount(String account) {
		LOGGER.info("In AccountServiceImpl createAccount");
		return repo.createAccount(account);
	}

	@Override
	public String updateAccount(Long id, String account) {
		LOGGER.info("In AccountServiceImpl updateAccount");
		return repo.updateAccount(id, account);
	}

	@Override
	public String deleteAccount(Long id) {
		LOGGER.info("In AccountServiceImpl deleteAccount");
		return repo.deleteAccount(id);

	}

	public void setRepo(AccountRepository repo) {
		this.repo = repo;
	}
}