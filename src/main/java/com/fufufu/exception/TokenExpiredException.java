package com.fufufu.exception;  
  
public class TokenExpiredException extends RuntimeException {  
    public TokenExpiredException(String message) {  
        super(message);  
    }  
      
    // 如果需要，可以添加其他构造函数，例如接受Throwable参数的构造函数  
}