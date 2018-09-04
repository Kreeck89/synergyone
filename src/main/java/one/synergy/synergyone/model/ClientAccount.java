package one.synergy.synergyone.model;

import lombok.Builder;
        import lombok.Data;
        import lombok.EqualsAndHashCode;
        import org.springframework.security.core.userdetails.UserDetails;

        import java.util.List;


@Data
@Builder
@EqualsAndHashCode
public class ClientAccount implements UserDetails {

    private List<Role> authorities;
    private String password;
    private String username;
//    private String email;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;

}