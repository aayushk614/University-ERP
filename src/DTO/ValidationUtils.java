package DTO;

/**
 * Basic validation utility to prevent common security issues
 */
public class ValidationUtils {
    
    /**
     * Checks if input contains potential SQL injection patterns
     */
    public static boolean containsSQLInjection(String input) {
        if (input == null) return false;
        
        String lowerInput = input.toLowerCase();
        String[] sqlKeywords = {
            "select", "insert", "update", "delete", "drop", "create", 
            "alter", "exec", "union", "'", "\"", ";", "--", "/*", "*/"
        };
        
        for (String keyword : sqlKeywords) {
            if (lowerInput.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Basic email validation
     */
    public static boolean isValidEmail(String email) {
        return email != null && email.contains("@") && email.contains(".");
    }
    
    /**
     * Basic input sanitization
     */
    public static String sanitizeInput(String input) {
        if (input == null) return null;
        return input.trim().replaceAll("[<>\"'&]", "");
    }
    
    /**
     * Check if string is not empty and doesn't contain dangerous characters
     */
    public static boolean isValidInput(String input) {
        return input != null && 
               !input.trim().isEmpty() && 
               !containsSQLInjection(input);
    }
} 