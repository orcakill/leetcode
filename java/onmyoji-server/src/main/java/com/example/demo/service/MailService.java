package com.example.demo.service;


import com.example.demo.service.impl.MailServiceImpl;


/**
 * @Classname MailService
 * @Description MailService
 * @Date 2022/8/8 1:34
 * @Created by orcakill
 */
public interface MailService {
	static void sendMail() {
		MailServiceImpl.sendMail ();
	}
}
