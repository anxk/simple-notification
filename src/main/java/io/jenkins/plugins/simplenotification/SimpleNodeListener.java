package io.jenkins.plugins.simplenotification;

import java.util.List;

import hudson.Extension;
import hudson.model.Computer;
import hudson.model.TaskListener;
import hudson.slaves.ComputerListener;
import hudson.slaves.OfflineCause;
import hudson.slaves.SlaveComputer;

@Extension
public class SimpleNodeListener extends ComputerListener {

    @Override
    public void onOffline(Computer c, OfflineCause cause) {
        Event event = new Event(new NodeState(c, cause));
		SimpleNotification.notify(event);
    }

    @Override
    public void onOnline(Computer c, TaskListener listener) {
		if (c instanceof SlaveComputer) {
			Event event = new Event(new NodeState(c));
			SimpleNotification.notify(event);
		}
	}
}