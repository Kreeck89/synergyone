package one.synergy.synergyone.service.impl;

        import lombok.NonNull;
        import one.synergy.synergyone.model.Client;
        import one.synergy.synergyone.model.ClientAccount;
        import one.synergy.synergyone.model.Role;
        import one.synergy.synergyone.repository.ClientRepository;
        import one.synergy.synergyone.service.ClientRoleAccountService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.security.core.userdetails.UserDetails;
        import org.springframework.security.core.userdetails.UserDetailsService;
        import org.springframework.security.core.userdetails.UsernameNotFoundException;
        import org.springframework.stereotype.Service;

        import java.util.Collections;
        import java.util.List;

@Service
public class ClientRoleAccountServiceImpl implements ClientRoleAccountService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String name) throws UsernameNotFoundException {
        Client byUsername = clientRepository.findByName(name);
        String username = byUsername.getName();
        if (byUsername.getName() != null) {
            return ClientAccount.builder()
                    .password("{noop}" + byUsername.getPassword())
                    .username(username)
                    .authorities(Collections.singletonList((Role.ADMIN)))
                    .accountNonExpired(true)
                    .accountNonLocked(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .build();
        } else {
            return null;
        }
    }
}