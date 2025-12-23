

package com.example.initierespringbd;

import com.example.initierespringbd.dtos.UserCreateRequest;
import com.example.initierespringbd.dtos.UserUpdateRequest;
import com.example.initierespringbd.mappers.UserMapper;
import com.example.initierespringbd.model.User;
import com.example.initierespringbd.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class View {

    UserRepository userRepository;

    UserMapper userMapper;

    public View(UserRepository userRepository,UserMapper userMapper){
        this.userMapper = userMapper;
        this.userRepository=userRepository;
        this.viewAllUsers();
        this.testSearchBy();
        this.findUserByLastName();
        this.findUserByEmail();
        this.findUsersByAgeRange();
        this.findUsersHiredBetween();
        this.search();
        this.countHiredBefore();
        this.userExistsByEmail();
//        this.add();
//        this.delete();
        this.update();
    }

    public void viewAllUsers(){
        this.userRepository.findAll().forEach(System.out::println);
    }

    public  void testSearchBy(){

        Optional<User>  userOptional=userRepository.findUserByEmail("ioana.marin@email.com");

        if(userOptional.isPresent()){
            System.out.println(userOptional);
        } else System.out.println("NU EXISTA");
    }

    public void findUserByLastName(){

        Optional<User> userOptional = userRepository.findByLastNameIgnoreCaseJPQL("popescu");

        if(userOptional.isPresent()){
            System.out.println(userOptional);
        } else System.out.println("Nu exista");
    }

    public void findUserByEmail(){

        Optional<User> userOptional = userRepository.findByEmailIgnoreCaseJQPL("");

        if(userOptional.isPresent()){
            System.out.println(userOptional);
        } else System.out.println("Nu Exista");
    }

    public void findUsersByAgeRange(){

        List<User> usersBetween = this.userRepository.findByAgeRange(20,30);

        if(usersBetween.isEmpty()){
            System.out.println("Niciun user gasit");
        } else usersBetween.forEach(System.out::println);
    }

    public void findUsersHiredBetween(){
        LocalDate from = LocalDate.of(2017,9,18);
        LocalDate to = LocalDate.of(2021,3,15);

        List<User> usersHired = this.userRepository.findHiredBetween(from,to);

        if(usersHired.isEmpty()){
            System.out.println("Niciun user angajat in perioada aia");
        } else usersHired.forEach(System.out::println);
    }

    public void search(){

        Page<User> search = this.userRepository.search("ia", PageRequest.of(0,2)); // sunt 3 persoane cu 'ia'
        if(search.isEmpty()){
            System.out.println("Empty");
        } else search.forEach(System.out::println);
    }

    public void countHiredBefore(){
        LocalDate date = LocalDate.of(2018,11,23);

        long usersHiredBefore = this.userRepository.countHiredBefore(date);

        if(usersHiredBefore == 0){
            System.out.println("Niciun user angajat inainte de perioada respectiva");
        } else System.out.println(usersHiredBefore);
    }

    public void userExistsByEmail(){

        boolean found = this.userRepository.existsByEmailJPQL("elena.stan@email.com");


        if(!found) {
            System.out.println("Doesn't Exist");
        } else System.out.println("User exists");
    }

    @Transactional
    public  void  add(){

        UserCreateRequest userCreateRequest = new UserCreateRequest("Adi","Abdul","abdul@gmail.com",37,LocalDate.now(),"0793827710","parolaparola");

        User user=this.userRepository.save(userMapper.toEntity(userCreateRequest));

        System.out.println(userMapper.toDto(user));


    }


    @Transactional
    public  void  delete(){
       Optional<User> userOptional = userRepository.findById(151L);

       if(userOptional.isPresent()){
           this.userRepository.delete(userOptional.get());


           System.out.println(userMapper.toDto(userOptional.get()));

       }
    }

    @Transactional
    public void update(){
        UserUpdateRequest userUpdateRequest = new UserUpdateRequest(28,"updatedEmail@gmail.com","updatedPassword");

        Optional<User> userOptional = this.userRepository.findById(1L);

        if(userOptional.isPresent()){
            User user=userOptional.get();
            if(userUpdateRequest.password().length()>0){

                user.setPassword(userUpdateRequest.password());
            }
            if(userUpdateRequest.age()>20){
                user.setAge(userUpdateRequest.age());
            }
            if(userUpdateRequest.email().length()>5){
                user.setEmail(userUpdateRequest.email());
            }



            userRepository.save(user);
        }
    }


}
