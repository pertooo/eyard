package prt.navitruck.back.app.domain;

import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.DigestUtils;

import java.util.Collection;

@Slf4j
@ToString(callSuper = true)
public class AuthTokenImpl  extends AbstractAuthenticationToken {

    @Setter
    private String username;

    public AuthTokenImpl(String username, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.username = username;
    }

    public void authenticate() {
        setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return "";
    }

    @Override
    public Object getPrincipal() {
        return username != null ? username.toString() : "";
    }

    public String getHash() {
        return DigestUtils.md5DigestAsHex(String.format("%s_%d", username, ((UserSession) getDetails()).getCreated().getTime()).getBytes());
    }

}
