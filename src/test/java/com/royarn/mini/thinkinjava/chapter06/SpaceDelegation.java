package com.royarn.mini.thinkinjava.chapter06;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-15
 */
public class SpaceDelegation {

    private String name;

    private SpaceControls controls = new SpaceControls();

    public SpaceDelegation(String name) {
        this.name = name;
    }

    void up(int volecity) {
        controls.up(volecity);
    }

    void down(int volecity) {
        controls.down(volecity);
    }

    void left(int volecity) {
        controls.left(volecity);
    }

    void right(int volecity) {
        controls.right(volecity);
    }

    void forward(int volecity) {
        controls.forward(volecity);
    }

    void back(int volecity) {
        controls.back(volecity);
    }

    void truboBoost(int volecity) {
        controls.turboBoost(volecity);
    }
}

class SpaceControls {
    void up(int volecity) {}
    void down(int volecity) {}
    void left(int volecity) {}
    void right(int volecity) {}
    void forward(int volecity) {}
    void back(int volecity) {}
    void turboBoost(int volecity) {}
}