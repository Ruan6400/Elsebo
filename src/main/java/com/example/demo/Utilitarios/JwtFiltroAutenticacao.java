package com.example.demo.Utilitarios;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import java.io.IOException;


@Component
public class JwtFiltroAutenticacao implements Filter{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException{
        HttpServletRequest req = (HttpServletRequest) request;
        
        String path = req.getRequestURI();
        System.out.println("Caminho: " + path);

        if(path.startsWith("/protected/")){
            String authHeader = req.getHeader("Authorization");
            System.out.println("rota protegida");
            System.out.println("Authorization: " + authHeader);
        
            if(authHeader != null && authHeader.startsWith("Bearer ")){
                String token = authHeader.replace("Bearer ","");
                try{
                    JwtUtil.validateToken(token);

                    String username = JwtUtil.getTokenUser(token);
                    String email = JwtUtil.getTokenEmail(token);
                    CustomUserdetails userDetails = new CustomUserdetails(username,email);

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));

                    SecurityContextHolder.getContext().setAuthentication(authToken);



                }catch (Exception e){
                    System.out.println("Token inválido ou expirado");
                    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token inválido ou expirado | " + e.getMessage());
                    return;
                }
            }else{
                System.out.println(path);
                System.out.println("Token ausente");
                ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Token ausente");
                return;
            }
        }
        chain.doFilter(request, response);
    }
}
