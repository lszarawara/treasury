package pl.sda.treasury.service;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service("securityService")
@RequiredArgsConstructor
public class SecurityService {

    private final UserService userService;
    private final ChildService childService;
    Logger logger = LoggerFactory.getLogger(SecurityService.class);
    Authentication authentication;

    public boolean isParent(Long childId){

        this.authentication = SecurityContextHolder.getContext().getAuthentication();

//        return authentication.getName().equals(user.getUserName()) || SecurityUtils.isAdmin();

        return userService.findByLogin(authentication.getName()).getChildren().contains(childService.find(childId));

//        return userService.findByLogin(principal.getName()).getChildren().contains(childService.find(ChildId));
    }

}
