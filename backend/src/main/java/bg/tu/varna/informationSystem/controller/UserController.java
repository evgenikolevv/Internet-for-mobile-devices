package bg.tu.varna.informationSystem.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAnyAuthority('add','view')")
public class UserController {

    @GetMapping()
    public String test() {
        return "imash pravo";
    }

}
