package io.github.kpatel.dsalg.model.util;

import java.util.ArrayList;
import java.util.Random;

public class RandomUtil {
    public static ArrayList<Integer> nextList(int size){
        Random random = new Random();
        ArrayList<Integer> list = new ArrayList<>(size);
        for(int i = 0; i < size; i++){
            list.add(i, (int) (random.nextDouble()*10+1));
        }
        return list;
    }
}
