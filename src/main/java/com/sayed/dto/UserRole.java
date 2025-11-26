package com.sayed.dto;



import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public enum UserRole {

    EMP(new HashSet()),
    ADMIN(new HashSet(Arrays.asList(RolePermission.COURSE_READ, RolePermission.COURSE_WRITE, RolePermission.EMP_READ, RolePermission.EMP_WRITE)));


    private final Set<RolePermission> permissions;

    UserRole(Set<RolePermission> permissions) {
        this.permissions = permissions;
    }

    public Set<RolePermission> getPermissions() {
        return permissions;
    }


}
