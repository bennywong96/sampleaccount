package com.qa.persistence.repository;

import static javax.transaction.Transactional.TxType.REQUIRED;
import static javax.transaction.Transactional.TxType.SUPPORTS;

import java.util.Collection;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.apache.log4j.Logger;

import com.qa.persistence.domain.Account;
import com.qa.util.JSONUtil;

@Transactional(SUPPORTS)
@Default
public class AccountDBRepository implements AccountRepository {
	
	private static final Logger LOGGER = Logger.getLogger(AccountRepository.class);
	

	@PersistenceContext(unitName = "primary")
	private EntityManager manager;

	@Inject
	private JSONUtil util;
	
	public String getAllAccounts() {
		LOGGER.info("In AccountDBRepoistory getAllAccounts ");
		Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
	}

	@Transactional(REQUIRED)
	public String createAccount(String accout) {
		LOGGER.info("In AccountDBRepoistory createAccount");
		Account anAccount = util.getObjectForJSON(accout, Account.class);
		manager.persist(anAccount);
		return "{\"message\": \"account has been sucessfully added\"}";
	}

	@Transactional(REQUIRED)
	public String updateAccount(Long id, String accountToUpdate) {
		LOGGER.info("In AccountDBRepoistory updateAccount");
		Account updatedAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		Account accountFromDB = findAccount(id);
		if (accountToUpdate != null) {
			LOGGER.info("updateAccount is not null");
			accountFromDB = updatedAccount;
			accountFromDB.setId(id);
			manager.merge(accountFromDB);
			return "{\"message\": \"account sucessfully updated\"}";
		}
		else {
			LOGGER.warn("updateAccount is null");
			return "{\"message\": \"Error has occurred\"}";
		}
	}

	@Transactional(REQUIRED)
	public String deleteAccount(Long id) {
		LOGGER.info("In AccountDBRepoistory deleteAccount");
		Account accountInDB = findAccount(id);
		if (accountInDB != null) {
			LOGGER.info("deleteAccount is not null");
			manager.remove(accountInDB);
			return "{\"message\": \"account sucessfully deleted\"}";
		}
		else {
			LOGGER.warn("updateAccount is null");
			return "{\"message\": \"Error has occurred\"}";
		}
	}

	private Account findAccount(Long id) {
		LOGGER.info("In AccountDBRepoistory findAccount");
		return manager.find(Account.class, id);
	}

	public void setManager(EntityManager manager) {
		this.manager = manager;
	}

	public void setUtil(JSONUtil util) {
		this.util = util;
	}

}