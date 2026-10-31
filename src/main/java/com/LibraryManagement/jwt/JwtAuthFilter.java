package com.LibraryManagement.jwt;

import com.LibraryManagement.repo.UserRepo;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try{
            final String authorizationHeader = request.getHeader("Authorization");
            System.out.println("AuthorizationHeader: " + authorizationHeader);
            final String jwtToken;
            final String userName ;
            if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            jwtToken = authorizationHeader.substring(7);
            userName = jwtService.extractUserName(jwtToken);

            if(userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                var userDetails = userRepo.findByUserName(userName).orElseThrow(()
                        -> new RuntimeException("Username not found"));

                if(jwtService.isTokenValid(jwtToken,userDetails)){
                    System.out.println("token valid");
                    List<SimpleGrantedAuthority> authorities = userDetails
                            .getRoles()
                            .stream()
                            .map(SimpleGrantedAuthority::new).toList();

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }

            }
        } catch (ExpiredJwtException ex) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write(
                    "{\"message\":\"JWT expired. Please login again.\"}"
            );
            return;
        }

        filterChain.doFilter(request, response);
    }
}
