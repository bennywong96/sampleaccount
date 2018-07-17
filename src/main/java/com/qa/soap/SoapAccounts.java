package com.qa.soap;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService(targetNamespace = "http://localhost:8080/sampleaccount/")
public interface SoapAccounts {

	@WebMethod
	String getAllAccounts();

	@WebMethod
	String addAccount(String account);

	@WebMethod
	String updateAccount(Long id, String account);

	@WebMethod
	String deleteAccount(Long id);

}
