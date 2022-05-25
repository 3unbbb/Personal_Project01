package com.eb.mail.action;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GoogleAuthentication extends Authenticator {
	PasswordAuthentication passAuth;
	
	public GoogleAuthentication(){
		passAuth = new PasswordAuthentication("3unbbb", "vbdoyspdmqhafouf");
	}
	
	public PasswordAuthentication getPasswordAuthentication(){
		return passAuth;
	}
	
}
