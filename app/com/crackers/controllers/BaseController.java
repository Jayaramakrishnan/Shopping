package com.crackers.controllers;

import play.mvc.Controller;

import com.crackers.interceptors.SessionHandler;


@SessionHandler
public class BaseController extends Controller
{}