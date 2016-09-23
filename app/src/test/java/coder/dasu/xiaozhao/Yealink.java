package coder.dasu.xiaozhao;

import java.util.Collection;

/**
 * Created by sxq on 2016/9/22.
 */

public class Yealink {

    public Collection insertSect(Collection co1, Collection co2){
        co1.retainAll(co2);
        return co1;
    }

}
