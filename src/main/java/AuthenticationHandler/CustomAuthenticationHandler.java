//package AuthenticationHandler;
//
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
//
//import java.io.IOException;
//import java.util.Iterator;
//
//public class CustomAuthenticationHandler implements AuthenticationSuccessHandler {
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//
//        {
//            User principal = (User) authentication.getPrincipal();
//            System.out.println("principal" + principal.getUsername());
//            boolean isAdmin = false;
//            Iterator<GrantedAuthority> grantedAuthorityIterator = principal.getAuthorities().iterator();
//            while (grantedAuthorityIterator.hasNext()) {
//                if (grantedAuthorityIterator.next().getAuthority().equalsIgnoreCase("ROLE_ADMIN")) {
//                    isAdmin = true;
//                }
//            }
//            if (isAdmin) {
//                response.sendRedirect("/admin/home");
//            } else {
//                response.sendRedirect("/user/home");
//            }
//        }
//    }
//}