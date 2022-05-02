package com.smu.forum.mapper;

import com.smu.forum.domain.Account;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    /**
     * create a new account
     * @param account
     * @return
     */
    @Insert("INSERT INTO accounts ( password, activation_time, email, is_vaild, salt, confirm_code)" +
    " VALUES (#{password}, #{activationTime}, #{email}, #{isVaild}, #{salt}, #{confirmCode} )")
    int insertUser(Account account);

    /**
     * query user by confirm_code
     * @param confirmCode
     * @return
     */
    @Select("SELECT email, activation_time FROM accounts WHERE confirm_code = #{confirmCode}")
    Account selectUserByConfirmCode(@Param("confirmCode") String confirmCode);

    /**
     * query user by confirm_code and set is_vaild = 1(available)
     * @param confirmCode
     * @return
     */
    @Update("UPDATE accounts SET is_vaild = 1 WHERE confirm_code = #{confirmCode}")
    int updateUserByConfirmCode(@Param("confirmCode") String confirmCode);

    /**
     * query user by email
     * @param email
     * @return
     */
    @Select("SELECT email, password, salt FROM accounts WHERE email = #{email} AND is_vaild = 1")
    List<Account> selectUserByEmail(@Param("email") String email);
}
