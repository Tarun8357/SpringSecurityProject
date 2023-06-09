package SecurityConfigurations;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;

@EnableWebSecurity
@Configuration
public class SecurityConfigs {
    @Autowired
    DataSource dataSource;
    @Bean
    UserDetailsManager users(DataSource dataSource) {
        UserDetails user = User.builder().username("user")
                .password("{bcrypt}$2a$12$.MHrAU7eSZMoTThqrnelo.AmpbMT7z9xTFDZuVtfADKFDPfc60FdK").roles("USER").build();
        UserDetails admin = User.builder().username("admin")
                .password("{bcrypt}$2a$12$.MHrAU7eSZMoTThqrnelo.AmpbMT7z9xTFDZuVtfADKFDPfc60FdK").roles("USER", "ADMIN")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        users.createUser(user);
        users.createUser(admin);
        return users;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http ) throws Exception {
        http.cors().disable().csrf().disable();


        http.authorizeHttpRequests()
//                .requestMatchers("/login").hasRole("ADMIN")
                .requestMatchers("/calculator").hasRole("ADMIN")
                .requestMatchers("/**")
                .authenticated()
                .and()
                .formLogin().permitAll().successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        PrintWriter out = response.getWriter();
                        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
                        String username = userDetails.getUsername();
                        if (username.equalsIgnoreCase("ADMIN")){
                        System.out.println("The user " + username + " has logged in.");

                        response.sendRedirect("http://localhost:8080/calculator/");}
                        else {
                            response.sendError(403,"Logging as user");

                        }
                    }
                });

        return http.build();
    }

}
