package com.smu.forum.service;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.SecureUtil;
import com.smu.forum.domain.Account;
import com.smu.forum.domain.Answer;
import com.smu.forum.domain.Question;
import com.smu.forum.mapper.UserMapper;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AccountService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private MailService mailService;
//    private final JdbcTemplate jdbcTemplate;
//
//    public AccountService(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }

//    public void addAccount(Account account) {
//        this.jdbcTemplate.update(
//            "insert into accounts (username, password) values('" +
//                account.getUsername() + "','" +
//                account.getPassword() + "')");
//    }
//
//    public Account getAccount(String username) {
//        return this.jdbcTemplate.queryForObject(
//        "select * from accounts where username = '" + username + "'",
//            new BeanPropertyRowMapper<Account>(Account.class));
//    }

    /**
     * register
     * @param account
     * @return
     */
    public Map<String, Object> createAccount(Account account){
        //snowflake algorithm
        String confirmCode = IdUtil.getSnowflake(1,1).nextIdStr();

        //salt for encryption a-z0-9
        String salt = RandomUtil.randomString(6);

        //encryption: password + salt
        String md5Pwd = SecureUtil.md5(account.getPassword() + salt);

        //activation time
        LocalDateTime ldt = LocalDateTime.now().plusDays(1);

        //initialize account
        account.setSalt(salt);
        account.setPassword(md5Pwd);
        account.setConfirmCode(confirmCode);
        account.setActivationTime(ldt);
        account.setIsVaild((byte) 0);

        //add account
        //if result > 0 then success
        int result = userMapper.insertUser(account);
        Map<String, Object> resultMap = new HashMap<>();
        if (result > 0){
            //TODO
            String activationUrl = "http://localhost/user/activation?confirmCode="+confirmCode;
            mailService.senMailForActivateAccount(activationUrl, account.getEmail());
            resultMap.put("code",200);
            resultMap.put("message","register successfully, please activate your account!");
        }else{
            resultMap.put("code",400);
            resultMap.put("message","register failed");
        }
        return resultMap;
    }

    public  Map<String, Object> loginAccount(Account account){
        Map<String, Object> resultMap = new HashMap<>();
        //query user by email
        //if there is no result, return: account doesn't exist
        List<Account> accountList = userMapper.selectUserByEmail(account.getEmail());
        System.out.println(account.getEmail());
        System.out.println(account.getPassword());

        //System.out.println(account.);
        if(accountList == null || accountList.isEmpty()){
            resultMap.put("code",400);
            resultMap.put("message","account doesn't exist or need activate!");
            return resultMap;
        }
        //if there are many account that have the same email, return: account error, contact admins
        if(accountList.size() > 1){
            resultMap.put("code",400);
            resultMap.put("message","account is in risk, please contact admins!");
            return resultMap;
        }
        //if there is only on account, match the password
        Account account1 = accountList.get(0);
        String md5Pwd = SecureUtil.md5(account.getPassword() + account1.getSalt());

        if(!account1.getPassword().equals(md5Pwd)){
            resultMap.put("code",400);
            resultMap.put("message","password doesn't match!");
            return resultMap;
        }
        resultMap.put("code",200);
        resultMap.put("message","Login successfully!");
        return resultMap;
    }

    public Map<String, Object> activationAccount(String confirmCode){
        Map<String, Object> resultMap = new HashMap<>();
        //query user by confirm code
        Account account = userMapper.selectUserByConfirmCode(confirmCode);

        //if time is expire
        boolean after = LocalDateTime.now().isAfter(account.getActivationTime());
        if(after){
            resultMap.put("code",400);
            resultMap.put("message","Link expired, please sign up again!");
            return resultMap;
        }

        // query user by confirm code and update is_vaild
        int result= userMapper.updateUserByConfirmCode(confirmCode);

        if(result > 0){
            resultMap.put("code",200);
            resultMap.put("message","Activate successfully!");
        }else{
            resultMap.put("code",400);
            resultMap.put("message","Activate failed!");
        }
        return resultMap;
    }
}
