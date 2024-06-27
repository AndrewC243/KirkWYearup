package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.exceptions.PrincipalNotFoundException;
import org.yearup.models.Profile;

import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("profile")
public class ProfilesController {
    @Autowired
    private ProfileDao profileDao;
    @Autowired
    private UserDao userDao;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Profile> getProfile(Principal principal) {
        if (principal == null) throw new PrincipalNotFoundException("No principal given");
        return ResponseEntity.ok(profileDao.getByUserId(userDao.getIdByUsername(principal.getName())));
    }

    @PutMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<Profile> updateProfile(Principal principal, @RequestBody Profile profile) {
        if (principal == null) throw new PrincipalNotFoundException("No principal given");
        return ResponseEntity.ok(profileDao.update(userDao.getIdByUsername(principal.getName()), profile));
    }
}
