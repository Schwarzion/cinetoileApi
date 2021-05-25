package com.cinetoile.SpringAPI.services;

import com.cinetoile.SpringAPI.NotFoundException;
import com.cinetoile.SpringAPI.dto.dtoOut.UserDTOOut;
import com.cinetoile.SpringAPI.exceptions.ForbiddenException;
import com.cinetoile.SpringAPI.models.ERole;
import com.cinetoile.SpringAPI.models.RoleEntity;
import com.cinetoile.SpringAPI.models.UserEntity;
import com.cinetoile.SpringAPI.dto.dtoIn.LoginDTOIn;
import com.cinetoile.SpringAPI.dto.dtoIn.SignupDTOIn;
import com.cinetoile.SpringAPI.dto.dtoOut.JwtDTOOut;
import com.cinetoile.SpringAPI.dto.dtoOut.MessageDTOOut;
import com.cinetoile.SpringAPI.repository.RoleRepository;
import com.cinetoile.SpringAPI.repository.UserRepository;
import com.cinetoile.SpringAPI.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService extends AuthenticationFacade {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private final UserRepository repository;
    private final RoleRepository roleRepository;

    UserService(UserRepository repository, RoleRepository roleRepository) {
        this.repository = repository;
        this.roleRepository = roleRepository;
    }

    /**
     * Login user
     *
     * @param loginRequest
     * @return ResponseEntity
     */
    public ResponseEntity<?> signin(@Valid @RequestBody LoginDTOIn loginRequest) throws ForbiddenException {
        UserEntity userTrySignin =
                this.repository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new NotFoundException(
                        "user"));
        if (userTrySignin.getStatus() == 1) {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
                    /*
                    UsernamePasswordAuthenticationToken gets {username, password} from login Request,
                    AuthenticationManager will use it to authenticate a login account.
                    */
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtDTOOut(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail()));
            /*
            AuthenticationManager has a DaoAuthenticationProvider (with help of UserDetailsService & PasswordEncoder)
            to validate UsernamePasswordAuthenticationToken object. If successful, AuthenticationManager returns a fully populated Authentication object (including granted authorities)
            */

        } else {
            throw new ForbiddenException();
        }
    }

    /**
     * Register user
     *
     * @param signUpRequest
     * @return ResponseEntity
     */
    public ResponseEntity<?> signup(@Valid @RequestBody SignupDTOIn signUpRequest) {
        if (repository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageDTOOut("Error: Username is already taken!"));
        }

        if (repository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageDTOOut("Error: Email is already in use!"));
        }


        // Create new user's account
        UserEntity user = new UserEntity(
                signUpRequest.getUsername(),
                signUpRequest.getLastname(),
                signUpRequest.getFirstname(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getStatus()
        );

        List<String> strRoles = signUpRequest.getRole();
        List<RoleEntity> roles = new ArrayList<>();

        if (strRoles != null) {
            strRoles.forEach(role -> {
                if (role =="admin") {
                    RoleEntity adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                } else if( role == "theater") {
                    RoleEntity theaterRole = roleRepository.findByName(ERole.ROLE_THEATER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(theaterRole);
                } else {
                    throw new RuntimeException("Error: Role is not found.");
                }
            });
        }

        user.setRoles(roles);
        repository.save(user);

        return ResponseEntity.ok(new MessageDTOOut("User registered successfully!"));
    }

    /**
     * Find all users
     *
     * @return UserDTOOut
     */
    public List<UserDTOOut> findAll() {
        List<UserEntity> list = repository.findAll();
        return list.stream()
                .map(this::convertToUserDto)
                .collect(Collectors.toList());
    }

    /**
     * Find user by id
     *
     * @param id
     * @return UserDTOOut
     */
    public UserEntity findById(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("user", id.toString()));
    }

    /**
     * Find user by id
     *
     * @param id
     * @return UserDTOOut
     */
    public UserDTOOut findDto(Integer id) {
        return convertToUserDto(findById(id));
    }


    /**
     * Update User
     *
     * @param newUser
     * @param id
     * @return UserDTOOut
     */
    public UserDTOOut update(UserEntity newUser, Integer id) {
        return repository.findById(id).map(user -> {
            user.setBirthdate(newUser.getBirthdate());
            user.setFirstname(newUser.getFirstname());
            user.setLastname(newUser.getLastname());
            user.setCity(newUser.getCity());
            user.setImage(newUser.getImage());
            user.setEmail(newUser.getEmail());
            user.setPassword(newUser.getPassword());
            user.setImage(newUser.getImage());
            user.setPhone(newUser.getPhone());
            user.setPostalCode(newUser.getPostalCode());
            user.setImage(newUser.getImage());

            repository.save(user);
            return convertToUserDto(user);
        }).orElseGet(() -> {
            newUser.setId(id);
            repository.save(newUser);
            return convertToUserDto(newUser);
        });
    }

    /**
     * Update user status
     * Status :
     * 0 : Desctivated
     * 1 : Activated
     *
     * @param id
     * @param status
     * @return UserDTOOut
     */
    public UserDTOOut updateStatus(Integer id, Integer status) {
        return repository.findById(id).map(user -> {
            user.setStatus(status);
            user = repository.save(user);
            return convertToUserDto(user);
        }).orElseThrow(() -> new NotFoundException("user", id.toString()));
    }

    /**
     * Delete User
     *
     * @param id
     * @Throws IllegalArgumentException
     */
    public void delete(Integer id) { repository.deleteById(id);}

    /**
     * Find current user
     * Using Authentication Facade Interface
     *
     * @return UserDetailsImpl
     */
    public UserDTOOut findCurrentUser() {
        UserDetailsImpl currentUser = getPrincipalCurrentUser();
        return findDto(currentUser.getId());
    }


    /**
     * Convert entity to dto
     *
     * @param user
     * @return UserDTOOut
     */
    private UserDTOOut convertToUserDto(UserEntity user) {
        return new UserDTOOut(
                user.getId(),
                user.getFirstname(),
                user.getLastname(),
                user.getCity(),
                user.getPostalCode(),
                user.getBirthdate(),
                user.getStatus(),
                user.getPhone(),
                user.getUsername(),
                user.getEmail(),
                user.getImage()
        );
    }

}
