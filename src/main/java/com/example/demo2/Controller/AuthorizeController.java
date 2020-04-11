package com.example.demo2.Controller;
import com.example.demo2.dto.AccesstokenDTO;
import com.example.demo2.dto.GithubUser;
import com.example.demo2.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSec;
    @Value("${github.redirect.uri}")
    private String rediruri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name = "state") String state){
        AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
        accesstokenDTO.setCode(code);
        accesstokenDTO.setState(state);
        accesstokenDTO.setRedirect_uri(rediruri);
        accesstokenDTO.setClient_id(clientId);
        accesstokenDTO.setClient_secret(clientSec);
        String accessToken=githubProvider.getaccessToken(accesstokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        System.out.println(user.getName());
        return  "index";
    }
}
