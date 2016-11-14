/**
 * @author rajagja
 * @date Nov 12, 2016
 */
package com.crackers.controllers;

import org.springframework.stereotype.Component;

import play.mvc.Controller;
import play.mvc.Result;

@Component
public class Application extends Controller
{

    public Result hello()
    {
        return ok("Hello Neo4j");
    }
}