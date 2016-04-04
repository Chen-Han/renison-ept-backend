package com.renison.jackson;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

public class JsonEptViewAnnotationIntrospector extends JacksonAnnotationIntrospector implements Versioned {
    public boolean hasRequiredPermission(AnnotatedMember annotatedField) {
        if (_hasAnnotation(annotatedField, JsonEptView.class)) {
            JsonEptView jsv = _findAnnotation(annotatedField, JsonEptView.class);
            if (jsv.roles() != null) {
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                if (principal != null && principal instanceof UserDetails) {
                    UserDetails principalUserDetails = (UserDetails) principal;
                    Collection<? extends GrantedAuthority> authorities = principalUserDetails.getAuthorities();
                    List<String> requiredRoles = Arrays.asList(jsv.roles()); // the list of all required authorities
                    // basically checking if requiredRoles is a subset of authorities
                    for (String requiredRole : requiredRoles) {
                        SimpleGrantedAuthority f = new SimpleGrantedAuthority(requiredRole);
                        // if any requiredRoles is not matched, false
                        if (!authorities.contains(f))
                            return false;
                    }
                    return true;
                }
            }
        }
        //        return true;
        return false;
    }

    @Override
    public boolean hasIgnoreMarker(AnnotatedMember annotatedField) {
        return super.hasIgnoreMarker(annotatedField) && hasRequiredPermission(annotatedField);
    }

    @Override
    public Version version() {
        return Version.unknownVersion();
    }
}