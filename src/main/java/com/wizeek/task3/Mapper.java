package com.wizeek.task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Artur_Araslanov on 6/9/2018.
 * <p>
 * You have two arrays, one is the numbers from 0 to n-1 sorted a = [0, 1, 2, 3, 4] and the other is the same array
 * shuffled in a random (not necessarily uniform) order b = [1, 3, 2, 4, 0].
 * Write a function that applies the same shuffling from 'a' -> 'b' to a third array:
 * <p>
 * E.g.
 * Input  ['cupcake’, 'donut', 'eclair', 'froyo', 'gingerbread']
 * Output ['donut', 'froyo', 'eclair', 'gingerbread', 'cupcake']
 * <p>
 * What if a is not sorted?
 * a =[2,3,0,4,1]
 * b =[1,3,2,4,0]
 */
public class Mapper {

//    List<String> map1(int[] mappingArray, List<String> sourceList) {
//        List<String> result = new ArrayList<>(sourceList.size());
//        for (int i = 0; i < mappingArray.length; i++) {
//            int j = mappingArray.indexOf(i);
//            result.add(j, sourceList.get(i));
//        }
//        return result;
//    }
//
//    List<String> map2(int[] sourceArray, int[] mappingArray, List<String> sourceList) {
//        List<String> result = new ArrayList<>(sourceList.size());
//        for (int i = 0; i < mappingArray.length; i++) {
//            int j = mappingArray.indexOf(i);
//            int k = sourceArray.indexOf(i);
//            result.add(j, sourceList.get(i));
//        }
//        return result;
//    }
//
//    Map<Integer, Integer> indexMap = new HashMap<>(sourceArray.length);
//for(
//    int i = 0;
//    i<sourceArray.length;i++)
//
//    {
//        indexMap.put(sourceArray[i], i);
//    }
//2-0
//        3-1
//        0-2
//        4-3
//        1-4
//
//    Map<Integer, Integer> finalMap = new HashMap<>(mappingArray.length);
//for(
//    int i = 0;
//    i<mappingArray.length;i++)
//
//    {
//        int oldIndex = indexMap.get(mappingArray[i]);
//        indexMap.put(oldIndex, i);
//    }
//4-0
//        1-1
//        0-2
//        3-3
//        2-4
//
//
//    class Mapper {
//        List<String> map3(int[] sourceArray, int[] mappingArray, List<String> sourceList) {
//            List<String> result = new ArrayList<>(sourceList.size());
//            for (Map.Entry entry : finalMap.entrySet()) {
//                int i = entry.getKey();
//                int j = entry.getValue();
//                result.add(j, sourceList.get(i));
//            }
//            return result;
//        }
//    }
//
//    a =[2,3,0,4,1]
//    b =[1,3,2,4,0]
//
//    class Mapper {
//        List<String> map4(int[] sourceArray, int[] mappingArray, List<String> sourceList) {
//            List<String> result = new ArrayList<>(sourceList.size());
//            for (int i = 0; i < mappingArray.length; i++) {
//                result.add(souceArray[i], indexMap.get(i));
//            }
//            return result;
//        }
//    }
//

}
