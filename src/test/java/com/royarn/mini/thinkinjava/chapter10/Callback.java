package com.royarn.mini.thinkinjava.chapter10;

/**
 * Description:
 *
 * @author dell
 * @date 2018-11-28
 */
interface Callback {
    void tellAnswer(String answer);
}

class Teacher implements Callback {
    private Student student;
    public Teacher(Student student) {
        this.student = student;
    }
    @Override
    public void tellAnswer(String answer) {
        System.out.println("receive answer : " + answer);
    }
    public void askQuestion() {
        student.resolveQuestion(this);
    }
}

interface Student {
    void resolveQuestion(Callback callback);
}

class Ricky implements Student {
    @Override
    public void resolveQuestion(Callback callback) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        callback.tellAnswer("if I was called, I will be call you again");
    }
}

class Tes {
    public static void main(String[] args) {
        Student student = new Ricky();
        Teacher teacher = new Teacher(student);
        teacher.askQuestion();
    }
}