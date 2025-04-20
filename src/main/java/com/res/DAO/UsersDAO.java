package com.res.DAO;

import java.util.ArrayList;
import com.res.DTO.Users;

public interface UsersDAO {

    boolean insertCustomer(Users user);
    boolean updateCustomer(Users user);
    boolean deleteCustomer(Users user);
    Users getCustomer(String mail, String password);
    Users getCustomer(String mail);
    ArrayList<Users> getAllUsers();
    Users getUserById(int id);
	boolean deleteUser(int userId);
}
