package com.example.codetribe1.unischool.util;

public class PMException extends Exception {
        public PMException(String message) {
           this.message = message; 
        }
        public String message;
    }