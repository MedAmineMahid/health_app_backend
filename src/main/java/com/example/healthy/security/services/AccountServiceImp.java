package com.example.healthy.security.services;

import com.example.healthy.security.entities.Role;
import com.example.healthy.security.entities.User;
import com.example.healthy.security.repositories.RoleRepository;
import com.example.healthy.security.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Transactional
public class AccountServiceImp implements AccountService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Autowired
    RoleServiceImp roleServiceImp;


    @Override
    public Role createRole(String role){
        Role newRole= Role.builder().role(role).build();
        return roleRepository.save(newRole);
    }

    @Override
    public void addRoleToUser(String username, Role role){
        User user = userRepository.findByUsername(username);
        if (user != null && role != null) {
            if (user.getRoles() == null) {
                user.setRoles(new ArrayList<>()); // Initialisation de la liste si elle est null
            }
            user.getRoles().add(role);
            userRepository.save(user);
        }
    }

    @Override
    public void removeRoleFromUser(String username, String role){
        User user = userRepository.findByUsername(username);
        Role hisRole = roleRepository.findById(role).orElse(null);
        user.getRoles().remove(hisRole);
    }


    private static final String LETTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    @Override
    public String usernameGenerator(String firstName, String lastName) {


            // Generate random letter
            char randomLetter = LETTERS.charAt(RANDOM.nextInt(LETTERS.length()));

            // Generate random number (between 1000 and 9999)
            int randomNumber = RANDOM.nextInt(9000) + 1000;

            // Combine all parts to form the username
            String username = String.format("%c%d%s%s%s", randomLetter, randomNumber,"_", lastName, firstName);

            // Optionally, you can lowercase the username
            return username.toUpperCase();
    }
    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String OTHER_CHAR = "@*_";

    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER + OTHER_CHAR;
    private static Random random = new Random();


    public String generateRandomPassword() {
        int length=8;
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomCharIndex = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            sb.append(DATA_FOR_RANDOM_STRING.charAt(randomCharIndex));
        }
        return sb.toString();
    }



    @Autowired EmailServiceImp sendEmail;
    @Override
    public User createUser(User user) {
        String username=user.getUsername();
        //verify that the username doesn't exist because it's unique
        if(userRepository.findByUsername(username)!=null){
            System.err.println("email already exists!");
            throw new RuntimeException("Error : Email already exists!");
        }else {
            user.setUserId(UUID.randomUUID().toString());
            String clearPassword = user.getPassword();
            user.setPassword(passwordEncoder.encode(clearPassword));
            //set role customer

            //max calories
            user.setMaxDailyCalories();
            User savedUser= userRepository.save(user);

            try{
                sendEmail.sendEmail(user.getUsername(),"Your Credentials For Healthy",username+" "+clearPassword);
                System.out.println("\n New user was created username  : "+user.getUsername() +"password   : "+clearPassword);
            }catch (Exception e) {
                System.out.println("ERROR SENDING EMAIL CREDENTIALS : " + e.getMessage() + "Email : " + username + "PASSWORD : " + clearPassword);
            }
            return savedUser;
        }

    }
    @Override
    public User loadUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            System.out.println("user not found");
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public List<String> listRole(User user){
        return user.getRoles().stream().map(Role::getRole).collect(Collectors.toList());
    }
    /*

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
    */

    /*



    public void sendPasswordByEmail(String email, String password) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(email);
        mailMessage.setSubject("Your New Account Password");
        mailMessage.setText("Your password is: " + password);

        javaMailSender.send(mailMessage);
    }
     */

      /*
    @Override
    public User createUser(String username, String password, String confirmPassword) {
        //verify that the username does't exist because it's unique
        if(userRepository.findByUsername(username)!=null) throw new RuntimeException("Error : username already exists!");
        //password confirmation is correct
        if(!password.equals(confirmPassword)) throw new RuntimeException("Error : the passwords are not the same!");
        User user = User.builder()
                .userId(UUID.randomUUID().toString())
                .username(username)
                .password(passwordEncoder.encode(password))
                .build();
        return userRepository.save(user);
    }

     */
    public void NewUser(User user , Role role){
        if((user != null) && (role != null)){
            User saved = createUser(user);
            addRoleToUser(saved.getUsername(),role);
        }
    }


}
