package com.ajain11337.base.mutability;

public class MutableStringBuilders {
    void testing(){
        String s = "a";

        s += "b"; // this will create a new string object

        StringBuilder sb = new StringBuilder();
        sb.append("a");
        sb.append("b"); // appends to existing StringBuilder
    }
}
