package com.supercell.elmm.service;

import javax.jms.Destination;

public interface ProducerService {
	public void sendMessage(Destination destination, final String message);
}
