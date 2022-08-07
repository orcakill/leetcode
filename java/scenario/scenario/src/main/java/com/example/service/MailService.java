package com.example.service;

import com.example.model.entity.EmailBoxPO;
import com.example.service.impl.MailServiceImpl;

import java.awt.*;

/**
 * @Classname MailService
 * @Description TODO
 * @Date 2022/8/8 1:34
 * @Created by orcakill
 */
public interface MailService {
	static void sendMail(EmailBoxPO emailBoxPO) throws InterruptedException, AWTException {
		MailServiceImpl.sendMail(emailBoxPO);
	}
}
