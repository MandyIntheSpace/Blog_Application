package com.application.blog.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;
    private JwtTokenHelper jwtTokenHelper;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1. get token
        String requestToken = request.getHeader("Authorization");
        System.out.println(requestToken);

        String username = null;
        String token = null;
        if (requestToken != null && requestToken.startsWith("Basic")) {
             token = requestToken.substring( 6);
             try{
                 username = this.jwtTokenHelper.getUsernameFromToken(token);
             } catch (IllegalArgumentException e) {
                 e.printStackTrace();
                 System.out.println(e.getMessage());
                 System.out.println("Unable to get Jwt token");
             } catch (ExpiredJwtException e) {
                 System.out.println(e.getMessage());
                 System.out.println("Jwt token has expired");
             } catch (MalformedJwtException e) {
                 System.out.println(e.getMessage());
                 System.out.println("Invalid jwt");
             }

        } else{
            System.out.println("Jwt token does not begin with Basic");
        }

        //Once we get the token, now we will validate the token which being fetched
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if(this.jwtTokenHelper.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }else {
                System.out.println("Invalid JWT token");
            }
        } else{
            System.out.println("The username is null and context is not null");
        }
        filterChain.doFilter(request, response);
    }
}
