package ua.lviv.iot.view;

import ua.lviv.iot.controller.AbstractController;

@FunctionalInterface
public interface Printable {
	void print(@SuppressWarnings("rawtypes") AbstractController controller);
}
