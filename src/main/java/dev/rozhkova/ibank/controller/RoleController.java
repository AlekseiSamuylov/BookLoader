package dev.rozhkova.ibank.controller;

import dev.rozhkova.ibank.dto.RoleDto;
import dev.rozhkova.ibank.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleService roleService;

    @Autowired
    public RoleController(final RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("/list")
    public ResponseEntity getAllRoles() {
        final List<RoleDto> allRole = roleService.getAllRoles();
        if (allRole != null) {
            return new ResponseEntity<>(allRole, FOUND);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), NOT_FOUND);
        }
        /*try {

        } catch (final UserException ex) {
            return new ResponseEntity<>(ex.toString(), INTERNAL_SERVER_ERROR);
        }*/
    }
}