package com.maven.controller;

import com.maven.entities.User;
import lombok.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.maven.services.UserService;

import javax.servlet.http.HttpSession;

@Controller
@Data
@RequestMapping("/user")
public class UserController {
    @Autowired
     private UserService userService;
  @RequestMapping(value = "/addUser",method = RequestMethod.POST)
  public String addUser(@ModelAttribute("user") User user){
       userService.addUser(user);
       return "redirect:/login";
  }
  @RequestMapping(value ="/login",method = RequestMethod.POST)
  public String loginUser(@ModelAttribute("user")User user, HttpSession session){
      System.out.println(user.toString());
      User user1 = userService.loginUser(user);
      if(user!=null){
          session.setAttribute("user",user1);
          return "redirect:/userProfile";
      }
      return "redirect:/login";
  }
}
