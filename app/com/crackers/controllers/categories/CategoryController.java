/**
 * @author jayaramakrishnanrajagopal
 * @date 06-Mar-2017
 */
package com.crackers.controllers.categories;

import org.springframework.stereotype.Component;

import com.crackers.controllers.BaseController;

import play.mvc.Result;

@Component
public class CategoryController extends BaseController {

	public Result getCategories() {
		return ok();
	}
}