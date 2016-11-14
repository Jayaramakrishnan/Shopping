package com.crackers.interceptors;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import play.libs.F.Promise;
import play.mvc.Action;
import play.mvc.Http;
import play.mvc.Result;

@Component
@Scope("prototype")
public class SessionAction extends Action<SessionHandler>
{

    @Override
    public Promise<Result> call(Http.Context ctx) throws Throwable
    {
        /*
         * String previousT = ctx.session().get("usertime"); if (Dashboard.clientConfigurationSettings != null && (previousT != null) && !previousT.equals(RestUrlAttribute.EMPTY_QUOTES)) { long currentT1 = new Date().getTime(); long previousT1 = Long.valueOf(previousT); long
         * timeout = Long.valueOf(Dashboard.clientConfigurationSettings.getSessionTimeout()); if ((currentT1 - previousT1) > timeout) { ctx.session().clear(); } else { String tickString = Long.toString(new Date().getTime()); ctx.session().put("usertime", tickString); } } else {
         * ctx.session().clear(); }
         */
        return delegate.call(ctx);
    }
}