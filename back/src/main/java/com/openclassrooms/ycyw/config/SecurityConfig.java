package com.openclassrooms.ycyw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.openclassrooms.ycyw.services.CustomUserDetailsService;

@Configuration
public class SecurityConfig {

    /**
     * Configure la chaîne de filtres de sécurité.
     *
     * @param http l'objet HttpSecurity à configurer
     * @return l'objet SecurityFilterChain configuré
     * @throws Exception si une erreur de configuration survient
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/ws/**").permitAll() // Permettre l'accès au point de terminaison WebSocket sans authentification
                .requestMatchers("/api/login").permitAll() // Permettre l'accès à l'URL /api/login sans authentification
                .anyRequest().authenticated() // Exiger l'authentification pour toutes les autres requêtes
            )
            .csrf(csrf -> csrf.disable()) // Désactiver CSRF pour les tests, mais à ne pas faire en production
            .httpBasic(Customizer.withDefaults()); // Utiliser l'authentification HTTP Basic pour simplifier

        return http.build();
    }

    /**
     * Configure les paramètres CORS.
     *
     * @return l'objet CorsConfigurationSource configuré
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:4200"); // Configurez les origines autorisées
        configuration.addAllowedMethod("*"); // Configurez les méthodes autorisées
        configuration.addAllowedHeader("*"); // Configurez les en-têtes autorisés
        configuration.setAllowCredentials(true); // Autoriser les informations d'identification
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    /**
     * Fournit un service de gestion des utilisateurs.
     *
     * @return l'objet UserDetailsService configuré
     */
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(); // Utiliser CustomUserDetailsService comme le seul bean UserDetailsService
    }

    /**
     * Fournit un encodeur de mots de passe.
     *
     * @return l'objet PasswordEncoder configuré
     */
    @SuppressWarnings("deprecation")
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); // Utiliser un encodeur de mots de passe qui ne fait rien
    }
}