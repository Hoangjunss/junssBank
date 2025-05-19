

/*import com.hoangjunss.junsBank.application.UserApplicationService;
import com.hoangjunss.junsBank.dto.AccountDTO;
import com.hoangjunss.junsBank.dto.AuthenticationDTO;
import com.hoangjunss.junsBank.dto.user.UserCreateDTO;
import com.hoangjunss.junsBank.dto.user.UserDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    private UserApplicationService userApplicationService;
    @PostMapping("/signup")
    public ResponseEntity<String> registration(@RequestBody UserCreateDTO createUserRequest,
                                                HttpServletRequest request) {
        log.info("User registration request: {}", createUserRequest.toString());

        userApplicationService.createUser(createUserRequest);


        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }
    @PostMapping("/verifi")
    public ResponseEntity<UserDTO> verifi(@RequestParam String email,
                                         @RequestParam String verifiCode,
                                               HttpServletRequest request) {
        log.info("User verifi request: {}", verifiCode);

        UserDTO user=userApplicationService.verification(email,verifiCode);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthenticationDTO> signIn(
            @RequestBody AccountDTO signInRequest, HttpServletRequest request) {
        AuthenticationDTO authenticationResponse = userApplicationService.login(signInRequest);

        return ResponseEntity.ok(authenticationResponse);
    }
    @PutMapping("/change")
    public ResponseEntity<String> change(@RequestParam String oldPassword,
                                         @RequestParam String newPassword,
            HttpServletRequest request) {
       userApplicationService.changePassword(oldPassword,newPassword);
        return ResponseEntity.ok("success");
    }
    @PostMapping("/verifi_password")
    public ResponseEntity<String> verifiPassword(@RequestParam String verificode,

             HttpServletRequest request) {
       userApplicationService.verificationChangePassword(verificode);

        return ResponseEntity.ok("success");
    }

}
*/