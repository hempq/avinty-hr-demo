package com.avinty.hr.service;

import com.avinty.hr.model.User;
import com.avinty.hr.repository.UserRepository;
import org.springframework.stereotype.Service;


/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
@Service
public class UserService extends BaseServiceImpl<User, UserRepository> {

	public UserService(UserRepository repository) {
		super(repository);
	}

}
