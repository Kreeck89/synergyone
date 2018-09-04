package one.synergy.synergyone.security;

import one.synergy.synergyone.service.impl.ClientRoleAccountServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final ClientRoleAccountServiceImpl clientRoleAccountServiceImpl;

    @Autowired
    public SecurityConfiguration(ClientRoleAccountServiceImpl userRoleAccountService) {
        this.clientRoleAccountServiceImpl = userRoleAccountService;
    }

    private  static  final Logger logger = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        logger.info("configureAuthentication");
        httpSecurity
                .authorizeRequests()
                .antMatchers(
                        "/css/**","/img/**","/fonts/**","/js/**"
                        ).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll();
    }
//.formLogin().loginPage("/login").usernameParameter("email").successForwardUrl("/admin").permitAll()
//    @Bean
//    public static PasswordEncoder bCryptPasswordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService);
//        authProvider.setPasswordEncoder(encoder());
//        return authProvider;
//    }

//
//
//    @Autowired
//    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
////        auth.authenticationProvider(authProvider());
//        auth
//                .userDetailsService(userRoleAccountService)
//                .passwordEncoder(passwordEncoder());
//    }




//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(16);
//    }
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }

//
//    @Bean
//    public DaoAuthenticationProvider authenticationProvider() {
//
//        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
//        authenticationProvider.setUserDetailsService(clientRoleAccountService);
//        authenticationProvider.setPasswordEncoder(passwordEncoder());
//        return authenticationProvider;
//    }


//    @Autowired
//    public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
//        authenticationManagerBuilder
//                .userDetailsService(userRoleAccountService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }

}