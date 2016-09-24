package coder.dasu.xiaozhao;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sxq on 2016/9/23.
 */

public class Xiaomi {
    /**
     * 题目描述：
     现在有一棵合法的二叉树，树的节点都是用数字表示，现在给定这棵树上所有的父子关系，求这棵树的高度
     输入
     输入的第一行表示节点的个数n（1<=n<=1000，节点的编号为0到n-1）组成，
     下面是n-1行，每行有两个整数，第一个数表示父节点的编号，第二个数表示子节点的编号
     输出
     输出树的高度，为一个整数

     样例输入
     5
     0 1
     0 2
     1 3
     1 4
     样例输出
     3
     */

    static class Node{
        int index;
        int height;
        Node left;
        Node right;

        public Node(int index){
            this.index = index;
            this.height = 1;
        }
    }

    public int nodeNumber;
    public int[][] nodeInfo;

    @Before
    public void init(){
        nodeNumber = 5;
        nodeInfo = new int[][]{{0,1},{0,2},{1,3},{1,4}};
    }

    @Test
    public void test1(){
        List<Node> tree = new ArrayList<>();
        List<Integer> root = new ArrayList<>();
        for (int i=0;i<nodeNumber;i++){
            Node node = new Node(i);
            tree.add(node);
            root.add(i);
        }
        for (int i=0;i<nodeNumber-1;i++){
            Node node = tree.get(nodeInfo[i][0]);
            if (node.left == null){
                node.left = tree.get(nodeInfo[i][1]);
            } else if (node.right == null) {
                node.right = tree.get(nodeInfo[i][1]);
            }
            Integer integer = new Integer(nodeInfo[i][1]);
            if (root.contains(integer)){
                root.remove(integer);
            }
        }
        int rootIndex = root.get(0);
        backWorkTree(tree, rootIndex);
        Assert.assertEquals(3, tree.get(rootIndex).height);

    }

    public void backWorkTree(List<Node> tree, int root){
        Node node = tree.get(root);
        if (node.left != null) {
            backWorkTree(tree, node.left.index);
        }
        if (node.right != null) {
            backWorkTree(tree, node.right.index);
        }
        if (node.left != null){
            node.height = node.left.height + 1;
        }
        if (node.right != null){
            node.height = Math.max(node.height, node.right.height +1);
        }

    }


    /**
     * 题目描述：
     * 给定一个句子（只包含字母和空格）， 将句子中的单词位置反转，单词用空格分割, 单词之间只有一个空格，前后没有空格。
     * 比如：
     * （1） “hello xiao mi”-> “mi xiao hello”
     * 输入
     * 输入数据有多组，每组占一行，包含一个句子(句子长度小于1000个字符)
     * 输出
     * 对于每个测试示例，要求输出句子中单词反转后形成的句子
     * <p>
     * 样例输入
     * hello xiao mi
     * 样例输出
     * mi xiao hello
     * <p>
     * Hint
     */

    public String reveserStr(String str) {
        String[] strArry = str.split(" ");
        int size = strArry.length;
        StringBuffer sb = new StringBuffer();
        for (int i = size - 1; i >= 0; i--) {
            sb.append(strArry[i] + " ");

        }
        return sb.toString().trim();
    }

    @Test
    public void test2() {
        String actuals = reveserStr("hello xiao mi");
        Assert.assertEquals("mi xiao hello", actuals);
    }

    /**
     * 题目描述：
     * 继MIUI8推出手机分身功能之后，MIUI9计划推出一个电话号码分身的功能：首先将电话号码中的每个数字加上8取个位，然后使用对应的大写字母代替
     * （"ZERO", "ONE", "TWO", "THREE", "FOUR",
     * "FIVE", "SIX", "SEVEN", "EIGHT", "NINE"），
     * 然后随机打乱这些字母，所生成的字符串即为电话号码对应的分身。
     * 输入
     * 第一行是一个整数T（1<=T<=100)表示测试样例数；接下来T行，每行给定一个分身后的电话号码的分身（长度在3到10000之间）。
     * 输出
     * 输出T行，分别对应输入中每行字符串对应的分身前的最小电话号码（允许前导0）。
     * <p>
     * 样例输入
     * 4
     * EIGHT
     * ZEROTWOONE
     * OHWETENRTEO
     * OHEWTIEGTHENRTEO
     * 样例输出
     * 0
     * 234
     * 345
     * 0345
     */

