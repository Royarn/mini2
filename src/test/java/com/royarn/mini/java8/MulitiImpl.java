package com.royarn.mini.java8;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-22
 */

/**
 * java8引入的默认实现
 *  利：  1.解决向后兼容性问题， 比如在原有接口上添加新的API，可以提供默认实现来避免其所有的实现类改造
 *        2.类似与模板模式，可以提供一些通用实现
 *
 */
public class MulitiImpl implements Rorateable, Moveable, Resizeable{


    @Override
    public void setRorationAngle(int angleInDegrees) {

    }

    @Override
    public int getRorationAngle() {
        return 0;
    }

    @Override
    public int getX() {
        return 0;
    }

    @Override
    public int getY() {
        return 0;
    }

    @Override
    public void setX(int x) {

    }

    @Override
    public void setY(int y) {

    }

    @Override
    public int getWeight() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public void setWeight(int weight) {

    }

    @Override
    public void setHeight(int height) {

    }

    @Override
    public void setAbsoluteSize(int weight, int height) {

    }

    public static void main(String[] args) {

    }
}

/**
 * 旋转能力
 */
interface Rorateable {

    void setRorationAngle(int angleInDegrees);

    int getRorationAngle();

    default void rorateBy(int angleDegrees) {
        setRorationAngle(angleDegrees);
    }
}

/**
 * 移动能力
 */
interface Moveable {

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    default void moveHorizontally(int distance) {
        setX(getX() + distance);
    }

    default void moveVertically(int distance) {
        setY(getY() + distance);
    }
}

/**
 * 调整能力
 */
interface Resizeable {

    int getWeight();

    int getHeight();

    void setWeight(int weight);

    void setHeight(int height);

    void setAbsoluteSize(int weight, int height);

    default void setRelativeSize(int wFactor, int yFactor) {
        setAbsoluteSize(getWeight() / wFactor, getHeight() / yFactor);
    }
}