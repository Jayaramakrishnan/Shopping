package com.crackers.controllers;

import com.crackers.interceptors.SessionHandler;

import play.mvc.Controller;

@SessionHandler
public class BaseController extends Controller
{}