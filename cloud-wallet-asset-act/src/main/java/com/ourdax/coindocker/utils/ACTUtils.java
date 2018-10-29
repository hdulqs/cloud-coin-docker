package com.ourdax.coindocker.utils;

import java.math.BigDecimal;
import java.math.BigInteger;
import org.web3j.utils.Strings;

/**
 * Created by zhangjinyang on 2018/1/31.
 */
public class ACTUtils {

  private ACTUtils() {}

  public static BigDecimal fromWei(BigInteger wei, int decimals) {
    return new BigDecimal(wei).divide(getRatio(decimals));
  }

  public static BigInteger toWei(BigDecimal amount, int decimals) {
    return amount.multiply(getRatio(decimals)).toBigInteger();
  }

  public static BigDecimal getRatio(int decimals) {
    String ratio = "1" + Strings.repeat('0', decimals) + ".0";
    return new BigDecimal(ratio);
  }
}
