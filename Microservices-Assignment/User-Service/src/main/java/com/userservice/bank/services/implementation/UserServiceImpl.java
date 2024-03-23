package com.userservice.bank.services.implementation;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.userservice.bank.entites.User;
import com.userservice.bank.exceptions.ResourceNotFoundException;
import com.userservice.bank.repositories.UserRepository;
import com.userservice.bank.services.AccountFeignClient;
import com.userservice.bank.services.TransactionFeignClient;
import com.userservice.bank.services.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService{
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountFeignClient accountFeignClient;

    @Autowired
    private TransactionFeignClient transactionFeignClient;

    @Override
    public User createUser(User user){
        log.info("User created : {}",user);
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    @Override
    public List<User> getAllUsers(){
       List<User> userList = userRepository.findAll();
       List<User> newUserList = userList.stream().map(user->{
        user.setAccount(accountFeignClient.getAccountofUser(user.getUserId()));
        user.setTransaction(transactionFeignClient.getTransactions(user.getUserId()));
        return user;
       }).collect(Collectors.toList());
       return newUserList;
    }

    @Override
    public User updateUser(User user){
        return userRepository.save(user);
    }

    @Override
    public User getUserByUserId(Long id){
        User user = userRepository.findByUserId(id);        
        if (user!= null) {
            user.setAccount(accountFeignClient.getAccountofUser(user.getUserId()));
            user.setTransaction(transactionFeignClient.getTransactions(user.getUserId()));
            log.info("User found with id: {}", id);
            return user;
        }else{
            log.info("User not found with id: {}", id);
            throw new ResourceNotFoundException("User not found with the given id: "+ id);
        }
    }
     
}
