package com.qa.integration;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.qa.business.service.AccountService;

@RunWith(MockitoJUnitRunner.class)
public class AccountEndPointTest {

	private static final String MOCK_VALUE2 = "test_value_2";

	private static final String MOCK_VALUE = "test_value";

	@InjectMocks
	private AccountEndPoint endpoint;

	@Mock
	private AccountService service;

	@Before
	public void setUp() throws Exception {

	}

	@Test
	public void gettingAllAccounts() {
		Mockito.when(service.getAllAccounts()).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.getAllAccounts());
	}
	

	@Test
	public void testCreateAccount() {
		Mockito.when(service.addAccount(MOCK_VALUE2)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.addAccount(MOCK_VALUE2));
		Mockito.verify(service).addAccount(MOCK_VALUE2);
	}

	@Test
	public void testUpdateAccount() {
		Mockito.when(service.updateAccount(1L, MOCK_VALUE2)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.updateAccount(1L, MOCK_VALUE2));
		Mockito.verify(service).updateAccount(1L, MOCK_VALUE2);
	}

	@Test
	public void testDeleteAccount() {
		Mockito.when(service.deleteAccount(1L)).thenReturn(MOCK_VALUE);
		Assert.assertEquals(MOCK_VALUE, endpoint.deleteAccount(1L));
		Mockito.verify(service).deleteAccount(1L);
	}
	

}