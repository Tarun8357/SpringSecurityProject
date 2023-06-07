package SecurityConfigurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class SecurityConfiguration {
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
        http.csrf().and().cors().disable();




        return http.build();
    }

}
