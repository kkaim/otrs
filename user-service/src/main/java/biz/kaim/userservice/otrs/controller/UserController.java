package biz.kaim.userservice.otrs.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/v1/user")
public class UserController {

    protected static final Logger logger = Logger.getLogger(UserController.class.getName());
}
