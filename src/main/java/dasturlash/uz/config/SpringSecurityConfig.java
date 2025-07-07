package dasturlash.uz.config;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SpringSecurityConfig {

    private final BCryptPasswordEncoder bCryptEncoder;
    private final UserDetailsService userDetailsService;
    private final JwtAuthFilter jwtAuthFilter;
    private DaoAuthenticationProvider authProvider; // singelton qilish sababi xotida unumdorligi

    public static final String[] openApiList = {
            "/auth/login",
            "/auth/register",
            "/profile/me",
            "/category/create"
    };

    @Bean
    public AuthenticationProvider authenticationProvider() {
        if (authProvider == null) authProvider = new DaoAuthenticationProvider();

        authProvider.setPasswordEncoder(bCryptEncoder);
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> {
            authorize
                    .requestMatchers(openApiList).permitAll()
                    .anyRequest().authenticated();  // controllerda metod ustiga yozib ketishimiz shart bu yerda yozib o'tirmadim

        }).addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        http.httpBasic(Customizer.withDefaults());

        http.csrf(AbstractHttpConfigurer::disable);
        http.cors(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
