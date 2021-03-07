package com.avinty.hr.controller;

import com.avinty.hr.exception.GeneralException;
import com.avinty.hr.model.User;
import com.avinty.hr.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.Base64;

/**
 * @author : Otto Heirich
 * @since : 2021. 03. 06., Sz
 **/
@RestController
@RequestMapping("${application-endpoint-url}" + "/user")
public class UserController extends BaseControllerImp<User, UserService> {

	@PostMapping(value = "/uploadProfilePics/{id}")
	public User uploadProfilePics(@PathVariable Integer id, @RequestBody String image) {

		if (!image.matches("^([A-Za-z0-9+/]{4})*([A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{2}==)?$")) {
			throw new GeneralException("Not Base64!");
		}

		User byId = service.findById(id);
		byId.setProfilePicture(Base64.getDecoder().decode(image));
		return service.update(id, byId);
	}

}
