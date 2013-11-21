package com.google.developers.gdgfirenze.admin.client;

import com.google.developers.gdgfirenze.service.SensormixService;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("service")
public interface GwtSensormixService extends RemoteService, SensormixService {

}
