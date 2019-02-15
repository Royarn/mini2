package com.royarn.mini.collection;

import java.util.*;

/**
 * Description:
 *
 * @author dell
 * @date 2019-02-15
 */
public class SimpleConllection {

    public static void main(String[] args) {
        //arraylist();
        //utils();
        //listIntf();
        /*System.out.println(fill(new ArrayList<>()));
        System.out.println(fill(new LinkedList<>()));
        System.out.println(fill(new HashSet<>()));
        System.out.println(fill(new TreeSet<>()));
        System.out.println(fill(new LinkedHashSet<>()));
        System.out.println(fill(new HashMap<>()));
        System.out.println(fill(new TreeMap<>()));
        System.out.println(fill(new LinkedHashMap<>()));*/
        //statistics();
        //queue();
        //priorityqueue();
        //iterator();
        envVariables();
    }

    /**
     * use arraylist to fill
     */
    public static void arraylist() {
        Collection<Integer> integers = new ArrayList<>();
        for (int i=1;i<10;i++) {
            integers.add(i);
        }
        System.out.println(integers);
    }

    /**
     * java.util工具类
     * Arrays、Collections
     */
    public static void utils() {
        Collection<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        Integer[] moreEles = {5, 6, 7, 8, 9};
        //integers.addAll(Arrays.asList(moreEles));
        Collections.addAll(integers, moreEles);
        System.out.println(integers);
    }

    /**
     *
     */
    public static void listIntf() {
        class Snow {}
        class Power extends Snow {}
        class Light extends Power {}
        class Heavy extends Power {}
        class Crusy extends Snow {}
        class Slush extends Snow {}

        List<Snow> snows = Arrays.asList(
                new Crusy(), new Slush(), new Power()
        );
        List<Snow> snows1 = Arrays.asList(new Light(), new Heavy());
    }

    /**
     * print containers
     */
    public static Collection fill(Collection<String> collection) {
        collection.add("rat");
        collection.add("cat");
        collection.add("dog");
        collection.add("dog");
        return collection;
    }

    /**
     * print map
     */
    public static Map fill(Map<String, String> map) {
        map.put("rat", "fuzzy");
        map.put("cat", "rabbit");
        map.put("dog", "bosco");
        map.put("dog", "spot");
        return map;
    }

    /**
     * 统计随机数是否分布均匀
     */
    public static void statistics() {
        Map<Integer, Integer> map = new HashMap<>();
        Random random = new Random(47);
        for (int i=0;i<10000;i++) {
            Integer num = random.nextInt(10);
            map.put(num, map.get(num) == null ? 1 : map.get(num)+1);
        }
        System.out.println(map);
    }

    /**
     * queue API
     *  peek() 和 element() 获取队头元素
     *  poll() 和 remove() 队头元素移除并返回该元素
     *  peek() 和 poll() 在队头为空时返回null
     *  element() 和 remove() 在队头为空时抛出异常NoSuchElementException
     */
    public static void queue() {
        Queue<Integer> queue = new LinkedList<>();
        Random random = new Random(47);
        for (int i=0;i<10;i++) {
            queue.offer(random.nextInt(i + 10));
        }
        System.out.println(queue);
        Queue<Character> queue1 = new LinkedList<>();
        for (char c : "royarn".toCharArray()) {
            queue1.offer(c);
        }
        System.out.println(queue1);
    }

    /**
     * priority queue
     */
    public static void priorityqueue() {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Random random = new Random(47);
        for (int i = 0;i<10;i++) {
            queue.offer(random.nextInt(i + 10));
        }
        //System.out.println(queue);
        List<Integer> ints = Arrays.asList(25, 22, 20, 18, 14, 9, 3, 1, 1, 2, 9);
        queue = new PriorityQueue<>(ints.size(), Collections.reverseOrder());
        queue.addAll(ints);
        System.out.println(queue);
    }

    /**
     * Iterable implement
     */
    public static void iterator() {
        class IterableImpl implements Iterable<String> {
            String[] words = ("this is how we worked").split(" ");
            @Override
            public Iterator<String> iterator() {
                return new Iterator() {
                    private int index = 0;
                    @Override
                    public boolean hasNext() {
                        return index < words.length;
                    }

                    @Override
                    public Object next() {
                        return words[index++];
                    }
                };
            }
        }
        for (String str : new IterableImpl()) {
            System.out.println(str);
        }
    }

    /**
     * env variables
     */
    public static void envVariables() {
        for (Map.Entry entry : System.getenv().entrySet()) {
            System.out.println(entry.getKey().toString() + " || " + entry.getKey().toString());
        }
    }

    /**
     * adapt pattern
     */
    public static void adapt() {
        class ReversibleArrayList<T> extends ArrayList<T> {
            public ReversibleArrayList(Collection<T> collection) {
                super(collection);
            }
            public Iterable<T> reversed() {
                return () -> new Iterator<T>() {
                            int current = size() - 1;
                            @Override
                            public boolean hasNext() {
                                return current > -1;
                            }

                            @Override
                            public T next() {
                                return get(current--);
                            }
                        };
            }
        }
    }
}