package kz.bit.kormefinall.services;

import kz.bit.kormefinall.models.Permission;
import kz.bit.kormefinall.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

    private final PermissionRepository permissionRepository;

    @Autowired
    public PermissionService(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    public Permission findById(Long id) {
        return permissionRepository.findById(id).orElse(null);
    }

    public Permission save(Permission permission) {
        return permissionRepository.save(permission);
    }

    public void changeRole(Long id, String newRole) {
        Permission permission = permissionRepository.findById(id).orElse(null);
        if (permission != null) {
            permission.setRole(newRole);
            permissionRepository.save(permission);
        }
    }
}
