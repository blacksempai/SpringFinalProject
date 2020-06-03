package com.javacourse.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ControllerCommand {
    String execute(HttpServletRequest request, HttpServletResponse response);
}
