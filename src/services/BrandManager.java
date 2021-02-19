package services;

import commons.FuncReadAndWrite;

import java.util.List;

public class BrandManager {
    public static List<String[]> findAllBrand() {
        List<String[]> list = FuncReadAndWrite.readFile("hangSanXuat.csv");
        return list;
    }

    public static void showAllBrand() {
        List<String[]> list = FuncReadAndWrite.readFile("hangSanXuat.csv");
        int i = 1;
        for (String[] line : list) {
            System.out.println(i + ". Brand{" +
                    "brandId='" + line[0] + "'," +
                    "brandName='" + line[1] + "'," +
                    "country='" + line[2] + "'}");
            i++;
        }
    }
}
