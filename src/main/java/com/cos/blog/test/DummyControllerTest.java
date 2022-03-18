package com.cos.blog.test;

import com.cos.blog.model.RoleType;
import com.cos.blog.model.User;
import com.cos.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RestController
public class DummyControllerTest {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/dummy/users")
    public List<User> list(){
        return userRepository.findAll();
    }

    @GetMapping("/dummy/user")
    public List<User> pageList(@PageableDefault(size = 2, sort = "id",direction = Sort.Direction.DESC)Pageable pageable){
        Page<User> pagingUser = userRepository.findAll(pageable);
        List<User> users = pagingUser.getContent();
        return users;
    }

    @GetMapping("/dummy/user/{id}")
    public User detail(@PathVariable int id){
       User user =  userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
           @Override
           public IllegalArgumentException get() {
               return new IllegalArgumentException("해당 유저는 없습니다." + id);
           }
       });
       return user;
    }

    @PostMapping("/dummy/join")
    public String join(User user){
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return "회원가입 완료";
    }
    //함수 종료시 자동 commit
//    @Transactional //Save 를 쓰지 안하도 update  가 된다 .
//    @PutMapping("/dummy/user/${id}")
//    public User userUpdate(@PathVariable int id, @RequestBody User requestUse){
//        User user = userRepository.findById(id).orElseThrow(()->{
//            return new IllegalArgumentException("수정 실패요 ");
//        });
//        user.setPassword(requestUse.getPassword());
//        user.setEmail(requestUse.getEmail());
//        // save 는 id 를 전달안하면 insert
//        //  아이드를 전달하면 update
//        //userRepository.save(user);
//
//        //더티 체킹
//        return null;
//    }
//    @DeleteMapping("/dummy/userDelete/${id}")
//    public String  delete(@PathVariable int id) {
//        try {
//            userRepository.deleteById(id);
//        }catch (EmptyResultDataAccessException e){
//            return "삭제에 실패했습니다 해당 아이디는 없어요 .";
//        }
//        return "삭제 완료";
//    }
}
