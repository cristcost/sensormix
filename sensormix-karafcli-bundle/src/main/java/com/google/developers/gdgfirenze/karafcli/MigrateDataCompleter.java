package com.google.developers.gdgfirenze.karafcli;

import java.util.List;

import org.apache.karaf.shell.console.Completer;
import org.apache.karaf.shell.console.completer.StringsCompleter;

public class MigrateDataCompleter implements Completer {

	@Override
	public int complete(String buffer, int cursor, List<String> candidates) {
		StringsCompleter delegate = new StringsCompleter();
	    delegate.getStrings().add("START");
	    delegate.getStrings().add("END");
	    return delegate.complete(buffer, cursor, candidates);
	}

}
