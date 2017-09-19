package sample.connect.spring.basic;

import com.atlassian.connect.spring.IgnoreJwt;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class PublicController {

    @RequestMapping(value = "/public", method = RequestMethod.GET)
    public Object getPrincipal(@AuthenticationPrincipal Object principal) {
        return Optional.ofNullable(principal).orElse("anonymous");
    }
}
