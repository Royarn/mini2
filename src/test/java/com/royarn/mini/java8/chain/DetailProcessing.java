package com.royarn.mini.java8.chain;

/**
 * Description:
 *
 * @author dell
 * @date 2018-10-19
 */
public class DetailProcessing {

    static class GenarateIns {

        public static synchronized Person getInstance(String clazzName) {
            Person person = null;
            try {
                Class<?> clazz = Class.forName(clazzName);
                person = (Person) clazz.newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            return person;
        }
    }

    static class NameIns extends ChainProcessing<Person> {

        @Override
        Person handleWork(Person person) {
            person.setName("royarn");
            return person;
        }
    }

    static class SexIns extends ChainProcessing<Person> {

        @Override
        Person handleWork(Person person) {
            person.setSex(true);
            return person;
        }
    }

    static class AgeIns extends ChainProcessing<Person> {

        @Override
        Person handleWork(Person person) {
            person.setAge(28);
            return person;
        }
    }

    public static void main(String[] args) {
        Person person = GenarateIns.getInstance("com.royarn.mini.java8.chain.Person");
        ChainProcessing<Person> nameIns = new NameIns();
        ChainProcessing<Person> sexIns = new SexIns();
        ChainProcessing<Person> ageIns = new AgeIns();
        nameIns.setChainProcessing(sexIns);
        sexIns.setChainProcessing(ageIns);
        System.out.println(nameIns.handle(person));
    }
}
