package org.example.lab1.Controller;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.example.lab1.entities.DTO.ToRoleAdminDTO;
import org.example.lab1.entities.enums.Role;
import org.example.lab1.entities.ToRoleAdmin;
import org.example.lab1.entities.User;
import org.example.lab1.Service.ToRoleAdminService;
import org.example.lab1.Service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@AllArgsConstructor
@RequestMapping("/api/admin")
public class RoleAdminController {
    private final UserService userService;
    private final ToRoleAdminService toRoleAdminService;

    @PostMapping(value = "/addAdmin", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> addAdmin(@RequestBody ToRoleAdminDTO toRoleAdminDTO, HttpServletRequest request) {
        System.out.println(toRoleAdminDTO);
        if(userService.getByUsername(toRoleAdminDTO.getUsernameAdmin()).getRole()==Role.ROLE_ADMIN && toRoleAdminService.findByUsername(toRoleAdminDTO.getUsername())!=null) {
            System.out.println(toRoleAdminDTO.getUsername());
            User user = userService.getByUsername(toRoleAdminDTO.getUsername());
            System.out.println(user);
            System.out.println(987777776);
            User user1 = User.builder().username(user.getUsername())
                    .password(user.getPassword()).id(user.getId()).role(Role.ROLE_ADMIN).build();
            userService.update(user1);
            toRoleAdminService.delete(toRoleAdminService.findByUsername(toRoleAdminDTO.getUsername()));
            return new ResponseEntity<>("s", HttpStatus.OK);
        } else {
            System.out.println(1234);
            return new ResponseEntity<>("s", HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping(value = "/add/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<String> add(@PathVariable String username, HttpServletRequest request) {
        if(toRoleAdminService.findByUsername(username)==null) {

            toRoleAdminService.add(ToRoleAdmin.builder().username(username).build());
        }
        return new ResponseEntity<>("s",HttpStatus.OK);
    }
    @GetMapping(value = "/get", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<ToRoleAdmin>> get(HttpServletRequest request) {
        return new ResponseEntity<>(toRoleAdminService.findAll(),HttpStatus.OK);
    }
}
