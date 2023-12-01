package com.wl;

import com.wl.tools.Print;

public class Main {
    public static void main(String[] args) throws Exception {

        int[][] test1 = {{2, 1}, {2, 3}};
        int[][] test2 = {{3, 4}, {6, 5}};

        Print.printArray(numberOfWays(test2));
    }


    public static int[] numberOfWays(int queries[][]) throws Exception {
        int[] result = new int[queries.length];
        for (int i = 0; i < result.length; i++){
            result[i] = waysOfOneGrid(queries[i]);
        }
        return result;
    }

    public static int waysOfOneGrid(int[] grid) throws Exception {
        if (grid.length != 2) {
            throw new Exception("Length is not 2!");
        }
        int sum = 0;
        //squares can be 1*1, 2*2, ... (squareLen * squareLen)
        int squareLen = Math.min(grid[0], grid[1]);
        int gridRow = grid[0];
        int gridColumn = grid[1];
        //System.out.println(squareLen);

        int columnSatisfyNum;
        int rowSatisfyNum;

        for (int i = 1; i <= squareLen; i++){
            columnSatisfyNum = gridColumn - i + 1;
            rowSatisfyNum = gridRow - i + 1;
            sum += columnSatisfyNum * rowSatisfyNum;
        }
        //System.out.println(sum);
        return sum;
    }
}