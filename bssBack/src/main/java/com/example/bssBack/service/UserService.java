package com.example.bssBack.service;

import com.example.bssBack.entity.Attempt;
import com.example.bssBack.entity.User;
import com.example.bssBack.repository.AttemptRepository;
import com.example.bssBack.repository.UserRepository;
import com.example.bssBack.security.JwtTokenProvider;
import com.example.bssBack.security.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;

    private AttemptRepository attemptRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       AttemptRepository attemptRepository,
                       PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.attemptRepository = attemptRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param sid
     * @return username가 받은 값인 user를 찾아 User(UserDetails의 자식 클래스) 객체를 반환합니다.
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String sid) throws UsernameNotFoundException {
        User user = userRepository.findBySid(sid)
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));
        return user;
    }

    /**
     * @param sid
     * @return id가 받은 값인 user를 찾아 User(UserDetails의 자식 클래스) 객체를 반환합니다.
     * @throws UsernameNotFoundException
     */
    public UserDetails loadUserById(String sid) throws UsernameNotFoundException {
        User user = userRepository.findBySid(sid)
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));
        return user;
    }

    /**
     * user를 생성합니다.
     * @param username
     * @param password
     * @param email
     */
    public void create(String sid, String username, String password, String email)
        throws Exception {
            if (!userRepository.existsBySid(sid) && !username.equals("anonymousUser")) {
                User user = new User(
                        sid,
                        username,
                        passwordEncoder.encode(password),
                        email,
                        "USER"
                );
                userRepository.save(user);

                Attempt attempt = new Attempt();
                attemptRepository.save(attempt);


                user.setAttempt(attempt);
                userRepository.save(user);
            }
            else {
                throw new Exception("Student ID is not unique");
            }
    }

    /**
     * 주어진 sid인 user를 찾아 password가 일치하면 newPassword(선택적)와 email을 수정합니다.
     * @Param sid 필수 (변경 불가)
     * @param username 필수
     * @param password 기존 비밀번호 필수
     * @param newPassword 새 비밀번호(선택적)
     * @param email 새 이메일(필수)
     * @throws Exception
     */
    public void update(String sid, String username, String password, String newPassword, String email) throws Exception {
        User user = userRepository.findBySid(sid)
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));

        if (passwordEncoder.matches(password, user.getPassword())) {
            if (newPassword != null && !newPassword.isBlank()) {
                user.setPassword(passwordEncoder.encode(newPassword));
            }

            if (username != null && !username.isBlank()){
                user.setUsername(username);
            }

            if (email != null && !email.isBlank()) {
                user.setEmail(email);
            }
            userRepository.save(user);
        }
        else {
            throw new IllegalArgumentException("Password not matched");
        }
    }


    /**
     * 주어진 sid인 user를 삭제합니다.
     * @param sid
     * @throws Exception
     */
    public void delete(String sid) throws Exception {
        User user = userRepository.findBySid(sid)
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));

        user.setDeleted(true);
        userRepository.save(user);
    }


    /**
     * username과 password로 로그인을 시도합니다. 100회 실패시 로그인이 잠깁니다.
     * 성공시 엑세스 토큰을 반환합니다.
     * @param sid
     * @param password
     * @return token 반환
     * @throws UsernameNotFoundException username 존재하지 않는 경우, user가 밴 또는 잠금 또는 삭제된 경우
     */
    public String tryLogin(String sid, String password) throws UsernameNotFoundException {
        User user = userRepository.findBySid(sid)
                .orElseThrow(() -> new UsernameNotFoundException("User not present"));

        if (user.isBanned() || user.isLocked() || user.isDeleted())
            throw new UsernameNotFoundException("User not present");

        if(!passwordEncoder.matches(password, user.getPassword())){
            int failed = user.getAttempt().getAttempts();
            if (failed + 1 < 100) {
                user.getAttempt().setAttempts(failed + 1);
                userRepository.save(user);
            }
            else
                this.lock(user);
            throw new IllegalArgumentException("Password not matched");
        }

        if (user.getAttempt().getAttempts() != 0) {
            user.getAttempt().setAttempts(0);
            userRepository.save(user);
        }

        Authentication authentication = new UserAuthentication(sid, password, user.getAuthorities());
        String token = JwtTokenProvider.generateToken(authentication);

        return token;
    }



    /**
     * username 중복을 확인하여 사용할 수 있는지 반환합니다.
     * @param sid
     * @return 사용할 수 있으면 true, 없으면 false
     */
    public boolean canUseAsUsername(String sid) {
        return !userRepository.existsBySid(sid) && !sid.equals("anonymousUser");
    }


    /**
     * user를 로그인할 수 없도록 잠급니다.
     * @param user
     */
    public void lock(User user) {
        user.setLocked(true);
        userRepository.save(user);
    }

    /**
     * user를 로그인할 수 없도록 밴 합니다.
     * @param user
     */
    public void ban(User user) {
        user.setBanned(true);
        userRepository.save(user);
    }


}
