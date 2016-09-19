package coder.dasu.xiaozhao;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sxq on 2016/9/2.
 */
public class HashMapTest {
    /**
     * 已知一个 HashMap<Integer，User>集合， User 有 name（String）和 age（int）属性。请写一个方法实现
     * 对HashMap的排序功能，该方法接收HashMap<Integer，User>为形参，返回类型为HashMap<Integer，User>，
     * 要求对HashMap中的User的age倒序进行排序。排序时key=value键值对不得拆散。
     */

    private HashMap<Integer, User> mInput;
    private LinkedHashMap<Integer, User> mExpectResult;

    @Before
    public void init(){
        mInput = new HashMap<>();
        mInput.put(1,new User("zhao",20));
        mInput.put(5,new User("sun",18));
        mInput.put(3,new User("zhou",27));
        mInput.put(4,new User("li",25));
        mInput.put(2,new User("qian",26));

        mExpectResult = new LinkedHashMap<>();
        mExpectResult.put(3,new User("zhou",27));
        mExpectResult.put(2,new User("qian",26));
        mExpectResult.put(4,new User("li",25));
        mExpectResult.put(1,new User("zhao",20));
        mExpectResult.put(5,new User("sun",18));
    }

    @Test
    public void main(){
        HashMap<Integer, User> map = sort(mInput);
        System.out.println(map.toString());
    }

    /**
     * 要做出这道题必须对集合的体系结构非常的熟悉。HashMap 本身就是不可排序的，但是该道题偏偏让给
     * HashMap排序，那我们就得想在API 中有没有这样的Map 结构是有序的，LinkedHashMap，对的，就是他，他是
     * Map 结构，也是链表结构，有序的，更可喜的是他是HashMap 的子类，我们返回LinkedHashMap<Integer,User>
     * 即可，还符合面向接口（父类编程的思想）。
     * 但凡是对集合的操作，我们应该保持一个原则就是能用JDK中的API就有JDK中的API，比如排序算法我们不应
     * 该 去 用 冒 泡 或 者 选 择 ， 而 是 首 先 想 到 用 Collections 集 合 工 具 类 。
     */
    public HashMap<Integer, User> sort(HashMap<Integer, User> data){
        LinkedHashMap<Integer, User> map = new LinkedHashMap<>(data);
        Set<Map.Entry<Integer, User> > set =  map.entrySet();
        List<Map.Entry<Integer, User> > list = new ArrayList<>(set);

        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {
            @Override
            public int compare(Map.Entry<Integer, User> lhs, Map.Entry<Integer, User> rhs) {
                return rhs.getValue().age - lhs.getValue().age;
            }
        });

        map.clear();
        for (Map.Entry<Integer, User> entry : list){
            map.put(entry.getKey(), entry.getValue());
        }

        return map;
    }

    class User {
        public String name;
        public int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }


}
