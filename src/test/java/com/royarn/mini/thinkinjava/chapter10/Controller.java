package com.royarn.mini.thinkinjava.chapter10;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-28
 */
public class Controller {

    private List<Event> eventList = new ArrayList<>();
    public void addEvent(Event event) {
        eventList.add(event);
    }
    public void run() {
        while (eventList.size() > 0) {
            eventList.stream()
                    .filter(event -> {
                        if (event.ready()) {
                            System.out.println(event);
                            event.action();
                            eventList.remove(event);
                        }
                        return true;
                    });
        }
    }
}

class GreenHouseController extends Controller {
    private boolean light = false;
    public class LightOn extends Event {
        public LightOn(long delayTime) {
            super(delayTime);
        }
        @Override
        public void action() {
            light = true;
        }
        @Override
        public String toString() {
            return "Light is on";
        }
    }
}