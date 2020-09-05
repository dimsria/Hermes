/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MiscUtils;

import UserUtils.LoginBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author srvmng
 */
@Stateless@Named(value = "activation")
public class ActivationBean {

  @Inject LoginBean login;

  private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

  public String activationCode(int count) {
    StringBuilder builder = new StringBuilder();
    while (count--!=0) {
      int character = (int)(Math.random() * ALPHA_NUMERIC_STRING.length());
      builder.append(ALPHA_NUMERIC_STRING.charAt(character));
    }

    System.out.print(builder.toString());
    return builder.toString();

  }

}