    public String answer(String str) {
        List<String> sl = new ArrayList<String>();
        List<String> rl = new ArrayList<String>();

        for (int i = 0; i < str.length(); i++) {
            sl.add(String.valueOf(str.charAt(i)));
        }
        while (findZero(sl, rl)) ;
        while (findOne(sl, rl)) ;
        while (findTwo(sl, rl)) ;
        while (findThree(sl, rl)) ;
        while (findFour(sl, rl)) ;
        while (findFive(sl, rl)) ;
        while (findSix(sl, rl)) ;
        while (findSeven(sl, rl)) ;
        while (findEight(sl, rl)) ;
        while (findNine(sl, rl)) ;
        String[] s = new String[rl.size()];
        rl.toArray(s);
        Arrays.sort(s);
        StringBuffer sb = new StringBuffer();
        for (String ss : s) {
            sb.append(ss);
        }
        return sb.toString();
    }

    public boolean findZero(List<String> str, List<String> result) {
        if (str.contains("Z") &&
                str.contains("E") &&
                str.contains("R") && str.contains("O")) {
            str.remove("Z");
            str.remove("E");
            str.remove("R");
            str.remove("O");
            result.add("2");
            return true;
        }
        return false;
    }

    public boolean findOne(List<String> str, List<String> result) {
        if (str.contains("O") &&
                str.contains("N") &&
                str.contains("E")) {
            str.remove("O");
            str.remove("N");
            str.remove("E");
            result.add("3");
            return true;
        }
        return false;
    }

    public boolean findTwo(List<String> str, List<String> result) {
        if (str.contains("T") &&
                str.contains("W") &&
                str.contains("O")) {
            str.remove("T");
            str.remove("W");
            str.remove("O");
            result.add("4");
            return true;
        }
        return false;
    }

    public boolean findThree(List<String> str, List<String> result) {
        if (str.contains("T") &&
                str.contains("H") &&
                str.contains("R") && (str.indexOf("E") != str.lastIndexOf("E"))) {
            str.remove("T");
            str.remove("H");
            str.remove("R");
            str.remove("E");
            str.remove("E");
            result.add("5");
            return true;
        }
        return false;
    }

    public boolean findFour(List<String> str, List<String> result) {
        if (str.contains("F") &&
                str.contains("O") &&
                str.contains("U") && str.contains("R")) {
            str.remove("F");
            str.remove("O");
            str.remove("U");
            str.remove("R");
            result.add("6");
            return true;
        }
        return false;
    }

    public boolean findFive(List<String> str, List<String> result) {
        if (str.contains("F") &&
                str.contains("I") &&
                str.contains("V") && str.contains("E")) {
            str.remove("F");
            str.remove("I");
            str.remove("V");
            str.remove("E");
            result.add("7");
            return true;
        }
        return false;
    }

    public boolean findSix(List<String> str, List<String> result) {
        if (str.contains("S") &&
                str.contains("I") &&
                str.contains("X")) {
            str.remove("S");
            str.remove("I");
            str.remove("X");
            result.add("8");
            return true;
        }
        return false;
    }

    public boolean findSeven(List<String> str, List<String> result) {
        if (str.contains("S") &&
                str.contains("V") &&
                str.contains("N") && (str.indexOf("E") != str.lastIndexOf("E"))) {
            str.remove("S");
            str.remove("E");
            str.remove("V");
            str.remove("E");
            str.remove("N");
            result.add("9");
            return true;
        }
        return false;
    }

    public boolean findEight(List<String> str, List<String> result) {
        if (str.contains("E") &&
                str.contains("I") &&
                str.contains("G") && str.contains("H") && str.contains("T")) {
            str.remove("E");
            str.remove("I");
            str.remove("G");
            str.remove("H");
            str.remove("T");
            result.add("0");
            return true;
        }
        return false;
    }

    public boolean findNine(List<String> str, List<String> result) {
        if (str.contains("I") &&
                str.contains("E") &&
                (str.indexOf("N") != str.lastIndexOf("N"))) {
            str.remove("N");
            str.remove("I");
            str.remove("N");
            str.remove("E");
            result.add("1");
            return true;
        }
        return false;
    }


}
