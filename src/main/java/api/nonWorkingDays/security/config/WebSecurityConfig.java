package api.nonWorkingDays.security.config;

import api.nonWorkingDays.security.JsonObjectAuthenticationFilter;
import api.nonWorkingDays.security.handlers.RestAuthenticationFailureHandler;
import api.nonWorkingDays.security.handlers.RestAuthenticationSuccessHandler;
import api.nonWorkingDays.security.jwt.JwtFilter;
import api.nonWorkingDays.security.model.AppUser;
import api.nonWorkingDays.security.repo.AppUserRepo;
import api.nonWorkingDays.security.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

@Configuration
@Order(1)
//@EnableWebSecurity(debug = false)
//@RequiredArgsConstructor

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private RestAuthenticationSuccessHandler authenticationSuccessHandler;
    private RestAuthenticationFailureHandler authenticationFailureHandler;
    private final String secret;
    private final DataSource dataSource;
    private final ObjectMapper objectMapper;
//
//    private UserDetailsServiceImpl userDetailsService;
//    private AppUserRepo appUserRepo;

//
//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return  new BCryptPasswordEncoder();
//    }


    public WebSecurityConfig(RestAuthenticationSuccessHandler authenticationSuccessHandler,
                             RestAuthenticationFailureHandler authenticationFailureHandler,
                             @Value("${jwt.secret}") String secret,
                             ObjectMapper objectMapper,
                             DataSource dataSource

//                             UserDetailsServiceImpl userDetailsService,
//                             AppUserRepo appUserRepo
                             ) {

        this.authenticationSuccessHandler = authenticationSuccessHandler;
        this.authenticationFailureHandler = authenticationFailureHandler;
        this.secret = secret;
        this.objectMapper = objectMapper;
        this.dataSource = dataSource;

//        this.userDetailsService = userDetailsService;
//        this.appUserRepo = appUserRepo;
//    }
//
//    @PostConstruct
//    public void postConstruct(){
//        AppUser appUserJanusz = new AppUser();
//        appUserJanusz.setUsername("janusz");
//        appUserJanusz.setPassword("{bcrypt}"+ new BCryptPasswordEncoder().encode("janusz"));
//        appUserJanusz.setRole("ROLE_ADMIN");
//        appUserJanusz.setEnabled(true);
//        appUserRepo.save(appUserJanusz);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        //http.headers().disable();
//        http.authorizeRequests()
//                .antMatchers("/", "/token", "/register").permitAll()
//
//                .antMatchers("/add", "/delete").hasRole("ADMIN")
//                .anyRequest().authenticated()
//               // .antMatchers("/between").authenticated()
//                .and()
//                    .addFilterBefore(authenticationFilter(), UsernamePasswordAuthenticationFilter.class)
//                    .exceptionHandling()
//                    .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED));
//    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http
                .authorizeRequests()
               .antMatchers("/").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/v2/api-docs").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/token", "/register").permitAll()
                .antMatchers("/add", "/delete").hasRole("USER")
                .antMatchers("/between", "/all").authenticated()
                .anyRequest().authenticated()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(authenticationFilter())
                .addFilter(new JwtFilter(authenticationManager(), userDetailsManager(), secret))
                .exceptionHandling()
                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                .and()
                .headers().frameOptions().disable();
    }

//    @Bean
    public JsonObjectAuthenticationFilter authenticationFilter() throws Exception {

        JsonObjectAuthenticationFilter filter = new JsonObjectAuthenticationFilter(objectMapper);

        filter.setAuthenticationSuccessHandler(authenticationSuccessHandler);
        filter.setAuthenticationFailureHandler(authenticationFailureHandler);
        filter.setAuthenticationManager(super.authenticationManager());
        return filter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
    builder.jdbcAuthentication()
            .withDefaultSchema()
            .dataSource(dataSource)
            .withUser("admin")
            .password("{bcrypt}"+ new BCryptPasswordEncoder().encode("admin"))
            .roles("ADMIN")
            .and()
            .dataSource(dataSource)
            .withUser("user")
            .password("{bcrypt}"+ new BCryptPasswordEncoder().encode("user"))
            .roles("USER");

   // builder.userDetailsService(userDetailsService);

        }




    @Bean
    public UserDetailsManager userDetailsManager() {
        return new JdbcUserDetailsManager(dataSource);
    }






}
