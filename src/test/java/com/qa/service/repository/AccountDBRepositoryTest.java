package com.qa.service.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.persistence.domain.Account;
import com.qa.persistence.domain.Transaction;
import com.qa.persistence.repository.AccountDBRepository;
import com.qa.util.JSONUtil;

@RunWith(MockitoJUnitRunner.class)
public class AccountDBRepositoryTest {

	@InjectMocks
	private AccountDBRepository repo;

	@Mock
	private EntityManager manager;

	@Mock
	private Query query;

	private JSONUtil util;

	private static final String MOCK_DATA_ARRAY = "[{\"firstName\":\"John\",\"lastName\":\"Doe\",\"accountNumber\":\"123456\"}]";

	private static final String MOCK_OBJECT = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"accountNumber\":\"123456\"}";
	

	@Before
	public void setup() {
		repo.setManager(manager);
		util = new JSONUtil();
		repo.setUtil(util);
	}

	@Test
	public void testGetAllAccounts() {
		Mockito.when(manager.createQuery(Mockito.anyString())).thenReturn(query);
		List<Transaction> transaction = new ArrayList<Transaction>();
		List<Account> accounts = new ArrayList<Account>();
		accounts.add(new Account("John", "Doe", "123456", null));
		Mockito.when(query.getResultList()).thenReturn(accounts);
		Assert.assertEquals(MOCK_DATA_ARRAY, repo.getAllAccounts());
	}

	@Test
	public void testCreateAccount() {
		String reply = repo.createAccount(MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"account has been sucessfully added\"}");
	}

	@Test
	public void testUpdateAccount() {
		String reply = repo.updateAccount(1L, MOCK_OBJECT);
		Assert.assertEquals(reply, "{\"message\": \"account sucessfully updated\"}");
	}
	
	@Test
	public void testUpdateAccountNull() {
		String reply = repo.updateAccount(1L, null);
		Assert.assertEquals(reply, "{\"message\": \"Error has occurred\"}");
	}
	
//	@Test
//	public void testDeleteAccount() {
//
//		String reply = repo.deleteAccount(1L);
//		System.out.println(reply);
//		Assert.assertEquals(reply, "{\"message\": \"account sucessfully deleted\"}");
//	}

	@Test
	public void failedToDeleteAccount() {
		String reply = repo.deleteAccount(1L);
		Assert.assertEquals(reply, "{\"message\": \"Error has occurred\"}");
	}

}