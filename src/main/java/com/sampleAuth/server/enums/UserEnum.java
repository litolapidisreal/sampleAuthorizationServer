package com.sampleAuth.server.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import static com.sampleAuth.server.enums.PermissionEnum.*;


public enum UserEnum {
    STUDENT(Sets.newHashSet(PRODUCT_READ,TRANSACTION_UPDATE,TRANSACTION_READ)),
    CLERK(Sets.newHashSet(PRODUCT_READ, TRANSACTION_READ, TRANSACTION_WRITE)),
    ADMIN(Sets.newHashSet(PRODUCT_READ,TRANSACTION_UPDATE,TRANSACTION_READ, PRODUCT_DELETE, PRODUCT_WRITE, PRODUCT_UPDATE, TRANSACTION_WRITE));


    private Set<PermissionEnum> permissionEnumSet;

    UserEnum(Set<PermissionEnum> permissionEnumSet) {
        this.permissionEnumSet = permissionEnumSet;
    }


    public static UserEnum findByAbbr(final String userType){
        return Arrays.stream(values()).filter(value -> value.name().equals(userType)).findFirst().orElse(null);
    }

    /**
     * NOTE: "ROLE_" is included in Authority Implementation
     * @return
     */
    public Set<SimpleGrantedAuthority> getGrantedAuthoritySet(){
        Set<SimpleGrantedAuthority> permission = getPermissionEnumSet().stream()
                .map(permissionEnumSet -> new SimpleGrantedAuthority(permissionEnumSet.getPermission()))
                .collect(Collectors.toSet());
        permission.add(new SimpleGrantedAuthority("ROLE_"+this.name()));
        return permission;
    }

    public Set<PermissionEnum> getPermissionEnumSet() {
        return permissionEnumSet;
    }
}
