package com.crackers.controllers;

import org.springframework.stereotype.Component;

import play.mvc.Controller;
import play.mvc.Result;
import views.html.index;

@Component
public class LoginController extends Controller
{

    public Result index(String url)
    {
        return ok(index.render("Diwali Crackers"));
    }
}