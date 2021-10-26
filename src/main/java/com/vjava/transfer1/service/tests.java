package com.vjava.transfer1.service;

import com.vjava.transfer1.model.Role;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class tests {


    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        list.add("ROLE_ADMIN");
        list.add("ROLE_USER");

        System.out.println(getRoleList(list));


    }

    public static String getRoleList(List<String> list) {
        StringBuilder sb = new StringBuilder();

        for (String r : list) {
            if (r.startsWith("ROLE_")) {
                sb.append(r.replaceFirst("ROLE_", " "));
                sb.append(",");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        //sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
