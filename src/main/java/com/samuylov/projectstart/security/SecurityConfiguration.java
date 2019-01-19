package com.samuylov.projectstart.security;

//@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {//extends WebSecurityConfigurerAdapter {
    //@Autowired
//    private UserDetailsService detailsService;
//
//    //@Autowired
//    private AuthenticationSuccessHandler successHandler;
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(detailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers().hasAnyAuthority()
//         .and()
//         .formLogin().successHandler(successHandler);
//         http.csrf().disable();
//
//         //        http.authorizeRequests()
////                .antMatchers("/VAADIN/**", "/vaadinServlet/**", "/**").permitAll()
////                .antMatchers("bookReader/**", "/**")
////                .hasAnyAuthority()
////                .and()
////                .formLogin().successHandler(successHandler).permitAll()
////                .and()
////                .logout();
////        http.csrf().disable();
//    }
//
//    //@Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    //@Bean
//    public ViewAccessControl accessControl() {
//        return new SecuredViewAccessControl();
//    }

}