package com.training.config;



import javax.servlet.Filter;
import java.util.List;

public class WebSecurityConfig {
//    @Autowired
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
//
//    @Autowired
//    private UserDetailsService jwtUserDetailsService;
//
//    @Autowired
//    private JwtRequestFilter jwtRequestFilter;
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        // configure AuthenticationManager so that it knows from where to load
//        // user for matching credentials
//        // Use BCryptPasswordEncoder
//        auth.userDetailsService(jwtUserDetailsService).passwordEncoder(passwordEncoder());
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    @Override
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
//
////    @Override
////    protected void configure(HttpSecurity http) throws Exception {
////        http.csrf().disable()
////                .authorizeRequests().antMatchers("").permitAll()
////                .anyRequest().authenticated().and()
////                .exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint).and()
////                .sessionManagement()
////                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
////        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
////    }
}
