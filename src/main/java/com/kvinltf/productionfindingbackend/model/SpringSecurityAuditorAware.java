package com.kvinltf.productionfindingbackend.model;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

class SpringSecurityAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("n/a");
//    return Optional.ofNullable(SecurityContextHolder.getContext())
//            .map(SecurityContext::getAuthentication)
//            .filter(Authentication::isAuthenticated)
//            .map(Authentication::getPrincipal)
//            .map(User.class::cast);
    }
}