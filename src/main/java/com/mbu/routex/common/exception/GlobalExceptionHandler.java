package com.mbu.routex.common.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public String handleNotFound(ResourceNotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("errorCode", "404");
        return "error/404";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNoHandler(NoHandlerFoundException ex, Model model) {
        model.addAttribute("message", "The page you are looking for does not exist.");
        model.addAttribute("errorCode", "404");
        return "error/404";
    }

    @ExceptionHandler(org.springframework.security.access.AccessDeniedException.class)
    public String handleAccessDenied(Model model) {
        model.addAttribute("message", "You do not have permission to access this resource.");
        model.addAttribute("errorCode", "403");
        return "error/access-denied";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneral(Exception ex, Model model) {
        model.addAttribute("message", "An unexpected error occurred. Please try again.");
        model.addAttribute("errorCode", "500");
        return "error/500";
    }
}
